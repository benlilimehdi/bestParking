package com.esprit.parking.metier;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esprit.parking.entities.Place;
import com.esprit.parking.entities.Subscribers;
import com.esprit.parking.entities.Subscription;
import com.esprit.parking.entities.TypeSubscription;
import com.esprit.parking.repository.SubscriptionRepository;
@Service
public class SubscriptionMetierImpl  implements SubscriptionMetier{

	@Autowired
	private SubscriptionRepository subscriptionRepository;
	
	@Autowired
	private SubscriptionMetierImpl subscriptionMetier;
	@Autowired
	private SubscriberMetierImpl subscriberMetier;
	@Autowired
	private PlaceMetierImpl placeMetier;
	@Autowired
	private TypeSubscriptionMetierImpl typeSubscriptionMetier;
	
	
	@Override
	public Subscription addSubscription(Subscription p) {
		return subscriptionRepository.save(p);
	}

	@Override
	public void editSubscription(Subscription p) {
		subscriptionRepository.save(p);
	}

	@Override
	public Subscription getSubscriptionByID(long idSubscription) {
		return subscriptionRepository.getOne(idSubscription);
	}

	@Override
	public Collection<Subscription> getAllSubscription(int page, int size) {
		return subscriptionRepository.findAll();
	}


	@Override
	public String checkInSbscription(long idSubscriber) {

		Subscribers subsciber = subscriberMetier.getSubscribersByID(idSubscriber);
		if (subsciber != null) {
			List<Subscription> lst = subscriptionMetier.getAllSubscriptionByIdSubscriber(idSubscriber);
			if (lst != null) {
				// System.out.println("***********collection not null");
				Iterator<Subscription> it = lst.iterator();
				boolean valideSub = false;
				boolean IsAlreadyOut = false;
				boolean test = false;
				Subscription s = new Subscription();
				while (it.hasNext() && (!test)) {
					s = it.next();
					valideSub = (s.getDateFinInscription().after(new Date()));
					IsAlreadyOut = (s.getIsIn().equals("out"));
					test = valideSub && IsAlreadyOut;
				}

				if (test) {
					s.setIsIn("in");
					subscriptionMetier.editSubscription(s);
					return "welcome";
				}

				else {
					if (!valideSub)
						return "expired subscription";
					else if ((!IsAlreadyOut))
						return "subscription already used";

				}
			}
		}
		return "subsciber=null";
	}

	@Override
	public String checkOutSubscription(long idSubscriber) {
		Subscribers subsciber = subscriberMetier.getSubscribersByID(idSubscriber);
		if (subsciber != null) {
			List<Subscription> lst = subscriptionMetier.getAllSubscriptionByIdSubscriber(idSubscriber);
			if (lst != null) {
				// System.out.println("***********collection not null");
				Iterator<Subscription> it = lst.iterator();
				Subscription valideSubscription = null;
				Subscription s = new Subscription();
				while (it.hasNext()) {
					s = it.next();
					Date lastDate = new Date();
					if (s.getDateFinInscription().after(lastDate)) {
						lastDate = s.getDateFinInscription();
						valideSubscription = s;
					}

				}
				if (s != null) {
					if ((s.getIsIn().equals("in"))) {
						s.setIsIn("out");
						subscriptionMetier.editSubscription(s);
						return "GoodBye";
					} else {
						return "subscription already used";

					}
				}
			}
		}

		return "Probelm of check out! pls contact the administration;";
	}

	@Override
	public Subscription getNewSubscription(Subscription p) {
		return null;
	}

	@Override
	public List<Subscription> getAllSubscriptionByIdSubscriber(long idSubscriber) {
		
		List<Subscription> l=  subscriptionRepository.getAllSubscriptionByIdSubscriberStream(idSubscriber) ;
		
		return l;
	}

	@Override
	@Transactional(readOnly = true)
	public String newSubscription(long idSubscriber, int idTypeSubscription) {
		if (placeMetier.countFreePlaces() != 0) {

			Subscription newSub = new Subscription();
			Subscribers sub = subscriberMetier.getSubscribersByID(idSubscriber);
			TypeSubscription typeSub = typeSubscriptionMetier.getTypeSubscriptionByID(idTypeSubscription);
			Place p = placeMetier.getFreePlaceByFloorAsc().get();

			newSub.setDateInscription(new Date());
			newSub.setDateFinInscription(generateDateFin(typeSub));
			newSub.setIsIn("out");
			newSub.setTypeSubscription(typeSub);
			newSub.setSubscriber(sub);
			newSub.setPlace(p);

			p.setEtat(1);
			placeMetier.editPlace(p);

			subscriptionMetier.addSubscription(newSub);
			return "abonnement effectué + date abonnement, prix a payer" + typeSub.getPrix();
		} else
			return "no place dispo for the moment";

	}
	
	
	
	private Date generateDateFin(TypeSubscription typeSub) {
		Calendar calendar = Calendar.getInstance();
		switch (typeSub.getIdType()) {
		case 5:
			calendar.add(Calendar.MONTH, 1);

			break;
		case 6:
			calendar.add(Calendar.MONTH, 3);

			break;
		case 7:
			calendar.add(Calendar.MONTH, 6);

			break;
		}
		return calendar.getTime();
	}

}
