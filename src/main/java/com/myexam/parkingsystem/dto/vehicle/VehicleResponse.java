package com.myexam.parkingsystem.dto.vehicle;

import com.myexam.parkingsystem.entity.type.VehicleType;
import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.myexam.parkingsystem.entity.Vehicle}
 */
@Value
public class VehicleResponse implements Serializable {
	Long id;
	String licensePlate;
	String model;
	VehicleType type;
}