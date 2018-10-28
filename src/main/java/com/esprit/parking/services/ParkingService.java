package com.esprit.parking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.entities.Parking;
import com.esprit.parking.repository.ParkingRepository;

@RestController
public class ParkingService  {
	
	@Autowired
	private ParkingRepository parkingRepository;
	
	@RequestMapping(value="/addparking",method=RequestMethod.GET)
	public Parking saveparking (Parking p) {
		return parkingRepository.save(p);
		
	}

}
