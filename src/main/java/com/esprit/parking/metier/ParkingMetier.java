package com.esprit.parking.metier;

import com.esprit.parking.entities.Parking;

public interface ParkingMetier {
	
	public Parking addParking(Parking p);
	public void editParking(Parking p);
	public Parking getParkingByID(long idParking);
	
	

}
