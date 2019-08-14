package services;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Activity;
import domain.Actor;
import domain.Conference;
import domain.Presentation;
import domain.Section;
import domain.Tutorial;

import repositories.TutorialRepository;

@Service
@Transactional
public class TutorialService {

	@Autowired
	private TutorialRepository tutorialRepository;
	
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ConferenceService conferenceService;
	
public Tutorial create(){
		
		Tutorial res= new Tutorial();
	
		Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
				"not.allowed");
		return res;
		
		
	}

public Tutorial findOne(final int tutorialId) {
	Tutorial result;

	result = this.tutorialRepository.findOne(tutorialId);
	Assert.notNull(result);

	return result;
}
	
	
public Collection<Tutorial> findAll(){
	
	return this.tutorialRepository.findAll();
}


public Tutorial save(Tutorial tutorial,Conference conference){
	
	Date currentMoment;
	Assert.isTrue(tutorial.getStartMoment().before(conference.getEndDate())&&tutorial.getStartMoment().after(conference.getStartDate()),"date.error");
	currentMoment = new Date(System.currentTimeMillis() - 1);
	if(tutorial.getAttachments().size()>0){
	for(String i : tutorial.getAttachments() ){
		Assert.isTrue(i.startsWith("https://www.dropbox.com")||i.startsWith("https://www.flickr.com"),"url.error");
	}
	}
	Actor principal = this.actorService.findByPrincipal();
	Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
			"not.allowed");
	Assert.isTrue(tutorial.getStartMoment().after(currentMoment),"invalid.date");
	Tutorial copy= new Tutorial();
	if(tutorial.getId()!=0){
		copy=this.findOne(tutorial.getId());
		copy.setAttachments(tutorial.getAttachments());
		copy.setRoom(tutorial.getRoom());
		copy.setSpeakers(tutorial.getSpeakers());
		copy.setStartMoment(tutorial.getStartMoment());
		copy.setSections(tutorial.getSections());
		copy.setSummary(tutorial.getSummary());
		copy.setTitle(tutorial.getTitle());
		copy.setDuration(tutorial.getDuration());
		
		this.tutorialRepository.save(copy);
		
	}else{
		
		copy.setAttachments(tutorial.getAttachments());
		copy.setRoom(tutorial.getRoom());
		copy.setSpeakers(tutorial.getSpeakers());
		copy.setStartMoment(tutorial.getStartMoment());
		copy.setSections(tutorial.getSections());
		copy.setSummary(tutorial.getSummary());
		copy.setTitle(tutorial.getTitle());
		copy.setDuration(tutorial.getDuration());
		

		
		conference.getActivities().add(copy);
		
		conferenceService.saveForce(conference);
	}
	return copy;
	
}

public void delete(Tutorial tutorial,Conference conference){
	
	
	Actor principal = this.actorService.findByPrincipal();
	Assert.isTrue(this.actorService.checkAuthority(principal, "ADMIN"),
			"not.allowed");
	
	//conference.getActivities().remove(panel);
	Collection<Activity> col= conference.getActivities();
	col.remove(tutorial);
	conference.setActivities(col);
	this.conferenceService.saveForce(conference);
	this.tutorialRepository.delete(tutorial);
	
	
	
}

public void saveForce(Tutorial tutorial) {

	this.tutorialRepository.save(tutorial);
	
}

public Tutorial TutorialOwn(int sectionId) {
	Tutorial res=this.tutorialRepository.TutorialOwn(sectionId);
	return res;
}
	
}
