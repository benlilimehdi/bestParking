package com.esprit.parking.metier;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.parking.entities.Place;
import com.esprit.parking.entities.Ticket;
import com.esprit.parking.entities.TypeSubscription;
import com.esprit.parking.repository.TicketRepository;
@Service
public class TicketMetierImpl implements TicketMetier {

	@Autowired
	private TicketRepository ticketRepository;
	
	
	@Autowired
	private PlaceMetierImpl placeMetier;
	@Autowired
	private TypeSubscriptionMetierImpl typeSubscriptionMetier;
	
	
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
	public String payTicket(long idTicket , float money) {

		Ticket t = getTicketByID(idTicket);
		// System.out.println("*******"+t.getIdTicket()+"$$$$$"+t.getDateEntree());
		if ((t != null) && (t.getDateSortie() == null)) {
			Date nowDate = new Date();
			Calendar c1 = Calendar.getInstance();
			c1.setTime(t.getDateEntree());
			Calendar c2 = Calendar.getInstance();
			c2.setTime(nowDate);

			Map<String, Integer> map = periodeBetween(c1, c2);
			float x = calculatePrice(map);
			if (x <= money) {
				t.getPlace().setEtat(1);
				// t.setDateSortie(new Date());
				t.setDatePayement(new Date());
				t.setPayed(true);
				t.setPrix(x);
				placeMetier.editPlace(t.getPlace());
				Float mr = x - money;
				return "successfully payed \n money restante: " + (mr);
			} else {
				Float me = money - x;
				return "more money: " + (me);
			}

			} else {
			if (t == null)
				return "invalide Ticket";

			else if (t.getDateSortie() == null) {
				return "ticket already payed";
			}
		}
		return "";
	
	}

	@Override
	public String checkIn() {
		if (placeMetier.countFreePlaces() != 0) {
			Optional<Place> op = placeMetier.getFreePlaceByFloorAsc();
			Place p = op.get();
			Ticket t = new Ticket(new Date(), null, null, false, p, null);
			addTicket(t);
			p.setEtat(1);

			placeMetier.editPlace(p);
			return "welcome;\n\n place number: " + p.getNumber() + "\n place floor: " + p.getEtage();
		}
		return "no dispo place";
	}

	@Override
	public String checkOut(long idTicket) {
		Ticket ticket = getTicketByID(idTicket);
		Map<String, Integer> map = new HashMap<String, Integer>();
		if ((ticket.getDatePayement() != null) || (ticket.getDateSortie() != null)) {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(ticket.getDatePayement());
			Calendar c2 = Calendar.getInstance();
			c2.setTime(ticket.getDateEntree());
			map = periodeBetween(c1, c2);
			if (map.get("M") > 30) {
				// traitement du retard de sortie
				return "you should pay again! ";
			} else {
				ticket.setDateSortie(new Date());
				Place p = placeMetier.getPlaceByID(ticket.getPlace().getIdPlace());
				p.setEtat(0);
				placeMetier.editPlace(p);

				return "GoodBye!";
			}
		} else
			return "go pay your ticket  pls !";

	}
	
	
	
	public Map<String, Integer> periodeBetween(Calendar startDate, Calendar endDate) {
		long end = endDate.getTimeInMillis();
		long start = startDate.getTimeInMillis();
		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("M", (int) TimeUnit.MILLISECONDS.toMinutes(Math.abs(end - start)));
		map.put("H", (int) TimeUnit.MILLISECONDS.toHours(Math.abs(end - start)));
		map.put("D", (int) TimeUnit.MILLISECONDS.toDays(Math.abs(end - start)));
		return map;
	}

	// tarif special pour 1h 4h et 8h
	// pour 1J on prend en compte les heures aussi
	// pour 2jours+1h ou plus chaque jour est payé en entier
	private float calculatePrice(Map<String, Integer> map) {
		Map<String, TypeSubscription> tarifMap = typeSubscriptionMetier.getMapTypeSubscription();
		int Days = map.get("D");
		int hours = map.get("H");

		float PriceResult = 1;

		if (Days == 0) {
			if ((hours == 0))
				PriceResult = PriceResult - 1 + tarifMap.get("1H").getPrix();
			if ((hours < 4) && (hours > 0))
				PriceResult = PriceResult * tarifMap.get("1H").getPrix() * hours;
			if ((hours < 8) && (hours >= 4))
				PriceResult = PriceResult * tarifMap.get("4H").getPrix() * hours;
			if ((hours >= 8))
				PriceResult = PriceResult * tarifMap.get("8H").getPrix() * hours;
		} else {
			if (Days == 1) {
				if (hours == 0)
					PriceResult = PriceResult * tarifMap.get("1J").getPrix();
				else {
					PriceResult = PriceResult * tarifMap.get("1J").getPrix();
					if ((hours < 4) && (hours >= 0))
						PriceResult = PriceResult + tarifMap.get("1H").getPrix() * hours;
					if ((hours < 8) && (hours >= 4))
						PriceResult = PriceResult + tarifMap.get("4H").getPrix() * hours;
					if ((hours >= 0))
						PriceResult = PriceResult + tarifMap.get("8H").getPrix() * hours;
				}
			} else {
				if (hours == 0)
					PriceResult = PriceResult * tarifMap.get("1J").getPrix() + tarifMap.get("1H").getPrix();
				else
					PriceResult = PriceResult * tarifMap.get("1J").getPrix() + tarifMap.get("1H").getPrix()
							+ tarifMap.get("1J").getPrix() + tarifMap.get("1H").getPrix();
			}
		}

		return PriceResult;

	}

}
