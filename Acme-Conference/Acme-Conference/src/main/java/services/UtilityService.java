package services;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
@Transactional
public class UtilityService {

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
}
