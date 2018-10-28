package com.esprit.parking.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.entities.TypeSubscription;
import com.esprit.parking.metier.TypeSubscriptionMetierImpl;
import com.esprit.parking.repository.TypeSubscriptionRepository;

@RestController
public class TypeSubscriptionService {
	
	@Autowired
	private TypeSubscriptionMetierImpl typeSubscriptionMetier ;
	@Autowired
	private TypeSubscriptionRepository typeSubscriptionRepository;
	
	
	@RequestMapping(value = "getAllTypeSubscription", method = RequestMethod.GET)
	public List<TypeSubscription> getAllTypeSubscription() {
		return typeSubscriptionMetier.getAllTypeSubscription();
	}
	
	
	
	
	
	
	
	///////////////////
	@RequestMapping(value = "/getTypeSubscriptionByLibelle", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public @ResponseBody Optional<TypeSubscription> getSubscriptionByLibelle( @RequestParam(value="libellee") String libelle) {
		
		Optional<TypeSubscription> t =null;
		try (Stream<TypeSubscription> stream = typeSubscriptionRepository.getTypeByLibelle(libelle)) {
			t=stream.findFirst();
		}
		
		return t;
	}
	////////////////////
	
	@RequestMapping(value = "editPriceTypeSubscription", method = RequestMethod.POST)
	@Transactional(readOnly = true)
	public void editPriceTypeSubscription( @RequestParam(value = "idtypeSubscription")int idtypeSubscription,@RequestParam(value = "price")float price) {
		
		
		TypeSubscription type=typeSubscriptionMetier.getTypeSubscriptionByID(idtypeSubscription);
		System.out.println("****************"+type.getIdType()+"***"+type.getPrix());
		type.setPrix(price);
		System.out.println("****************"+type.getIdType()+"***"+type.getPrix());
		 //typeSubscriptionMetier.editTypeSubscription(type);
		typeSubscriptionRepository.save(type);
	}
	
	
	

}
