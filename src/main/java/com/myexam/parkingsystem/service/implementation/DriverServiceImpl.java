package com.myexam.parkingsystem.service.implementation;

import com.myexam.parkingsystem.config.global.exception.ConflictException;
import com.myexam.parkingsystem.config.global.exception.ResourceNotFoundException;
import com.myexam.parkingsystem.dto.driver.CreateDriverRequest;
import com.myexam.parkingsystem.dto.driver.DriverResponse;
import com.myexam.parkingsystem.dto.driver.UpdateDriverRequest;
import com.myexam.parkingsystem.entity.Driver;
import com.myexam.parkingsystem.entity.Vehicle;
import com.myexam.parkingsystem.mapper.DriverMapper;
import com.myexam.parkingsystem.repository.DriverRepository;
import com.myexam.parkingsystem.repository.VehicleRepository;
import com.myexam.parkingsystem.service.DriverService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
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
	@Transactional(readOnly = true)
	public DriverResponse getDriverById(Long id) {
		Driver driver = driverRepository.findById(id).orElse(null);

		return driverMapper.toResponse(driver);
	}

	@Override
	@Transactional(readOnly = true)
	public List<DriverResponse> getAllDrivers() {
		return driverRepository.findAll().stream().map(driverMapper::toResponse).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public DriverResponse updateDriver(Long id, UpdateDriverRequest request) {
		Driver driver = driverRepository.findById(id).orElse(null);
		String email = request.getEmail().trim().toLowerCase();

		boolean emailAlreadyUsed =
				driverRepository.existsByEmailIgnoreCaseAndIdNot(
						email,
						id
				);

		if (emailAlreadyUsed) {
			throw new ConflictException(
					"A driver with this email already exists"
			);
		}

		boolean licenceAlreadyUsed =
				driverRepository
						.existsByDrivingLicenseNumberAndIdNot(
								request.getDrivingLicenseNumber(),
								id
						);
		if (licenceAlreadyUsed) {
			throw new ConflictException(
					"A driver with this licence number already exists"
			);
		}

		driverMapper.updateEntity(driver, request);
		Driver updatedDriver = driverRepository.save(driver);

		return driverMapper.toResponse(updatedDriver);
	}

	@Override
	public void deleteDriver(Long id) {
		Driver driver = driverRepository.findById(id).orElse(null);

		List<Vehicle> assignedVehicles = List.copyOf(
				driver.getVehicles()
		);

		assignedVehicles.forEach(driver::removeVehicle);
		driverRepository.delete(driver);
	}

	@Override
	public DriverResponse assignVehicle(Long driverId, Long vehicleId) {
		Driver driver = findDriverById(driverId);
		Vehicle vehicle = findVehicleById(vehicleId);

		if (driver.getVehicles().contains(vehicle)) {
			throw new ConflictException(
					"The vehicle is already assigned to this driver"
			);
		}

		driver.addVehicle(vehicle);
		Driver updatedDriver = driverRepository.save(driver);
		return driverMapper.toResponse(updatedDriver);
	}

	@Override
	public DriverResponse removeVehicle(Long driverId, Long vehicleId) {
		Driver driver = findDriverById(driverId);
		Vehicle vehicle = findVehicleById(vehicleId);

		if (!driver.getVehicles().contains(vehicle)) {
			throw new ConflictException(
					"The vehicle is not assigned to this driver"
			);
		}

		driver.removeVehicle(vehicle);
		Driver updatedDriver = driverRepository.save(driver);
		return driverMapper.toResponse(updatedDriver);
	}

	private Driver findDriverById(Long id) {
		return driverRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Driver not found with id: " + id
				));
	}

	private Vehicle findVehicleById(Long id) {
		return vehicleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Vehicle not found with id: " + id
				));
	}
}
