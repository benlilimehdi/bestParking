package com.esprit.parking.entities;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;


@Entity
public class Parking {
	
	
	@Id
	@GeneratedValue
	private long idParking;
	private String nameParking;
	private long capacity;
	
	@OneToMany(mappedBy="parking")
	private Collection<Place> places;
	
	



	public Parking() {
		super();
	}
	
	public Parking(long idParking, String nameParking, long capacity) {
		super();
		this.idParking = idParking;
		this.nameParking = nameParking;
		this.capacity = capacity;
	}

	
	
	public Parking(String nameParking, long capacity) {
		super();
		this.nameParking = nameParking;
		this.capacity = capacity;
	}
	
	
	
	@JsonIgnore
	public Collection<Place> getPlaces() {
		return places;
	}
	@JsonSetter
	public void setPlaces(Collection<Place> places) {
		this.places = places;
	}
	public long getIdParking() {
		return idParking;
	}
	public void setIdParking(long idParking) {
		this.idParking = idParking;
	}
	public String getNameParking() {
		return nameParking;
	}
	public void setNameParking(String nameParking) {
		this.nameParking = nameParking;
	}
	
	public long getCapacity() {
		return capacity;
	}
	
	public void setCapacity(long capacity) {
		this.capacity = capacity;
	}
	
	
	

}
