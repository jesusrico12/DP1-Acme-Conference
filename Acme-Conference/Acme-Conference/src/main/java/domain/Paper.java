package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Paper extends DomainEntity{

	private String title;
	private Boolean isCameraReady;
	private List<String> authors;
	private String summary;
	private String document;
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Boolean getIsCameraReady() {
		return isCameraReady;
	}
	public void setIsCameraReady(Boolean isCameraReady) {
		this.isCameraReady = isCameraReady;
	}
	@NotEmpty
	@ElementCollection
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	@NotBlank
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@URL
	@NotBlank
	public String getDocument() {
		return document;
	}
	public void setDocument(String document) {
		this.document = document;
	}
	
	
	
	
	
	
}
