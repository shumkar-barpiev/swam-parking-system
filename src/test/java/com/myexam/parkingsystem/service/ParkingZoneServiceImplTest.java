package com.myexam.parkingsystem.service;

import com.myexam.parkingsystem.config.global.exception.ConflictException;
import com.myexam.parkingsystem.config.global.exception.ResourceNotFoundException;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneRequest;
import com.myexam.parkingsystem.dto.parking_zone.ParkingZoneResponse;
import com.myexam.parkingsystem.entity.ParkingZone;
import com.myexam.parkingsystem.mapper.ParkingZoneMapper;
import com.myexam.parkingsystem.repository.ParkingZoneRepository;
import com.myexam.parkingsystem.service.implementation.ParkingZoneServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParkingZoneServiceImplTest {

	@Mock
	private ParkingZoneRepository parkingZoneRepository;

	@Mock
	private ParkingZoneMapper parkingZoneMapper;

	@InjectMocks
	private ParkingZoneServiceImpl parkingZoneService;

	private ParkingZoneRequest request;
	private ParkingZone zoneEntity;
	private ParkingZoneResponse zoneResponse;

	@BeforeEach
	void setUp() {
		request = new ParkingZoneRequest(
				"Downtown Zone",
				"ZONE-A",
				"Central area parking",
				new BigDecimal("3.50"),
				true
		);

		zoneEntity = new ParkingZone();
		zoneEntity.setId(1L);
		zoneEntity.setName("Downtown Zone");
		zoneEntity.setCode("ZONE-A");
		zoneEntity.setDescription("Central area parking");
		zoneEntity.setHourlyRate(new BigDecimal("3.50"));
		zoneEntity.setActive(true);

		zoneResponse = new ParkingZoneResponse(
				1L,
				"Downtown Zone",
				"ZONE-A",
				"Central area parking",
				new BigDecimal("3.50"),
				true,
				List.of()
		);
	}

	@Test
	void createParkingZone_Success() {
		when(parkingZoneRepository.existsByNameIgnoreCase("Downtown Zone")).thenReturn(false);
		when(parkingZoneRepository.existsByCodeIgnoreCase("ZONE-A")).thenReturn(false);
		when(parkingZoneMapper.toEntity(request)).thenReturn(zoneEntity);
		when(parkingZoneRepository.save(zoneEntity)).thenReturn(zoneEntity);
		when(parkingZoneMapper.toResponse(zoneEntity)).thenReturn(zoneResponse);

		ParkingZoneResponse result = parkingZoneService.createParkingZone(request);

		assertNotNull(result);
		assertEquals(1L, result.getId());
		assertEquals("Downtown Zone", result.getName());
		assertEquals("ZONE-A", result.getCode());
		verify(parkingZoneRepository).save(zoneEntity);
	}

	@Test
	void createParkingZone_NameConflict() {
		when(parkingZoneRepository.existsByNameIgnoreCase("Downtown Zone")).thenReturn(true);

		ConflictException exception = assertThrows(
				ConflictException.class,
				() -> parkingZoneService.createParkingZone(request)
		);

		assertEquals("A parking zone with this name already exists", exception.getMessage());
		verify(parkingZoneRepository, never()).save(any());
	}

	@Test
	void createParkingZone_CodeConflict() {
		when(parkingZoneRepository.existsByNameIgnoreCase("Downtown Zone")).thenReturn(false);
		when(parkingZoneRepository.existsByCodeIgnoreCase("ZONE-A")).thenReturn(true);

		ConflictException exception = assertThrows(
				ConflictException.class,
				() -> parkingZoneService.createParkingZone(request)
		);

		assertEquals("A parking zone with this code already exists", exception.getMessage());
		verify(parkingZoneRepository, never()).save(any());
	}

	@Test
	void getParkingZone_Success() {
		when(parkingZoneRepository.findById(1L)).thenReturn(Optional.of(zoneEntity));
		when(parkingZoneMapper.toResponse(zoneEntity)).thenReturn(zoneResponse);

		ParkingZoneResponse result = parkingZoneService.getParkingZone(1L);

		assertNotNull(result);
		assertEquals(1L, result.getId());
	}

	@Test
	void getParkingZone_NotFound() {
		when(parkingZoneRepository.findById(99L)).thenReturn(Optional.empty());

		ResourceNotFoundException exception = assertThrows(
				ResourceNotFoundException.class,
				() -> parkingZoneService.getParkingZone(99L)
		);

		assertEquals("Parking zone not found with id: 99", exception.getMessage());
	}

	@Test
	void getParkingZones_Success() {
		when(parkingZoneRepository.findAll()).thenReturn(List.of(zoneEntity));
		when(parkingZoneMapper.toResponse(zoneEntity)).thenReturn(zoneResponse);

		List<ParkingZoneResponse> results = parkingZoneService.getParkingZones();

		assertEquals(1, results.size());
		assertEquals("Downtown Zone", results.get(0).getName());
	}

	@Test
	void updateParkingZone_Success() {
		when(parkingZoneRepository.findById(1L)).thenReturn(Optional.of(zoneEntity));
		when(parkingZoneRepository.existsByNameIgnoreCaseAndIdNot("Downtown Zone", 1L)).thenReturn(false);
		when(parkingZoneRepository.existsByCodeIgnoreCaseAndIdNot("ZONE-A", 1L)).thenReturn(false);
		when(parkingZoneRepository.save(zoneEntity)).thenReturn(zoneEntity);
		when(parkingZoneMapper.toResponse(zoneEntity)).thenReturn(zoneResponse);

		ParkingZoneResponse result = parkingZoneService.updateParkingZone(1L, request);

		assertNotNull(result);
		verify(parkingZoneMapper).updateEntity(zoneEntity, request);
		verify(parkingZoneRepository).save(zoneEntity);
	}

	@Test
	void updateParkingZone_NameConflict() {
		when(parkingZoneRepository.findById(1L)).thenReturn(Optional.of(zoneEntity));
		when(parkingZoneRepository.existsByNameIgnoreCaseAndIdNot("Downtown Zone", 1L)).thenReturn(true);

		ConflictException exception = assertThrows(
				ConflictException.class,
				() -> parkingZoneService.updateParkingZone(1L, request)
		);

		assertEquals("A parking zone with this name already exists", exception.getMessage());
		verify(parkingZoneRepository, never()).save(any());
	}

	@Test
	void updateParkingZone_CodeConflict() {
		when(parkingZoneRepository.findById(1L)).thenReturn(Optional.of(zoneEntity));
		when(parkingZoneRepository.existsByNameIgnoreCaseAndIdNot("Downtown Zone", 1L)).thenReturn(false);
		when(parkingZoneRepository.existsByCodeIgnoreCaseAndIdNot("ZONE-A", 1L)).thenReturn(true);

		ConflictException exception = assertThrows(
				ConflictException.class,
				() -> parkingZoneService.updateParkingZone(1L, request)
		);

		assertEquals("A parking zone with this code already exists", exception.getMessage());
		verify(parkingZoneRepository, never()).save(any());
	}

	@Test
	void deleteParkingZone_Success() {
		when(parkingZoneRepository.findById(1L)).thenReturn(Optional.of(zoneEntity));

		parkingZoneService.deleteParkingZone(1L);

		verify(parkingZoneRepository).delete(zoneEntity);
	}

	@Test
	void deleteParkingZone_NotFound() {
		when(parkingZoneRepository.findById(99L)).thenReturn(Optional.empty());

		ResourceNotFoundException exception = assertThrows(
				ResourceNotFoundException.class,
				() -> parkingZoneService.deleteParkingZone(99L)
		);

		assertEquals("Parking zone not found with id: 99", exception.getMessage());
		verify(parkingZoneRepository, never()).delete(any());
	}
}
