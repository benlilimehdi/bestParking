package com.esprit.parking.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Subscription {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idSubscription;
	private Date dateInscription;
	private Date dateFinInscription;
	private String isIn;
	
	@ManyToOne
	@JoinColumn(name="idPlace")
	private Place place;
	@ManyToOne
	@JoinColumn(name="idType")
	private TypeSubscription typeSubscription;
	@ManyToOne
	@JoinColumn(name="idSubscriber")
	private Subscribers subscriber;
	
	
	public Subscription(Date dateInscription, String isIn, Place place, TypeSubscription typeSubscription,
			Subscribers subscriber) {
		super();
		this.dateInscription = dateInscription;
		this.isIn = isIn;
		this.place = place;
		this.typeSubscription = typeSubscription;
		this.subscriber = subscriber;
	}

	
	
	public Subscription(long idSubscription, Date dateInscription, Date dateFinInscription, String isIn, Place place,
			TypeSubscription typeSubscription, Subscribers subscriber) {
		super();
		this.idSubscription = idSubscription;
		this.dateInscription = dateInscription;
		this.dateFinInscription = dateFinInscription;
		this.isIn = isIn;
		this.place = place;
		this.typeSubscription = typeSubscription;
		this.subscriber = subscriber;
	}



	public Subscription( Date dateInscription, Date dateFinInscription, String isIn, Place place,
			TypeSubscription typeSubscription, Subscribers subscriber) {
		super();
		this.dateInscription = dateInscription;
		this.dateFinInscription = dateFinInscription;
		this.isIn = isIn;
		this.place = place;
		this.typeSubscription = typeSubscription;
		this.subscriber = subscriber;
	}



	public Subscription() {
		super();
	}

	public long getIdSubscription() {
		return idSubscription;
	}


	public void setIdSubscription(long idSubscription) {
		this.idSubscription = idSubscription;
	}


	public Date getDateInscription() {
		return dateInscription;
	}


	public void setDateInscription(Date dateInscription) {
		this.dateInscription = dateInscription;
	}


	public String getIsIn() {
		return isIn;
	}


	public void setIsIn(String isIn) {
		this.isIn = isIn;
	}


	public Place getPlace() {
		return place;
	}


	public void setPlace(Place place) {
		this.place = place;
	}


	public TypeSubscription getTypeSubscription() {
		return typeSubscription;
	}


	public void setTypeSubscription(TypeSubscription typeSubscription) {
		this.typeSubscription = typeSubscription;
	}


	public Subscribers getSubscriber() {
		return subscriber;
	}


	public void setSubscriber(Subscribers subscriber) {
		this.subscriber = subscriber;
	}

	public Date getDateFinInscription() {
		return dateFinInscription;
	}

	public void setDateFinInscription(Date dateFinInscription) {
		this.dateFinInscription = dateFinInscription;
	}

	
	
	
	
}
