package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Conference;

import domain.Registration;

import services.ActorService;
import services.ConferenceService;
import services.RegistrationService;
import services.SystemConfigurationService;

@Controller
@RequestMapping(value = "/registration")
public class RegistrationController  extends AbstractController {

	
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private ConferenceService conferenceService ;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private SystemConfigurationService configurationService;
	
	//create
		
	//edit
	
	//save
	
	//list
	
	//Display
	
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int registrationId) {
		ModelAndView result;
		Registration registration=this.registrationService.findOne(registrationId);
		result = new ModelAndView("registration/display");
		result.addObject("registration",registration );

		return result;
	}
	

	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;

		
		result = new ModelAndView("registration/list");


		result.addObject("conferencesNotStarted",this.conferenceService.conferencesNotStarted());
		result.addObject("registrationsPerAuthor",this.registrationService.registrationPerAuthor(this.actorService.findByPrincipal().getId()));
		
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId){
		ModelAndView result;
		
		Conference conference=this.conferenceService.findOne(conferenceId);
		
		Registration registration = this.registrationService.create(conference);
		result = this.createEditModelAndView(registration,conference);
		
		result.addObject("conferenceId", this.conferenceService.findOne(conferenceId).getId());
		result.addObject("registration", registration);
		result.addObject("conference",conference);
		return result;


	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int registrationId) {
		ModelAndView result;
		Registration registration;
		registration = this.registrationService.findOne(registrationId);
		Assert.notNull(registration);
		result = this.createEditModelAndView(registration,registration.getConference());
		result.addObject("registration",registration);

		return result;
	}

	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Registration registration, final BindingResult binding) {
		ModelAndView result;
			
		
		if(binding.hasErrors()){
			result = this.createEditModelAndView(registration,registration.getConference(), null);
		}else{
		try {
			
	
			this.registrationService.save(registration,registration.getConference());
			
			result = new ModelAndView("redirect:/registration/list.do");

		}catch (Throwable oops) {
			if(oops.getMessage()==null){
				
				result = new ModelAndView("redirect:/welcome/index.do");
			
		}else{
		result = this.createEditModelAndView(registration,registration.getConference(),  oops.getMessage());
		}
		}
		}
	
	
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Registration registration, Conference conference) {
		ModelAndView result;

		result = this.createEditModelAndView(registration,conference, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Registration registration,Conference conference,
			String messageCode) {
		ModelAndView result;
		
	
		result = new ModelAndView("registration/edit");

		
		
		result.addObject("registration", registration);
		result.addObject("conference", conference);
		result.addObject("message", messageCode);
		result.addObject("makes",this.configurationService.makesSystem());

		return result;
	}

	
	
}
