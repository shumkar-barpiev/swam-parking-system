package com.myexam.parkingsystem.service.implementation;

import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotRequest;
import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotResponse;
import com.myexam.parkingsystem.service.ParkingSpotService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ParkingSpotServiceImpl implements ParkingSpotService {
	@Override
	public ParkingSpotResponse createParkingSpot(ParkingSpotRequest request) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public ParkingSpotResponse updateParkingSpot(ParkingSpotRequest request) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public ParkingSpotResponse getParkingSpotById(Long id) {
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkingSpotResponse> getParkingSpots() {
		return List.of();
	}

	@Override
	public void deleteParkingSpot(Long id) {

	}
}
