package com.myexam.parkingsystem.service.implementation;

import com.myexam.parkingsystem.config.global.exception.ConflictException;
import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotRequest;
import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotResponse;
import com.myexam.parkingsystem.entity.ParkingSpot;
import com.myexam.parkingsystem.entity.ParkingZone;
import com.myexam.parkingsystem.mapper.ParkingSpotMapper;
import com.myexam.parkingsystem.repository.ParkingSpotRepository;
import com.myexam.parkingsystem.repository.ParkingZoneRepository;
import com.myexam.parkingsystem.service.ParkingSpotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {

	private final ParkingSpotRepository parkingSpotRepository;
	private final ParkingSpotMapper parkingSpotMapper;
	private final ParkingZoneRepository parkingZoneRepository;

	ParkingSpotServiceImpl(ParkingSpotRepository parkingSpotRepository,
						   ParkingSpotMapper parkingSpotMapper,
						   ParkingZoneRepository parkingZoneRepository) {
		this.parkingSpotRepository = parkingSpotRepository;
		this.parkingSpotMapper = parkingSpotMapper;
		this.parkingZoneRepository = parkingZoneRepository;
	}

	@Override
	@Transactional
	public ParkingSpotResponse createParkingSpot(ParkingSpotRequest request) {
		ParkingSpot parkingSpot = parkingSpotMapper.toEntity(request);
		ParkingZone parkingZone = parkingZoneRepository.findById(request.getParkingZoneId()).orElse(null);

		if (parkingZone == null) {
			throw new ConflictException(
					"Parking zone with id " + request.getParkingZoneId() + " not found"
			);
		}

		parkingSpot.setParkingZone(parkingZone);
		parkingSpotRepository.save(parkingSpot);

		return parkingSpotMapper.toResponse(parkingSpot);
	}

	@Override
	@Transactional
	public ParkingSpotResponse updateParkingSpot(Long id, ParkingSpotRequest request) {
		ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElse(null);
		ParkingZone parkingZone = parkingZoneRepository.findById(request.getParkingZoneId()).orElse(null);

		if (parkingSpot == null) {
			throw new ConflictException(
					"Parking spot with id " + id + " not found"
			);
		}

		if (parkingZone == null) {
			throw new ConflictException(
					"Parking zone with id " + request.getParkingZoneId() + " not found"
			);
		}

		parkingSpotMapper.updateEntity(parkingSpot, request);
		parkingSpot.setParkingZone(parkingZone);
		parkingSpotRepository.save(parkingSpot);

		return parkingSpotMapper.toResponse(parkingSpot);
	}

	@Override
	@Transactional(readOnly = true)
	public ParkingSpotResponse getParkingSpotById(Long id) {
		ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElse(null);
		if (parkingSpot == null) {
			throw new ConflictException(
					"Parking spot with id " + id + " not found"
			);
		}

		return parkingSpotMapper.toResponse(parkingSpot);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkingSpotResponse> getParkingSpots() {
		return parkingSpotRepository.findAll().stream().map(parkingSpotMapper::toResponse).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void deleteParkingSpot(Long id) {
		ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElse(null);
		parkingSpotRepository.delete(parkingSpot);
	}

	@Override
	@Transactional
	public ParkingSpotResponse assignParkingZone(Long parkingSpotId, Long parkingZoneId) {
		ParkingSpot parkingSpot = parkingSpotRepository.findById(parkingSpotId).orElse(null);
		ParkingZone parkingZone = parkingZoneRepository.findById(parkingZoneId).orElse(null);

		if (parkingSpot == null) {
			throw new ConflictException(
					"Parking spot with id " + parkingSpotId + " not found"
			);
		}

		if (parkingZone == null) {
			throw new ConflictException(
					"Parking zone with id " + parkingZoneId + " not found"
			);
		}

		parkingSpot.setParkingZone(parkingZone);
		parkingSpotRepository.save(parkingSpot);

		return parkingSpotMapper.toResponse(parkingSpot);
	}
}
