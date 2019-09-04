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
import domain.Panel;


import services.ActorService;
import services.ConferenceService;
import services.PanelService;

@Controller
public class PanelController extends AbstractController {

	
	@Autowired
	private PanelService panelService;
	
	@Autowired
	private ConferenceService conferenceService;
	
	@Autowired
	private ActorService actorService;

	
	
	//DISPLAY
	
	@RequestMapping(value = "panel/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int panelId) {
	 	ModelAndView result;
		boolean permission=false;
		Panel panel=this.panelService.findOne(panelId);
		boolean Autheticated=true;
		
		try{
			Actor principal=this.actorService.findByPrincipal();
			if(this.conferenceService.ConferenceOwn(panel.getId()).getAdministrator()==principal){
				permission=true;
			
			}
			}catch(Throwable oops){
				Autheticated=false;
			}
		
		result = new ModelAndView("panel/display");
		result.addObject("panel",panel );
		result.addObject("conference",this.conferenceService.ConferenceOwn(panelId));
		result.addObject("permission",permission);
		result.addObject("Autheticated",Autheticated);
		result.addObject("speakers",panel.getSpeakers());
		result.addObject("attachments",panel.getAttachments());
		return result;
	}
	//Create

	@RequestMapping(value = "panel/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int conferenceId){
		ModelAndView result;
		try{
			//SEGURIDAD
		Actor principal=this.actorService.findByPrincipal();
		Assert.isTrue(principal==this.conferenceService.findOne(conferenceId).getAdministrator());
		Panel panel = this.panelService.create();
		Conference c=this.conferenceService.findOne(conferenceId);
		Date currentMoment = new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(c.getEndDate().after(currentMoment)&& c.getIsDraft()==false);
	

		result = this.createEditModelAndView(panel,c);
		
		result.addObject("conferenceId", this.conferenceService.findOne(conferenceId));
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
		

		return result;


	}
	@RequestMapping(value = "panel/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int panelId) {
		ModelAndView result;
		try{
			//SEGURIDAD
		Panel panel;
		Actor principal=this.actorService.findByPrincipal();
		panel = this.panelService.findOne(panelId);
		Assert.isTrue(principal==this.conferenceService.ConferenceOwn(panelId).getAdministrator());
		
		Conference c;
		
		Assert.notNull(panel);
		c=this.conferenceService.ConferenceOwn(panelId);
		result = this.createEditModelAndView(panel,c);
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}
		
		return result;
	}

	
	@RequestMapping(value = "panel/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int conferenceId,@Valid Panel panel, final BindingResult binding) {
		ModelAndView result;
		Conference c;
		if(panel.getId()!=0){
		 c=this.conferenceService.ConferenceOwn(panel.getId());
		 c=this.conferenceService.findOne(c.getId());
		}else {
			 c = this.conferenceService.findOne(conferenceId);
		}
		if(binding.hasErrors()){
			result = this.createEditModelAndView(panel,c, null);
		}else{
		try {
			
			
			if (panel.getId() != 0) {
			
				Assert.isTrue(this.panelService.findOne(panel.getId())!=null,"commit.error");
				
			}
			
			
	
			this.panelService.save(panel,c);
			if(panel.getId()!=0){
			result = new ModelAndView("redirect:/panel/display.do?panelId="+panel.getId());
			}else{
				result = new ModelAndView("redirect:/conference/display.do?conferenceId="+c.getId());
			}

		}
		
		catch (Throwable oops) {
			if(oops.getMessage()==null){
				
					result = new ModelAndView("redirect:/welcome/index.do");
				
			}else{
			result = this.createEditModelAndView(panel,c,  oops.getMessage());
			}
		}
		}
		return result;
	}
	
	@RequestMapping(value = "panel/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam final int conferenceId,final Panel panel, final BindingResult binding) {
		ModelAndView result;
		Conference c;


		try {
			c = this.conferenceService.findOne(conferenceId);
			Assert.isTrue(c!=null,"commit.error");
			
			this.panelService.delete(panel, c);
			result = new ModelAndView("redirect:/conference/display.do?conferenceId="+c.getId());
		} catch (final Throwable oops) {
			c = this.conferenceService.ConferenceOwn(panel.getId());
			result = this.createEditModelAndView(panel, c,  oops.getMessage());
		}
		return result;
	}

	
	protected ModelAndView createEditModelAndView(Panel panel, Conference c) {
		ModelAndView result;

		result = this.createEditModelAndView(panel,c, null);

		return result;
	}
	protected ModelAndView createEditModelAndView(Panel panel,Conference c,
			String messageCode) {
		ModelAndView result;
		
	
		result = new ModelAndView("panel/edit");


		result.addObject("panel", panel);
		result.addObject("conference", c);
		result.addObject("message", messageCode);

		return result;
	}
	
	
	
	
	
	

}
