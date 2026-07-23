package com.myexam.entity;

import java.util.List;

public class ParkingSpot {
	public enum ParkingSpotType {
		STANDARD,
		MOTORCYCLE,
		ELECTRIC,
		DISABLED
	}

	private Long id;
	private String spotNumber;
	private ParkingSpotType type;
	private boolean active;

	private ParkingZone parkingZone;
	private List<ParkingTicket> parkingTickets;
}

