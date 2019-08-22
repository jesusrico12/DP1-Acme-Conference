package controllers;

import javax.validation.Valid;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Conference;
import domain.Presentation;


import services.ConferenceService;
import services.PaperService;
import services.PresentationService;


@Controller
public class PresentationController extends AbstractController {

	@Autowired
	private PresentationService presentationService;
	
	@Autowired
	private ConferenceService conferenceService;
	
	@Autowired
	private PaperService paperService;
	

	

	//DISPLAY
	
	@RequestMapping(value = "presentation/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int presentationId) {
		ModelAndView result;
		Presentation presentation=this.presentationService.findOne(presentationId);
		result = new ModelAndView("presentation/display");
		result.addObject("presentation",presentation );
		result.addObject("conference",this.conferenceService.ConferenceOwn(presentationId));
		return result;
	}
	//Create

	@RequestMapping(value = "presentation/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId){
		ModelAndView result;
		Presentation presentation = this.presentationService.create();
		Conference c=this.conferenceService.findOne(conferenceId);
		

		result = this.createEditModelAndView(presentation,c);
		
		result.addObject("conferenceId", this.conferenceService.findOne(conferenceId));
		result.addObject("papers", this.paperService.paperReadys(conferenceId));
		return result;


	}
	@RequestMapping(value = "presentation/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int presentationId) {
		ModelAndView result;
		Presentation presentation;
		Conference c;
		presentation = this.presentationService.findOne(presentationId);
		Assert.notNull(presentation);
		c=this.conferenceService.ConferenceOwn(presentationId);
		result = this.createEditModelAndView(presentation,c);
		result.addObject("papers", this.paperService.paperReadys(c.getId()));

		return result;
	}

	
	@RequestMapping(value = "presentation/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int conferenceId,@Valid Presentation presentation, final BindingResult binding) {
		ModelAndView result;
		Conference c;
		if(presentation.getId()!=0){
		 c=this.conferenceService.ConferenceOwn(presentation.getId());
		}else {
			 c = this.conferenceService.findOne(conferenceId);
		}
		if(binding.hasErrors()){
			result = this.createEditModelAndView(presentation,c, null);
		}else{
		try {
			
			/*
			if (presentation.getId() != 0) {
				Assert.isTrue(c.getActivities()
						.contains(presentation),"no.permission");
				
			}*/
	
			this.presentationService.save(presentation,c);
			
			if(presentation.getId()!=0){
				result = new ModelAndView("redirect:/presentation/display.do?presentationId="+presentation.getId());
			}
			else{
				result = new ModelAndView("redirect:/conference/display.do?conferenceId="+c.getId());
			}
			

		}catch (Throwable oops) {
			if(oops.getMessage()==null){
				
				result = new ModelAndView("redirect:/welcome/index.do");
			
		}else{
		result = this.createEditModelAndView(presentation,c,  oops.getMessage());
		}
	}
	}
		return result;
	}
	
	@RequestMapping(value = "presentation/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Presentation presentation, final BindingResult binding) {
		ModelAndView result;
		Conference c;
//		Administrator admin= this.administratorService.findByPrincipal();
//		Assert.isTrue();

		try {
			c = this.conferenceService.ConferenceOwn(presentation.getId());
			Assert.isTrue(c!=null,"commit.error");
			this.presentationService.delete(presentation, c);
			result = new ModelAndView("redirect:/conference/display.do?conferenceId="+c.getId());
		} catch (final Throwable oops) {
			c = this.conferenceService.ConferenceOwn(presentation.getId());
			result = this.createEditModelAndView(presentation, c,  oops.getMessage());
		}
		return result;
	}

	
	protected ModelAndView createEditModelAndView(Presentation presentation, Conference c) {
		ModelAndView result;

		result = this.createEditModelAndView(presentation,c, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Presentation presentation,Conference c,
			String messageCode) {
		ModelAndView result;
		
	
		result = new ModelAndView("presentation/edit");


		result.addObject("presentation", presentation);
		result.addObject("conference", c);
		result.addObject("message", messageCode);
		result.addObject("papers", this.paperService.paperReadys(c.getId()));

		return result;
	}
	
	
	
	
}
