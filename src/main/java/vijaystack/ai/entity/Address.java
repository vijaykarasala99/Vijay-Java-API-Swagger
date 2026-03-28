package vijaystack.ai.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {

	@Column(length = 100)
	private String city;

	@Column(length = 100)
	private String state;

	@Column(length = 10)
	private String zipCode;

	/*
	 * @Embeddable: Purpose: Defines a reusable value object that can be embedded in
	 * side an entity.
	 * 
	 * When to use: When multiple fields logically belong together and should be
	 * Note: This class cannot exist independently as an entity.
	 */
}
