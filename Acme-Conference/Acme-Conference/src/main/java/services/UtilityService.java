package services;

import java.security.SecureRandom;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Author;
import domain.Submission;

@Service
@Transactional
public class UtilityService {

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private SubmissionService submissionService;

	/**
	 * 
	 * Checks if the date of the credit card is expired
	 * 
	 * @param expirationMonth
	 * @param expirationYear
	 * 
	 * @return boolean
	 * @throws ParseException
	 **/


	/* [+]\d{3} */
	public boolean checkCC(String cc) {
		final Pattern pattern = Pattern.compile("([+]\\d{3})");
		final Matcher matcher = pattern.matcher(cc);

		return (matcher.matches() ? true : false);
	}

	/*
	 *  The ticker is a string of the form “ABC-XXXX”, where “ABC”
refers to the initials of the author who is making the submission and “XXXX” are four random
uppercase letters and/or numbers; in cases in which an author does not have a middlename,
the corresponding initial is “X”*/


	
	public String getTicker(){
		String res;
		Author principal = (Author) this.actorService.findByPrincipal();
		String A=principal.getName().trim().substring(0, 1);
		String B;
		if(principal.getMiddleName()!=null){
			 B=principal.getMiddleName().trim().substring(0, 1);	
		}else{
			 B="X";
		}
		
		String C=principal.getSurname().trim().substring(0, 1);
		
		res=A+B+C+"-"+this.randomString();
		
		
		return res;
			
	}
	
	public String randomString() {

		final String possibleChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234566789";
		final SecureRandom rnd = new SecureRandom();
		final int length = 4;

		final StringBuilder stringBuilder = new StringBuilder(length);

		for (int i = 0; i < length; i++)
			stringBuilder.append(possibleChars.charAt(rnd.nextInt(possibleChars.length())));
		return stringBuilder.toString();

	}
	
}
