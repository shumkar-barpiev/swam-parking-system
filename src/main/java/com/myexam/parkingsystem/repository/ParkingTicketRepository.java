package com.myexam.parkingsystem.repository;

import com.myexam.parkingsystem.entity.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingTicketRepository extends JpaRepository<ParkingTicket, Long> {
}
