package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PaperRepository;
import domain.Actor;
import domain.Paper;
import domain.Submission;

@Transactional
@Service

public class PaperService {

	//Services
	
	@Autowired
	private ActorService actorService;
	

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
		Actor principal = this.actorService.findByPrincipal();
		
		if(paper.getId() == 0){
			
			Assert.isTrue(submission.getAuthor().equals(principal),"no.permission.submission");
			Assert.isTrue(paper.getDocument().startsWith("https://www.dropbox.com")||paper.getDocument().startsWith("https://www.flickr.com"),"url.error");
			Assert.isTrue(paper.getIsCameraReady() == false,"changed.boolean");
			
			result = this.paperRepository.saveAndFlush(paper);
			
			submission.setPaper(result);
		}else{
			
			Paper bd = this.findOne(paper.getId());
			
			Assert.isTrue(bd.getIsCameraReady() == paper.getIsCameraReady(),"changed.boolean");
			Assert.isTrue(submission.getAuthor().equals(principal),"no.permission.submission");
			Assert.isTrue(paper.getDocument().startsWith("https://www.dropbox.com")||paper.getDocument().startsWith("https://www.flickr.com"),"url.error");
			
			paper.setIsCameraReady(true);
			
			result = this.paperRepository.saveAndFlush(paper);
		}
		
		
		return result;
	}
	
	public Paper findOne(int id){
		Paper result = this.paperRepository.findOne(id);
		
		return result;
	}
	
	public Collection<Paper> paperReadys(int conferenceId){
		Collection<Paper> res= this.paperRepository.paperReadys(conferenceId);
		return res;
	}
	public Collection<Paper> findAll(){
		Collection<Paper> res= this.paperRepository.findAll();
		return res;
	}
}

