package com.myexam.parkingsystem.repository;

import com.myexam.parkingsystem.entity.ParkingZone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingZoneRepository extends JpaRepository<ParkingZone, Long> {
}
