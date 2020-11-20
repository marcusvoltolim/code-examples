package marcusvoltolim.example.enum_generic_converter.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType implements PersistableEnum<Integer> {

	ADMIN(777),
	READER(1),
	WRITER(2),
	READER_WRITER(3);

	private final Integer id;

}
