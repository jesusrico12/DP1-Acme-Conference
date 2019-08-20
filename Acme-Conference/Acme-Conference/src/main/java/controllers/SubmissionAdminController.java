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
import domain.Submission;

@Controller
@RequestMapping("submission/administrator")
public class SubmissionAdminController extends AbstractController{

	//Services
		@Autowired
		private ConferenceService conferenceService;
		
		@Autowired
		private PaperService paperService;
		
		@Autowired
		private SubmissionService submissionService;
		
		@Autowired
		private ActorService actorService;
		
		
		@RequestMapping(value="/list", method = RequestMethod.GET)
		public ModelAndView list(@RequestParam int conferenceId){
			ModelAndView result;
			
			result = new ModelAndView("submission/list");
			
			
			boolean isAccepted = false;
			boolean isRejected = false;
			boolean isUnder = false;
			boolean isBorder = false;
			
			Collection<Submission> accepted = this.submissionService.getAcceptedByConference(conferenceId);
			Collection<Submission> rejected = this.submissionService.getRejectedByConference(conferenceId);
			Collection<Submission> under = this.submissionService.getUnderByConference(conferenceId);
			Collection<Submission>  border = this.submissionService.getBorderByConference(conferenceId);
			
			if(!accepted.isEmpty()){
				result.addObject("accepted", accepted);
				
				isAccepted = true;
			}
			
			if(!rejected.isEmpty()){
				result.addObject("rejected", rejected);
				
				isRejected = true;
			}
			
			if(!under.isEmpty()){
				result.addObject("under", under);
				
				isUnder = true;
			}
			
			if(!border.isEmpty()){
				result.addObject("border", border);
				
				isBorder = true;
			}
			
			
			result.addObject("isAccepted", isAccepted);
			result.addObject("isRejected", isRejected);
			result.addObject("isUnder", isUnder);
			result.addObject("isBorder", isBorder);
			
			
			return result;
			
		}
		
		@RequestMapping(value="/display", method=RequestMethod.GET)
		public ModelAndView display(@RequestParam int submissionId){
			ModelAndView result;
			
			Submission submission = this.submissionService.findOne(submissionId);
			
			result = new ModelAndView("submission/display");
			
			result.addObject("submission", submission);
			result.addObject("paper", submission.getPaper());
			
			
			return result;
			
		}
		

		
		
		
		
		
		
		
}
