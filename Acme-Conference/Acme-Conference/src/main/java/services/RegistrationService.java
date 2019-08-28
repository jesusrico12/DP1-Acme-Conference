package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Author;
import domain.Conference;
import domain.Registration;

import java.text.ParseException;


import repositories.RegistrationRepository;

@Service
@Transactional
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	@Autowired
	private ActorService actorService;
	
	
	@Autowired
	private SystemConfigurationService configurationService;
	
	@Autowired
	private ConferenceService conferenceService;
	
	
	public Registration create(Conference conference){
		
		Registration res= new Registration();
		Actor principal = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(principal, "AUTHOR"),
				"not.allowed");
		Assert.isTrue(this.conferenceService.conferencesNotStarted().contains(conference),"commit.error");
		res.setAuthor((Author)principal);
		res.setConference(conference);
		return res;
		
		
	}
	public Registration save(Registration registration,Conference conference) throws ParseException{
		Actor principal = this.actorService.findByPrincipal();
		Registration res;
		Date currentMoment=new Date(System.currentTimeMillis() - 1);
		Assert.isTrue(this.actorService.checkAuthority(principal, "AUTHOR"),
				"not.allowed");
		Assert.isTrue(this.actorService.findByPrincipal()==registration.getAuthor(),"not.allowed");
		Assert.isTrue(currentMoment.before(conference.getStartDate()),"commit.error");
		Assert.isTrue(checkIfExpired(registration.getCreditCard().getExpirationMonth()
				,registration.getCreditCard().getExpirationYear())==false,"creditCard.expired");
		Assert.isTrue(this.configurationService.findMySystemConfiguration().getCreditCardMakes().contains(registration.getCreditCard().getMake()),"Make.incorrect");
		Assert.isTrue(this.conferenceService.conferencesNotStarted().contains(conference),"commit.error");
		
		res=this.registrationRepository.save(registration);
		
		return res;
		
	}
	
	
	
	public boolean checkIfExpired(Integer expirationMonth,
			Integer expirationYear) throws ParseException {

		boolean res = false;

		String expiration = ((expirationMonth.toString().length() == 1) ? "0"
				+ expirationMonth.toString() : expirationMonth.toString())
				+ ((expirationYear.toString().length() == 1) ? "0"
						+ expirationYear.toString() : expirationYear.toString());

		DateFormat date = new SimpleDateFormat("MMyy");

		Date expiryDate = date.parse(expiration);
		Date currentDate = new Date();

		if (currentDate.after(expiryDate))
			res = true;

		return res;
	}
	public Registration findOne(int registrationId) {
		
		return this.registrationRepository.findOne(registrationId);
	}
	public Collection<Registration> findAll(){
		return this.registrationRepository.findAll();
	}
	
	public Collection<Registration> registrationPerAuthor(int authorId){
		return this.registrationRepository.registrationPerAuthor(authorId);
	}
	
	public Collection<Registration> getRegistrationByConference(int id){
		return this.registrationRepository.getRegistrationByConference(id);
		
	}
	
	public Collection<Conference> conferencesInRegistration(int authorId){
		return this.registrationRepository.conferencesInRegistration(authorId);
	}
}

