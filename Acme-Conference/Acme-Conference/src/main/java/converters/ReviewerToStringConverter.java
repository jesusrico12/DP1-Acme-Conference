package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Reviewer;

	@Component
	@Transactional
	public class ReviewerToStringConverter implements Converter<Reviewer, String> {

		@Override
		public String convert(final Reviewer actor) {
			String result;

			if (actor == null)
				result = null;
			else
				result = String.valueOf(actor.getId());
			return result;
		}
}
