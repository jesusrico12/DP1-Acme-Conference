package services;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;


import domain.Author;

import forms.AuthorForm;

import repositories.AuthorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import security.UserAccountRepository;

@Service
@Transactional
public class AuthorService {

	@Autowired
	private AuthorRepository	authorRepository;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private SystemConfigurationService configurationService;

	@Autowired
	private Validator				validator;




	public Author save(Author author) {
		Author result, saved;
		final UserAccount logedUserAccount;
		Authority authority;
		Md5PasswordEncoder encoder;
		
	/*	if(author.getId() == 0) {
			UserAccount adminUserAccount = LoginService.getPrincipal();
			Assert.isTrue(adminUserAccount.getAuthorities().iterator().next().getAuthority().equals("AUTHOR"));
		}*/

		encoder = new Md5PasswordEncoder();
		authority = new Authority();
		authority.setAuthority("AUTHOR");
		Assert.notNull(author, "author.not.null");
		Pattern pattern0 = Pattern.compile("^([A-z0-9 ]+[ ]<[A-z0-9]+@(([A-z0-9]+\\.)+[A-z0-9]+){0,}>|[A-z0-9]+@(([A-z0-9]+\\.)+[A-z0-9]+){0,})$");
		Assert.isTrue(pattern0.matcher(author.getEmail()).find());

		if (this.exists(author.getId())) {
			logedUserAccount = LoginService.getPrincipal();
			Assert.notNull(logedUserAccount, "author.notLogged ");
			int string1 = logedUserAccount.getId();
			int string2 = author.getUserAccount().getId();
			Assert.isTrue(string1 == string2, "author.notEqual.userAccount");

			saved = this.authorRepository.findOne(author.getId());
			Assert.notNull(saved, "author.not.null");
			Assert.isTrue(saved.getUserAccount().getUsername().equals(author.getUserAccount().getUsername()), "author.notEqual.username");
		} else {
			author.getUserAccount().setPassword(encoder.encodePassword(author.getUserAccount().getPassword(), null));
		
		}

		result = this.authorRepository.save(author);

		return result;
	}

	public Author create() {
		Author result;
		UserAccount userAccount;
		Authority authority;

		result = new Author();
		userAccount = new UserAccount();
		authority = new Authority();

		

		authority.setAuthority("AUTHOR");
		userAccount.addAuthority(authority);



		result.setUserAccount(userAccount);



		return result;

	}

	public Author findByPrincipal() {
		Author result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Author findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Author result;
		result = this.authorRepository.findByUserAccountId(userAccount.getId());
		return result;
	}

	public AuthorForm construct(Author author) {
		AuthorForm result = new AuthorForm();
		result.setName(author.getName());
		result.setSurname(author.getSurname());
		result.setMiddleName(author.getMiddleName());
		result.setPhoto(author.getPhoto());
		
		result.setPhoneNumber(author.getPhoneNumber());
		result.setAddress(author.getAddress());
	
		result.setEmail(author.getEmail());

		result.setUsername(author.getUserAccount().getUsername());
		result.setId(author.getId());

	

		return result;
	}

	public Author reconstruct(AuthorForm form, BindingResult binding) {
		Author result;

		if (form.getId() == 0)
			result = this.create();
		else
			result = (Author) this.actorService.findByActorId(form.getId());

		result.setName(form.getName());
		result.setSurname(form.getSurname());
		result.setPhoto(form.getPhoto());
		result.setEmail(form.getEmail());
		result.setMiddleName(form.getMiddleName());



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
				binding.rejectValue("username", "author.validator.username", "This username already exists");
		result.getUserAccount().setUsername(form.getUsername());
		result.getUserAccount().setPassword(form.getNewPassword());

		if (form.getNewPassword().trim().length() < 5)
			binding.rejectValue("newPassword", "author.validator.password", "Size must be between 5 and 32 characters");

		if (form.getConfirmPassword().trim().length() < 5)
			binding.rejectValue("confirmPassword", "author.validator.password", "Size must be between 5 and 32 characters");

		this.validator.validate(result, binding);

		return result;

	}

	public Author verifyAndSave(AuthorForm form, BindingResult binding) {
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

		Author temp = this.reconstruct(form, binding);
		if (!passwordConfirm.equals("") && form.getId() > 0)
			temp.getUserAccount().setPassword(encoder.encodePassword(temp.getUserAccount().getPassword(), null));

		Author result = this.save(temp);
		return result;
	}

	public List<Author> findAll() {
		return this.authorRepository.findAll();
	}

	public Author findOne(Integer id) {
		return this.authorRepository.findOne(id);
	}

	public boolean exists(Integer id) {
		return this.authorRepository.exists(id);
	}


	
	public void flush() {
		authorRepository.flush();
	}
	
}
