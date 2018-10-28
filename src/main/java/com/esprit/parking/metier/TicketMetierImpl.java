package com.esprit.parking.metier;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.esprit.parking.entities.Ticket;
import com.esprit.parking.repository.TicketRepository;
@Service
public class TicketMetierImpl implements TicketMetier {

	@Autowired
	private TicketRepository ticketRepository;
	
	
	@Override
	public Ticket addTicket(Ticket p) {
		return ticketRepository.save(p);
	}

	@Override
	public void editTicket(Ticket p) {
		ticketRepository.save(p);
	}

	@Override
	public Ticket getTicketByID(long idTicket) {
		return ticketRepository.findById(idTicket).orElse(null);
	}

	@Override
	public Collection<Ticket> getAllTicket(int page, int size) {
		
		return ticketRepository.findAll();
	}

	@Override
	public Ticket payTicket(Ticket p, float money) {
		
		
		return null;
	}

}
