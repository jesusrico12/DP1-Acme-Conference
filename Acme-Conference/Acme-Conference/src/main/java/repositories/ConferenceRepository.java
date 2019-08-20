package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;

@Repository
public interface ConferenceRepository  extends
JpaRepository<Conference, Integer>{

	@Query("select c from Conference c where (c.startDate > CURRENT_DATE) and (c.isDraft = false)")
	Collection<Conference> getForthcoming();

	@Query("select c from Conference c where (c.endDate < CURRENT_DATE) and (c.isDraft = false)")
	Collection<Conference> getPast();

	@Query("select c from Conference c where (CURRENT_DATE between c.startDate and c.endDate) and (c.isDraft = false)")
	Collection<Conference> getRunning();
	
	@Query("select c from Conference c where (datediff(c.submissionDeadline, CURRENT_DATE) <= 5) and (c.submissionDeadline <= CURRENT_DATE)")
	Collection<Conference> getSubmissionLast5();
	
	@Query("select c from Conference c where (datediff(c.notificationDeadline, CURRENT_DATE) <= 5) and (c.submissionDeadline >= CURRENT_DATE)")
	Collection<Conference> getNotificationLess5();
	
	@Query("select c from Conference c where (datediff(c.cameraReadyDeadline, CURRENT_DATE) <= 5) and (c.cameraReadyDeadline >= CURRENT_DATE)")
	Collection<Conference> getCameraLess5();
	
	@Query("select c from Conference c where (datediff(c.startDate, CURRENT_DATE) <= 5) and (c.startDate >= CURRENT_DATE)")
	Collection<Conference> getStartLess5();
	
	@Query("select c from Conference c where c.submissionDeadline >= CURRENT_DATE and c.isDraft = false")
	Collection<Conference> getToMakeSubmission();
}
