package com.myexam.parkingsystem.service.implementation;

import com.myexam.parkingsystem.config.global.exception.ConflictException;
import com.myexam.parkingsystem.config.global.exception.ResourceNotFoundException;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneRequest;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneResponse;
import com.myexam.parkingsystem.entity.ParkingZone;
import com.myexam.parkingsystem.mapper.ParkingZoneMapper;
import com.myexam.parkingsystem.repository.ParkingZoneRepository;
import com.myexam.parkingsystem.service.ParkingZoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ParkingZoneServiceImpl implements ParkingZoneService {
	private final ParkingZoneRepository parkingZoneRepository;
	private final ParkingZoneMapper parkingZoneMapper;

	public ParkingZoneServiceImpl(ParkingZoneRepository parkingZoneRepository, ParkingZoneMapper parkingZoneMapper) {
		this.parkingZoneRepository = parkingZoneRepository;
		this.parkingZoneMapper = parkingZoneMapper;
	}

	@Override
	@Transactional
	public ParkingZoneResponse createParkingZone(ParkingZoneRequest request) {
		if (parkingZoneRepository.existsByNameIgnoreCase(request.getName().trim())) {
			throw new ConflictException(
					"A parking zone with this name already exists"
			);
		}

		if (parkingZoneRepository.existsByCodeIgnoreCase(request.getCode().trim())) {
			throw new ConflictException(
					"A parking zone with this code already exists"
			);
		}

		ParkingZone parkingZone = parkingZoneMapper.toEntity(request);
		ParkingZone savedParkingZone = parkingZoneRepository.save(parkingZone);

		return parkingZoneMapper.toResponse(savedParkingZone);
	}

	@Override
	@Transactional
	public ParkingZoneResponse updateParkingZone(Long id, ParkingZoneRequest request) {
		ParkingZone parkingZone = findParkingZoneById(id);

		if (parkingZoneRepository.existsByNameIgnoreCaseAndIdNot(request.getName().trim(), id)) {
			throw new ConflictException(
					"A parking zone with this name already exists"
			);
		}

		if (parkingZoneRepository.existsByCodeIgnoreCaseAndIdNot(request.getCode().trim(), id)) {
			throw new ConflictException(
					"A parking zone with this code already exists"
			);
		}

		parkingZoneMapper.updateEntity(parkingZone, request);
		ParkingZone updatedParkingZone = parkingZoneRepository.save(parkingZone);

		return parkingZoneMapper.toResponse(updatedParkingZone);
	}

	@Override
	@Transactional(readOnly = true)
	public ParkingZoneResponse getParkingZone(Long id) {
		ParkingZone parkingZone = findParkingZoneById(id);
		return parkingZoneMapper.toResponse(parkingZone);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkingZoneResponse> getParkingZones() {
		return parkingZoneRepository.findAll()
				.stream()
				.map(parkingZoneMapper::toResponse)
				.toList();
	}

	@Override
	@Transactional
	public void deleteParkingZone(Long id) {
		ParkingZone parkingZone = findParkingZoneById(id);
		parkingZoneRepository.delete(parkingZone);
	}

	private ParkingZone findParkingZoneById(Long id) {
		return parkingZoneRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						"Parking zone not found with id: " + id
				));
	}
}
