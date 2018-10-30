package com.esprit.parking.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employe {
	
	
	@Id
	@GeneratedValue
	private long idEmployee;
	private String Name;
	private String Post;
	private String Login;
	private String Password;
	
	
	

}
