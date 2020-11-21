package marcusvoltolim.example.enum_generic_converter;

import marcusvoltolim.example.enum_generic_converter.enums.EnumsConverters;
import marcusvoltolim.example.enum_generic_converter.enums.UserType;
import marcusvoltolim.example.enum_generic_converter.model.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static marcusvoltolim.example.enum_generic_converter.enums.UserType.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class EnumGenericConverterTest {

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	void usingEnumConverterGeneric() {
		var id = testEntityManager.persistAndGetId(new User(ADMIN));
		var typeBd = testEntityManager.getEntityManager()
									  .createNativeQuery("SELECT user_type_converter FROM User WHERE id = :id")
									  .setParameter("id", id)
									  .getSingleResult();

		assertThat(typeBd)
			.isEqualTo(ADMIN.getId())
			.isNotEqualTo(ADMIN.ordinal())
			.isNotEqualTo(ADMIN.name());
	}

	@Test
	void usingOrdinalValue() {
		var id = testEntityManager.persist(new User(ADMIN));
		var typeBd = testEntityManager.getEntityManager()
									  .createNativeQuery("SELECT user_type_ordinal FROM User WHERE id = :id")
									  .setParameter("id", id)
									  .getSingleResult();

		assertThat(typeBd)
			.isNotEqualTo(ADMIN.getId())
			.isEqualTo(ADMIN.ordinal())
			.isNotEqualTo(ADMIN.name());

	}

	@Test
	void usingStringValue() {
		var id = testEntityManager.persistAndGetId(new User(ADMIN));
		var typeBd = testEntityManager.getEntityManager()
									  .createNativeQuery("SELECT user_type_string FROM User WHERE id = :id")
									  .setParameter("id", id)
									  .getSingleResult();

		assertThat(typeBd)
			.isNotEqualTo(ADMIN.getId())
			.isNotEqualTo(ADMIN.ordinal())
			.isEqualTo(ADMIN.name());
	}

	@Test
	void nullValue() {
		assertNull(EnumsConverters.AbstractPersistableEnumConverter.fromId(null, UserType.values()));
	}

	@Test
	void values() {
		var values = UserType.values();
		Arrays.stream(values).forEach(value -> assertEquals(value, EnumsConverters.AbstractPersistableEnumConverter.fromId(value.getId(), values)));
	}

}