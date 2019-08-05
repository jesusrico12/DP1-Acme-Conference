package services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubmissionRepository;
import domain.Author;
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

	//CRUD Methods ----------------

	public Submission create(){

		Author principal = (Author) this.actorService.findByPrincipal();
		
		Submission result = new Submission();		

		//Setear ticker
		result.setMadeMoment(new Date(System.currentTimeMillis()-1));

		//Autimatizarlo con system configuration
		result.setStatus("UNDER-REVIEW");
		result.setToReview(false);
		result.setAuthor(principal);
	
		return result;

	}
	
	public Submission save(Submission submission){
		Submission result;
		
		Author principal = (Author) this.actorService.findByPrincipal();
		
		//Guardando una nueva
		if(submission.getId() == 0){
			
			Assert.isTrue(submission.getId() == principal.getId(), "Not your submission");
			Assert.isTrue(submission.getConference().getSubmissionDeadline().after(new Date(System.currentTimeMillis()-1)),
					"Submission deadline has elapsed");
			
			result = this.submissionRepository.save(submission);
			
		}else{
			
			Assert.isTrue(submission.getStatus().equals("ACCEPTED"));
			Assert.isTrue(submission.getConference().getCameraReadyDeadline().after(new Date(System.currentTimeMillis()-1)));
			
			result = this.submissionRepository.save(submission);
			
		}
		
		
		
		return result;
		
	}


}
