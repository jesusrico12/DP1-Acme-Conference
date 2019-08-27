package services;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
		
		Actor principal=this.actorService.findByPrincipal();
		Assert.isTrue(conference.getAdministrator()==principal,"commit.error");
		
	//	Presentation copy= new Presentation();
		
		Date currentMoment;
		Assert.isTrue(presentation.getStartMoment().before(conference.getEndDate())&&presentation.getStartMoment().after(conference.getStartDate()),"date.error");
		currentMoment = new Date(System.currentTimeMillis() - 1);
		if(presentation.getAttachments().size()>0){
		for(String i : presentation.getAttachments() ){
			Assert.isTrue(i.startsWith("https://www.dropbox.com")||i.startsWith("https://www.flickr.com"),"url.error");
		 }
		}
		
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		Assert.isTrue(presentation.getStartMoment().after(currentMoment),"invalid.date");
		Assert.isTrue(presentation.getPaper().getIsCameraReady()==true,"paper.invalid");
		
		if(presentation.getId()!=0){
//			copy=this.findOne(presentation.getId());
//			copy.setAttachments(presentation.getAttachments());
//			copy.setRoom(presentation.getRoom());
//			copy.setSpeakers(presentation.getSpeakers());
//			copy.setStartMoment(presentation.getStartMoment());
//			copy.setPaper(presentation.getPaper());
//			copy.setSummary(presentation.getSummary());
//			copy.setTitle(presentation.getTitle());
//			copy.setDuration(presentation.getDuration());
			Assert.isTrue(this.findOne(presentation.getId())!=null,"commit.error");
			this.presentationRepository.save(presentation);
			
		}else{
			
//			copy.setAttachments(presentation.getAttachments());
//			copy.setRoom(presentation.getRoom());
//			copy.setSpeakers(presentation.getSpeakers());
//			copy.setStartMoment(presentation.getStartMoment());
//			copy.setPaper(presentation.getPaper());
//			copy.setSummary(presentation.getSummary());
//			copy.setTitle(presentation.getTitle());
//			copy.setDuration(presentation.getDuration());
			
			presentation=this.presentationRepository.save(presentation);
			Set<Activity> actis= new HashSet<Activity>();
			for(Activity a :conference.getActivities()){
				actis.add(a);
			}
			actis.add(presentation);
			
			conference.setActivities(actis);
			
			conferenceService.saveForce(conference);
		}
		
		
		return presentation;
	}
	
	public void delete(Presentation presentation,Conference conference){
		
		
		Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		Assert.isTrue(this.findOne(presentation.getId())!=null,"commit.error");
		
		Assert.isTrue(conference.getAdministrator()==principal,"commit.error");
		
		Conference c=this.conferenceService.findOne(conference.getId());

		Collection<Activity> col= c.getActivities();
		
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
