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

import services.TutorialService;
import services.SectionService;

import domain.Tutorial;
import domain.Section;
@Controller
public class SectionController extends AbstractController{

	
	
	@Autowired
	private SectionService sectionService;
	@Autowired
	private TutorialService tutorialService;

	
	
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
		Section section = this.sectionService.create();
		Tutorial c=this.tutorialService.findOne(tutorialId);
		

		result = this.createEditModelAndView(section,c);
		
		result.addObject("tutorialId", this.tutorialService.findOne(tutorialId));
		
		return result;


	}
	
	@RequestMapping(value = "section/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int sectionId) {
		ModelAndView result;
		Section section;
		Tutorial c;
		section = this.sectionService.findOne(sectionId);
		Assert.notNull(section);
		c=this.tutorialService.TutorialOwn(sectionId);
		result = this.createEditModelAndView(section,c);
		

		return result;
	}
	@RequestMapping(value = "section/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int tutorialId,@Valid Section section, final BindingResult binding) {
		ModelAndView result;
		Tutorial c;
		if(section.getId()!=0){
		 c=this.tutorialService.TutorialOwn(section.getId());
		}else {
			 c = this.tutorialService.findOne(tutorialId);
		}
		if(binding.hasErrors()){
			result = this.createEditModelAndView(section,c, null);
		}else{
		try {
			
			/*
			if (section.getId() != 0) {
				Assert.isTrue(c.getActivities()
						.contains(section),"no.permission");
				
			}*/
	
			this.sectionService.save(section,c);
			result = new ModelAndView("redirect:/tutorial/list.do");
			

		} catch (Throwable oops) {
			result = this.createEditModelAndView(section,c,  oops.getMessage());
		}
		}
		return result;
	}
	
	@RequestMapping(value = "section/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Section section, final BindingResult binding) {
		ModelAndView result;
		Tutorial c;
//		Administrator admin= this.administratorService.findByPrincipal();
//		Assert.isTrue();

		try {
			c = this.tutorialService.TutorialOwn(section.getId());
			Assert.isTrue(c!=null,"commit.error");
			this.sectionService.delete(section, c);
			result = new ModelAndView("redirect:/tutorial/list.do");
		} catch (final Throwable oops) {
			c = this.tutorialService.TutorialOwn(section.getId());
			result = this.createEditModelAndView(section, c,  oops.getMessage());
		}
		return result;
	}
	protected ModelAndView createEditModelAndView(Section section, Tutorial c) {
		ModelAndView result;

		result = this.createEditModelAndView(section,c, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Section section,Tutorial c,
			String messageCode) {
		ModelAndView result;
		
	
		result = new ModelAndView("section/edit");


		result.addObject("section", section);
		result.addObject("tutorial", c);
		result.addObject("message", messageCode);
	

		return result;
	}
}
