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
import domain.Presentation;


import services.ActorService;
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
	
	@Autowired
	private ActorService actorService;
	

	

	//DISPLAY
	
	@RequestMapping(value = "presentation/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int presentationId) {
		ModelAndView result;
		boolean permission=false;
		Presentation presentation=this.presentationService.findOne(presentationId);
		try{
			Actor principal=this.actorService.findByPrincipal();
			if(this.conferenceService.ConferenceOwn(presentation.getId()).getAdministrator()==principal){
				permission=true;
			
			}
			}catch(Throwable oops){
				
			}
		result = new ModelAndView("presentation/display");
		result.addObject("presentation",presentation );
		result.addObject("conference",this.conferenceService.ConferenceOwn(presentationId));
		result.addObject("permission",permission);
		return result;
	}
	//Create

	@RequestMapping(value = "presentation/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId){
		ModelAndView result;
		try{
		Actor principal=this.actorService.findByPrincipal();
		Assert.isTrue(principal==this.conferenceService.findOne(conferenceId).getAdministrator());
		Presentation presentation = this.presentationService.create();
		Conference c=this.conferenceService.findOne(conferenceId);
		Date currentMoment = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(c.getEndDate().after(currentMoment)&& c.getIsDraft()==false);

		result = this.createEditModelAndView(presentation,c);
		
		result.addObject("conferenceId", this.conferenceService.findOne(conferenceId));
		result.addObject("papers", this.paperService.paperReadys(conferenceId));
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
		return result;


	}
	@RequestMapping(value = "presentation/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int presentationId) {
		ModelAndView result;
		try{
		Presentation presentation;
		Conference c;
		Actor principal=this.actorService.findByPrincipal();
		presentation = this.presentationService.findOne(presentationId);
		Assert.notNull(presentation);
		Assert.isTrue(principal==this.conferenceService.ConferenceOwn(presentationId).getAdministrator());
		c=this.conferenceService.ConferenceOwn(presentationId);
		result = this.createEditModelAndView(presentation,c);
		result.addObject("papers", this.paperService.paperReadys(c.getId()));
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
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
	public ModelAndView delete(@RequestParam final int conferenceId,final Presentation presentation, final BindingResult binding) {
		ModelAndView result;
		Conference c;


		try {
			c = this.conferenceService.findOne(conferenceId);
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
