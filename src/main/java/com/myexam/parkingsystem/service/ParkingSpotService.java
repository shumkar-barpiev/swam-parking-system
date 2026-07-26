package com.myexam.parkingsystem.service;

import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotRequest;
import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotResponse;

import java.util.List;

public interface ParkingSpotService {
	ParkingSpotResponse createParkingSpot(ParkingSpotRequest request);

	ParkingSpotResponse updateParkingSpot(Long id, ParkingSpotRequest request);

	ParkingSpotResponse getParkingSpotById(Long id);

	List<ParkingSpotResponse> getParkingSpots();

	void deleteParkingSpot(Long id);
}
