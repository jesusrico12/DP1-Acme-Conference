package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Access(AccessType.PROPERTY)
public class Author extends Actor {

	
	private String email;

	@NotBlank
	@Pattern(regexp = "^([A-z0-9 ]+[ ]<[A-z0-9]+@([A-z0-9]+\\.{0,1})+[A-z0-9]+>|[A-z0-9]+@([A-z0-9]+\\.{0,1})+[A-z0-9]+)$")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
