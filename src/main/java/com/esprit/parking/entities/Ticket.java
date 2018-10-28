package com.esprit.parking.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
@Entity
public class Ticket {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idTicket;
	private Date dateEntree;
	private Date dateSortie;
	private Date datePayement;
	private Float prix;
	private boolean isPayed;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idPlace")
	private Place place;

	
	
	public Ticket(Date dateEntree, Date dateSortie, Float prix, boolean isPayed, Place place) {
		super();
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
		this.prix = prix;
		this.isPayed = isPayed;
		this.place = place;
	}
	public Ticket(Date dateEntree, Date dateSortie, Float prix, boolean isPayed, Place place,Date datePayement) {
		super();
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
		this.prix = prix;
		this.isPayed = isPayed;
		this.place = place;
		this.datePayement = datePayement;
	}

	public Ticket(long idTicket, Date dateEntree, Date dateSortie, Float prix, boolean isPayed, Place place) {
		super();
		this.idTicket = idTicket;
		this.dateEntree = dateEntree;
		this.dateSortie = dateSortie;
		this.prix = prix;
		this.isPayed = isPayed;
		this.place = place;
	}
	

	public Ticket() {
		super();
	}

	public Ticket(long idTicket, Date dateEntree, Place place) {
		super();
		this.idTicket = idTicket;
		this.dateEntree = dateEntree;
		this.place = place;
	}

	public long getIdTicket() {
		return idTicket;
	}

	public void setIdTicket(long idTicket) {
		this.idTicket = idTicket;
	}

	public Date getDateEntree() {
		return dateEntree;
	}

	public void setDateEntree(Date dateEntree) {
		this.dateEntree = dateEntree;
	}

	public Date getDateSortie() {
		return dateSortie;
	}

	public void setDateSortie(Date dateSortie) {
		this.dateSortie = dateSortie;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public boolean isPayed() {
		return isPayed;
	}

	public void setPayed(boolean isPayed) {
		this.isPayed = isPayed;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}
	public Date getDatePayement() {
		return datePayement;
	}
	public void setDatePayement(Date datePayement) {
		this.datePayement = datePayement;
	}
	

	
	
}
