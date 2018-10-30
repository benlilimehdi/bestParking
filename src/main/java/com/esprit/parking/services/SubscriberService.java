package com.esprit.parking.services;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.entities.Subscribers;
import com.esprit.parking.metier.SubscriberMetierImpl;

@RestController
public class SubscriberService {
	
	
	@Autowired
	private SubscriberMetierImpl subscriberMetier;
	

	@RequestMapping(value = "/inscriptionSubscriber", method = RequestMethod.POST)
	public void InscriptionSubscripber(Subscribers p) {
		 subscriberMetier.addSubscribers(p);

	}
	
	@RequestMapping(value = "/editSubscriber", method = RequestMethod.POST)
	public void editSubscripber(Subscribers p) {
		 subscriberMetier.editSubscribers(p);
	}
	
}
