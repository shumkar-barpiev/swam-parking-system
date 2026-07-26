package com.myexam.parkingsystem.dto.parking_zone;

import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotResponse;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * DTO for {@link com.myexam.parkingsystem.entity.ParkingZone}
 */
@Value
public class ParkingZoneResponse implements Serializable {
	Long id;
	String name;
	String code;
	String description;
	BigDecimal hourlyRate;
	boolean active;
	List<ParkingSpotResponse> parkingSpots;
}