package com.myexam.parkingsystem.entity;

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
@Table(name = "drivers",
		uniqueConstraints = {
				@UniqueConstraint(name = "uk_driver_email", columnNames = "email"),
				@UniqueConstraint(name = "uk_driver_license", columnNames = "driving_license")
		}
)
public class Driver {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String fullName;

	@Column(nullable = false, length = 120, unique = true)
	private String email;

	@Column(length = 30)
	private String phoneNumber;

	@Column(name = "driving_license", nullable = false, length = 50)
	private String drivingLicenseNumber;

	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id", nullable = false, unique = true)
	private Address address;

	@ManyToMany
	@JoinTable(
			name = "driver_vehicle",
			joinColumns = @JoinColumn(name = "driver_id"),
			inverseJoinColumns = @JoinColumn(name = "vehicle_id"),
			uniqueConstraints = {
					@UniqueConstraint(
							name = "uk_driver_vehicle",
							columnNames = {"driver_id", "vehicle_id"}
					)
			}
	)
	private Set<Vehicle> vehicles = new HashSet<>();

	@OneToMany(mappedBy = "driver")
	private List<ParkingTicket> parkingTickets = new ArrayList<>();

	public void assignAddress(Address address) {
		this.address = address;

		if (address != null) {
			address.setDriver(this);
		}
	}

	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
		vehicle.getDrivers().add(this);
	}

	public void removeVehicle(Vehicle vehicle) {
		vehicles.remove(vehicle);
		vehicle.getDrivers().remove(this);
	}
}