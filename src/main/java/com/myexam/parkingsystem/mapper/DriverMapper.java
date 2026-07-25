package com.myexam.parkingsystem.mapper;

import com.myexam.parkingsystem.dto.address.AddressRequest;
import com.myexam.parkingsystem.dto.address.AddressResponse;
import com.myexam.parkingsystem.dto.driver.CreateDriverRequest;
import com.myexam.parkingsystem.dto.driver.DriverResponse;
import com.myexam.parkingsystem.dto.driver.UpdateDriverRequest;
import com.myexam.parkingsystem.entity.Address;
import com.myexam.parkingsystem.entity.Driver;
import com.myexam.parkingsystem.entity.Vehicle;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DriverMapper {
	public Driver toEntity(CreateDriverRequest request) {
		Driver driver = new Driver();

		driver.setFullName(request.getFullName());
		driver.setEmail(request.getEmail());
		driver.setPhoneNumber(request.getPhoneNumber());
		driver.setDrivingLicenseNumber(
				request.getDrivingLicenseNumber()
		);

		Address address = toAddressEntity(request.getAddress());
		driver.assignAddress(address);

		return driver;
	}

	public DriverResponse toResponse(Driver driver) {
		Set<Long> vehicleIds;

		if (driver.getVehicles() == null) {
			vehicleIds = Collections.emptySet();
		} else {
			vehicleIds = driver.getVehicles()
					.stream()
					.map(Vehicle::getId)
					.collect(Collectors.toUnmodifiableSet());
		}

		return new DriverResponse(
				driver.getId(),
				driver.getFullName(),
				driver.getEmail(),
				driver.getPhoneNumber(),
				driver.getDrivingLicenseNumber(),
				toAddressResponse(driver.getAddress()),
				vehicleIds
		);
	}

	public void updateEntity(
			Driver driver,
			UpdateDriverRequest request
	) {
		driver.setFullName(request.getFullName());
		driver.setEmail(normalizeEmail(request.getEmail()));
		driver.setPhoneNumber(request.getPhoneNumber());
		driver.setDrivingLicenseNumber(
				request.getDrivingLicenseNumber()
		);

		updateAddress(driver, request.getAddress());
	}

	private Address toAddressEntity(AddressRequest request) {
		Address address = new Address();

		address.setStreet(request.getStreet());
		address.setCity(request.getCity());
		address.setPostalCode(request.getPostalCode());
		address.setCountry(request.getCountry());

		return address;
	}

	private AddressResponse toAddressResponse(Address address) {
		if (address == null) {
			return null;
		}

		return new AddressResponse(
				address.getId(),
				address.getStreet(),
				address.getCity(),
				address.getPostalCode(),
				address.getCountry()
		);
	}

	private void updateAddress(
			Driver driver,
			AddressRequest request
	) {
		Address address = driver.getAddress();

		if (address == null) {
			address = new Address();
			driver.assignAddress(address);
		}

		address.setStreet(request.getStreet());
		address.setCity(request.getCity());
		address.setPostalCode(request.getPostalCode());
		address.setCountry(request.getCountry());
	}

	private String normalizeEmail(String email) {
		return email.trim().toLowerCase();
	}
}
