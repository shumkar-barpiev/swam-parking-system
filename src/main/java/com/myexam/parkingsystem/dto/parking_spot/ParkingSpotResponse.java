package com.myexam.parkingsystem.dto.parking_spot;

import com.myexam.parkingsystem.entity.ParkingZone;
import com.myexam.parkingsystem.entity.type.ParkingSpotType;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.myexam.parkingsystem.entity.ParkingSpot}
 */
@Value
public class ParkingSpotResponse implements Serializable {
	Long id;
	String spotNumber;
	ParkingSpotType type;
	boolean active;
	Long parkingZoneId;
}