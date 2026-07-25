package com.myexam.dto.driver;

import com.myexam.dto.address.AddressResponse;
import com.myexam.entity.Vehicle;
import lombok.Value;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link com.myexam.entity.Driver}
 */
@Value
public class DriverResponse implements Serializable {
	Long id;
	String fullName;
	String email;
	String phoneNumber;
	String drivingLicenseNumber;
	AddressResponse address;
	Set<Long> vehicleIds;
}