package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Paper;


	@Component
	@Transactional
	public class PaperToStringConverter implements Converter<Paper, String> {

		@Override
		public String convert(final Paper actor) {
			String result;

			if (actor == null)
				result = null;
			else
				result = String.valueOf(actor.getId());
			return result;
		}
}
