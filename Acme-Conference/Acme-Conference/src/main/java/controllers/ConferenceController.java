package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
import domain.Activity;
import domain.Actor;
import domain.Administrator;
import domain.Conference;
import domain.Panel;
import domain.Presentation;
import domain.Tutorial;

@Controller
@RequestMapping(value = "/conference")
public class ConferenceController extends AbstractController{

	//Services

	@Autowired
	private ConferenceService conferenceService;

	@Autowired
	private ActorService actorService;
	



	//Create

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Conference conference = this.conferenceService.create();

		result = this.createEditModelAndView(conference);


		return result;


	}
	
	@RequestMapping(value = "/display" , method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int conferenceId){
		ModelAndView result;
		Conference conference = this.conferenceService.findOne(conferenceId);

		boolean permission=false;
		boolean Autheticated=true;
		try{
		Actor principal=this.actorService.findByPrincipal();
		if(this.conferenceService.findOne(conferenceId).getAdministrator()==principal){
			permission=true;
		
		}
		}catch(Throwable oops){
			Autheticated=false;
		}

		boolean isActivity = false;
		boolean isPanel=false;
		boolean isTutorial=false;
		boolean isPresentation=false;
		Collection<Activity> activities = conference.getActivities();
		boolean isTimeToDecisionMaking=false;
		
		result = new ModelAndView("conference/display");
		
		if(this.conferenceService.conferencesBetweenSubDeadlineNotifDeadline().contains(conference)){
			isTimeToDecisionMaking=true;
			
		}
	 	result.addObject("isTimeToDecisionMaking",isTimeToDecisionMaking);
		if(!activities.isEmpty()){
			result.addObject("activities", activities);
			isActivity = true;
			Set<Activity>  panels= new HashSet<Activity>();
			Set<Activity>  pres= new HashSet<Activity>();
			Set<Activity>  tuts= new HashSet<Activity>();
			for(Activity i: activities){
			if(i instanceof Panel){
				panels.add(i);
				result.addObject("panels", panels);
				isPanel = true;
				result.addObject("isPanel", isPanel);
			}else if(i instanceof Presentation){
				pres.add(i);
				result.addObject("presentations", pres);
				isPresentation = true;
				result.addObject("isPresentation", isPresentation);
			}else if(i instanceof Tutorial){
				tuts.add(i);
				result.addObject("tutorials", tuts);
			
				isTutorial = true;
				result.addObject("isTutorial", isTutorial);
			}
			}
		}
		
		result.addObject("conference", conference);
		result.addObject("isActivity", isActivity);
		result.addObject("permission",permission);
		result.addObject("Autheticated",Autheticated);
		return result;
		
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int conferenceId){
		ModelAndView result;

		Conference conference= this.conferenceService.findOne(conferenceId);

		result = this.createEditModelAndView(conference);

		return result;
	}

	@RequestMapping(value = "/edit" , method = RequestMethod.POST, params = "saveNormal")
	public ModelAndView saveNormal(@Valid Conference conference, BindingResult binding){
		ModelAndView result;

		if(binding.hasErrors()){
			result = this.createEditModelAndView(conference);
		}else{
			try{
				this.conferenceService.save(conference);
				result = new ModelAndView("redirect:list.do");

			}catch(Throwable oops){
				result = this.createEditModelAndView(conference, oops.getMessage());
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit" , method = RequestMethod.POST, params = "saveFinal")
	public ModelAndView saveFinal(@Valid Conference conference, BindingResult binding){
		ModelAndView result;

		if(binding.hasErrors()){
			result = this.createEditModelAndView(conference);
		}else{
			try{

				conference.setIsDraft(false);
				this.conferenceService.save(conference);

				result = new ModelAndView("redirect:list.do");

			}catch(Throwable oops){
				result = this.createEditModelAndView(conference, oops.getMessage());
			}
		}
		return result;
	}

	

	
	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;


		boolean isForthcoming = false;
		boolean isPast = false;
		boolean isRunning = false;
		
		boolean isAll = false;
		
		boolean isSubmission = false;
		boolean isNotification = false;
		boolean isCamera = false;
		boolean isStart = false;
		
		boolean isMakeSubmission = false;

		result = new ModelAndView("conference/list");

		//Non authorized and author,reviewer
		Collection<Conference> forthcoming = this.conferenceService.getForthcoming();
		Collection<Conference> past = this.conferenceService.getPast();
		Collection<Conference> running = this.conferenceService.getRunning();
		Collection<Conference> conferences = this.conferenceService.findAll();
		
		if(!forthcoming.isEmpty()){
			result.addObject("forthcoming", forthcoming);

			isForthcoming = true;

		}

		if(!past.isEmpty()){
			result.addObject("past", past);

			isPast = true;

		}

		if(!running.isEmpty()){
			result.addObject("running", running);

			isRunning = true;

		}

		result.addObject("isForthcoming", isForthcoming);
		result.addObject("isPast", isPast);
		result.addObject("isRunning", isRunning);


		//Admin list



		Collection<Conference> submission = this.conferenceService.getSubmissionLast5();
		Collection<Conference> notification = this.conferenceService.getNotificationLess5();
		Collection<Conference> camera = this.conferenceService.getCameraLess5();
		Collection<Conference> start = this.conferenceService.getStartLess5();
		
		if(!submission.isEmpty()){
			result.addObject("submission", submission);
			
			isSubmission = true;
		}
		
		if(!notification.isEmpty()){
			result.addObject("notification", notification);
			
			isNotification = true;
			
		}
		
		if(!camera.isEmpty()){
			result.addObject("camera", camera);
			
			isCamera = true;
			
		}

		if(!start.isEmpty()){
			result.addObject("start", start);
			
			isStart = true;
		}
		
		if(!conferences.isEmpty()){
			isAll = true;
			
			result.addObject("conferences", conferences);
		}
		
		

		result.addObject("isSubmission", isSubmission);
		result.addObject("isNotification", isNotification);
		result.addObject("isCamera", isCamera);
		result.addObject("isStart", isStart);
		result.addObject("isAll", isAll);
	
		
		
		
		
		
		
		return result;
	}

	
	@RequestMapping(value="/listSubmission", method = RequestMethod.GET)
	public ModelAndView listMakeSubmission(){
		ModelAndView result;
		
		boolean isToMakeSubmission = false;
		
		Collection<Conference> conferencesToMakeSubmission = this.conferenceService.getToMakeSubmission();
		
		result = new ModelAndView("conference/listSubmission");
		
		if(!conferencesToMakeSubmission.isEmpty()){
			result.addObject("conferencesToMakeSubmission", conferencesToMakeSubmission);
			
			isToMakeSubmission = true;
		}
		
		result.addObject("isToMakeSubmission", isToMakeSubmission);
		
		return result;

	}
	
	//FINDER 
	
	@RequestMapping(value = "/finder" , method = RequestMethod.POST)
	public ModelAndView finder(@RequestParam String keyword){
		ModelAndView result;
		
		Collection<Conference> conferencesFinder=new LinkedList<>();
		if(!keyword.isEmpty()){
			conferencesFinder=this.conferenceService.conferencesFinder(keyword);
		}
		result = new ModelAndView("conference/finder");
		result.addObject("conferencesFinder",conferencesFinder);
		result.addObject("conferencesFinals", this.conferenceService.conferencesFinals());
		
		return result;
	
	}
	
	//DECISIONMAKING
	@RequestMapping(value = "/decisionMaking" , method = RequestMethod.POST)
	public ModelAndView decisionMaking(@RequestParam int conferenceId){
		ModelAndView result;
		
		this.conferenceService.decisionMaking(conferenceId);
	
		result = new ModelAndView("redirect:list.do");
		
		
		return result;
	

	}

	
	@RequestMapping(value = "/finder" , method = RequestMethod.GET)
	public ModelAndView finder(){
		ModelAndView result;
		
		result = new ModelAndView("conference/finder");
		
		
		result.addObject("conferencesFinals", this.conferenceService.conferencesFinals());
		
		
		return result;
		
	}

	protected ModelAndView createEditModelAndView(Conference conference) {
		ModelAndView result;

		result = this.createEditModelAndView(conference, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Conference conference,
			String messageCode) {
		ModelAndView result;
		Administrator principal = (Administrator) this.actorService.findByPrincipal();
		Boolean possible = false;

		result = new ModelAndView("conference/edit");

		if(conference.getId() != 0){

			if(conference.getAdministrator().equals(principal)){
				possible = true;
			}
			result.addObject("title", conference.getTitle());
			result.addObject("acronym", conference.getAcronym());
			result.addObject("venue", conference.getVenue());
			result.addObject("submissionDL", conference.getSubmissionDeadline());
			result.addObject("notificationDL", conference.getNotificationDeadline());
			result.addObject("cameraReadyDL", conference.getCameraReadyDeadline());
			result.addObject("startDate", conference.getStartDate());
			result.addObject("endDate", conference.getEndDate());
			result.addObject("summary", conference.getSummary());
			result.addObject("fee", conference.getFee());
			result.addObject("possible", possible);

		}

		result.addObject("conference", conference);
		result.addObject("message", messageCode);

		return result;
	}
}
