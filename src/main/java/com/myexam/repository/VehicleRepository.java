package com.myexam.repository;

import com.myexam.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
	Optional<Vehicle> findByLicensePlateIgnoreCase(String drivingLicenseNumber);

	boolean existsByLicensePlateIgnoreCase(String drivingLicenseNumber);
}
