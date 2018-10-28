package com.esprit.parking.repository;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esprit.parking.entities.Place;

public interface PlaceRepository extends JpaRepository<Place, Long> {
	
	@Query("select p from Place p where p.etat = :etat  Order By p.etage ASC")
	Stream<Place> findByFreePlaceReturnStream(@Param("etat") int etat);
	
	@Query("select p from Place p where p.etat = 0")
	Stream<Place> getFreePlaceByFloorAsca();
	 
	// OrderBy ASC

}
