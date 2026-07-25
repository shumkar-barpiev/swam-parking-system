package com.myexam.parkingsystem.service;

import com.myexam.parkingsystem.dto.vehicle.CreateVehicleRequest;
import com.myexam.parkingsystem.dto.vehicle.UpdateVehicleRequest;
import com.myexam.parkingsystem.dto.vehicle.VehicleResponse;

import java.util.List;

public interface VehicleService {
	VehicleResponse createVehicle(CreateVehicleRequest vehicleRequest);

	VehicleResponse getVehicleById(Long id);

	List<VehicleResponse> getAllVehicles();

	VehicleResponse updateVehicle(Long id, UpdateVehicleRequest vehicle);

	void deleteVehicle(Long id);
}
