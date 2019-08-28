package repositories;


import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Submission;

@Repository
public interface SubmissionRepository extends
JpaRepository<Submission, Integer>{
	
	
	@Query("select s from Submission s where s.conference.id = ?1 and s.status = 'ACCEPTED'")
	Collection<Submission> getAcceptedSubmissionByConference(int conferenceId);
	
	@Query("select s from Submission s where s.conference.id = ?1 and s.status = 'REJECT'")
	Collection<Submission> getRejectedSubmissionByConference(int conferenceId);
	
	@Query("select s from Submission s where s.conference.id = ?1 and s.status = 'BORDER-LINE'")
	Collection<Submission> getBorderSubmissionByConference(int conferenceId);
	
	@Query("select s from Submission s where s.conference.id = ?1 and s.status = 'UNDER-REVIEW'")
	Collection<Submission> getUnderSubmissionByConference(int conferenceId);
	
	
	@Query("select s from Submission s where s.author.id = ?1")
	Collection<Submission> getSubmissionByOwner(int id);
	
	@Query("select s from Submission s where (s.toReview = true and (s.status = 'ACCEPTED') and (s.paper.isCameraReady = false) and (s.conference.cameraReadyDeadline >= CURRENT_DATE) and (s.author.id = ?1))")
	Collection<Submission> getSubmissionToUpload(int authorId);
	
	
	@Query("select s from Submission s join s.conference c join s.reviewers r where r.id= ?1 and c.notificationDeadline > CURRENT_DATE and c.submissionDeadline < CURRENT_DATE and  s.toAuthor = 0 and s.status like 'UNDER-REVIEW'")
	Collection<Submission> submissionsOfReviewer(int reviewerId);
	
	@Query("select s from Submission s join s.conference c where c.notificationDeadline < CURRENT_DATE and s.author.id = ?1")
	Collection<Submission> submissionsToAuthorAfterNotification(int authorId);

	
	@Query("select s from Submission s join s.conference c where c.notificationDeadline > CURRENT_DATE and c.submissionDeadline < CURRENT_DATE and s.conference.id = ?1")
	Collection<Submission> submissionsPerConferenceDecisionMaking(int conferenceId);
	
	@Query("select s from Submission s join s.conference  c join s.reports r where c.notificationDeadline > CURRENT_DATE and c.submissionDeadline < CURRENT_DATE and  r.id= ?1 and s.toAuthor = 0")
	Submission reportTimeToComment(int reportId);
	@Query("select s from Submission s where s.ticker like '?1'")
	Submission isSubUnique(String res);
	
	@Query("select s from Submission s where s.conference.id = ?1 and s.status = 'UNDER-REVIEW'")
	Collection<Submission> getSubmissionToAssign(int conferenceId);
	
	@Query("select s from Submission s where s.toAuthor = 1 and s.author.id=?1")
	Collection<Submission> submissionsToAuthor(int authorId);
	
	
	@Query("select s from Submission s join s.reports r where s.toAuthor = 1 and r.id= ?1 and s.author.id= ?2")
	Submission SubDisplayReportAuthor(int  reportId,int authorId);
	
	
	@Query("select s from Submission s where s.conference.id = ?1")
	Collection<Submission> getSubmissionsByConference(int conferenceId);
}


