package com.esprit.parking.metier;

import java.util.HashMap;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esprit.parking.entities.TypeSubscription;
import com.esprit.parking.repository.TypeSubscriptionRepository;
@Service
public class TypeSubscriptionMetierImpl implements TypeSubscriptionMetier {
	
	@Autowired
	private TypeSubscriptionRepository typeSubscriptionRepository;
	

	@Override
	public TypeSubscription addTypeSubscription(TypeSubscription p) {
		return typeSubscriptionRepository.save(p);
	}

	@Override
	public void editTypeSubscription(TypeSubscription p) {
		// TODO Auto-generated method stub
		typeSubscriptionRepository.save(p);
	}

	@Override
	@Transactional(readOnly=true)
	public TypeSubscription getTypeSubscriptionByID(int idTypeSubscription) {
		return typeSubscriptionRepository.getOne(idTypeSubscription);
	}
	

	@Override
	public List<TypeSubscription> getAllTypeSubscription() {
		return typeSubscriptionRepository.findAll();
	}

	
	public Map<String, TypeSubscription> getMapTypeSubscription() {
		List<TypeSubscription> l = getAllTypeSubscription();
		Map<String, TypeSubscription> map = new HashMap<String, TypeSubscription>();
		Iterator<TypeSubscription> it = l.iterator();
		while (it.hasNext()) {
			TypeSubscription type=it.next();
			map.put(type.getLibelle(), type);
		}
		return map;
		
		
	}

	@Override
	public String editPriceTypeSubscription(int idType, float price) {
		TypeSubscription type=getTypeSubscriptionByID(idType);
		type.setPrix(price);
		 editTypeSubscription(type);
		 return "price edited";
		 
	
	}
	


}
