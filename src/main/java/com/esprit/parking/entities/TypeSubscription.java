package com.esprit.parking.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TypeSubscription {

	@Id
	private int idType;
	private String libelle;
	private float prix;
	private int etat;
	
	
	@OneToMany(mappedBy="typeSubscription")
	private Collection<Subscription> subscriptions;
	
	
	public TypeSubscription() {
		
	}

	public TypeSubscription(int idType, String libelle, float prix, int etat) {
		super();
		this.idType = idType;
		this.libelle = libelle;
		this.prix = prix;
		this.etat = etat;
	}

	
	public TypeSubscription(String libelle, float prix, int etat) {
		super();
		this.libelle = libelle;
		this.prix = prix;
		this.etat = etat;
	}
	
	
	
	
	public int getIdType() {
		return idType;
	}
	public void setIdType(int idType) {
		this.idType = idType;
	}
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public float getPrix() {
		return prix;
	}
	public void setPrix(float prix) {
		this.prix = prix;
	}
	public int getEtat() {
		return etat;
	}
	public void setEtat(int etat) {
		this.etat = etat;
	}
	
	

	
	
}
