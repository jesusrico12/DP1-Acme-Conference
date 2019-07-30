package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SystemConfigurationRepository;
import domain.Actor;
import domain.SystemConfiguration;

@Transactional
@Service
public class SystemConfigurationService {

	/* Working repository */

	@Autowired
	private SystemConfigurationRepository systemConfigurationRepository;

	@Autowired
	private ActorService actorService;

	@Autowired
	private UtilityService utilityService;

	@Autowired
	private Validator validator;

	/* Simple CRUD methods */

	/* Saving a system configuration */
	public SystemConfiguration save(
			final SystemConfiguration systemConfiguration) {
		Assert.notNull(systemConfiguration, "null.system.configuration");
		Actor principal;
		SystemConfiguration result;
		SystemConfiguration aux = this.systemConfigurationRepository
				.findSystemConf();

		principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");

		aux.setSystemName(systemConfiguration.getSystemName());
		aux.setWelcomeMessage(systemConfiguration.getWelcomeMessage());
		aux.setBanner(systemConfiguration.getBanner());
		aux.setCountryCode(systemConfiguration.getCountryCode());



		result = this.systemConfigurationRepository.save(aux);

		return result;
	}

	/* Other methods */

	/* Find system configuration */
	public SystemConfiguration findMySystemConfiguration() {
		final SystemConfiguration result;

		result = this.systemConfigurationRepository.findSystemConf();

		return result;
	}

	/* Find banner */
	public String findMyBanner() {

		String result;

		result = this.findMySystemConfiguration().getBanner();

		return result;
	}

	/* Find welcome message */
	public Map<String, String> findWelcomeMessage() {
		final Map<String, String> result;

		result = this.findMySystemConfiguration().getWelcomeMessage();

		return result;
	}


	public SystemConfiguration reconstruct(
			final SystemConfiguration systemConfiguration, final String nameES,
			final String nameEN, final String nEs, final String nEn,
			BindingResult binding) {

		Assert.isTrue(this.actorService.checkAuthority(
				this.actorService.findByPrincipal(), "ADMIN"));

		SystemConfiguration res = new SystemConfiguration();

		res.setId(systemConfiguration.getId());
		res.setVersion(systemConfiguration.getVersion());
		res.setWelcomeMessage(systemConfiguration.getWelcomeMessage());

		res.setSystemName(systemConfiguration.getSystemName());
		res.setBanner(systemConfiguration.getBanner());
		res.setCountryCode(systemConfiguration.getCountryCode());


		res.setWelcomeMessage(new HashMap<String, String>());

		res.getWelcomeMessage().put("Español", nameES);
		res.getWelcomeMessage().put("English", nameEN);


		try {
			Assert.isTrue(!res.getSystemName().isEmpty(), "name.error");
		} catch (Throwable oops) {
			binding.rejectValue("systemName", "name.error");
		}

		try {
			Assert.isTrue(!res.getBanner().isEmpty(), "banner.error");
		} catch (Throwable oops) {
			binding.rejectValue("banner", "banner.error");
		}

		try {
			Assert.isTrue(this.utilityService.checkCC(res.getCountryCode()),
					"cc.error");
		} catch (Throwable oops) {
			binding.rejectValue("countryCode", "cc.error");
		}

	




		this.validator.validate(res, binding);

		return res;
	}

	public Map<SystemConfiguration, Collection<String>> reconstructWA(
			final SystemConfiguration systemConfiguration, final String nameES,
			final String nameEN, final String nEs, final String nEn,
			BindingResult binding) {

		Assert.isTrue(this.actorService.checkAuthority(
				this.actorService.findByPrincipal(), "ADMIN"));

		Map<SystemConfiguration, Collection<String>> wA = new HashMap<>();
		Collection<String> errMessages = new ArrayList<>();

		SystemConfiguration res = new SystemConfiguration();

		res.setId(systemConfiguration.getId());
		res.setVersion(systemConfiguration.getVersion());
		res.setWelcomeMessage(systemConfiguration.getWelcomeMessage());

		res.setSystemName(systemConfiguration.getSystemName());
		res.setBanner(systemConfiguration.getBanner());
		res.setCountryCode(systemConfiguration.getCountryCode());


		res.setWelcomeMessage(new HashMap<String, String>());

		res.getWelcomeMessage().put("Español", nameES);
		res.getWelcomeMessage().put("English", nameEN);

		try {
			Assert.isTrue(!res.getSystemName().isEmpty(), "name.error");
		} catch (Throwable oops) {
			binding.rejectValue("systemName", "name.error");
			errMessages.add("name.error");
		}

		try {
			Assert.isTrue(!res.getBanner().isEmpty(), "banner.error");
		} catch (Throwable oops) {
			binding.rejectValue("banner", "banner.error");
			errMessages.add("banner.error");
		}

		try {
			Assert.isTrue(this.utilityService.checkCC(res.getCountryCode()),
					"cc.error");
		} catch (Throwable oops) {
			binding.rejectValue("countryCode", "cc.error");
			errMessages.add("cc.error");
		}

	

		try {
			Assert.notNull(nEn);
			Assert.notNull(nEs);
		} catch (Throwable oops) {
			errMessages.add("welcome.error");
		}

		wA.put(systemConfiguration, errMessages);
		this.validator.validate(res, binding);

		return wA;
	}

	/* Find one system configuration */
	public SystemConfiguration findOne(final int systemConfigurationId) {
		SystemConfiguration res;

		res = this.systemConfigurationRepository.findOne(systemConfigurationId);

		return res;

	}


}
