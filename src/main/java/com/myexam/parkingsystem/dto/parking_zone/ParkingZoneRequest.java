package com.myexam.parkingsystem.dto.parking_zone;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link com.myexam.parkingsystem.entity.ParkingZone}
 */
@Value
public class ParkingZoneRequest implements Serializable {
	@Size(max = 100)
	@NotBlank(message = "Parking Zone name can't be blank.")
	String name;

	@Size(max = 50)
	@NotBlank(message = "Parking zone code can't be blank.")
	String code;

	@Size(max = 300)
	String description;

	@NotNull(message = "Hourly rate can't be blank.")
	BigDecimal hourlyRate;

	boolean active;
}