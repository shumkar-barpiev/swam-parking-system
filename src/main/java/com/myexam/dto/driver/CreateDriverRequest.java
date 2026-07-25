package com.myexam.dto.driver;

import com.myexam.dto.address.AddressRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.myexam.entity.Driver}
 */
@Value
public class CreateDriverRequest implements Serializable {
	@Size(max = 100)
	@NotBlank(message = "Full name is required")
	String fullName;

	@Size(max = 120)
	@NotBlank(message = "Email is required")
	String email;

	@Size(max = 30)
	String phoneNumber;

	@Size(max = 50)
	@NotBlank(message = "Driving License Number is required")
	String drivingLicenseNumber;

	@NotNull(message = "Address is required")
	@Valid
	AddressRequest address;
}