package marcusvoltolim.example.enum_generic_converter.enums;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import lombok.NonNull;

@SuppressWarnings("unused")
public interface EnumsConverters {

	abstract class AbstractPersistableEnumConverter<E extends Enum<E> & PersistableEnum<I>, I> implements AttributeConverter<E, I> {

		private final E[] enumConstants;

		public AbstractPersistableEnumConverter(@NonNull Class<E> enumType) {
			enumConstants = enumType.getEnumConstants();
		}

		@Override
		public I convertToDatabaseColumn(E attribute) {
			return Objects.isNull(attribute) ? null : attribute.getId();
		}

		@Override
		public E convertToEntityAttribute(I dbData) {
			return fromId(dbData, enumConstants);
		}

		public static <E extends Enum<E> & PersistableEnum<I>, I> E fromId(I idValue, E[] enumConstants) {
			return Optional.ofNullable(idValue).flatMap(id -> Stream.of(enumConstants)
																	.filter(e -> e.getId().equals(id))
																	.findAny()).orElse(null);
		}

	}

	@Converter(autoApply = true)
	class UserTypeConverter extends AbstractPersistableEnumConverter<UserType, Integer> {

		public UserTypeConverter() {
			super(UserType.class);
		}

	}

	class GenderTypeConverter extends AbstractPersistableEnumConverter<GenderType, Integer> {

		public GenderTypeConverter() {
			super(GenderType.class);
		}

	}

}
