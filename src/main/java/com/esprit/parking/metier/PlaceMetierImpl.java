package com.esprit.parking.metier;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.esprit.parking.entities.Place;
import com.esprit.parking.repository.PlaceRepository;


@Service
public class PlaceMetierImpl implements PlaceMetier {
	
	
	@Autowired
	private PlaceRepository placeRepository;
	

	@Override
	public Place addPlace(Place p) {
		// TODO Auto-generated method stub
		return placeRepository.save(p);
	}

	@Override
	public void editPlace(Place p) {
		// TODO Auto-generated method stub
		 placeRepository.save(p);
	}

	@Override
	public Place getPlaceByID(long idPlace) {
		// TODO Auto-generated method stub
		return placeRepository.getOne(idPlace);
	}

	public int countFreePlaces() {
		return (int) placeRepository.findByFreePlaceReturnStream(0).count();
		
	}

	public Optional<Place> getFreePlaceByFloorAsc() {
		Optional<Place> p = null ;
		
		if  (countFreePlaces()!=0) {
			p=placeRepository.getFreePlaceByFloorAsca().findFirst();
		}
		return p;
	}

}
