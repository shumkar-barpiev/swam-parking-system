package com.myexam.parkingsystem.controller;

import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneRequest;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneResponse;
import com.myexam.parkingsystem.service.ParkingZoneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/parking-zones")
public class ParkingZoneController {
	private final ParkingZoneService parkingZoneService;

	ParkingZoneController(ParkingZoneService parkingZoneService) {
		this.parkingZoneService = parkingZoneService;
	}

	@PostMapping
	public ResponseEntity<ParkingZoneResponse> createParkingZone(@Valid @RequestBody ParkingZoneRequest request) {
		ParkingZoneResponse parkingZone = parkingZoneService.createParkingZone(request);

		URI location = URI.create(
				"/api/parking-zones/" + parkingZone.getId()
		);

		return ResponseEntity
				.created(location)
				.body(parkingZone);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParkingZoneResponse> getParkingZoneById(@PathVariable Long id) {
		ParkingZoneResponse parkingZoneResponse = parkingZoneService.getParkingZone(id);

		return ResponseEntity.ok(parkingZoneResponse);
	}

	@GetMapping
	public ResponseEntity<List<ParkingZoneResponse>> getAllParkingZones() {
		return ResponseEntity.ok(parkingZoneService.getParkingZones());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParkingZoneResponse> updateParkingZone(
			@PathVariable Long id,
			@Valid @RequestBody ParkingZoneRequest request
	) {
		return ResponseEntity.ok(parkingZoneService.updateParkingZone(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteParkingZone(@PathVariable Long id) {
		parkingZoneService.deleteParkingZone(id);
		return ResponseEntity.noContent().build();
	}
}
