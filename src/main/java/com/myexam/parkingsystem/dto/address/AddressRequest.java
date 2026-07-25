package com.myexam.parkingsystem.dto.address;

import com.myexam.parkingsystem.entity.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Address}
 */
@Value
public class AddressRequest implements Serializable {
	@Size(max = 150)
	@NotBlank(message = "Street can't be blank")
	String street;

	@Size(max = 100)
	@NotBlank(message = "City can't be blank.")
	String city;

	@Size(max = 20)
	@NotBlank(message = "Postal code can't be blank.")
	String postalCode;

	@Size(max = 100)
	@NotBlank(message = "Country can't be blank")
	String country;
}