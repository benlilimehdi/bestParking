package com.esprit.parking.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.entities.Place;
import com.esprit.parking.metier.PlaceMetier;
import com.esprit.parking.metier.PlaceMetierImpl;
import com.esprit.parking.repository.PlaceRepository;

@RestController
public class PlaceService {

	@Autowired
	private PlaceRepository placeRepository;
	@Autowired
	private PlaceMetier placeMetier;

	@RequestMapping(value = "/addPlace", method = RequestMethod.POST)
	public Place savePlace(Place p) {
		// p.setParking(new Parking(1,"FirstParking",20));
		return placeRepository.save(p);

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "getAllPlaces")
	public Page<Place> getAllPlaces(int page, int size) {
		return placeRepository.findAll(new PageRequest(page, size));

	}

	@RequestMapping(value = "getOneFreePlace")
	@Transactional(readOnly = true)
	public Optional<Place> getAllFreePlaces() {
		return placeMetier.getFreePlaceByFloorAsc();
	}

	@RequestMapping(value = "/getAllFreePlaces2")
	@Transactional(readOnly = true)
	public List<Place> getAllFreePlaces2() {
		List<Place> l = new ArrayList<>();
		try (Stream<Place> stream = placeRepository.findByFreePlaceReturnStream(0)) {
			stream.forEach(x -> l.add(x));
		}
		return l;
	}

	
	@RequestMapping(value = "/countFreePlaces")
	@Transactional(readOnly = true)
	public int coutFreePlace() {
		int x = 0;
		try (Stream<Place> stream = placeRepository.getFreePlaceByFloorAsca()) {
			x = (int) stream.count();
		}
		// p.setParking(new Parking(1,"FirstParking",20));
		return x;
	}

}
