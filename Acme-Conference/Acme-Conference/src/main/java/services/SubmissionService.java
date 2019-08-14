package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		
		Submission result = new Submission();
		
		Author principal = (Author)this.actorService.findByPrincipal();
		
		result.setMadeMoment(new Date(System.currentTimeMillis()-1));
		result.setStatus("UNDER-REVIEW");
		result.setToReview(false);
		
		
		
		return result;
	}
	
	
/*	public Collection<Submission> submissionAccepted(){
		Collection<Submission> res;
		
		res= this.submissionRepository.submissionAccepted();
		return res;
		
	}*/
	
	

}
