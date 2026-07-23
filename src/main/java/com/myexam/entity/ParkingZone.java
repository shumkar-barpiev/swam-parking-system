package com.myexam.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "parking_zones",
		uniqueConstraints = {
				@UniqueConstraint(name = "uk_parking_zone_name", columnNames = "name"),
				@UniqueConstraint(name = "uk_parking_zone_code", columnNames = "code")
		})
public class ParkingZone {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name;

	@Column(nullable = false, length = 100)
	private String code;

	@Column(length = 250)
	private String description;

	@Column(name = "hourly_rate", nullable = false, precision = 10, scale = 2)
	private BigDecimal hourlyRate;


	@Column(nullable = false)
	private boolean active = true;

	@OneToMany(
			mappedBy = "parkingZone",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<ParkingSpot> parkingSpots;

	public void addParkingSpot(ParkingSpot parkingSpot) {
		parkingSpots.add(parkingSpot);
		parkingSpot.setParkingZone(this);
	}

	public void removeParkingSpot(ParkingSpot parkingSpot) {
		parkingSpots.remove(parkingSpot);
		parkingSpot.setParkingZone(null);
	}
}
