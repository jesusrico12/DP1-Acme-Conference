package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Section;

	@Component
	@Transactional
	public class SectionTostringConverter implements Converter<Section, String> {

		@Override
		public String convert(final Section actor) {
			String result;

			if (actor == null)
				result = null;
			else
				result = String.valueOf(actor.getId());
			return result;
		}
}
