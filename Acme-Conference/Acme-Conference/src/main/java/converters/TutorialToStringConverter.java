package converters;

import org.springframework.core.convert.converter.Converter;

import domain.Tutorial;


	public class TutorialToStringConverter implements Converter<Tutorial, String> {
		@Override
		public String convert(final Tutorial actor) {
			String result;

			if (actor == null)
				result = null;
			else
				result = String.valueOf(actor.getId());
			return result;
		}
}
