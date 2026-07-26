package com.myexam.parkingsystem.service;

import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketRequest;
import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketResponse;

import java.util.List;

public interface ParkingTicketService {
	ParkingTicketResponse createParkingTicket(ParkingTicketRequest parkingTicketRequest);

	ParkingTicketResponse updateParkingTicket(Long id, ParkingTicketRequest parkingTicketRequest);

	ParkingTicketResponse getParkingTicketById(Long id);

	List<ParkingTicketResponse> getParkingTickets();

	void deleteParkingZone(Long id);
}
