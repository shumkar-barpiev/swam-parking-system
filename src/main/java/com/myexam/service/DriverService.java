package com.myexam.service;

import com.myexam.dto.driver.CreateDriverRequest;
import com.myexam.dto.driver.DriverResponse;
import com.myexam.dto.driver.UpdateDriverRequest;

import java.util.List;

public interface DriverService {
	DriverResponse createDriver(CreateDriverRequest request);

	DriverResponse getDriverById(Long id);

	List<DriverResponse> getAllDrivers();

	DriverResponse updateDriver(
			Long id,
			UpdateDriverRequest request
	);

	void deleteDriver(Long id);

	DriverResponse assignVehicle(
			Long driverId,
			Long vehicleId
	);

	DriverResponse removeVehicle(
			Long driverId,
			Long vehicleId
	);
}
