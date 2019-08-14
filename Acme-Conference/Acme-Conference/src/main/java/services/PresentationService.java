package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Activity;
import domain.Actor;
import domain.Conference;

import domain.Presentation;

import repositories.PresentationRepository;

@Service
@Transactional
public class PresentationService {
	
	@Autowired
	private PresentationRepository presentationRepository;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ConferenceService conferenceService;
	
	public Presentation create(){
		
		Presentation res= new Presentation();
		Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		return res;
		
		
	}
	
	public Presentation save(Presentation presentation,Conference conference){
		
		
		Date currentMoment;
		Assert.isTrue(presentation.getStartMoment().before(conference.getEndDate())&&presentation.getStartMoment().after(conference.getStartDate()),"date.error");
		currentMoment = new Date(System.currentTimeMillis() - 1);
		if(presentation.getAttachments().size()>0){
		for(String i : presentation.getAttachments() ){
			Assert.isTrue(i.startsWith("https://www.dropbox.com")||i.startsWith("https://www.flickr.com"),"url.error");
		}
		}
		Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		Assert.isTrue(presentation.getStartMoment().after(currentMoment),"invalid.date");
		Assert.isTrue(presentation.getPaper().getIsCameraReady()==true,"paper.invalid");
		this.presentationRepository.save(presentation);
		if(presentation.getId()==0){
			
			conference.getActivities().add(presentation);
			
			conferenceService.saveForce(conference);
			
		}
		
		
		return presentation;
	}
	
	public void delete(Presentation presentation,Conference conference){
		
		
		Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		
		//conference.getActivities().remove(panel);
		Collection<Activity> col= conference.getActivities();
		col.remove(presentation);
		conference.setActivities(col);
		this.conferenceService.saveForce(conference);
		this.presentationRepository.delete(presentation);
		
		
		
	}
	public Presentation findOne(int id){
		return this.presentationRepository.findOne(id);
	}
	public Collection<Presentation> findAll(){
		
		return this.presentationRepository.findAll();
	}
	
	

}
