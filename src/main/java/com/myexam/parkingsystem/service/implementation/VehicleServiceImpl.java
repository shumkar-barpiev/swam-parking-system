package com.myexam.parkingsystem.service.implementation;

import com.myexam.parkingsystem.config.global.exception.ConflictException;
import com.myexam.parkingsystem.dto.vehicle.CreateVehicleRequest;
import com.myexam.parkingsystem.dto.vehicle.UpdateVehicleRequest;
import com.myexam.parkingsystem.dto.vehicle.VehicleResponse;
import com.myexam.parkingsystem.entity.Vehicle;
import com.myexam.parkingsystem.mapper.VehicleMapper;
import com.myexam.parkingsystem.repository.VehicleRepository;
import com.myexam.parkingsystem.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {
	private final VehicleRepository vehicleRepository;
	private final VehicleMapper vehicleMapper;

	VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper) {
		this.vehicleMapper = vehicleMapper;
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	@Transactional
	public VehicleResponse createVehicle(CreateVehicleRequest vehicleRequest) {
		Vehicle vehicle = vehicleMapper.toEntity(vehicleRequest);
		boolean vehicleExists = vehicleRepository.existsByLicensePlateIgnoreCase(vehicle.getLicensePlate());

		if (vehicleExists) {
			throw new ConflictException(
					"A vehicle with this license plate already exists"
			);
		}

		vehicleRepository.save(vehicle);
		return vehicleMapper.toResponse(vehicle);
	}

	@Override
	@Transactional(readOnly = true)
	public VehicleResponse getVehicleById(Long id) {
		Vehicle vehicle = vehicleRepository.findById(id).orElse(null);

		if (vehicle == null) {
			throw new ConflictException(
					"Vehicle with id " + id + " not found"
			);
		}

		return vehicleMapper.toResponse(vehicle);
	}

	@Override
	@Transactional(readOnly = true)
	public List<VehicleResponse> getAllVehicles() {
		List<Vehicle> vehicles = vehicleRepository.findAll();
		return vehicles.stream()
				.map(vehicleMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional
	public VehicleResponse updateVehicle(Long id, UpdateVehicleRequest request) {
		Vehicle vehicle = vehicleRepository.findById(id).orElse(null);

		if (vehicle == null) {
			throw new ConflictException(
					"Vehicle with id " + id + " not found"
			);
		}

		vehicleMapper.updateEntity(vehicle, request);
		vehicleRepository.save(vehicle);

		return vehicleMapper.toResponse(vehicle);
	}

	@Override
	@Transactional
	public void deleteVehicle(Long id) {
		Vehicle vehicle = vehicleRepository.findById(id).orElse(null);
		vehicleRepository.delete(vehicle);
	}
}
