package com.myexam.parkingsystem.dto.parking_spot;

import com.myexam.parkingsystem.entity.type.ParkingSpotType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.myexam.parkingsystem.entity.ParkingSpot}
 */
@Value
public class ParkingSpotRequest implements Serializable {
	Long parkingZoneId;

	@Size(max = 20)
	@NotBlank(message = "Parking spot number can't be blank.")
	String spotNumber;

	@NotNull(message = "Parking Spot type can't be blank.")
	ParkingSpotType type;

	boolean active;
}