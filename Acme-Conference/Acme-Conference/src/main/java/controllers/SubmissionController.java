package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConferenceService;
import services.PaperService;
import services.SubmissionService;
import domain.Actor;
import domain.Author;
import domain.Conference;
import domain.Submission;

@Controller
@RequestMapping("submission/author")
public class SubmissionController extends AbstractController{
	
	//Services
	@Autowired
	private ConferenceService conferenceService;
	
	@Autowired
	private PaperService paperService;
	
	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private ActorService actorService;
	
	@RequestMapping(value="/create" , method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;			
		
		Submission submission = this.submissionService.create();
		
		result = this.createEditModelAndView(submission);
		
		return result;
		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Author principal = (Author) this.actorService.findByPrincipal();
		
		
		Collection<Submission> submissions = this.submissionService.getSubmissionByOwner(principal.getId());
		
		
		result = new ModelAndView("submission/list");
		
		result.addObject("submissions", submissions);
		
		return result;
		
	}
	
	@RequestMapping(value="/listUpload", method=RequestMethod.GET)
	public ModelAndView listUpload(){
		ModelAndView result;
		
		Author principal = (Author) this.actorService.findByPrincipal();
		
		Collection<Submission> submissions = this.submissionService.getSubmissionToUpload(principal.getId());
		
		
		result = new ModelAndView("submission/listUpload");
		
		result.addObject("submissions", submissions);
		
		return result;
		
	}
	
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam int submissionId){
		ModelAndView result;
		Actor principal = this.actorService.findByPrincipal();
		boolean possible = false;
		
		Submission submission = this.submissionService.findOne(submissionId);
		
		if(submission.getAuthor().equals(principal) || submission.getReviewers().contains(principal)){
			possible = true;
		}
		result = new ModelAndView("submission/display");
		
		result.addObject("submission", submission);
		result.addObject("paper", submission.getPaper());
		result.addObject("possible", possible);
		
		return result;
		
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int conferenceId){
		ModelAndView result;
		
		Conference conference = this.conferenceService.findOne(conferenceId);
		
		Submission submission = this.submissionService.create();
		
		submission.setConference(conference);
		
		
		
		try{
			this.submissionService.save(submission);
			
			result = new ModelAndView("redirect:/paper/edit.do?conferenceId="+conferenceId);
			
			result.addObject("submission", submission);
			result.addObject("conference", conference);
			result.addObject("conferenceId", conferenceId);
			
		}catch(Throwable oops){
			result = this.createEditModelAndView(submission, oops.getMessage());
		}
		
		return result;
		
	}
	
	
	protected ModelAndView createEditModelAndView(Submission submission){
		ModelAndView result;
		
		result = this.createEditModelAndView(submission, null);
		
		return result;
		
	}
	
	protected ModelAndView createEditModelAndView(Submission submission, String messageCode){
		ModelAndView result;
		
		result = new ModelAndView("submission/edit");
		
		result.addObject("conference", submission.getConference());
		result.addObject("submission", submission);
		result.addObject("messageCode", messageCode);
		
		return result;
		
	}
}
