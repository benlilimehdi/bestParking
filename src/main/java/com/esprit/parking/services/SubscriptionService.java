package com.esprit.parking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.entities.Subscribers;
import com.esprit.parking.entities.Subscription;
import com.esprit.parking.metier.PlaceMetierImpl;
import com.esprit.parking.metier.SubscriptionMetierImpl;
import com.esprit.parking.metier.TypeSubscriptionMetierImpl;
import com.esprit.parking.repository.SubscriptionRepository;

@RestController
public class SubscriptionService {
	
	//@Autowired
	private SubscriptionMetierImpl subscriptionMetier;
	
	private PlaceMetierImpl placeMetier;
	private TypeSubscriptionMetierImpl typeSubscriptionMetier;
	
	
	@RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
	public void newSubscription(long idSubscriber, int idTypeSubscription) {
		
		
		

	}
	
	
	
	
}
