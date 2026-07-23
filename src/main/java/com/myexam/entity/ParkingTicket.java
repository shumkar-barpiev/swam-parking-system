package com.myexam.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingTicket {

	public enum TicketStatus {
		ACTIVE,
		COMPLETED,
		CANCELLED
	}

	private Long id;
	private String ticketNumber;
	private LocalDateTime entryTime;
	private LocalDateTime exitTime;
	private TicketStatus status;
	private BigDecimal totalCost;

	private Driver driver;
	private Vehicle vehicle;
	private ParkingSpot parkingSpot;
}
