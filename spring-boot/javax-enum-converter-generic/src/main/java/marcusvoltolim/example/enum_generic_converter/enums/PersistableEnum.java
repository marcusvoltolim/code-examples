package marcusvoltolim.example.enum_generic_converter.enums;

@FunctionalInterface
public interface PersistableEnum<I> {

	I getId();

}
