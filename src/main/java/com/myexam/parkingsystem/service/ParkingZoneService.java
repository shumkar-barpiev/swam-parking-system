package com.myexam.parkingsystem.service;

import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneRequest;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneResponse;

import java.util.List;

public interface ParkingZoneService {
	ParkingZoneResponse createParkingZone(ParkingZoneRequest request);

	ParkingZoneResponse updateParkingZone(Long id, ParkingZoneRequest request);

	ParkingZoneResponse getParkingZone(Long id);

	List<ParkingZoneResponse> getParkingZones();

	void deleteParkingZone(Long id);
}
