package com.myexam.parkingsystem.controller;

import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketRequest;
import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketResponse;
import com.myexam.parkingsystem.service.ParkingTicketService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/parking-tickets")
public class ParkingTicketController {
	private final ParkingTicketService parkingTicketService;

	ParkingTicketController(ParkingTicketService parkingTicketService) {
		this.parkingTicketService = parkingTicketService;
	}

	@PostMapping
	public ResponseEntity<ParkingTicketResponse> createParkingTicket(@Valid @RequestBody ParkingTicketRequest request) {
		ParkingTicketResponse parkingTicket = parkingTicketService.createParkingTicket(request);

		URI location = URI.create(
				"/api/parking-tickets/" + parkingTicket.getId()
		);

		return ResponseEntity
				.created(location)
				.body(parkingTicket);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParkingTicketResponse> getParkingTicketById(@PathVariable Long id) {
		ParkingTicketResponse parkingTicketResponse = parkingTicketService.getParkingTicketById(id);

		return ResponseEntity.ok(parkingTicketResponse);
	}

	@GetMapping
	public ResponseEntity<List<ParkingTicketResponse>> getAllParkingTickets() {
		return ResponseEntity.ok(parkingTicketService.getParkingTickets());
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParkingTicketResponse> updateParkingTicket(
			@PathVariable Long id,
			@Valid @RequestBody ParkingTicketRequest request
	) {
		return ResponseEntity.ok(parkingTicketService.updateParkingTicket(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteParkingTicket(@PathVariable Long id) {
		parkingTicketService.deleteParkingTicket(id);
		return ResponseEntity.noContent().build();
	}
}
