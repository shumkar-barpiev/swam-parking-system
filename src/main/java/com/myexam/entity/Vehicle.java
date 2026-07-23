package com.myexam.entity;

import java.util.List;
import java.util.Set;

public class Vehicle {
	public enum VehicleType {
		CAR,
		MOTORCYCLE,
		VAN,
		ELECTRIC
	}

	private Long id;
	private String licensePlate;
	private String model;
	private VehicleType type;

	private Set<Driver> drivers;
	private List<ParkingTicket> parkingTickets;
}

