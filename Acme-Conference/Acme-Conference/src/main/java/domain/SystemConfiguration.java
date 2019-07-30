package domain;

import java.util.Map;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SystemConfiguration extends DomainEntity {

	/* Attributes */

	private String systemName;
	private Map<String, String> welcomeMessage;
	private String banner;
	private String countryCode;
	private String creditCardMakes;
	private Map<String,String> topics;
	
	


	/* Getters&Setters */



	@NotBlank
	public String getSystemName() {
		return this.systemName;
	}

	public void setSystemName(final String systemName) {
		this.systemName = systemName;
	}


	@NotEmpty
	@ElementCollection
	public Map<String, String> getWelcomeMessage() {
		return this.welcomeMessage;
	}

	public void setWelcomeMessage(final Map<String, String> welcomeMessage) {
		this.welcomeMessage = welcomeMessage;
	}

	@URL
	@NotBlank
	public String getBanner() {
		return this.banner;
	}

	public void setBanner(final String banner) {
		this.banner = banner;
	}

	@NotBlank
	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(final String countryCode) {
		this.countryCode = countryCode;
	}
	@NotBlank
	public String getCreditCardMakes() {
		return creditCardMakes;
	}
	
	public void setCreditCardMakes(String creditCardMakes) {
		this.creditCardMakes = creditCardMakes;
	}
	@NotEmpty
	@ElementCollection
	public Map<String, String> getTopics() {
		return topics;
	}

	public void setTopics(Map<String, String> topics) {
		this.topics = topics;
	}

	

}