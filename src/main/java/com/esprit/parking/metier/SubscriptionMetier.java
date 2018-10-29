package com.esprit.parking.metier;

import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.esprit.parking.entities.Subscribers;
import com.esprit.parking.entities.Subscription;

public interface SubscriptionMetier {
	
	
	public Subscription addSubscription(Subscription p);
	public void editSubscription(Subscription p);
	
	public Subscription getSubscriptionByID(long idSubscription);
	
	public Collection<Subscription> getAllSubscription(int page, int size);
	public List<Subscription> getAllSubscriptionByIdSubscriber(long idSubscriber);
	public String newSubscription(long idSubscriber, int idTypeSubscription);
	
	public String checkInSbscription(long idSubscriber);
	public String checkOutSubscription(long idSubscriber);

	public Subscription getNewSubscription(Subscription p);
	
}
