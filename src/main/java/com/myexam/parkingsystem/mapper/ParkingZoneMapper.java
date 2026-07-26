package com.myexam.parkingsystem.mapper;

import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotResponse;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneRequest;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneResponse;
import com.myexam.parkingsystem.entity.ParkingZone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
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

	public void updateEntity(ParkingZone parkingZone, ParkingZoneRequest request) {
		parkingZone.setName(request.getName());
		parkingZone.setCode(request.getCode());
		parkingZone.setDescription(request.getDescription());
		parkingZone.setHourlyRate(request.getHourlyRate());
		parkingZone.setActive(request.isActive());
	}

	public ParkingZoneResponse toResponse(ParkingZone parkingZone) {
		if (parkingZone == null) {
			return null;
		}

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
