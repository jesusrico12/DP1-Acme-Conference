package controllers;

import java.util.Date;

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
import domain.Tutorial;




import services.ActorService;
import services.ConferenceService;
import services.TutorialService;

@Controller
public class TutorialController extends AbstractController {
	
	
	@Autowired
	private TutorialService tutorialService;
	@Autowired
	private ConferenceService conferenceService;
	
	@Autowired
	private ActorService actorService;
	
	//DISPLAY
	
	@RequestMapping(value = "tutorial/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int tutorialId) {
		ModelAndView result;
		boolean permission=false;
		
		Tutorial tutorial=this.tutorialService.findOne(tutorialId);
		try{
			Actor principal=this.actorService.findByPrincipal();
			if(this.conferenceService.ConferenceOwn(tutorial.getId()).getAdministrator()==principal){
				permission=true;
			
			}
			}catch(Throwable oops){
				
			}
		
		result = new ModelAndView("tutorial/display");
		result.addObject("tutorial",tutorial );
		result.addObject("sections",tutorial.getSections());
		result.addObject("conference",this.conferenceService.ConferenceOwn(tutorialId));
		result.addObject("permission",permission);
		return result;
	}
	//Create

	@RequestMapping(value = "tutorial/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId){
		ModelAndView result;
		try{
			//SEGURIDAD
		Actor principal=this.actorService.findByPrincipal();
		Assert.isTrue(principal==this.conferenceService.findOne(conferenceId).getAdministrator());
		Tutorial tutorial = this.tutorialService.create();
		Conference c=this.conferenceService.findOne(conferenceId);
		Date currentMoment = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(c.getEndDate().after(currentMoment)&& c.getIsDraft()==false);

		result = this.createEditModelAndView(tutorial,c);
		
		result.addObject("conferenceId", this.conferenceService.findOne(conferenceId));
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
		
		return result;


	}
	
	@RequestMapping(value = "tutorial/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tutorialId) {
		ModelAndView result;
		try{
			//SEGURIDAD
		Tutorial tutorial;
		Conference c;
		Actor principal=this.actorService.findByPrincipal();
		tutorial = this.tutorialService.findOne(tutorialId);
		Assert.notNull(tutorial);
		Assert.isTrue(principal==this.conferenceService.ConferenceOwn(tutorialId).getAdministrator());
		c=this.conferenceService.ConferenceOwn(tutorialId);
		result = this.createEditModelAndView(tutorial,c);
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}
	@RequestMapping(value = "tutorial/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int conferenceId,@Valid Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		Conference c;
		if(tutorial.getId()!=0){
		 c=this.conferenceService.ConferenceOwn(tutorial.getId());
		}else {
			 c = this.conferenceService.findOne(conferenceId);
		}
		if(binding.hasErrors()){
			result = this.createEditModelAndView(tutorial,c, null);
		}else{
		try {
	
			this.tutorialService.save(tutorial,c);
			result = new ModelAndView("redirect:/tutorial/display.do?tutorialId="+tutorial.getId());
			if(tutorial.getId()!=0){
				result = new ModelAndView("redirect:/tutorial/display.do?tutorialId="+tutorial.getId());
			}
			else{
				result = new ModelAndView("redirect:/conference/display.do?conferenceId="+c.getId());
			}
			
			

		} catch (Throwable oops) {
			if(oops.getMessage()==null){
				
				result = new ModelAndView("redirect:/welcome/index.do");
			
		}else{
		result = this.createEditModelAndView(tutorial,c,  oops.getMessage());
		}
	}
	}
		return result;
	}
	
	@RequestMapping(value = "tutorial/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int conferenceId,final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		Conference c;


		try {
			c = this.conferenceService.findOne(conferenceId);
			Assert.isTrue(c!=null,"commit.error");
			this.tutorialService.delete(tutorial, c);
			result = new ModelAndView("redirect:/conference/display.do?conferenceId="+c.getId());
		} catch (final Throwable oops) {
			c = this.conferenceService.ConferenceOwn(tutorial.getId());
			result = this.createEditModelAndView(tutorial, c,  oops.getMessage());
		}
		return result;
	}
	protected ModelAndView createEditModelAndView(Tutorial tutorial, Conference c) {
		ModelAndView result;

		result = this.createEditModelAndView(tutorial,c, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Tutorial tutorial,Conference c,
			String messageCode) {
		ModelAndView result;
		
	
		result = new ModelAndView("tutorial/edit");


		result.addObject("tutorial", tutorial);
		result.addObject("conference", c);
		result.addObject("message", messageCode);
		result.addObject("sections",tutorial.getSections()); 

		return result;
	}
	
	
	
}
