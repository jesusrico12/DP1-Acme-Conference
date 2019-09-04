package controllers;

import java.util.ArrayList;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Actor;
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
		try{
		Registration registration=this.registrationService.findOne(registrationId);
		Actor principal=this.actorService.findByPrincipal();
		Assert.isTrue(registration.getAuthor()==principal);
			
		result = new ModelAndView("registration/display");
		result.addObject("registration",registration );
		}catch(Throwable opps ){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
		return result;
	}
	

	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;

		
		result = new ModelAndView("registration/list");
		
		
		List<Conference> conferencesNotStarted= new ArrayList<Conference>();
		for(Conference c:this.conferenceService.conferencesNotStarted()){
			if(!this.registrationService.conferencesInRegistration((this.actorService.findByPrincipal().getId())).contains(c)){
				conferencesNotStarted.add(c);
			}
			
		}
		
		result.addObject("conferencesNotStarted",conferencesNotStarted);
		result.addObject("registrationsPerAuthor",this.registrationService.registrationPerAuthor(this.actorService.findByPrincipal().getId()));
		
		return result;
	}
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId){
		ModelAndView result;
		try{
		Conference conference=this.conferenceService.findOne(conferenceId);
		
		
			Assert.isTrue(!this.registrationService.conferencesInRegistration((this.actorService.findByPrincipal().getId()))
					.contains(conference)||this.conferenceService.conferencesNotStarted().contains(conference));
				
		
		
		Registration registration = this.registrationService.create(conference);
		result = this.createEditModelAndView(registration,conference);
		
		result.addObject("conferenceId", this.conferenceService.findOne(conferenceId).getId());
		result.addObject("registration", registration);
		result.addObject("conference",conference);
		}catch(Throwable opps ){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
		return result;


	}
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int registrationId) {
		ModelAndView result;
		try{
		Registration registration;
		registration = this.registrationService.findOne(registrationId);
		Assert.notNull(registration);
		Actor principal=this.actorService.findByPrincipal();
		Assert.isTrue(registration.getAuthor()==principal);
		result = this.createEditModelAndView(registration,registration.getConference());
		result.addObject("registration",registration);
		}catch(Throwable opps ){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
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
