package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PaperRepository;
import domain.Paper;
import domain.Submission;

@Transactional
@Service

public class PaperService {

	//Services
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private SubmissionService submissionService;
	//Repository
	
	@Autowired
	private PaperRepository paperRepository;
	
	
	public Paper create(){
		Paper result = new Paper();
		
		result.setIsCameraReady(false);
		
		return result;
		
	}
	
	public Paper save(Paper paper,Submission submission){
		
		Paper result;
		
		if(paper.getId() == 0){
			result = this.paperRepository.saveAndFlush(paper);
			
			submission.setPaper(result);
		}else{
			paper.setIsCameraReady(true);
			
			result = this.paperRepository.saveAndFlush(paper);
		}
		
		
		return result;
	}
	
	public Paper findOne(int id){
		Paper result = this.paperRepository.findOne(id);
		
		return result;
	}
	
	public Collection<Paper> paperReadys(){
		Collection<Paper> res= this.paperRepository.paperReadys();
		return res;
	}
}

