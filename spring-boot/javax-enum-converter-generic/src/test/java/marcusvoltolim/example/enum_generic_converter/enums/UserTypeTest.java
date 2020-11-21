package marcusvoltolim.example.enum_generic_converter.enums;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTypeTest {

	@Test
	void idsUniques() {
		var values = UserType.values();
		assertEquals(values.length, Arrays.stream(values).map(UserType::getId).distinct().count());
	}

}