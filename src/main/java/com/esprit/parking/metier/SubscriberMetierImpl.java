package com.esprit.parking.metier;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esprit.parking.entities.Subscribers;
import com.esprit.parking.repository.SubscribersRepository;

@Service
public class SubscriberMetierImpl implements SubscriberMetier {
	
	@Autowired
	private SubscribersRepository subscribersRepository;
	
	

	@Override
	public Subscribers addSubscribers(Subscribers p) {
		p.setInscriptionDate(new  Date());
		return subscribersRepository.save(p);
	}

	@Override
	public void editSubscribers(Subscribers p) {
		 subscribersRepository.save(p);
	}
	@Override
	@Transactional(readOnly=true)
	public Subscribers getSubscribersByID(long idSubscribers) {
		Optional<Subscribers> p = subscribersRepository.findById(idSubscribers);
		if(p.isPresent())
		return p.get() ;
		else
			return null;
		
	}

	@Override
	public List<Subscribers> getAllSubscribers(int page, int size) {
		// TODO Auto-generated method stub
		return  subscribersRepository.findAll();
	}

	@Override
	public Subscribers getSubscribersByName(String firstName, String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

}
