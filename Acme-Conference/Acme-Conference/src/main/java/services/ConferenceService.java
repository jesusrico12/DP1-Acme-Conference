package services;

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
			Assert.isTrue(conference.getAdministrator().getId() == principal.getId(), "Not your conference");

			//Check dates

			Assert.isTrue(conference.getSubmissionDeadline().before(conference.getNotiticationDeadline()),
					"Submission deadline must be before notification deadline");

			Assert.isTrue(conference.getNotiticationDeadline().before(conference.getCameraReadyDeadline()),
					"Notification deadline must be before camera ready deadline");

			Assert.isTrue(conference.getCameraReadyDeadline().before(conference.getStartDate()),
					"Camera ready deadline must be before start date");

			Assert.isTrue(conference.getStartDate().before(conference.getEndDate()), 
					"Start date must be before end date");


			result = this.conferenceRepository.save(conference);

		}else{

			Conference db = this.conferenceRepository.findOne(conference.getId());

			//Checking can edit this conference
			Assert.isTrue(db.getAdministrator().getId() == principal.getId(),"Not your conference");
			Assert.isTrue(db.getIsDraft() == true,"Conference in final mode");

			//Check dates

			Assert.isTrue(conference.getSubmissionDeadline().before(conference.getNotiticationDeadline()),
					"Submission deadline must be before notification deadline");

			Assert.isTrue(conference.getNotiticationDeadline().before(conference.getCameraReadyDeadline()),
					"Notification deadline must be before camera ready deadline");

			Assert.isTrue(conference.getCameraReadyDeadline().before(conference.getStartDate()),
					"Camera ready deadline must be before start date");

			Assert.isTrue(conference.getStartDate().before(conference.getEndDate()), 
					"Start date must be before end date");


			result = this.conferenceRepository.save(conference);
		}

		return result;

	}
	
	//Other methods ------------------------
	
	
}
