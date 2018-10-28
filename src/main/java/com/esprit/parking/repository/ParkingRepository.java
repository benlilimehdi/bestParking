package com.esprit.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esprit.parking.entities.Parking;

public interface ParkingRepository extends JpaRepository<Parking, Long> {

}
