package com.esprit.parking.entities;

import java.util.Collection;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
public class Subscribers {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idSubscriber;
	private String firstName;
	private String lastName;
	private String matricule;
	private Date inscriptionDate;
	
	
	
	
	public Subscribers() {
		super();
	}



	public Subscribers(long idSubscriber, String firstName, String lastName, String matricule, Date inscriptionDate) {
		super();
		this.idSubscriber = idSubscriber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.matricule = matricule;
		this.inscriptionDate = inscriptionDate;
	}



	public Subscribers(String firstName, String lastName, String matricule, Date inscriptionDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.matricule = matricule;
		this.inscriptionDate = inscriptionDate;
	}
	
	

	public long getIdSubscriber() {
		return idSubscriber;
	}


	public void setIdSubscriber(long idSubscriber) {
		this.idSubscriber = idSubscriber;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getMatricule() {
		return matricule;
	}


	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}


	public Date getInscriptionDate() {
		return inscriptionDate;
	}


	public void setInscriptionDate(Date inscriptionDate) {
		this.inscriptionDate = inscriptionDate;
	}


	
	

}
