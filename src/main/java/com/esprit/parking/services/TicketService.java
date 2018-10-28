package com.esprit.parking.services;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.parking.entities.Place;
import com.esprit.parking.entities.Ticket;
import com.esprit.parking.entities.TypeSubscription;
import com.esprit.parking.metier.PlaceMetierImpl;
import com.esprit.parking.metier.TicketMetierImpl;
import com.esprit.parking.metier.TypeSubscriptionMetierImpl;

@RestController
public class TicketService {

	@Autowired
	private TicketMetierImpl ticketMetierImpl;
	@Autowired
	private PlaceMetierImpl placeMetier;
	@Autowired
	private TypeSubscriptionMetierImpl typeSubscriptionMetier;

	@RequestMapping(value = "/getNewTicket", method = RequestMethod.POST)
	@Transactional
	public String checkIn() {
		if (placeMetier.countFreePlaces() != 0) {
			Optional<Place> op = placeMetier.getFreePlaceByFloorAsc();
			Place p = op.get();
			Ticket t = new Ticket(new Date(), null, null, false, p);
			ticketMetierImpl.addTicket(t);
			p.setEtat(1);
			
			placeMetier.editPlace(p);
			return "welcome";
		}
		return "no dispo place";
	}

	public Ticket checkOut() {

		return null;
	}

	@RequestMapping(value = "payTicket", method = RequestMethod.POST)
	public String payTicket(@RequestParam(value = "idTicket")long idTicket,@RequestParam(value = "money") float money) {
		Ticket t=ticketMetierImpl.getTicketByID(idTicket);
		//System.out.println("*******"+t.getIdTicket()+"$$$$$"+t.getDateEntree());
		if((t!=null)&&(t.getDateSortie()==null)) {
			Date nowDate=new Date();
			Calendar c1=Calendar.getInstance();
			c1.setTime(t.getDateEntree());
			Calendar c2=Calendar.getInstance();
			c2.setTime(nowDate);
			
			Map<String, Integer> map= periodeBetween(c1, c2);
			float x =calculatePrice(map);
			if (x<= money) {
				t.getPlace().setEtat(1);
				t.setDateSortie(new Date());
				t.setPayed(true);
				t.setPrix(x);
				placeMetier.editPlace(t.getPlace());
				Float mr=x-money;
				return "successfully payed \n money restante: "+ (mr);
			}else {
				Float me=money-x;
				return "zid"+(me);
			}
			
		}
		else {if(t==null) 
			return "invalide Ticket";
		
		else if(t.getDateSortie()==null) {
			return "ticket already payed";
		}}
		
		
		
		return "";
	}
		

	public String lostTicket(int etage, int numPlace) {

		return null;
	}

	public Map<String, Integer> periodeBetween(Calendar startDate, Calendar endDate) {
		long end = endDate.getTimeInMillis();
		long start = startDate.getTimeInMillis();
		Map<String, Integer> map = new HashMap<String, Integer>();
		;
		//map.put("M", TimeUnit.MILLISECONDS.toMinutes(Math.abs(end - start)));
		map.put("H", (int) TimeUnit.MILLISECONDS.toHours(Math.abs(end - start)));
		map.put("D", (int) TimeUnit.MILLISECONDS.toDays(Math.abs(end - start)));
		return map;
	}

	
	
	
	
	// tarif special pour 1h 4h et 8h
	// pour 1J on prend en compte les heures aussi
	// pour 2jours+1h ou plus chaque jour est payé en entier
	private float calculatePrice(Map<String, Integer> map) {
		Map<String, TypeSubscription>tarifMap=typeSubscriptionMetier.getMapTypeSubscription();
		int Days =map.get("D");
		int hours = map.get("H");
		
		float PriceResult =1;
		
		if (Days==0) {
			if((hours==0))  PriceResult = PriceResult-1+tarifMap.get("1H").getPrix();
			if((hours<4)&&(hours>0))  PriceResult = PriceResult*tarifMap.get("1H").getPrix();
			if((hours<8)&&(hours>=4))  PriceResult = PriceResult*tarifMap.get("4H").getPrix();
			if((hours>=8))  PriceResult = PriceResult*tarifMap.get("8H").getPrix();
		}else {
			if (Days==1) {
				if (hours==0) PriceResult = PriceResult*tarifMap.get("1J").getPrix();
				else {
					PriceResult = PriceResult*tarifMap.get("1J").getPrix();
					if((hours<4)&&(hours>=0))  PriceResult = PriceResult+tarifMap.get("1H").getPrix()*hours;
					if((hours<8)&&(hours>=4))  PriceResult = PriceResult+tarifMap.get("4H").getPrix()*hours;
					if((hours>=0))  PriceResult = PriceResult+tarifMap.get("8H").getPrix()*hours;
				}
			}else {
				if(hours==0) PriceResult = PriceResult*tarifMap.get("1J").getPrix() + tarifMap.get("1H").getPrix();
				else PriceResult = PriceResult*tarifMap.get("1J").getPrix() + tarifMap.get("1H").getPrix()+tarifMap.get("1J").getPrix() + tarifMap.get("1H").getPrix();
			}
		}
		
		
		return PriceResult;

	}
	
}
