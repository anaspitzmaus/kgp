package com.rose.kgp.personnel;

import java.time.LocalDate;

public class Staff extends Person{

	protected String password;
	protected String username;
	protected LocalDate onset;
	protected String alias;
	protected Integer id;
	
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public LocalDate getOnset() {
		return onset;
	}


	public void setOnset(LocalDate onset) {
		this.onset = onset;
	}

	
	public String getAlias() {
		return alias;
	}


	public void setAlias(String alias) {
		this.alias = alias;
	}


	public Staff(String surname, String firstname) {
		super(surname, firstname);
		// TODO Auto-generated constructor stub
	}
	
	

}
