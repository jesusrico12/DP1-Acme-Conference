package domain;


import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Report extends DomainEntity {
	
	private Double originalityScore;
	private Double qualityScore;
	private Double readabilityScore;
	private String decision;
	private List<String> comments;
	private Reviewer reviewer;
	

	@Range(min=(long) 0.0,max=10,message="0.0-10.0")
	@NotNull
	@Digits(integer=10, fraction=2,message="Decimal")
	public Double getOriginalityScore() {
		return originalityScore;
	}
	public void setOriginalityScore(Double originalityScore) {
		this.originalityScore = originalityScore;
	}
	
	@NotNull
	@Range(min=(long) 0.0,max=10,message="0.0-10.0")
	@Digits(integer=10, fraction=2,message="Decimal")
	public Double getQualityScore() {
		return qualityScore;
	}
	public void setQualityScore(Double qualityScore) {
		this.qualityScore = qualityScore;
	}

	@NotNull
	@Range(min=(long) 0.0,max=10,message="0.0-10.0")
	@Digits(integer=10, fraction=2,message="Decimal")
	public Double getReadabilityScore() {
		return readabilityScore;
	}
	public void setReadabilityScore(Double readabilityScore) {
		this.readabilityScore = readabilityScore;
	}
	@NotBlank
	@Pattern(regexp = "\\b(REJECT|ACCEPT|BORDER-LINE)\\b")
	public String getDecision() {
		return decision;
	}
	public void setDecision(String decision) {
		this.decision = decision;
	}
	@NotEmpty
	@ElementCollection
	public List<String> getComments() {
		return comments;
	}
	public void setComments(List<String> comments) {
		this.comments = comments;
	}
	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Reviewer getReviewer() {
		return reviewer;
	}
	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}
	
	
	
	
	
	
	

}
