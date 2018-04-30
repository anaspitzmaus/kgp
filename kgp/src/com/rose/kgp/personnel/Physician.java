package com.rose.kgp.personnel;

public class Physician extends Staff {

	protected String title;
	protected String alias;
	protected String status;
	protected Integer id;
	
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAlias(){
		return this.alias;
	}
	
	public void setAlias(String alias){
		this.alias = alias;
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
