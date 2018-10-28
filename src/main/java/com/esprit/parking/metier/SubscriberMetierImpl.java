package com.esprit.parking.metier;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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

	@Transactional(readOnly=true)
	public Subscribers getSubscribersByID(long idSubscribers) {
		// TODO Auto-generated method stub
		return subscribersRepository.getOne(idSubscribers);
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
