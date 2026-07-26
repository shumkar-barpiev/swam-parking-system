package com.myexam.parkingsystem.mapper;

import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketRequest;
import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketResponse;
import com.myexam.parkingsystem.entity.ParkingTicket;
import org.springframework.stereotype.Component;

@Component
public class ParkingTicketMapper {
	public ParkingTicket toEntity(ParkingTicketRequest request) {
		ParkingTicket parkingTicket = new ParkingTicket();

		parkingTicket.setTicketNumber(request.getTicketNumber());
		parkingTicket.setEntryTime(request.getEntryTime());
		parkingTicket.setExitTime(request.getExitTime());
		parkingTicket.setStatus(request.getStatus());
		parkingTicket.setTotalCost(request.getTotalCost());

		return parkingTicket;
	}

	public ParkingTicketResponse toResponse(ParkingTicket parkingTicket) {
		return new ParkingTicketResponse(
				parkingTicket.getId(),
				parkingTicket.getTicketNumber(),
				parkingTicket.getEntryTime(),
				parkingTicket.getExitTime(),
				parkingTicket.getStatus(),
				parkingTicket.getTotalCost()
		);
	}

	public void updateEntity(ParkingTicket parkingTicket, ParkingTicketRequest request) {
		parkingTicket.setTicketNumber(request.getTicketNumber());
		parkingTicket.setEntryTime(request.getEntryTime());
		parkingTicket.setExitTime(request.getExitTime());
		parkingTicket.setStatus(request.getStatus());
		parkingTicket.setTotalCost(request.getTotalCost());
	}
}
