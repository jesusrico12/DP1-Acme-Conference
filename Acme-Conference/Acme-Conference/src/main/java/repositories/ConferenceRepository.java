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
	
	@Query("select c from Conference c where DATEDIFF(CURRENT_DATE, c.submissionDeadline)< 5 and DATEDIFF(CURRENT_DATE, c.submissionDeadline) > 0")
	Collection<Conference> getSubmissionLast5();
	
	@Query("select c from Conference c where DATEDIFF(c.notificationDeadline, CURRENT_DATE) < 5 and DATEDIFF(CURRENT_DATE, c.notificationDeadline) < 0")
	Collection<Conference> getNotificationLess5();
	
	@Query("select c from Conference c where DATEDIFF(c.cameraReadyDeadline, CURRENT_DATE) < 5 and DATEDIFF(CURRENT_DATE, c.cameraReadyDeadline) < 0")
	Collection<Conference> getCameraLess5();
	
	@Query("select c from Conference c where DATEDIFF(c.startDate, CURRENT_DATE) < 5 and DATEDIFF(CURRENT_DATE, c.startDate) < 0 ")
	Collection<Conference> getStartLess5();
	

	@Query("select c from Conference c where c.submissionDeadline >= CURRENT_DATE and c.isDraft = false")
	Collection<Conference> getToMakeSubmission();

	@Query("select c from Conference c join c.activities a where a.id= ?1")
	Conference ConferenceOwn(int activityId);
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
	@Query("select c from Conference c where c.isDraft = 0")
	Collection<Conference> conferencesFinals();
	@Query("select c from Conference c where c.submissionDeadline < CURRENT_DATE and c.notificationDeadline > CURRENT_DATE")
	Collection<Conference> conferencesBetweenSubDeadlineNotifDeadline();
	
	@Query("select c from Conference c where c.submissionDeadline < CURRENT_DATE and c.notificationDeadline > CURRENT_DATE and c.isDraft = 'false' and c.administrator.id = ?1")
	Collection<Conference> conferencesToAutoAssign(int id);
	
	
	@Query("select c from Conference c where c.startDate > CURRENT_DATE")
	Collection<Conference> conferencesNotStarted();
	
	@Query("select c from Conference c where c.administrator.id = ?1")
	Collection<Conference> adminConferences(int adminId);


}
