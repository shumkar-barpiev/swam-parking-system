package com.myexam.parkingsystem.controller;

import com.myexam.parkingsystem.dto.driver.CreateDriverRequest;
import com.myexam.parkingsystem.dto.driver.DriverResponse;
import com.myexam.parkingsystem.dto.driver.UpdateDriverRequest;
import com.myexam.parkingsystem.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {
	private final DriverService driverService;

	DriverController(DriverService driverService) {
		this.driverService = driverService;
	}

	@PostMapping
	public ResponseEntity<DriverResponse> createDriver(@Valid @RequestBody CreateDriverRequest request) {
		DriverResponse createdDriver = driverService.createDriver(request);

		URI location = URI.create(
				"/api/drivers/" + createdDriver.getId()
		);

		return ResponseEntity
				.created(location)
				.body(createdDriver);
	}

	@GetMapping("/{id}")
	public ResponseEntity<DriverResponse> getDriverById(@PathVariable Long id) {
		DriverResponse driverResponse = driverService.getDriverById(id);

		return ResponseEntity
				.ok()
				.body(driverResponse);
	}


	@PutMapping("/{id}")
	public ResponseEntity<DriverResponse> updateDriver(@PathVariable Long id, @Valid @RequestBody UpdateDriverRequest request) {

		return ResponseEntity
				.ok()
				.body(driverService.updateDriver(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteDriver(@PathVariable Long id) {
		driverService.deleteDriver(id);

		return ResponseEntity
				.noContent()
				.build();
	}

	@PutMapping("/{driverId}/vehicles/{vehicleId}")
	public ResponseEntity<DriverResponse> assignVehicleToDriver(
			@PathVariable Long driverId,
			@PathVariable Long vehicleId
	) {

		DriverResponse updatedDriver = driverService.assignVehicle(driverId, vehicleId);

		return ResponseEntity
				.ok()
				.body(updatedDriver);
	}

	@DeleteMapping("/{driverId}/vehicles/{vehicleId}")
	public ResponseEntity<DriverResponse> removeVehicleFromDriver(
			@PathVariable Long driverId,
			@PathVariable Long vehicleId
	) {
		DriverResponse updatedDriver = driverService.removeVehicle(driverId, vehicleId);

		return ResponseEntity
				.ok()
				.body(updatedDriver);
	}
}
