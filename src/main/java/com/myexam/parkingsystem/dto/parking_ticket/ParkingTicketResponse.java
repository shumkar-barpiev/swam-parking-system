package com.myexam.parkingsystem.dto.parking_ticket;

import com.myexam.parkingsystem.entity.type.TicketStatus;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.myexam.parkingsystem.entity.ParkingTicket}
 */
@Value
public class ParkingTicketResponse implements Serializable {
	Long id;
	String ticketNumber;
	LocalDateTime entryTime;
	LocalDateTime exitTime;
	TicketStatus status;
	BigDecimal totalCost;
}