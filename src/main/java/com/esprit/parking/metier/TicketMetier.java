package com.esprit.parking.metier;

import java.util.Collection;


import com.esprit.parking.entities.Ticket;

public interface TicketMetier {

	public Ticket addTicket(Ticket p);
	public void editTicket(Ticket p);
	
	public Ticket getTicketByID(long idTicket);
	
	public Collection<Ticket> getAllTicket(int page, int size);
	
	public String payTicket(long idTicket, float money);
	public String checkIn();
	public String checkOut(long idTicket);
	
	
	
	
}
