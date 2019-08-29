package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ConferenceService;
import services.PaperService;
import services.SubmissionService;
import domain.Actor;
import domain.Conference;
import domain.Paper;
import domain.Submission;

@Controller
@RequestMapping("/paper")
public class PaperController extends AbstractController{

	//Services

	@Autowired
	private PaperService paperService;

	@Autowired
	private SubmissionService submissionService;

	@Autowired
	private ConferenceService conferenceService;

	@Autowired
	private Validator validator;

	@Autowired
	private ActorService actorService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int conferenceId){
		ModelAndView result;

		Paper paper = this.paperService.create();


		result = this.createEditModelAndView(paper);

		result.addObject("conferenceId", conferenceId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int paperId){
		ModelAndView result;

		Paper paper = this.paperService.findOne(paperId);

		result = this.createEditModelAndView(paper);

		result.addObject("paper", paper);

		return result;
	}

	@RequestMapping(value="/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Paper paper, BindingResult binding, Integer conferenceId){
		ModelAndView result;

		if(binding.hasErrors()){
			result = this.createEditModelAndView(paper);
		}else{
			try{


				Submission submission = this.submissionService.create();


				if(conferenceId!= null){


					Conference conference = this.conferenceService.findOne(conferenceId);

					submission.setConference(conference);
				}


				this.paperService.save(paper,submission);

				if(conferenceId != null){
					this.submissionService.save(submission);

				}

				result = new ModelAndView("redirect:/submission/author/list.do");
			}catch(Throwable oops){
				result = this.createEditModelAndView(paper, oops.getMessage());
				result.addObject("conferenceId", conferenceId);

			}
		}

		return result;
	}


	@RequestMapping(value="/display" , method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int paperId){
		ModelAndView result;
		Actor principal = this.actorService.findByPrincipal();
		boolean possible = false;
		
		Paper paper = this.paperService.findOne(paperId);
		Submission submission = this.submissionService.getSubmissionByPaper(paperId);
		
		if(submission.getAuthor().equals(principal) ||
				submission.getReviewers().contains(principal)||
				submission.getConference().getAdministrator().equals(principal)){
			
			possible = true;
			
		}
		result = new ModelAndView("paper/display");

		result.addObject("paper", paper);
		result.addObject("possible", possible);
		
		return result;
	}



	protected ModelAndView createEditModelAndView(Paper paper){
		ModelAndView result;

		result = this.createEditModelAndView(paper, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Paper paper, String messageCode){
		ModelAndView result;
		boolean possible = false;

		Actor principal = this.actorService.findByPrincipal();

		if(paper.getId()!=0){
			Submission submission = this.submissionService.getSubmissionByPaper(paper.getId());

			if(submission.getAuthor().equals(principal)){
				possible = true;
			}
		}else{
			possible = true;
		}


		result = new ModelAndView("paper/edit");

		result.addObject("paper", paper);
		result.addObject("messageCode", messageCode);

		result.addObject("possible", possible);
		return result;
	}
}
