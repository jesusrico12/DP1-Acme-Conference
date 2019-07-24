package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

import domain.CreditCard;


@Transactional
@Service
public class AdministratorService { 

	/* Working repository */

	@Autowired
	private AdministratorRepository administratorRepository;



	@Autowired
	private SystemConfigurationService systemConfigurationService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	private UtilityService utilityService;

	/* Simple CRUD methods */

	/**
	 * Create an administrator
	 * 
	 * @return Administrator
	 */
	public Administrator create() {
		Administrator res;
		UserAccount userAccount;
		Authority auth;
		Collection<Authority> authority;
		CreditCard creditCard;

		creditCard = new CreditCard();
		auth = new Authority();
		authority = new ArrayList<Authority>();
		userAccount = new UserAccount();
		res = new Administrator();

		auth.setAuthority(Authority.ADMIN);
		authority.add(auth);
		userAccount.setAuthorities(authority);

		res.setUserAccount(userAccount);
		

		return res;
	}

	/**
	 * Find an administrator on the database
	 * 
	 * @param administratorId
	 * 
	 * @return Administrator
	 */
	public Administrator findOne(final Integer administratorId) {
		Administrator res;

		Assert.notNull(administratorId);
		res = this.administratorRepository.findOne(administratorId);

		return res;
	}

	/**
	 * Find all administrators
	 * 
	 * @return Collection<Administrator>
	 */
	public List<Administrator> findAll() {

		return this.administratorRepository.findAll();
	}

	/**
	 * Save an administrator
	 * 
	 * @param Administator
	 * 
	 * @return Administrator
	 */
	public Administrator save(final Administrator administrator) {
		Administrator res;
		Actor principal;

		Assert.notNull(administrator);

		principal = this.actorService.findByPrincipal();

		if (administrator.getId() == 0) {

			Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
					"no.permission");

			/* Managing phone number */
			final char[] phoneArray = administrator.getPhoneNumber()
					.toCharArray();
			if ((!administrator.getPhoneNumber().equals(null) && !administrator
					.getPhoneNumber().equals("")))
				if (phoneArray[0] != '+' && Character.isDigit(phoneArray[0])) {
					final String cc = this.systemConfigurationService
							.findMySystemConfiguration().getCountryCode();
					administrator.setPhoneNumber(cc + " "
							+ administrator.getPhoneNumber());
				}

			/* Managing email */
			/*
			 * String email = administrator.getEmail(); Assert.isTrue(
			 * this.actorService.checkEmail(email, administrator
			 * .getUserAccount().getAuthorities().iterator()
			 * .next().toString()), "actor.email.error");
			 * 
			 * /* Managing photo
			 */
			/*
			 * Assert.isTrue(ResourceUtils.isUrl(administrator.getPhoto()),
			 * "actor.photo.error");
			 */
		} else {

			Assert.isTrue(principal.getId() == administrator.getId(),
					"no.permission");

			administrator.setUserAccount(principal.getUserAccount());

			/* Managing phone number */
			final char[] phoneArray = administrator.getPhoneNumber()
					.toCharArray();
			if ((!administrator.getPhoneNumber().equals(null) && !administrator
					.getPhoneNumber().equals("")))
				if (phoneArray[0] != '+' && Character.isDigit(phoneArray[0])) {
					final String cc = this.systemConfigurationService
							.findMySystemConfiguration().getCountryCode();
					administrator.setPhoneNumber(cc + " "
							+ administrator.getPhoneNumber());
				}

			/* Managing email */
			/*
			 * String email = administrator.getEmail(); Assert.isTrue(
			 * this.actorService.checkEmail(email, administrator
			 * .getUserAccount().getAuthorities().iterator()
			 * .next().toString()), "actor.email.error");
			 * 
			 * /* Managing photo
			 */
			/*
			 * Assert.isTrue(ResourceUtils.isUrl(administrator.getPhoto()),
			 * "actor.photo.error");
			 */
		}

		res = this.administratorRepository.save(administrator);
		return res;

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

	public Long count() {
		final Long res = this.administratorRepository.count();
		return res;
	}

	public void flush() {
		this.administratorRepository.flush();
	}


}
