package com.esprit.parking.metier;

import java.util.Optional;


import com.esprit.parking.entities.Place;

public interface PlaceMetier {
	
	
	public Place addPlace(Place p);
	public void editPlace(Place p);
	
	public Place getPlaceByID(long idPlace);
	
	public int countFreePlaces();
	
	
	public Optional<Place> getFreePlaceByFloorAsc ();
	
	

}
