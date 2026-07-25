package com.myexam.parkingsystem.dto.vehicle;

import com.myexam.parkingsystem.entity.type.VehicleType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.myexam.parkingsystem.entity.Vehicle}
 */
@Value
public class UpdateVehicleRequest implements Serializable {
	@Size(max = 20)
	@NotBlank(message = "License Plate can't be blank.")
	String licensePlate;

	@Size(max = 50)
	@NotBlank
	String model;

	@NotNull
	VehicleType type;
}