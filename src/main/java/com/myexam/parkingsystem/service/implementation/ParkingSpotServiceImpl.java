package com.myexam.parkingsystem.service.implementation;

import com.myexam.parkingsystem.config.global.exception.ConflictException;
import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotRequest;
import com.myexam.parkingsystem.dto.parking_spot.ParkingSpotResponse;
import com.myexam.parkingsystem.entity.ParkingSpot;
import com.myexam.parkingsystem.mapper.ParkingSpotMapper;
import com.myexam.parkingsystem.repository.ParkingSpotRepository;
import com.myexam.parkingsystem.service.ParkingSpotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingSpotServiceImpl implements ParkingSpotService {

	private final ParkingSpotRepository parkingSpotRepository;
	private final ParkingSpotMapper parkingSpotMapper;

	ParkingSpotServiceImpl(ParkingSpotRepository parkingSpotRepository, ParkingSpotMapper parkingSpotMapper) {
		this.parkingSpotRepository = parkingSpotRepository;
		this.parkingSpotMapper = parkingSpotMapper;
	}

	@Override
	@Transactional
	public ParkingSpotResponse createParkingSpot(ParkingSpotRequest request) {
		ParkingSpot parkingSpot = parkingSpotMapper.toEntity(request);
		parkingSpotRepository.save(parkingSpot);

		return parkingSpotMapper.toResponse(parkingSpot);
	}

	@Override
	@Transactional
	public ParkingSpotResponse updateParkingSpot(Long id, ParkingSpotRequest request) {
		ParkingSpot parkingSpot = parkingSpotRepository.findById(id).orElse(null);
		if (parkingSpot == null) {
			throw new ConflictException(
					"Parking spot with id " + id + " not found"
			);
		}

		parkingSpotMapper.updateEntity(parkingSpot, request);
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
}
