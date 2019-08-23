
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Paper;

@Repository
public interface PaperRepository extends JpaRepository<Paper, Integer>{
	
//	@Query("select p from Paper p where p.isCameraReady= '1'")
	@Query("select s.paper from Submission s join s.conference c  where s.paper.isCameraReady = 1 and c.id= ?1")
	Collection<Paper> paperReadys(int conferenceId);

}

