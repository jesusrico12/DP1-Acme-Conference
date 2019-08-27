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

import services.ActorService;
import services.ConferenceService;
import services.TutorialService;
import services.SectionService;

import domain.Actor;
import domain.Tutorial;
import domain.Section;
@Controller
public class SectionController extends AbstractController{

	
	
	@Autowired
	private SectionService sectionService;
	@Autowired
	private TutorialService tutorialService;

	@Autowired
	private ConferenceService conferenceService;
	
	@Autowired
	private ActorService actorService;
	
	//DISPLAY
	
	@RequestMapping(value = "section/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int sectionId) {
		ModelAndView result;
		Section section=this.sectionService.findOne(sectionId);
		result = new ModelAndView("section/display");
		result.addObject("section",section );
		

		return result;
	}
	//Create

	@RequestMapping(value = "section/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int tutorialId){
		ModelAndView result;
		try{
			//SEGURIDAD
		Actor principal=this.actorService.findByPrincipal();
		Assert.isTrue(this.conferenceService.ConferenceOwn(tutorialId).getAdministrator()==principal,"commit.error");
		Section section = this.sectionService.create();
		Tutorial c=this.tutorialService.findOne(tutorialId);
		

		result = this.createEditModelAndView(section,c);
		
		result.addObject("tutorialId", this.tutorialService.findOne(tutorialId));
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
		return result;


	}
	
	@RequestMapping(value = "section/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int sectionId) {
		ModelAndView result;
		
		try{
			Section section;
		
		Tutorial c;
		Actor principal=this.actorService.findByPrincipal();
		section = this.sectionService.findOne(sectionId);
		Assert.notNull(section);
		c=this.tutorialService.TutorialOwn(sectionId);
		Assert.isTrue(this.conferenceService.ConferenceOwn(c.getId()).getAdministrator()==principal,"commit.error");
		result = this.createEditModelAndView(section,c);
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}

		return result;
	}
	@RequestMapping(value = "section/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int tutorialId,@Valid Section section, final BindingResult binding) {
		ModelAndView result;
		Tutorial tutorial;
		if(section.getId()!=0){
			tutorial=this.tutorialService.TutorialOwn(section.getId());
		}else {
			tutorial = this.tutorialService.findOne(tutorialId);
		}
		if(binding.hasErrors()){
			result = this.createEditModelAndView(section,tutorial, null);
		}else{
		try {
			
		
	
			this.sectionService.save(section,tutorial);
			result = new ModelAndView("redirect:/tutorial/display.do?tutorialId="+tutorial.getId());
			

		} catch (Throwable oops) {
			result = this.createEditModelAndView(section,tutorial,  oops.getMessage());
		}
		}
		return result;
	}
	
	@RequestMapping(value = "section/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Section section, final BindingResult binding) {
		ModelAndView result;
		Tutorial tutorial;

		try {
			tutorial = this.tutorialService.TutorialOwn(section.getId());
			Assert.isTrue(tutorial!=null,"commit.error");
			this.sectionService.delete(section, tutorial);
			result = new ModelAndView("redirect:/tutorial/display.do?tutorialId="+tutorial.getId());
		} catch (final Throwable oops) {
			tutorial = this.tutorialService.TutorialOwn(section.getId());
			result = this.createEditModelAndView(section, tutorial,  oops.getMessage());
		}
		return result;
	}
	protected ModelAndView createEditModelAndView(Section section, Tutorial tutorial) {
		ModelAndView result;

		result = this.createEditModelAndView(section,tutorial, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Section section,Tutorial tutorial,
			String messageCode) {
		ModelAndView result;
		
	
		result = new ModelAndView("section/edit");


		result.addObject("section", section);
		result.addObject("tutorial", tutorial);
		result.addObject("message", messageCode);
	

		return result;
	}
}
