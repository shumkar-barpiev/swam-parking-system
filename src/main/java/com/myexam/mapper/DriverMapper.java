package com.myexam.mapper;

import com.myexam.dto.address.AddressRequest;
import com.myexam.dto.driver.CreateDriverRequest;
import com.myexam.entity.Address;
import com.myexam.entity.Driver;

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

	private Address toAddressEntity(AddressRequest request) {
		Address address = new Address();

		address.setStreet(request.getStreet());
		address.setCity(request.getCity());
		address.setPostalCode(request.getPostalCode());
		address.setCountry(request.getCountry());

		return address;
	}
}
