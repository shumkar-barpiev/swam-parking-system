package com.myexam.parkingsystem.dto.driver;

import com.myexam.parkingsystem.dto.address.AddressResponse;
import com.myexam.parkingsystem.entity.Driver;
import lombok.Value;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link Driver}
 */
@Value
public class DriverResponse implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	Long id;
	String fullName;
	String email;
	String phoneNumber;
	String drivingLicenseNumber;
	AddressResponse address;
	private Set<Long> vehicleIds;
}