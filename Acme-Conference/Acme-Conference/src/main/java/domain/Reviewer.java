package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;

@Entity
@Access(AccessType.PROPERTY)
public class Reviewer extends Actor {

	private List<String> keywords;
	private String email;
	
	@ElementCollection
	@NotEmpty
	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}


	@NotBlank
	@SafeHtml
	@Pattern(regexp = "^([A-z0-9 ]+[ ]<[A-z0-9]+@([A-z0-9]+\\.{0,1})+[A-z0-9]+>|[A-z0-9]+@([A-z0-9]+\\.{0,1})+[A-z0-9]+)$")
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	
	

}
