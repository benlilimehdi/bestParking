package com.esprit.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.esprit.parking.entities.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

	// where s.ID_SUBSCRIBER   = :idSubscriber
	
	@Query(nativeQuery = true,value="select * from Subscription s where ID_SUBSCRIBER   = :idSubscriber ")
	List<Subscription> getAllSubscriptionByIdSubscriberStream(@Param("idSubscriber") long etat);
	
	
	
}
