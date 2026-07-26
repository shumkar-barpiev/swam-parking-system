package com.myexam.parkingsystem.service.implementation;

import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketRequest;
import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketResponse;
import com.myexam.parkingsystem.entity.Driver;
import com.myexam.parkingsystem.entity.ParkingSpot;
import com.myexam.parkingsystem.entity.ParkingTicket;
import com.myexam.parkingsystem.entity.Vehicle;
import com.myexam.parkingsystem.mapper.ParkingTicketMapper;
import com.myexam.parkingsystem.repository.DriverRepository;
import com.myexam.parkingsystem.repository.ParkingSpotRepository;
import com.myexam.parkingsystem.repository.ParkingTicketRepository;
import com.myexam.parkingsystem.repository.VehicleRepository;
import com.myexam.parkingsystem.service.ParkingTicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingTicketServiceImpl implements ParkingTicketService {
	private final ParkingTicketRepository parkingTicketRepository;
	private final ParkingTicketMapper parkingTicketMapper;
	private final ParkingSpotRepository parkingSpotRepository;
	private final DriverRepository driverRepository;
	private final VehicleRepository vehicleRepository;

	ParkingTicketServiceImpl(
			ParkingTicketRepository parkingTicketRepository,
			ParkingTicketMapper parkingTicketMapper,
			ParkingSpotRepository parkingSpotRepository,
			DriverRepository driverRepository,
			VehicleRepository vehicleRepository
	) {
		this.parkingTicketRepository = parkingTicketRepository;
		this.parkingTicketMapper = parkingTicketMapper;
		this.parkingSpotRepository = parkingSpotRepository;
		this.driverRepository = driverRepository;
		this.vehicleRepository = vehicleRepository;
	}

	@Override
	@Transactional
	public ParkingTicketResponse createParkingTicket(ParkingTicketRequest request) {
		ParkingTicket parkingTicket = parkingTicketMapper.toEntity(request);

		Driver driver = getDriverById(request.getDriverId());
		Vehicle vehicle = getVehicleById(request.getVehicleId());
		ParkingSpot parkingSpot = getParkingSpotById(request.getParkingSpotId());

		parkingTicket.setDriver(driver);
		parkingTicket.setVehicle(vehicle);
		parkingTicket.setParkingSpot(parkingSpot);

		parkingTicketRepository.save(parkingTicket);

		return parkingTicketMapper.toResponse(parkingTicket);
	}

	@Override
	@Transactional
	public ParkingTicketResponse updateParkingTicket(Long id, ParkingTicketRequest request) {
		ParkingTicket parkingTicket = parkingTicketRepository.findById(id).orElse(null);

		if (parkingTicket == null) {
			throw new RuntimeException(
					"Parking ticket not found with id " + id
			);
		}

		parkingTicketMapper.updateEntity(parkingTicket, request);

		parkingTicket.setDriver(getDriverById(request.getDriverId()));
		parkingTicket.setVehicle(getVehicleById(request.getVehicleId()));
		parkingTicket.setParkingSpot(getParkingSpotById(request.getParkingSpotId()));

		parkingTicketRepository.save(parkingTicket);
		return parkingTicketMapper.toResponse(parkingTicket);
	}

	@Override
	@Transactional(readOnly = true)
	public ParkingTicketResponse getParkingTicketById(Long id) {
		ParkingTicket parkingTicket = parkingTicketRepository.findById(id).orElse(null);
		if (parkingTicket == null) {
			throw new RuntimeException(
					"Parking ticket not found with id " + id
			);
		}

		return parkingTicketMapper.toResponse(parkingTicket);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ParkingTicketResponse> getParkingTickets() {
		return parkingTicketRepository.findAll().stream().map(parkingTicketMapper::toResponse).collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void deleteParkingTicket(Long id) {
		ParkingTicket parkingTicket = parkingTicketRepository.findById(id).orElse(null);
		parkingTicketRepository.delete(parkingTicket);
	}

	private Driver getDriverById(Long driverId) {
		return driverRepository.findById(driverId).orElseThrow(() ->
				new RuntimeException("Driver not found with id " + driverId));
	}

	private Vehicle getVehicleById(Long vehicleId) {
		return vehicleRepository.findById(vehicleId).orElseThrow(() ->
				new RuntimeException("Vehicle not found with id " + vehicleId));
	}

	private ParkingSpot getParkingSpotById(Long parkingSpotId) {
		return parkingSpotRepository.findById(parkingSpotId).orElseThrow(() ->
				new RuntimeException("Parking spot not found with id " + parkingSpotId));
	}
}
