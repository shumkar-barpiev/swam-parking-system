package com.myexam.parkingsystem.controller;

import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotRequest;
import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotResponse;
import com.myexam.parkingsystem.service.ParkingSpotService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/parking-spots")
public class ParkingSpotController {
	private final ParkingSpotService parkingSpotService;

	ParkingSpotController(ParkingSpotService parkingSpotService) {
		this.parkingSpotService = parkingSpotService;
	}

	@PostMapping
	public ResponseEntity<ParkingSpotResponse> createParkingSpot(@Valid @RequestBody ParkingSpotRequest request) {
		ParkingSpotResponse parkingSpot = parkingSpotService.createParkingSpot(request);

		URI location = URI.create(
				"/api/parking-spots/" + parkingSpot.getId()
		);

		return ResponseEntity
				.created(location)
				.body(parkingSpot);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParkingSpotResponse> getParkingSpotById(@PathVariable Long id) {
		ParkingSpotResponse parkingSpotResponse = parkingSpotService.getParkingSpotById(id);

		return ResponseEntity.ok(parkingSpotResponse);
	}

	@GetMapping
	public ResponseEntity<List<ParkingSpotResponse>> getAllParkingSpots() {
		return ResponseEntity.ok(parkingSpotService.getParkingSpots());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParkingSpotResponse> updateParkingSpot(
			@PathVariable Long id,
			@Valid @RequestBody ParkingSpotRequest request
	) {
		return ResponseEntity.ok(parkingSpotService.updateParkingSpot(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteParkingSpot(@PathVariable Long id) {
		parkingSpotService.deleteParkingSpot(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{parkingSpotId}/assign-zone/{parkingZoneId}")
	public ResponseEntity<ParkingSpotResponse> assignParkingZone(
			@PathVariable Long parkingSpotId,
			@PathVariable Long parkingZoneId
	) {
		ParkingSpotResponse updatedParkingSpot = parkingSpotService.assignParkingZone(parkingSpotId, parkingZoneId);
		return ResponseEntity.ok(updatedParkingSpot);
	}
}
