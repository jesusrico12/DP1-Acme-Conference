package converters;


import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Activity;

@Component
@Transactional
public class ActivityToStringConverter implements Converter<Activity, String> {

	
	@Override
	public String convert(final Activity actor) {
		String result;

		if (actor == null)
			result = null;
		else
			result = String.valueOf(actor.getId());
		return result;
	}
}
