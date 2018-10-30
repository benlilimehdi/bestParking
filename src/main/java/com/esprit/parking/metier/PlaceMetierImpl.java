package com.esprit.parking.metier;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	@Override
	public int countFreePlaces() {
		int x = 0;
		try (Stream<Place> stream = placeRepository.getFreePlaceByFloorAsca()) {
			x = (int) stream.count();
		}
		// p.setParking(new Parking(1,"FirstParking",20));
		return x;		
	}
	@Override
	@Transactional(readOnly=true)
	public Optional<Place> getFreePlaceByFloorAsc() {
		Optional<Place> p = null ;
		
		if  (countFreePlaces()!=0) {
			p=placeRepository.getFreePlaceByFloorAsca().findFirst();
		}
		return p;
	}

}
