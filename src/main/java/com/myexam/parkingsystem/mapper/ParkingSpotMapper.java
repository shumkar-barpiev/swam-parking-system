package com.myexam.parkingsystem.mapper;

import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotRequest;
import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotResponse;
import com.myexam.parkingsystem.entity.ParkingSpot;
import org.springframework.stereotype.Component;

@Component
public class ParkingSpotMapper {
	public ParkingSpot toEntity(ParkingSpotRequest request) {
		ParkingSpot parkingSpot = new ParkingSpot();

		parkingSpot.setSpotNumber(request.getSpotNumber());
		parkingSpot.setActive(request.isActive());
		parkingSpot.setType(request.getType());

		return parkingSpot;
	}

	public void updateEntity(ParkingSpot parkingSpot, ParkingSpotRequest request) {
		parkingSpot.setSpotNumber(request.getSpotNumber());
		parkingSpot.setActive(request.isActive());
		parkingSpot.setType(request.getType());
	}

	public ParkingSpotResponse toResponse(ParkingSpot parkingSpot) {
		return new ParkingSpotResponse(
				parkingSpot.getId(),
				parkingSpot.getSpotNumber(),
				parkingSpot.getType(),
				parkingSpot.isActive(),
				parkingSpot.getParkingZone() != null ? parkingSpot.getParkingZone().getId() : null
		);
	}
}
