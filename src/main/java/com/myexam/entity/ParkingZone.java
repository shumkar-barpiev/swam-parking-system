package com.myexam.entity;

import java.math.BigDecimal;
import java.util.List;

public class ParkingZone {
	private Long id;
	private String name;
	private String code;
	private String description;
	private BigDecimal hourlyRate;
	private boolean active;

	private List<ParkingSpot> parkingSpots;
}
