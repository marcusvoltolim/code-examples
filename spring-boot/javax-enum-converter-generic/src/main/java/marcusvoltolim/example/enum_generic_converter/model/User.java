package marcusvoltolim.example.enum_generic_converter.model;

import marcusvoltolim.example.enum_generic_converter.enums.UserType;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class User extends AbstractPersistable<Long> {

	private UserType userTypeConverter;

	@Convert(disableConversion = true)
	@Enumerated(EnumType.STRING)
	private UserType userTypeString;

	@Convert(disableConversion = true)
	private UserType userTypeOrdinal;

	public User(UserType userTypeConverter) {
		this.userTypeConverter = userTypeConverter;
		this.userTypeString = userTypeConverter;
		this.userTypeOrdinal = userTypeConverter;
	}

}
