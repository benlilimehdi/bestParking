package com.esprit.parking.metier;

import java.util.Collection;

import com.esprit.parking.entities.Subscribers;
import com.esprit.parking.entities.Subscription;

public interface SubscriptionMetier {
	
	
	public Subscription addSubscription(Subscription p);
	public void editSubscription(Subscription p);
	
	public Subscription getSubscriptionByID(long idSubscription);
	
	public Collection<Subscription> getAllSubscription(int page, int size);
	public Collection<Subscription> getAllSubscriptionBySubscriber(int page, int size , Subscribers subscriber);
	
	
	public Subscription checkInSbscription(Subscription p);
	public Subscription checkOutSubscription(Subscription p);

	public Subscription getNewSubscription(Subscription p);
	
}
