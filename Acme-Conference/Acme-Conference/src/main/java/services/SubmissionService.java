package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubmissionRepository;
import domain.Author;
import domain.Conference;
import domain.Submission;

@Transactional
@Service

public class SubmissionService {

	//Repository ------------------

	@Autowired
	private SubmissionRepository submissionRepository;


	//Services

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private PaperService paperService;

	//CRUD Methods ----------------

	public Submission create(){

		Submission result = new Submission();

		Author principal = (Author)this.actorService.findByPrincipal();
		
		result.setMadeMoment(new Date(System.currentTimeMillis()-1));
		result.setStatus("UNDER-REVIEW");
		result.setToReview(false);
		result.setAuthor(principal);
		result.setConference(new Conference());
		result.setToReview(false);
		result.setTicker("ss");
		
		return result;
	}

	public Submission save(Submission submission){
		
		Author principal = (Author) this.actorService.findByPrincipal();


		if(submission.getId() != 0){

			Assert.isTrue(submission.getMadeMoment().before(submission.getConference().getSubmissionDeadline()), "submission.deadline");
			Assert.isTrue(submission.getStatus().equals("ACCEPTED"), "submission.notAccepted");
			Assert.isTrue(new Date(System.currentTimeMillis()-1).before(submission.getConference().getCameraReadyDeadline()), "submission.Camera");



		}else{
			Assert.isTrue(submission.getAuthor().equals(principal), "no.permission");
			Assert.isTrue(submission.getStatus().equals("UNDER-REVIEW"), "commit.error");
			

		}
		
			
		this.submissionRepository.saveAndFlush(submission);

		return submission;

	}
	
	public Submission findOne(int id){
		Submission result = this.submissionRepository.findOne(id);
		
		return result;
	}

	public Collection<Submission> findAll(){
		Collection<Submission> result = this.submissionRepository.findAll();
		
		return result;
	}
	
	
	public Collection<Submission> getSubmissionByOwner(int id){
		Collection<Submission> result = this.submissionRepository.getSubmissionByOwner(id);
		
		return result;
		
	}
	
	public Collection<Submission> getSubmissionToUpload(){
		Collection<Submission> result = this.submissionRepository.getSubmissionToUpload();
		
		return result;
	}
	
	public Collection<Submission> getAcceptedByConference(int id){
		Collection<Submission> result = this.submissionRepository.getAcceptedSubmissionByConference(id);
		
		return result;
	}
	
	public Collection<Submission> getRejectedByConference(int id){
		Collection<Submission> result = this.submissionRepository.getRejectedSubmissionByConference(id);
		
		return result;
	}
	
	public Collection<Submission> getUnderByConference(int id){
		Collection<Submission> result = this.submissionRepository.getUnderSubmissionByConference(id);
		
		return result;
	}
	
	public Collection<Submission> getBorderByConference(int id){
		Collection<Submission> result = this.submissionRepository.getBorderSubmissionByConference(id);
		
		return result;
	}
	
	
}
