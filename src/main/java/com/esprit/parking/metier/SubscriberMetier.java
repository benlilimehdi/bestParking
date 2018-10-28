package com.esprit.parking.metier;

import java.util.List;

import com.esprit.parking.entities.Subscribers;

public interface SubscriberMetier {
	
	public Subscribers addSubscribers(Subscribers p);
	public void editSubscribers(Subscribers p);
	
	public Subscribers getSubscribersByID(long idSubscribers);
	
	public List<Subscribers> getAllSubscribers(int page, int size);
	
	public Subscribers getSubscribersByName(String firstName, String lastName);
	
	
	

}
