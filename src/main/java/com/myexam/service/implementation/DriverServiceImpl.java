package com.myexam.service.implementation;

import com.myexam.config.global.exception.ConflictException;
import com.myexam.dto.driver.CreateDriverRequest;
import com.myexam.dto.driver.DriverResponse;
import com.myexam.dto.driver.UpdateDriverRequest;
import com.myexam.entity.Driver;
import com.myexam.mapper.DriverMapper;
import com.myexam.repository.DriverRepository;
import com.myexam.repository.VehicleRepository;
import com.myexam.service.DriverService;

import java.util.List;

public class DriverServiceImpl implements DriverService {
	private final DriverRepository driverRepository;
	private final VehicleRepository vehicleRepository;
	private final DriverMapper driverMapper;

	public DriverServiceImpl(DriverRepository driverRepository, VehicleRepository vehicleRepository, DriverMapper driverMapper) {
		this.driverRepository = driverRepository;
		this.vehicleRepository = vehicleRepository;
		this.driverMapper = driverMapper;
	}

	@Override
	public DriverResponse createDriver(CreateDriverRequest request) {
		String email = request.getEmail().trim().toLowerCase();

		if (driverRepository.existsByEmailIgnoreCase(email)) {
			throw new ConflictException(
					"A driver with this email already exists"
			);
		}

		if (driverRepository.existsByDrivingLicenseNumber(
				request.getDrivingLicenseNumber().trim().toLowerCase()
		)) {
			throw new ConflictException(
					"A driver with this licence number already exists"
			);
		}

		Driver driver = driverMapper.toEntity(request);
		Driver savedDriver = driverRepository.save(driver);

		return driverMapper.toResponse(savedDriver);
	}

	@Override
	public DriverResponse getDriverById(Long id) {
		return null;
	}

	@Override
	public List<DriverResponse> getAllDrivers() {
		return List.of();
	}

	@Override
	public DriverResponse updateDriver(Long id, UpdateDriverRequest request) {
		return null;
	}

	@Override
	public void deleteDriver(Long id) {

	}

	@Override
	public DriverResponse assignVehicle(Long driverId, Long vehicleId) {
		return null;
	}

	@Override
	public DriverResponse removeVehicle(Long driverId, Long vehicleId) {
		return null;
	}
}
