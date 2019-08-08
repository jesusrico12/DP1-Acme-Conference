package converters;


import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Conference;

@Component
@Transactional
public class ConferenceToStringConverter implements Converter<Conference, String> {

	
	@Override
	public String convert(final Conference actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());
		return result;
	}
}
