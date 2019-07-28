package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Message extends DomainEntity{
	
	private Date sendMoment;
	private String subject;
	private String body;
	private String topic;
	private Actor receiver;
	private Actor sender;
	
	@Past
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getSendMoment() {
		return sendMoment;
	}
	public void sedSentMoment(Date sendMoment) {
		this.sendMoment = sendMoment;
	}
	@NotBlank
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	@NotBlank
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@NotBlank
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getReceiver() {
		return receiver;
	}
	public void setReceiver(Actor receiver) {
		this.receiver = receiver;
	}
	
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getSender() {
		return sender;
	}
	public void setSender(Actor sender) {
		this.sender = sender;
	}
	

}
