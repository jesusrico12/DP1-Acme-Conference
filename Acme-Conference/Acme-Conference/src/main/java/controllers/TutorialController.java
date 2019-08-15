package controllers;

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
import domain.Tutorial;
import domain.Tutorial;
import domain.Tutorial;



import services.ConferenceService;
import services.TutorialService;

@Controller
public class TutorialController extends AbstractController {
	
	
	@Autowired
	private TutorialService tutorialService;
	@Autowired
	private ConferenceService conferenceService;
	
	
	//DISPLAY
	
	@RequestMapping(value = "tutorial/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int tutorialId) {
		ModelAndView result;
		Tutorial tutorial=this.tutorialService.findOne(tutorialId);
		result = new ModelAndView("tutorial/display");
		result.addObject("tutorial",tutorial );
		result.addObject("sections",tutorial.getSections());

		return result;
	}
	//Create

	@RequestMapping(value = "tutorial/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId){
		ModelAndView result;
		Tutorial tutorial = this.tutorialService.create();
		Conference c=this.conferenceService.findOne(conferenceId);
		

		result = this.createEditModelAndView(tutorial,c);
		
		result.addObject("conferenceId", this.conferenceService.findOne(conferenceId));
		
		return result;


	}
	
	@RequestMapping(value = "tutorial/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tutorialId) {
		ModelAndView result;
		Tutorial tutorial;
		Conference c;
		tutorial = this.tutorialService.findOne(tutorialId);
		Assert.notNull(tutorial);
		c=this.conferenceService.ConferenceOwn(tutorialId);
		result = this.createEditModelAndView(tutorial,c);
		

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
			
			/*
			if (tutorial.getId() != 0) {
				Assert.isTrue(c.getActivities()
						.contains(tutorial),"no.permission");
				
			}*/
	
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
	public ModelAndView delete(final Tutorial tutorial, final BindingResult binding) {
		ModelAndView result;
		Conference c;
//		Administrator admin= this.administratorService.findByPrincipal();
//		Assert.isTrue();

		try {
			c = this.conferenceService.ConferenceOwn(tutorial.getId());
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
