package com.myexam.parkingsystem.service.implementation;

import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketRequest;
import com.myexam.parkingsystem.dto.parking_ticket.ParkingTicketResponse;
import com.myexam.parkingsystem.entity.ParkingTicket;
import com.myexam.parkingsystem.mapper.ParkingTicketMapper;
import com.myexam.parkingsystem.repository.ParkingTicketRepository;
import com.myexam.parkingsystem.service.ParkingTicketService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingTicketServiceImpl implements ParkingTicketService {
	private final ParkingTicketRepository parkingTicketRepository;
	private final ParkingTicketMapper parkingTicketMapper;

	ParkingTicketServiceImpl(ParkingTicketRepository parkingTicketRepository, ParkingTicketMapper parkingTicketMapper) {
		this.parkingTicketRepository = parkingTicketRepository;
		this.parkingTicketMapper = parkingTicketMapper;
	}

	@Override
	@Transactional
	public ParkingTicketResponse createParkingTicket(ParkingTicketRequest parkingTicketRequest) {
		ParkingTicket parkingTicket = parkingTicketMapper.toEntity(parkingTicketRequest);
		parkingTicketRepository.save(parkingTicket);

		return parkingTicketMapper.toResponse(parkingTicket);
	}

	@Override
	@Transactional
	public ParkingTicketResponse updateParkingTicket(Long id, ParkingTicketRequest parkingTicketRequest) {
		ParkingTicket parkingTicket = parkingTicketRepository.findById(id).orElse(null);

		if (parkingTicket == null) {
			throw new RuntimeException(
					"Parking ticket with id " + id + " not found"
			);
		}

		parkingTicketMapper.updateEntity(parkingTicket, parkingTicketRequest);
		parkingTicketRepository.save(parkingTicket);
		return parkingTicketMapper.toResponse(parkingTicket);
	}

	@Override
	@Transactional(readOnly = true)
	public ParkingTicketResponse getParkingTicketById(Long id) {
		ParkingTicket parkingTicket = parkingTicketRepository.findById(id).orElse(null);
		if (parkingTicket == null) {
			throw new RuntimeException(
					"Parking ticket with id " + id + " not found"
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
	public void deleteParkingZone(Long id) {
		ParkingTicket parkingTicket = parkingTicketRepository.findById(id).orElse(null);
		parkingTicketRepository.delete(parkingTicket);
	}
}
