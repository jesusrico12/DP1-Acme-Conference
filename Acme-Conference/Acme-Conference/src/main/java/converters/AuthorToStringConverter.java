package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import domain.Author;

	@Component
	@Transactional
	public class AuthorToStringConverter implements Converter<Author, String> {

		@Override
		public String convert(final Author actor) {
			String result;

			if (actor == null)
				result = null;
			else
				result = String.valueOf(actor.getId());
			return result;
		}
}