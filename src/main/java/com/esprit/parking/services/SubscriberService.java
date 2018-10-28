package com.esprit.parking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.entities.Subscribers;
import com.esprit.parking.metier.SubscriberMetierImpl;
import com.esprit.parking.repository.SubscribersRepository;

@RestController
public class SubscriberService {
	
	
	@Autowired
	private SubscriberMetierImpl subscriberMetier;
	
	

	@RequestMapping(value = "/inscriptionSubscriber", method = RequestMethod.POST)
	public void InscriptionSubscripber(Subscribers p) {
		 subscriberMetier.addSubscribers(p);

	}
	
	
	
	
}
