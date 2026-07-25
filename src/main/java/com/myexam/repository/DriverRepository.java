package com.myexam.repository;

import com.myexam.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	List<Driver> findDriverByFullName(String name);

	Optional<Driver> findDriverByPhoneNumber(String phoneNumber);

	Optional<Driver> findDriverByEmail(String email);

	Optional<Driver> findDriverById(Long id);

	boolean existsByEmailIgnoreCase(String email);

	boolean existsByDrivingLicenseNumber(String drivingLicenseNumber);

	boolean existsByEmailIgnoreCaseAndIdNot(
			String email,
			Long id
	);

	boolean existsByDrivingLicenseNumberAndIdNot(
			String drivingLicenseNumber,
			Long id
	);
}
