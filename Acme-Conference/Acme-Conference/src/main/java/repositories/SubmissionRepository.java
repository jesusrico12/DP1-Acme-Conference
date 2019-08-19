package repositories;



import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import domain.Submission;

@Repository
public interface SubmissionRepository extends
JpaRepository<Submission, Integer>{
	
	

	//@Query(" select s from Submission s where s.status like 'ACCEPTED'")
	//Collection<Submission> submissionAccepted();
	
	@Query("select s from Submission s join s.conference c join s.reviewers r where r.id= ?1 and c.notificationDeadline > CURRENT_DATE and c.submissionDeadline < CURRENT_DATE")
	Collection<Submission> submissionsOfReviewer(int reviewerId);
	
	@Query("select s from Submission s join s.conference c where c.notificationDeadline < CURRENT_DATE and s.author.id = ?1")
	Collection<Submission> submissionsToAuthorAfterNotification(int authorId);

	
	@Query("select s from Submission s join s.conference c where c.notificationDeadline > CURRENT_DATE and c.submissionDeadline < CURRENT_DATE and s.conference.id = ?1")
	Collection<Submission> submissionsPerConferenceDecisionMaking(int conferenceId);
	
	@Query("select s from Submission s join s.conference  c join s.reports r where c.notificationDeadline > CURRENT_DATE and c.submissionDeadline < CURRENT_DATE and  r.id= ?1")
	Submission reportTimeToComment(int reportId);
}
