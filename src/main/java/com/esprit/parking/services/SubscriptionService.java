package com.esprit.parking.services;

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.entities.Place;
import com.esprit.parking.entities.Subscribers;
import com.esprit.parking.entities.Subscription;
import com.esprit.parking.entities.TypeSubscription;
import com.esprit.parking.metier.PlaceMetierImpl;
import com.esprit.parking.metier.SubscriberMetierImpl;
import com.esprit.parking.metier.SubscriptionMetierImpl;
import com.esprit.parking.metier.TypeSubscriptionMetierImpl;

@RestController
public class SubscriptionService {

	@Autowired
	private SubscriptionMetierImpl subscriptionMetier;
	@Autowired
	private SubscriberMetierImpl subscriberMetier;
	@Autowired
	private PlaceMetierImpl placeMetier;
	@Autowired
	private TypeSubscriptionMetierImpl typeSubscriptionMetier;

	@RequestMapping(value = "/addSubscription", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public String newSubscription(@RequestParam(value = "idSubscriber") long idSubscriber,
			@RequestParam(value = "idTypeSubscription") int idTypeSubscription) {

		return subscriptionMetier.newSubscription(idSubscriber, idTypeSubscription);

	}

	@RequestMapping(value = "/checkOutSubscriber", method = RequestMethod.POST)
	public String checkOutSubscriber(@RequestParam(value = "idSubscriber") long idSubscriber) {
	return subscriptionMetier.checkOutSubscription(idSubscriber);
	}


	@RequestMapping(value = "/checkInSubscriber", method = RequestMethod.POST)
	public String checkInSubscriber(@RequestParam(value = "idSubscriber") long idSubscriber) {
		
		return subscriptionMetier.checkInSbscription(idSubscriber);
	
	}

	

}
