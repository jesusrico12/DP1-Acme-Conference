package controllers;

import java.util.ArrayList;

import java.util.Collection;

import java.util.List;

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



import domain.Actor;

import domain.Report;

import domain.Submission;

import services.ActorService;
import services.ReportService;
import services.SubmissionService;

@Controller
@RequestMapping(value = "/report")
public class ReportController extends AbstractController{




	@Autowired
	private ReportService reportService;


	@Autowired
	private ActorService actorService;

	@Autowired
	private SubmissionService submissionService;





	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int submissionId){
		ModelAndView result;
		Actor principal;
		try{
		principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "REVIEWER"));
		Assert.isTrue(this.submissionService.submissionsOfReviewer(principal.getId()).contains(this.submissionService.findOne(submissionId)));
		Report report = this.reportService.create();
		Collection<String> decisions=new ArrayList<String>();
		decisions.add("REJECT");
		decisions.add("ACCEPT");
		decisions.add("BORDER-LINE");
		result = this.createEditModelAndView(report);

		result.addObject("submissionId",submissionId);
		result.addObject("decisions",decisions);
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}



		return result;


	}

	@RequestMapping(value = "/list" , method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		try{
			Collection<Submission> submissionsOfReviewer=this.submissionService.submissionsOfReviewer(this.actorService.findByPrincipal().getId());
			Collection<Report> reportsPerReviewer=this.reportService.reportsPerReviewer(this.actorService.findByPrincipal().getId());

			Actor principal;
			principal = this.actorService.findByPrincipal();
			result = new ModelAndView("report/list");


			result.addObject(reportsPerReviewer);

			//lista de submission que puede hacer el principal un report
			result.addObject("submissionsOfReviewer",submissionsOfReviewer);
			//lista de reports realizadas por el principal
			result.addObject("reportsPerReviewer",reportsPerReviewer);


			if(	this.actorService.checkAuthority(principal, "AUTHOR")){
			Collection<Submission> submissionsToAuthor=this.submissionService.submissionsToAuthor(principal.getId());
			result.addObject("submissionsToAuthor",submissionsToAuthor);
			}
			


		}catch (Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");	
		}





		return result;
	}

	@RequestMapping(value = "/toAuthor" , method = RequestMethod.GET)
	public ModelAndView toAuthor(@RequestParam int submissionId){
		ModelAndView result;
		try{
			Actor principal;
			principal = this.actorService.findByPrincipal();


			Assert.isTrue(this.actorService.checkAuthority(principal, "AUTHOR"));
				Assert.isTrue(this.submissionService.getSubmissionByOwner(principal.getId()).contains(this.submissionService.findOne(submissionId)));
			
			result = new ModelAndView("report/toAuthor");



			result.addObject("reports",this.submissionService.findOne(submissionId).getReports());


		}catch (Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");	
		}





		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int reportId) {

		ModelAndView result;
		Report report;
		Actor principal;
		try{
			principal = this.actorService.findByPrincipal();
			Assert.isTrue(this.actorService.checkAuthority(principal, "REVIEWER"));
			report = this.reportService.findOne(reportId);
			Assert.notNull(report);
			Assert.isTrue(!this.submissionService.submissionsOfReviewer(principal.getId()).isEmpty(),"no.permission");
			Assert.isTrue(report.getReviewer()==principal,"no.permission");
			result = this.createEditModelAndView(report);
		}catch(Throwable oops){
			result = new ModelAndView("redirect:/welcome/index.do");
		}


		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@RequestParam final int submissionId,@Valid Report report, final BindingResult binding) {
		ModelAndView result;
		Actor principal;

		if(binding.hasErrors()){
			result = this.createEditModelAndView(report, null);
		}else{
			try {
				principal = this.actorService.findByPrincipal();
				Assert.isTrue(this.actorService.checkAuthority(principal, "REVIEWER"));
				Submission submission =this.submissionService.findOne(submissionId);
				Assert.isTrue(this.submissionService.submissionsOfReviewer(this.actorService.findByPrincipal().getId()).contains(submission),"commit.error");
				if(report.getId()!=0){
					Assert.isTrue(report.getReviewer()==principal,"commit.error");
				}
				String comment= report.getComments().toString();
				List<String> commentL= new ArrayList<String>();
				commentL.add(comment);
				report.setComments(commentL);


				Report r=this.reportService.save(report,submission);


				result = new ModelAndView("redirect:/report/display.do?reportId="+r.getId());



			}catch (Throwable oops) {

				if(oops.getMessage()==null){

					result = new ModelAndView("redirect:/welcome/index.do");

				}else{
					result = this.createEditModelAndView(report,  oops.getMessage());
				}

			}
		}

		result.addObject("submissionId",submissionId);
		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int reportId) {
		ModelAndView result;
		boolean timeToComment=false;
		boolean permissionAuthor=false;
		Actor principal; 
	try{
		if(this.submissionService.reportTimeToComment(reportId)!=null){
			timeToComment=true;
		}
		principal = this.actorService.findByPrincipal();

		


		Report report=this.reportService.findOne(reportId);
		
		if(this.actorService.checkAuthority(principal, "REVIEWER")){
			Assert.isTrue(report.getReviewer()==principal);
			
		}
		result = new ModelAndView("report/display");
		result.addObject("report",report );

		result.addObject("timeToComment",timeToComment);
		if(this.submissionService.SubDisplayReportAuthor(reportId,principal.getId())!=null && this.actorService.checkAuthority(principal, "AUTHOR")){
			permissionAuthor=true;
		}
		result.addObject("permissionAuthor",permissionAuthor);
	}catch(Throwable opps){
		
		result = new ModelAndView("redirect:/welcome/index.do");
		
	}
		return result;
	}

	//ADDCOMMENT

	@RequestMapping(value = "/comment" , method = RequestMethod.POST)
	public ModelAndView finder(@RequestParam final int reportId,@RequestParam String comment){

		ModelAndView result;
		Actor principal;
		try {
			principal = this.actorService.findByPrincipal();
			Assert.isTrue(this.actorService.checkAuthority(principal, "REVIEWER"));
			Report report =this.reportService.findOne(reportId);
			Assert.isTrue(principal==report.getReviewer(),"commit.error");
			report.getComments().add(comment);
			this.reportService.saveForce(report);

			result = new ModelAndView("redirect:display.do?reportId="+reportId);

		}catch (Throwable oops) {
			result = new ModelAndView("redirect:/welcome/index.do");	
		}

		return result;

	}


	protected ModelAndView createEditModelAndView(Report report) {
		ModelAndView result;

		result = this.createEditModelAndView(report, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Report report,
			String messageCode) {
		ModelAndView result;


		result = new ModelAndView("report/edit");


		result.addObject("report",report);
		result.addObject("message", messageCode);


		return result;
	}

}
