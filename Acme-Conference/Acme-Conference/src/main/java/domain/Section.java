package domain;

import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Section  extends DomainEntity {

	private String title;
	private String summary ;
	private List<String> pictures;
	
	
	@NotBlank
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@NotBlank
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@ElementCollection
	public List<String> getPictures() {
		return pictures;
	}
	public void setPictures(List<String> pictures) {
		this.pictures = pictures;
	}
	
	
	
	
}
