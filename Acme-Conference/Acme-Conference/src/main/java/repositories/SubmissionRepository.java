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
	
	@Query("select s from Submission s where (s.toReview = true and (s.status = 'ACCEPTED') and (s.paper.isCameraReady = false) and (s.conference.cameraReadyDeadline >= CURRENT_DATE))")
	Collection<Submission> getSubmissionToUpload();
	

}
