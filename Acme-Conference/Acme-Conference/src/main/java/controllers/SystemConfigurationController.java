package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.SystemConfigurationService;
import domain.Actor;
import domain.SystemConfiguration;

@Controller
@RequestMapping("/sysconfig/administrator")
public class SystemConfigurationController extends AbstractController {

	// Services

	@Autowired
	private SystemConfigurationService systemConfigurationService;

	@Autowired
	private ActorService actorService;

	// Constructor

	public SystemConfigurationController() {
		super();
	}

	// Actions

	/* Display */

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView res;
		Actor principal;
		SystemConfiguration sysConfig;
		Map<String, String> welcomeMessage;
		Map<String,String> topics;
		Boolean err;

		try {
			principal = this.actorService.findByPrincipal();
			Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"));

			sysConfig = this.systemConfigurationService
					.findMySystemConfiguration();

			welcomeMessage = sysConfig.getWelcomeMessage();
			topics=sysConfig.getTopics();

			res = new ModelAndView("sysConfig/display");
			res.addObject("sysConfig", sysConfig);
			res.addObject("welcomeMessage", welcomeMessage);
			res.addObject("topics",topics);


		} catch (final Throwable oopsie) {
			res = new ModelAndView("sysConfig/display");
			err = true;

			res.addObject("errMsg", oopsie);
			res.addObject("err", err);
		}
		return res;
	}

	// Editing sysConfig

	@RequestMapping(value = "/edit", method = RequestMethod.GET, params = "systemconfigurationID")
	public ModelAndView edit(@RequestParam final int systemconfigurationID) {
		ModelAndView res;
		SystemConfiguration systemConfiguration;

		systemConfiguration = this.systemConfigurationService
				.findOne(systemconfigurationID);
		Assert.notNull(systemConfiguration);
		res = this.createEditModelAndView(systemConfiguration);

		return res;
	}

	protected ModelAndView createEditModelAndView(
			final SystemConfiguration systemConfiguration) {
		ModelAndView res;

		res = this.createEditModelAndView(systemConfiguration, null);

		return res;
	}

	protected ModelAndView createEditModelAndView(
			final SystemConfiguration systemConfiguration,
			final String messageCode) {
		ModelAndView res;

		res = new ModelAndView("sysConfig/edit");
		res.addObject("sysConfig", systemConfiguration);
		res.addObject("message", messageCode);

		return res;
	}

	// Save

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(SystemConfiguration systemConfiguration,
			@RequestParam("nameES") final String nameES,
			@RequestParam("nameEN") final String nameEN,
			@RequestParam("topicsES") final String topicsES,
			@RequestParam("topicsEN") final String topicsEN,
			 BindingResult binding) {
		ModelAndView res;
		Collection<String> errMessages = new ArrayList<>();
		SystemConfiguration sysConfig;
		Map<SystemConfiguration, Collection<String>> wA = new HashMap<>();

		wA = this.systemConfigurationService.reconstructWA(systemConfiguration,
				nameES, nameEN,topicsES,topicsEN, binding);

		sysConfig = wA.keySet().iterator().next();
		errMessages = wA.get(sysConfig);

		if (binding.hasErrors()) {
			res = new ModelAndView("sysConfig/edit");

			res.addObject("sysConfig",
					this.systemConfigurationService.findMySystemConfiguration());
			res.addObject("errMessages",errMessages);
			//res = this.createEditModelAndView(sysConfig,
				//	binding.getAllErrors().get(binding.getAllErrors().size()-1).getCode());
		} else
			try {

				this.systemConfigurationService.save(sysConfig);

				res = new ModelAndView("redirect:display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(sysConfig,
						"system.error");
			}
		return res;
	}



}