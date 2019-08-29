package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;
import domain.Registration;;

	@Repository
	public interface RegistrationRepository  extends
	JpaRepository<Registration, Integer>{

		
		
		@Query("select r from Registration r where r.author.id= ?1")
		Collection<Registration> registrationPerAuthor(int authorId);
		
		@Query("select r from Registration r where r.conference.id = ?1")
		Collection<Registration> getRegistrationByConference(int conferenceId);
		
		@Query("select r.conference from Registration r where r.author.id= ?1")
		Collection<Conference> conferencesInRegistration(int authorId);
		
		@Query("select r from Registration r where r.creditCard.number like ?1")
		Registration uniqueReg(String number);
		
	}


