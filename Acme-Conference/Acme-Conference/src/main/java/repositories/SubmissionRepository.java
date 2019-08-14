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
	
	
}
