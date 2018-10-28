package com.esprit.parking.metier;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.parking.entities.Subscribers;
import com.esprit.parking.entities.Subscription;
import com.esprit.parking.repository.SubscriptionRepository;

public class SubscriptionMetierImpl  implements SubscriptionMetier{

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	
	@Override
	public Subscription addSubscription(Subscription p) {
		//p.setDateInscription(new Date());
		return subscriptionRepository.save(p);
	}

	@Override
	public void editSubscription(Subscription p) {
		subscriptionRepository.save(p);
	}

	@Override
	public Subscription getSubscriptionByID(long idSubscription) {
		// TODO Auto-generated method stub
		return subscriptionRepository.getOne(idSubscription);
	}

	@Override
	public Collection<Subscription> getAllSubscription(int page, int size) {
		return subscriptionRepository.findAll();
	}

	@Override
	public Collection<Subscription> getAllSubscriptionBySubscriber(int page, int size, Subscribers subscriber) {
		
		return null;
	}

	@Override
	public Subscription checkInSbscription(Subscription p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscription checkOutSubscription(Subscription p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Subscription getNewSubscription(Subscription p) {
		// TODO Auto-generated method stub
		return null;
	}

}
