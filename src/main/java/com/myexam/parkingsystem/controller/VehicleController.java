package com.myexam.parkingsystem.controller;

import com.myexam.parkingsystem.dto.vehicle.CreateVehicleRequest;
import com.myexam.parkingsystem.dto.vehicle.UpdateVehicleRequest;
import com.myexam.parkingsystem.dto.vehicle.VehicleResponse;
import com.myexam.parkingsystem.service.VehicleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {
	private final VehicleService vehicleService;

	VehicleController(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	@PostMapping
	public ResponseEntity<VehicleResponse> createVehicle(@Valid @RequestBody CreateVehicleRequest request) {
		VehicleResponse vehicle = vehicleService.createVehicle(request);

		URI location = URI.create(
				"/api/vehicles/" + vehicle.getId()
		);

		return ResponseEntity
				.created(location)
				.body(vehicle);
	}

	@GetMapping
	public ResponseEntity<List<VehicleResponse>> getAllVehicles() {
		return ResponseEntity.ok(vehicleService.getAllVehicles());
	}

	@GetMapping("/{id}")
	public ResponseEntity<VehicleResponse> getVehicleById(@PathVariable Long id) {
		VehicleResponse vehicleResponse = vehicleService.getVehicleById(id);

		return ResponseEntity.ok(vehicleResponse);
	}

	@PutMapping("/{id}")
	public ResponseEntity<VehicleResponse> updateVehicle(@PathVariable Long id, @Valid @RequestBody UpdateVehicleRequest request) {

		return ResponseEntity.ok(vehicleService.updateVehicle(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteVehicle(@PathVariable Long id) {
		vehicleService.deleteVehicle(id);
		return ResponseEntity.noContent().build();
	}
}
