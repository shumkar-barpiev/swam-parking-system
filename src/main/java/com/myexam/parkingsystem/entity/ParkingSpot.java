package com.myexam.parkingsystem.entity;

import com.myexam.parkingsystem.entity.type.ParkingSpotType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "parking_spots", uniqueConstraints = {
		@UniqueConstraint(name = "uk_parking_spot_number", columnNames = {"parking_zone_id", "spot_number"})
})
public class ParkingSpot {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "spot_number", nullable = false, length = 20)
	private String spotNumber;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private ParkingSpotType type;

	@Column(length = 20)
	private boolean active = true;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "parking_zone_id", nullable = false)
	private ParkingZone parkingZone;

	@OneToMany(mappedBy = "parkingSpot")
	private List<ParkingTicket> parkingTickets = new ArrayList<>();
}

