package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Conference;
import domain.Panel;

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
	
	@Query("select c from Conference c join c.activities a where a.id= ?1")
	Conference ConferenceOwn(int panelId);
	@Query("select c from Conference c where c.isDraft = 0 and ( c.title like %?1% or c.venue like %?1% or c.summary like %?1% )")
	Collection<Conference> conferencesFinder(String keyword);
	
	@Query("select max(1.0*(select count(*) from Submission c where c.conference=f)),min(1.0*(select count(*) from Submission c where c.conference=f)),avg(1.0*(select count(*) from Submission c where c.conference=f)),stddev(1.0*(select count(*) from Submission c where c.conference=f)) from Conference f")
	Double[] submissionsPerConference();
	
	@Query("select max(1.0*(select count(*) from Registration c where c.conference=f)),min(1.0*(select count(*) from Registration c where c.conference=f)),avg(1.0*(select count(*) from Registration c where c.conference=f)),stddev(1.0*(select count(*) from Registration c where c.conference=f)) from Conference f")
	Double[] registrationsPerConference();
	@Query("select max(c.fee),min(c.fee),avg(c.fee),stddev(c.fee) from Conference c")
	Double[] feesPerConference();
	@Query("select max(c.durationDays),min(c.durationDays),avg(c.durationDays),stddev(c.durationDays) from Conference c")
	Double[] numberOfDaysPerConference();
	
	
}
