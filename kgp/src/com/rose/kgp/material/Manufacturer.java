package com.rose.kgp.material;

public class Manufacturer {
	String notation;
	String contact_person;
	String mobil;
	
	
	public String getNotation() {
		return notation;
	}

	public void setNotation(String notation) {
		this.notation = notation;
	}


	public String getContact_person() {
		return contact_person;
	}

	public void setContact_person(String contact_person) {
		this.contact_person = contact_person;
	}

	
	public String getMobil() {
		return mobil;
	}

	public void setMobil(String mobil) {
		this.mobil = mobil;
	}

	public Manufacturer(String notation) {
		this.notation = notation;
	}
}
