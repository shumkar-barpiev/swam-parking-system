package com.myexam.parkingsystem.mapper;

import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotResponse;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneRequest;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneResponse;
import com.myexam.parkingsystem.entity.ParkingSpot;
import com.myexam.parkingsystem.entity.ParkingZone;

import java.util.ArrayList;
import java.util.List;

public class ParkingZoneMapper {
	public ParkingZone toEntity(ParkingZoneRequest request) {
		ParkingZone parkingZone = new ParkingZone();

		parkingZone.setName(request.getName());
		parkingZone.setCode(request.getCode());
		parkingZone.setDescription(request.getDescription());
		parkingZone.setHourlyRate(request.getHourlyRate());
		parkingZone.setActive(request.isActive());

		return parkingZone;
	}

	public ParkingZoneResponse toResponse(ParkingZone parkingZone) {
		List<ParkingSpotResponse> parkingSpotResponses =
				parkingZone.getParkingSpots() == null
						? List.of()
						: parkingZone.getParkingSpots()
						.stream()
						.map(parkingSpot -> new ParkingSpotResponse(
								parkingSpot.getId(),
								parkingSpot.getSpotNumber(),
								parkingSpot.getType(),
								parkingSpot.isActive()
						))
						.toList();

		return new ParkingZoneResponse(
				parkingZone.getId(),
				parkingZone.getName(),
				parkingZone.getCode(),
				parkingZone.getDescription(),
				parkingZone.getHourlyRate(),
				parkingZone.isActive(),
				parkingSpotResponses
		);
	}
}
