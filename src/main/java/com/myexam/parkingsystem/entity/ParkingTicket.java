package com.myexam.parkingsystem.entity;

import com.myexam.parkingsystem.entity.type.TicketStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "parking_tickets",
		uniqueConstraints = {
				@UniqueConstraint(name = "uk_parking_ticket_number", columnNames = "ticket_number")
		},
		indexes = {
				@Index(name = "idx_ticket_spot_status", columnList = "parking_spot_id, status"),
				@Index(name = "idx_ticket_vehicle_status", columnList = "vehicle_id, status"),
		}
)
public class ParkingTicket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "ticket_number", nullable = false, length = 50)
	private String ticketNumber;

	@Column(name = "entry_time", nullable = false)
	private LocalDateTime entryTime;

	@Column(name = "exit_time")
	private LocalDateTime exitTime;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private TicketStatus status;

	@Column(nullable = false, length = 50, precision = 10, scale = 2)
	private BigDecimal totalCost;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "driver_id", nullable = false)
	private Driver driver;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "vehicle_id", nullable = false)
	private Vehicle vehicle;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "parking_spot_id", nullable = false)
	private ParkingSpot parkingSpot;
}
