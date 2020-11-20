package marcusvoltolim.example.enum_generic_converter.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderType implements PersistableEnum<Integer> {

	F(1),
	M(2);

	private final Integer id;

}
