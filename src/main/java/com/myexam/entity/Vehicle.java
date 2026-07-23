package com.myexam.entity;

import com.myexam.entity.type.VehicleType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(
		name = "vehicles",
		uniqueConstraints = {
				@UniqueConstraint(
						name = "uk_vehicle_license_plate",
						columnNames = "license_plate"
				)
		})
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "license_plate", nullable = false, length = 20)
	private String licensePlate;

	@Column(length = 50)
	private String model;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 30)
	private VehicleType type;

	@ManyToMany(mappedBy = "vehicles")
	private Set<Driver> drivers = new HashSet<>();

	@OneToMany(mappedBy = "vehicle")
	private List<ParkingTicket> parkingTickets = new ArrayList<>();
}

