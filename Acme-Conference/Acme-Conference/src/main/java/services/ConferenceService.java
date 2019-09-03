package services;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConferenceRepository;
import domain.Activity;
import domain.Actor;
import domain.Administrator;
import domain.Conference;
import domain.Report;
import domain.Submission;


@Transactional
@Service
public class ConferenceService {

	//Repository ------------

	@Autowired
	private ConferenceRepository conferenceRepository;

	//Services --------------

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private SubmissionService submissionService;

	
	@Autowired
	private MessageService messageService;

	//Create

	public Conference create(){

		Conference result = new Conference();

		Actor principal = this.actorService.findByPrincipal();
		
		Collection<Activity> actis= new LinkedList<>();
		result.setActivities(actis);

		result.setAdministrator((Administrator)principal);
		result.setIsDraft(true);

		return result;

	}

	//Save

	public Conference saveNormal(Conference conference){

		Conference result;

		Administrator principal = (Administrator) this.actorService.findByPrincipal();

		if(conference.getId() == 0){

			//Check owner
			Assert.isTrue(conference.getAdministrator().getId() == principal.getId(), "no.permission");

			//Check dates
			
			Assert.isTrue(conference.getSubmissionDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getNotificationDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getCameraReadyDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getStartDate().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getEndDate().after(new Date(System.currentTimeMillis()-1)), "past.date");

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
			Assert.isTrue(conference.getAdministrator().getId() == principal.getId(), "no.permission");
			Assert.isTrue(db.getIsDraft() == true,"no.final");
			
			if(!db.getActivities().isEmpty()){
				conference.setActivities(db.getActivities());
			}
			
			//Check dates
			
			Assert.isTrue(conference.getSubmissionDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getNotificationDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getCameraReadyDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getStartDate().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getEndDate().after(new Date(System.currentTimeMillis()-1)), "past.date");
			
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
	
	
	
	public Conference saveFinal(Conference conference){

		Conference result;

		Administrator principal = (Administrator) this.actorService.findByPrincipal();

		if(conference.getId() == 0){

			//Check owner
			Assert.isTrue(conference.getAdministrator().getId() == principal.getId(), "no.permission");

			//Check dates
			
			Assert.isTrue(conference.getSubmissionDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getNotificationDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getCameraReadyDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getStartDate().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getEndDate().after(new Date(System.currentTimeMillis()-1)), "past.date");

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
		    conference.setIsDraft(false);
		    conference.setDurationDays(daysdiff);

			result = this.conferenceRepository.save(conference);

		}else{

			Conference db = this.conferenceRepository.findOne(conference.getId());
			
			//Checking can edit this conference
			Assert.isTrue(conference.getAdministrator().getId() == principal.getId(), "no.permission");
			Assert.isTrue(db.getIsDraft() == true,"no.final");
			
			if(!db.getActivities().isEmpty()){
				conference.setActivities(db.getActivities());
			}
			
			//Check dates
			
			Assert.isTrue(conference.getSubmissionDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getNotificationDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getCameraReadyDeadline().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getStartDate().after(new Date(System.currentTimeMillis()-1)), "past.date");
			Assert.isTrue(conference.getEndDate().after(new Date(System.currentTimeMillis()-1)), "past.date");
			
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
		    conference.setIsDraft(false);
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
	public 	Conference ConferenceOwn(int activityId){
		Conference cof=this.conferenceRepository.ConferenceOwn(activityId);
		return cof;
	}

	public Conference saveForce(Conference conference) {
		
		return this.conferenceRepository.save(conference);
		
	}
	public Collection<Conference> conferencesFinder(String keyword){
		
		Collection<Conference> res= this.conferenceRepository.conferencesFinder(keyword);
		return res;
	}
	public  Double[] submissionsPerConference(){
		return this.conferenceRepository.submissionsPerConference();
	}
	
	
	public Double[] registrationsPerConference(){
		return this.conferenceRepository.registrationsPerConference();
	}
	
	
	public Double[] feesPerConference(){
		return this.conferenceRepository.feesPerConference();
	}
	
	
	public Double[] numberOfDaysPerConference(){
		return this.conferenceRepository.numberOfDaysPerConference();
	}
	
	public Collection<Conference> conferencesFinals(){
		return this.conferenceRepository.conferencesFinals();
	}
	
	
	public void decisionMaking (int conferenceId){
		Collection<Submission> subs=this.submissionService.submissionsPerConferenceDecisionMaking(conferenceId);
		
		for (Submission s:subs){
			int aceptado=0;
			int rechazado=0;
			
			for(Report r : s.getReports()){
				
				if(r.getDecision().toString().equals("ACCEPT")){
					aceptado++;
				}else if(r.getDecision().toString().equals("REJECT")){
					rechazado++;
				}			
				
			}	
			if(rechazado>aceptado){
				s.setStatus("REJECTED");
			}else{
				s.setStatus("ACCEPTED");
			}
		
			this.submissionService.saveForce(s);
			
			this.messageService.notificateAuthors(s);
			
			
		}
		
	
	}
	
	public Collection<Conference> conferencesBetweenSubDeadlineNotifDeadline(){
		return this.conferenceRepository.conferencesBetweenSubDeadlineNotifDeadline();
	}
	public Collection<Conference> conferencesNotStarted(){
		return this.conferenceRepository.conferencesNotStarted();
	}
	
	public Collection<Conference> getToMakeSubmission(){
		Collection<Conference> result = this.conferenceRepository.getToMakeSubmission();
		
		return result;
	}
	

	public Collection<Conference> getAdminConferences(int id){
		Collection<Conference> result = this.conferenceRepository.adminConferences(id);
		
		return result;
	}
	
	public Collection<Conference> getToAutoAssign(int id){
		Collection<Conference> result = this.conferenceRepository.conferencesToAutoAssign(id);
		
		return result;
	}

	
}
