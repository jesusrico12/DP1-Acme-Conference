package services;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Reviewer;
import forms.ReviewerForm;

import repositories.ReviewerRepository;

import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;

@Transactional
@Service
public class ReviewerService {

	
	
	@Autowired
	private ReviewerRepository reviewerRepository;
	
	
	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private SystemConfigurationService configurationService;

	@Autowired
	private Validator				validator;




	public Reviewer save(Reviewer reviewer) {
		Reviewer result, saved;
		final UserAccount logedUserAccount;
		Authority authority;
		Md5PasswordEncoder encoder;
		
	/*	if(reviewer.getId() == 0) {
			UserAccount adminUserAccount = LoginService.getPrincipal();
			Assert.isTrue(adminUserAccount.getReviewerities().iterator().next().getAuthority().equals("REVIEWER"));
		}*/

		encoder = new Md5PasswordEncoder();
		authority = new Authority();
		authority.setAuthority("REVIEWER");
		Assert.notNull(reviewer, "reviewer.not.null");
		Pattern pattern0 = Pattern.compile("^([A-z0-9 ]+[ ]<[A-z0-9]+@(([A-z0-9]+\\.)+[A-z0-9]+){0,}>|[A-z0-9]+@(([A-z0-9]+\\.)+[A-z0-9]+){0,})$");
		Assert.isTrue(pattern0.matcher(reviewer.getEmail()).find());

		if (this.exists(reviewer.getId())) {
			logedUserAccount = LoginService.getPrincipal();
			Assert.notNull(logedUserAccount, "reviewer.notLogged ");
			int string1 = logedUserAccount.getId();
			int string2 = reviewer.getUserAccount().getId();
			Assert.isTrue(string1 == string2, "reviewer.notEqual.userAccount");

			saved = this.reviewerRepository.findOne(reviewer.getId());
			Assert.notNull(saved, "reviewer.not.null");
			Assert.isTrue(saved.getUserAccount().getUsername().equals(reviewer.getUserAccount().getUsername()), "reviewer.notEqual.username");
		} else {
			reviewer.getUserAccount().setPassword(encoder.encodePassword(reviewer.getUserAccount().getPassword(), null));
		
		}

		result = this.reviewerRepository.save(reviewer);

		return result;
	}

	public Reviewer create() {
		Reviewer result;
		UserAccount userAccount;
		Authority authority;

		result = new Reviewer();
		userAccount = new UserAccount();
		authority = new Authority();

		

		authority.setAuthority("REVIEWER");
		userAccount.addAuthority(authority);



		result.setUserAccount(userAccount);



		return result;

	}

	public Reviewer findByPrincipal() {
		Reviewer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Reviewer findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Reviewer result;
		result = this.reviewerRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public ReviewerForm construct(Reviewer reviewer) {
		ReviewerForm result = new ReviewerForm();
		result.setName(reviewer.getName());
		result.setSurname(reviewer.getSurname());
		result.setMiddleName(reviewer.getMiddleName());
		result.setPhoto(reviewer.getPhoto());
		result.setKeywords(reviewer.getKeywords());
		result.setPhoneNumber(reviewer.getPhoneNumber());
		result.setAddress(reviewer.getAddress());
	
		result.setEmail(reviewer.getEmail());

		result.setUsername(reviewer.getUserAccount().getUsername());
		result.setId(reviewer.getId());

	

		return result;
	}

	public Reviewer reconstruct(ReviewerForm form, BindingResult binding) {
		Reviewer result;

		if (form.getId() == 0)
			result = this.create();
		else
			result = (Reviewer) this.actorService.findByActorId(form.getId());

		result.setName(form.getName());
		result.setSurname(form.getSurname());
		result.setPhoto(form.getPhoto());
		result.setEmail(form.getEmail());
		result.setMiddleName(form.getMiddleName());
		result.setKeywords(form.getKeywords());



		if (!StringUtils.isEmpty(form.getPhoneNumber())) {

			Pattern pattern = Pattern.compile("^\\d{4,}$", Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(form.getPhoneNumber());

			if (matcher.matches())
				form.setPhoneNumber(this.configurationService.findMySystemConfiguration().getCountryCode() + form.getPhoneNumber());
		}

		result.setPhoneNumber(form.getPhoneNumber());
		result.setAddress(form.getAddress());
		String s1 = form.getUsername();
		String s2 = result.getUserAccount().getUsername();

		if (!s1.equals(s2) || s2 == null)
			if (!this.userAccountRepository.findUserAccountsByUsername(form.getUsername()).isEmpty())
				binding.rejectValue("username", "reviewer.validator.username", "This username already exists");
		result.getUserAccount().setUsername(form.getUsername());
		result.getUserAccount().setPassword(form.getNewPassword());

		if (form.getNewPassword().trim().length() < 5)
			binding.rejectValue("newPassword", "reviewer.validator.password", "Size must be between 5 and 32 characters");

		if (form.getConfirmPassword().trim().length() < 5)
			binding.rejectValue("confirmPassword", "reviewer.validator.password", "Size must be between 5 and 32 characters");

		this.validator.validate(result, binding);

		return result;

	}

	public Reviewer verifyAndSave(ReviewerForm form, BindingResult binding) {
		Md5PasswordEncoder encoder = new Md5PasswordEncoder();

		String passwordConfirm = form.getNewPassword();
		if (form.getId() != 0) {
			UserAccount ua = this.actorService.findByPrincipal().getUserAccount();
			if (form.getNewPassword() == "" && form.getOldPassword() == "" && form.getConfirmPassword() == "") {
				form.setOldPassword(ua.getPassword());
				form.setNewPassword(ua.getPassword());
				form.setConfirmPassword(ua.getPassword());
				Assert.isTrue(form.getOldPassword().equals(ua.getPassword()), "error.password.incorrectOld");
			} else
				Assert.isTrue(encoder.encodePassword(form.getOldPassword(), null).equals(ua.getPassword()), "error.password.incorrectOld");
		}

		String s1 = form.getConfirmPassword();
		String s2 = form.getNewPassword();
		Assert.isTrue(s1.equals(s2), "error.password.notMatching");

		Reviewer temp = this.reconstruct(form, binding);
		if (!passwordConfirm.equals("") && form.getId() > 0)
			temp.getUserAccount().setPassword(encoder.encodePassword(temp.getUserAccount().getPassword(), null));

		Reviewer result = this.save(temp);
		return result;
	}

	public List<Reviewer> findAll() {
		return this.reviewerRepository.findAll();
	}

	public Reviewer findOne(Integer id) {
		return this.reviewerRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		return this.reviewerRepository.exists(id);
	}


	
	public void flush() {
		reviewerRepository.flush();
	}
	
}
