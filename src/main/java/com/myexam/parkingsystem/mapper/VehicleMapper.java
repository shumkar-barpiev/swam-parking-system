package com.myexam.parkingsystem.mapper;

import com.myexam.parkingsystem.dto.vehicle.CreateVehicleRequest;
import com.myexam.parkingsystem.dto.vehicle.UpdateVehicleRequest;
import com.myexam.parkingsystem.dto.vehicle.VehicleResponse;
import com.myexam.parkingsystem.entity.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {
	public Vehicle toEntity(CreateVehicleRequest request) {
		Vehicle vehicle = new Vehicle();

		vehicle.setLicensePlate(request.getLicensePlate());
		vehicle.setModel(request.getModel());
		vehicle.setType(request.getType());

		return vehicle;
	}

	public VehicleResponse toResponse(Vehicle vehicle) {
		return new VehicleResponse(
				vehicle.getId(),
				vehicle.getLicensePlate(),
				vehicle.getModel(),
				vehicle.getType()
		);
	}

	public void updateEntity(Vehicle vehicle, UpdateVehicleRequest request) {
		vehicle.setLicensePlate(request.getLicensePlate());
		vehicle.setModel(request.getModel());
		vehicle.setType(request.getType());
	}
}
