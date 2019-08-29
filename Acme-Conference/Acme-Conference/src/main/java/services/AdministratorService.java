package services;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;


import java.util.regex.Matcher;
import java.util.regex.Pattern;



import org.springframework.util.StringUtils;

import org.springframework.validation.Validator;


import security.LoginService;

import security.UserAccountRepository;


import forms.AdministratorForm;


@Transactional
@Service
public class AdministratorService { 

	/* Working repository */

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private UserAccountRepository	userAccountRepository;

	@Autowired
	private SystemConfigurationService systemConfigurationService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private Validator				validator;





	/* Simple CRUD methods */


	public Administrator save(Administrator administrator) {
		Administrator result, saved;
		
		final UserAccount logedUserAccount;
		Authority authority;
		Md5PasswordEncoder encoder;

		encoder = new Md5PasswordEncoder();
		authority = new Authority();
		
		authority.setAuthority("ADMIN");
		Assert.notNull(administrator, "administrator.not.null");
		
		Pattern pattern0 = Pattern.compile("^([A-z0-9 ]+[ ]<[A-z0-9]+@(([A-z0-9]+\\.)+[A-z0-9]+){0,}>|[A-z0-9]+@(([A-z0-9]+\\.)+[A-z0-9]+){0,})$");
		Assert.isTrue(pattern0.matcher(administrator.getEmail()).find());
		
		if (this.exists(administrator.getId())) {
			logedUserAccount = LoginService.getPrincipal();
			
			Assert.notNull(logedUserAccount, "administrator.notLogged ");
			int string1 = logedUserAccount.getId();
			int string2 = administrator.getUserAccount().getId();
			
			Assert.isTrue(string1 == string2, "administrator.notEqual.userAccount");

			saved = this.administratorRepository.findOne(administrator.getId());
			
			Assert.notNull(saved, "administrator.not.null");
			
			Assert.isTrue(saved.getUserAccount().getUsername().equals(administrator.getUserAccount().getUsername()), "administrator.notEqual.username");
		} else {
			administrator.getUserAccount().setPassword(encoder.encodePassword(administrator.getUserAccount().getPassword(), null));

		}

		result = this.administratorRepository.save(administrator);

		return result;
	}

	public Administrator create() {
		Administrator result;
		
		UserAccount userAccount;
		Authority authority;

		result = new Administrator();
		
		userAccount = new UserAccount();
		authority = new Authority();



		authority.setAuthority("ADMIN");
		userAccount.addAuthority(authority);



		result.setUserAccount(userAccount);




		return result;

	}

	public Administrator findByPrincipal() {
		Administrator result;
		
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		
		result = this.findByUserAccount(userAccount);
		Assert.notNull(result);

		return result;
	}

	public Administrator findByUserAccount(UserAccount userAccount) {
		Assert.notNull(userAccount);
		Administrator result;
		
		result = this.administratorRepository.findByUserAccountId(userAccount.getId());
		
		return result;
	}

	public AdministratorForm construct(Administrator admin) {
		
		AdministratorForm result = new AdministratorForm();
		result.setName(admin.getName());
		
		result.setSurname(admin.getSurname());
		
		result.setMiddleName(admin.getMiddleName());
		result.setPhoto(admin.getPhoto());
		result.setPhoneNumber(admin.getPhoneNumber());
		result.setAddress(admin.getAddress());

		result.setEmail(admin.getEmail());

		result.setUsername(admin.getUserAccount().getUsername());
		result.setId(admin.getId());



		return result;
	}

	public Administrator reconstruct(AdministratorForm form, BindingResult binding) {
		Administrator result;

		if (form.getId() == 0)
			result = this.create();
		else
			result = (Administrator) this.actorService.findByActorId(form.getId());

		result.setName(form.getName());
		result.setSurname(form.getSurname());
		
		result.setMiddleName(form.getMiddleName());
		result.setPhoto(form.getPhoto());
		
		result.setEmail(form.getEmail());




		if (!StringUtils.isEmpty(form.getPhoneNumber())) {

			Pattern pattern = Pattern.compile("^\\d{4,}$", Pattern.CASE_INSENSITIVE);
			
			Matcher matcher = pattern.matcher(form.getPhoneNumber());

			if (matcher.matches())
				
				form.setPhoneNumber(this.systemConfigurationService.findMySystemConfiguration().getCountryCode() + form.getPhoneNumber());
		}


		result.setPhoneNumber(form.getPhoneNumber());
		result.setAddress(form.getAddress());
		
		String s1 = form.getUsername();
		
		String s2 = result.getUserAccount().getUsername();

		if (!s1.equals(s2) || s2 == null)
			
			if (!this.userAccountRepository.findUserAccountsByUsername(form.getUsername()).isEmpty())
				binding.rejectValue("username", "administrator.validator.username", "This username already exists");
		
	
		result.getUserAccount().setUsername(form.getUsername());
		
		result.getUserAccount().setPassword(form.getNewPassword());

		if (form.getNewPassword().trim().length() < 5)
			binding.rejectValue("newPassword", "administrator.validator.password", "Size must be between 5 and 32 characters");

		if (form.getConfirmPassword().trim().length() < 5)
			binding.rejectValue("confirmPassword", "administrator.validator.password", "Size must be between 5 and 32 characters");

		this.validator.validate(result, binding);

		return result;

	}

	public Administrator verifyAndSave(AdministratorForm form, BindingResult binding) {
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

		Administrator temp = this.reconstruct(form, binding);
		
		if (!passwordConfirm.equals("") && form.getId() > 0)
			
			temp.getUserAccount().setPassword(encoder.encodePassword(temp.getUserAccount().getPassword(), null));

		Administrator result = this.save(temp);
		return result;
	}


	/**
	 * Delete an administrator
	 * 
	 * @param Administator
	 */
	public void delete(final Administrator administrator) {
		Actor principal;

		Assert.notNull(administrator);
		

		principal = this.actorService.findByPrincipal();
		

		Assert.isTrue(principal.getId() == administrator.getId(),
				"no.permission");

		this.administratorRepository.delete(administrator.getId());
	}


	public Administrator findByUsername(final String username) {
		return this.administratorRepository.findByUsername(username);
	}
	public boolean exists(Integer id) {
		return this.administratorRepository.exists(id);
	}


	public void flush() {
		this.administratorRepository.flush();
	}
	
	public void autoAssignReviewers(){
		
	}

}
