package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import domain.Administrator;
import domain.Conference;

@Transactional
@Service
public class ConferenceService {

	//Repository ------------

	@Autowired
	private ConferenceRepository conferenceRepository;

	//Services --------------

	@Autowired
	private ActorService actorService;


	//Create

	public Conference create(){

		Conference result = new Conference();

		Administrator principal = (Administrator) this.actorService.findByPrincipal();

		result.setAdministrator(principal);
		result.setIsDraft(true);

		return result;

	}

	//Save

	public Conference save(Conference conference){

		Conference result;

		Administrator principal = (Administrator) this.actorService.findByPrincipal();

		if(conference.getId() == 0){

			//Check owner
			Assert.isTrue(conference.getAdministrator().getId() == principal.getId(), "no.permission");

			//Check dates

			Assert.isTrue(conference.getSubmissionDeadline().before(conference.getNotificationDeadline()),
					"submission.before.notification");

			Assert.isTrue(conference.getNotificationDeadline().before(conference.getCameraReadyDeadline()),
					"notification.before.camera");

			Assert.isTrue(conference.getCameraReadyDeadline().before(conference.getStartDate()),
					"camera.before.start");

			Assert.isTrue(conference.getStartDate().before(conference.getEndDate()), 
					"start.before.end");
			
			int daysdiff = 0;
		    long diff = conference.getEndDate().getTime() - conference.getStartDate().getTime();
		    long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
		    daysdiff = (int) diffDays;
		    
		    conference.setDurationDays(daysdiff);

			result = this.conferenceRepository.save(conference);

		}else{

			Conference db = this.conferenceRepository.findOne(conference.getId());

			//Checking can edit this conference
			Assert.isTrue(db.getAdministrator().getId() == principal.getId(),"no.permission");
			Assert.isTrue(db.getIsDraft() == true,"no.final");
			
			if(!db.getActivities().isEmpty()){
				conference.setActivities(db.getActivities());
			}
			
			//Check dates

			Assert.isTrue(conference.getSubmissionDeadline().before(conference.getNotificationDeadline()),
					"submission.before.notification");

			Assert.isTrue(conference.getNotificationDeadline().before(conference.getCameraReadyDeadline()),
					"notification.before.camera");

			Assert.isTrue(conference.getCameraReadyDeadline().before(conference.getStartDate()),
					"camera.before.start");

			Assert.isTrue(conference.getStartDate().before(conference.getEndDate()), 
					"start.before.end");
			
			int daysdiff = 0;
		    long diff = conference.getEndDate().getTime() - conference.getStartDate().getTime();
		    long diffDays = diff / (24 * 60 * 60 * 1000) + 1;
		    daysdiff = (int) diffDays;
		    
		    conference.setDurationDays(daysdiff);
			
			result = this.conferenceRepository.save(conference);
		}

		return result;

	}
	
	//Other methods ------------------------
	
	public Conference findOne(int id){
		Conference result = this.conferenceRepository.findOne(id);
		
		return result;
	}
	
	public Collection<Conference> findAll(){
		Collection<Conference> result = this.conferenceRepository.findAll();
		
		return result;
	}
	
	public Collection<Conference> getPast(){
		Collection<Conference> result = this.conferenceRepository.getPast();
		
		return result;
	}
	
	public Collection<Conference> getForthcoming(){
		Collection<Conference> result = this.conferenceRepository.getForthcoming();
		
		return result;
	}
	
	public Collection<Conference> getRunning(){
		Collection<Conference> result = this.conferenceRepository.getRunning();
		
		return result;
	}
	
	public Collection<Conference> getSubmissionLast5(){
		Collection<Conference> result = this.conferenceRepository.getSubmissionLast5();
		
		return result;
	}
	
	public Collection<Conference> getNotificationLess5(){
		Collection<Conference> result = this.conferenceRepository.getNotificationLess5();
		
		return result;
	}
	
	public Collection<Conference> getCameraLess5(){
		Collection<Conference> result = this.conferenceRepository.getCameraLess5();
		
		return result;
	}
	
	public Collection<Conference> getStartLess5(){
		Collection<Conference> result = this.conferenceRepository.getStartLess5();
		
		return result;
	}
	
}
