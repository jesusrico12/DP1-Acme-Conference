package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Activity extends DomainEntity{
	
		
		private String title;
		private List<String> speakers;
		private Date startMoment;
		private Integer duration;
		private String room ;
		private String summary;
		private List<String> attachments;
		
		
		@NotBlank
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		@NotEmpty
		@ElementCollection(targetClass = String.class)
		public List<String> getSpeakers() {
			return speakers;
		}
		public void setSpeakers(List<String> speakers) {
			this.speakers = speakers;
		}
		@NotNull
		@Temporal(TemporalType.TIMESTAMP)
		@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
		public Date getStartMoment() {
			return startMoment;
		}
		public void setStartMoment(Date startMoment) {
			this.startMoment = startMoment;
		}
		@Range(min=1,message="Min. 1")
		public Integer getDuration() {
			return duration;
		}
		public void setDuration(Integer duration) {
			this.duration = duration;
		}
		@NotBlank
		public String getRoom() {
			return room;
		}
		public void setRoom(String room) {
			this.room = room;
		}
		@NotBlank
		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		
		@ElementCollection
		public List<String> getAttachments() {
			return attachments;
		}
		public void setAttachments(List<String> attachments) {
			this.attachments = attachments;
		}
		
		
		
}
