package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Presentation extends Activity {
	
	private Submission submission;

	@Valid
	@NotNull
	@OneToOne
	public Submission getSubmission() {
		return submission;
	}

	public void setSubmission(Submission submission) {
		this.submission = submission;
	}


	
	

}
