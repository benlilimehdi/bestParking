package com.esprit.parking.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public String newSubscription(@RequestParam(value ="idSubscriber") long idSubscriber,@RequestParam(value ="idTypeSubscription") int idTypeSubscription) {
		
		Subscription newSub= new Subscription();
		Subscribers sub= subscriberMetier.getSubscribersByID(idSubscriber);
		TypeSubscription typeSub = typeSubscriptionMetier.getTypeSubscriptionByID(idTypeSubscription);
		newSub.setDateInscription(new Date());
		newSub.setDateFinInscription(generateDateFin(typeSub));
		
		newSub.setIsIn("in");
		newSub.setTypeSubscription(typeSub);
		
		newSub.setSubscriber(sub);
		System.out.println(sub.getIdSubscriber());
		newSub.setPlace(placeMetier.getFreePlaceByFloorAsc().get());
		
		subscriptionMetier.addSubscription(newSub);
		return "abonnement effectué + date abonnement, prix a payer"+typeSub.getPrix();
	}


	public String checkInSubscriber() {
		
		
		
		
		return "";
	}
	
	
	
	
	
	
	
	
	
	
	
	private Date generateDateFin(TypeSubscription typeSub) {
		Calendar calendar=Calendar.getInstance();
		switch (typeSub.getIdType())
		{
		case 5: calendar.add(Calendar.MONTH, 1); ;
		break;
		case 6: calendar.add(Calendar.MONTH, 3);;
		break;
		case 7: calendar.add(Calendar.MONTH, 6);;
		break;
		}
		return calendar.getTime();
	}
	
}
