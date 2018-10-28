package com.esprit.parking.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Place {
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idPlace;
	private int etage;
	private int number;
	private int etat;
	
	@ManyToOne
	@JoinColumn(name="idParking")
	private Parking parking;
	
	@OneToMany(mappedBy="place")
	private Collection<Ticket> tickets ;
	
	@OneToMany(mappedBy="place")
	private Collection<Subscription> subscriptions;



	public Place() {}
	
	public Place(int etage, int number, int etat) {
		super();
		this.etage = etage;
		this.number = number;
		this.etat = etat;
	}
	
	public Place(int etage, int number, int etat, Parking parking) {
		super();
		this.etage = etage;
		this.number = number;
		this.etat = etat;
		this.parking = parking;
	}



	public Parking getParking() {
		return parking;
	}



	public void setParking(Parking parking) {
		this.parking = parking;
	}


	@JsonIgnore
	public Collection<Ticket> getTickets() {
		return tickets;
	}


	@JsonSetter
	public void setTickets(Collection<Ticket> tickets) {
		this.tickets = tickets;
	}



	public Collection<Subscription> getSubscriptions() {
		return subscriptions;
	}



	public void setSubscriptions(Collection<Subscription> subscriptions) {
		this.subscriptions = subscriptions;
	}


	public long getIdPlace() {
		return idPlace;
	}
	public void setIdPlace(long idPlace) {
		this.idPlace = idPlace;
	}
	public int getEtage() {
		return etage;
	}
	public void setEtage(int etage) {
		this.etage = etage;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}

	public int getEtat() {
		return etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	
	
	
	

}
