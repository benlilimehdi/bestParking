package com.esprit.parking.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.annotation.Secured;
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
	private TypeSubscriptionMetierImpl typeSubscriptionMetier;
	@Autowired
	private TypeSubscriptionRepository typeSubscriptionRepository;

	
	
	@RequestMapping(value = "getAllTypeSubscription", method = RequestMethod.GET)
	public List<TypeSubscription> getAllTypeSubscription() {
		return typeSubscriptionMetier.getAllTypeSubscription();
	}

	
	@RequestMapping(value = "/getTypeSubscriptionById", method = RequestMethod.POST)
	@Transactional
	public TypeSubscription getSubscriptionType(@RequestParam(value = "idType") int idType) {
		TypeSubscription t = typeSubscriptionRepository.findById(idType).get();
		return t;
	}

	@Secured(value= {"ROLE_MANAGER"})
	@RequestMapping(value = "editPriceTypeSubscription", method = RequestMethod.GET)
	public void editPriceTypeSubscription(@RequestParam(value = "idtypeSubscription") int idtypeSubscription,
			@RequestParam(value = "price") float price) {
		typeSubscriptionMetier.editPriceTypeSubscription(idtypeSubscription, price);
	}

}
