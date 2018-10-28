package com.esprit.parking.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esprit.parking.entities.TypeSubscription;

public interface TypeSubscriptionRepository extends JpaRepository<TypeSubscription, Integer> {

	@Query(nativeQuery = true,value="SELECT p FROM TYPE_SUBSCRIPTION p WHERE LOWER(p.libelle) = LOWER(:libelle)")
	Stream<TypeSubscription> getTypeByLibelle( String libelle);
	
}
