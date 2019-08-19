package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Registration;


	@Component
	@Transactional
	public class RegistrationToStringConverter implements Converter<Registration, String> {

		@Override
		public String convert(final Registration actor) {
			String result;

			if (actor == null)
				result = null;
			else
				result = String.valueOf(actor.getId());
			return result;
		}
}
