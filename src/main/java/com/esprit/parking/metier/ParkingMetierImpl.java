package com.esprit.parking.metier;

import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.parking.entities.Parking;
import com.esprit.parking.repository.ParkingRepository;

public class ParkingMetierImpl implements ParkingMetier {

	@Autowired
	private ParkingRepository parkingRepository;
	
	
	
	@Override
	public Parking addParking(Parking p) {
		// TODO Auto-generated method stub
		return parkingRepository.save(p);
	}

	@Override
	public void editParking(Parking p) {
		// TODO Auto-generated method stub
		 parkingRepository.save(p);
	}

	@Override
	public Parking getParkingByID(long idParking) {
		// TODO Auto-generated method stub
		return parkingRepository.getOne(idParking);
	}

}
