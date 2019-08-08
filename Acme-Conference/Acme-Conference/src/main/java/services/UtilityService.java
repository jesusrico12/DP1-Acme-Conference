package services;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Author;

@Service
@Transactional
public class UtilityService {

	@Autowired
	private ActorService actorService;

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

		Author principal = (Author) this.actorService.findByPrincipal();
		String name = principal.getName().substring(0, 1);
		String surname = principal.getSurname().substring(0, 1);
		String middleName;

		if(principal.getMiddleName() != null){
			middleName = principal.getMiddleName().substring(0, 1);
		}else{
			middleName = "X";
		}

		String ticker = name+surname+middleName;

		



		
		
		return ticker;

	}
	
}
