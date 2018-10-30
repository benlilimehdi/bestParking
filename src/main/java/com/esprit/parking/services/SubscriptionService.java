package com.esprit.parking.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.metier.SubscriptionMetierImpl;

@RestController
public class SubscriptionService {

	@Autowired
	private SubscriptionMetierImpl subscriptionMetier;

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
