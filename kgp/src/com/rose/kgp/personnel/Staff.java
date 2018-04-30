package com.rose.kgp.personnel;

public class Staff extends Person{

	protected String password;
	protected String username;
	
	
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


	public Staff(String surname, String firstname) {
		super(surname, firstname);
		// TODO Auto-generated constructor stub
	}
	
	

}
