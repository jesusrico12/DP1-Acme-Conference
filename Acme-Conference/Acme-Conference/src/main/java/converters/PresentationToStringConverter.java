package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Presentation;

	@Component
	@Transactional
	public class PresentationToStringConverter implements Converter<Presentation, String> {

		@Override
		public String convert(final Presentation actor) {
			String result;

			if (actor == null)
				result = null;
			else
				result = String.valueOf(actor.getId());
			return result;
		}
}
