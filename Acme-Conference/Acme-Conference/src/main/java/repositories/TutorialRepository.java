package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import domain.Tutorial;
@Repository
public interface TutorialRepository  extends
JpaRepository<Tutorial, Integer>  {

	@Query("select t from Tutorial t join t.sections a where a.id= ?1")
	public Tutorial TutorialOwn(int sectionId);

}
