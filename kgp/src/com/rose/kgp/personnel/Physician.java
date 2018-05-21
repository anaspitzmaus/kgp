package com.rose.kgp.personnel;



public class Physician extends Staff {

	protected String title;
	
	protected String status;
	


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
		
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Physician(String alias){
		super("", "");
		this.alias = alias;
	}

	public Physician(String surname, String firstname) {
		super(surname, firstname);		
	}


	


	

}
