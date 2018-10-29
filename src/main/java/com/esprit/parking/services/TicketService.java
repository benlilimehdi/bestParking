package com.esprit.parking.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.metier.TicketMetierImpl;

@RestController
public class TicketService {

	@Autowired
	private TicketMetierImpl ticketMetierImpl;
	

	@RequestMapping(value = "/getNewTicket", method = RequestMethod.POST)
	@Transactional
	public String checkIn() {
		return ticketMetierImpl.checkIn();
	}

	
	@RequestMapping(value = "payTicket", method = RequestMethod.POST)
	public String payTicket(@RequestParam(value = "idTicket") long idTicket,
			@RequestParam(value = "money") float money) {
		return ticketMetierImpl.payTicket(idTicket, money);
		
	}

	@RequestMapping(value = "/checkOut", method = RequestMethod.POST)
	public String checkOut(@RequestParam(value = "idTicket") long idTicket) {
		
		return ticketMetierImpl.checkOut(idTicket);
		}

	
	
	public String lostTicket(int etage, int numPlace) {

		return null;
	}

	

}
