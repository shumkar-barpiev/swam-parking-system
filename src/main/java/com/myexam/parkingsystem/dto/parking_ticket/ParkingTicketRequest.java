package com.myexam.parkingsystem.dto.parking_ticket;

import com.myexam.parkingsystem.entity.type.TicketStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for {@link com.myexam.parkingsystem.entity.ParkingTicket}
 */
@Value
public class ParkingTicketRequest implements Serializable {
	@Size(max = 40)
	@NotBlank(message = "Ticket number can't be blank.")
	String ticketNumber;

	@NotNull(message = "Entry time can't be null.")
	LocalDateTime entryTime;

	LocalDateTime exitTime;

	@NotNull(message = "Ticket status must be provided.")
	TicketStatus status;

	@NotNull
	BigDecimal totalCost;
}