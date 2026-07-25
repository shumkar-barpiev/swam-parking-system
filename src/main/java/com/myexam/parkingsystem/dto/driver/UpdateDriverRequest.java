package com.myexam.parkingsystem.dto.driver;

import com.myexam.parkingsystem.dto.address.AddressRequest;
import com.myexam.parkingsystem.entity.Driver;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link Driver}
 */
@Value
public class UpdateDriverRequest implements Serializable {
	@NotBlank(message = "Full name can not be blank")
	String fullName;

	@NotBlank(message = "Email can not be blank.")
	String email;

	@Size(max = 30)
	String phoneNumber;

	@Size(max = 50)
	@NotBlank(message = "Driving License number can not be blank.")
	String drivingLicenseNumber;

	@NotNull
	AddressRequest address;
}