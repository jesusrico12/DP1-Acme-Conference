package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Submission extends DomainEntity{

	private String ticker;
	private Date madeMoment;
	private String status;
	private Boolean toReview;
	private Boolean toAuthor;
	private Paper paper;
	private Author author;
	private Conference conference;
	private Collection<Reviewer> reviewers;
	private Collection<Report> reports;
	
	@NotBlank
	@Column(unique = true)
	//@Pattern(regexp = "^([A-Z]){3}-([A-Z|0-9]){4}")
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMadeMoment() {
		return madeMoment;
	}
	public void setMadeMoment(Date madeMoment) {
		this.madeMoment = madeMoment;
	}
	@NotBlank
	@Pattern(regexp = "\\b(UNDER-REVIEW|ACCEPTED|REJECTED)\\b")
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Boolean getToReview() {
		return toReview;
	}
	public void setToReview(Boolean toReview) {
		this.toReview = toReview;
	}
	@Valid
	@OneToOne
	@NotNull
	public Paper getPaper() {
		return paper;
	}
	public void setPaper(Paper paper) {
		this.paper = paper;
	}
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	@Valid
	@NotNull
	@ManyToOne(optional=false)
	public Conference getConference() {
		return conference;
	}
	public void setConference(Conference conference) {
		this.conference = conference;
	}
	@Valid
	@ElementCollection
	@ManyToMany
	public Collection<Reviewer> getReviewers() {
		return reviewers;
	}
	public void setReviewers(Collection<Reviewer> reviewers) {
		this.reviewers = reviewers;
	}
	@Valid
	@ElementCollection
	@OneToMany
	public Collection<Report> getReports() {
		return reports;
	}
	public void setReports(Collection<Report> reports) {
		this.reports = reports;
	}
	
	public Boolean getToAuthor() {
		return toAuthor;
	}
	public void setToAuthor(Boolean toAuthor) {
		this.toAuthor = toAuthor;
	}
	
	
	
}
