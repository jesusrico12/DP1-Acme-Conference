package services;

import java.util.Collection;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import domain.Activity;
import domain.Administrator;
import domain.Conference;
import domain.Report;
import domain.Reviewer;
import domain.Submission;

import repositories.ReportRepository;
import repositories.SubmissionRepository;

@Transactional
@Service
public class ReportService {
	
	
	@Autowired
	private ReportRepository reportRepository;
	
	@Autowired
	private SubmissionService submissionService;
	
	
	@Autowired
	private ActorService actorService;
	
	
	
	public Report create(){

		Report result = new Report();

		Reviewer principal = (Reviewer) this.actorService.findByPrincipal();
		
		Assert.isTrue(!this.submissionService.submissionsOfReviewer(principal.getId()).isEmpty(),"no.permission");
		result.setReviewer(principal);

		return result;

	}
	//Save

		public Report save(Report report,Submission submission){

			Report result;

			Reviewer principal = (Reviewer) this.actorService.findByPrincipal();
			Assert.isTrue(!this.submissionService.submissionsOfReviewer(principal.getId()).isEmpty(),"no.permission");
			if(report.getId() == 0){

			
			    
			 

				result = this.reportRepository.save(report);
				
				submission.getReports().add(report);
				this.submissionService.saveForce(submission);
				

			}else{
				
				Report db = this.reportRepository.findOne(report.getId());
				Assert.isTrue(report.getReviewer()==principal,"no.permission");
				Assert.isTrue(db!=null,"commit.error");
				
				result = this.reportRepository.save(report);
			}
			
			return result;

		}
	
		public Report findOne(int id){
			Report result = this.reportRepository.findOne(id);
			
			return result;
		}
		
		public Collection<Report> findAll(){
			Collection<Report> result = this.reportRepository.findAll();
			
			return result;
		}
		public Collection<Report> reportsPerReviewer(int reviewerId){
			return this.reportRepository.reportsPerReviewer(reviewerId);
		}
		

}
