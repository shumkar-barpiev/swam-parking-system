package com.myexam.entity;

import java.util.List;
import java.util.Set;

public class Driver {
	private Long id;
	private String fullName;
	private String email;
	private String phoneNumber;
	private String drivingLicenseNumber;

	private Address address;
	private Set<Vehicle> vehicles;
	private List<ParkingTicket> parkingTickets;
}