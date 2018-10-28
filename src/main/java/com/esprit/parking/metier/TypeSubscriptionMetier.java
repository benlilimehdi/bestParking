package com.esprit.parking.metier;

import java.util.List;
import java.util.Optional;

import com.esprit.parking.entities.TypeSubscription;

public interface TypeSubscriptionMetier {
	
	public TypeSubscription addTypeSubscription(TypeSubscription p);
	public void editTypeSubscription(TypeSubscription p);
	
	public TypeSubscription getTypeSubscriptionByID(int idTypeSubscription);
	
	public List<TypeSubscription> getAllTypeSubscription();
	
	

}
