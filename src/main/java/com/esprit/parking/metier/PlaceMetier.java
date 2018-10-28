package com.esprit.parking.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.esprit.parking.entities.Place;

public interface PlaceMetier {
	
	
	public Place addPlace(Place p);
	public void editPlace(Place p);
	
	public Place getPlaceByID(long idPlace);
	
	public int countFreePlaces();
	
	
	public Optional<Place> getFreePlaceByFloorAsc ();
	
	

}
