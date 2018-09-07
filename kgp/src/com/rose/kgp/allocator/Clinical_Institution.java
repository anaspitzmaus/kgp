package com.rose.kgp.allocator;

public class Clinical_Institution {
	String notation, shortNotation, street, city, postalCode;
	Integer id;
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getNotation() {
		return notation;
	}



	public void setNotation(String notation) {
		this.notation = notation;
	}



	public String getShortNotation() {
		return shortNotation;
	}



	public void setShortNotation(String shortNotation) {
		this.shortNotation = shortNotation;
	}



	public String getStreet() {
		return street;
	}



	public void setStreet(String street) {
		this.street = street;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getPostalCode() {
		return postalCode;
	}



	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}



	public Clinical_Institution(String notation, String shortNotation, String street, String postalCode, String city) {
		this.notation = notation;
		this.shortNotation = shortNotation;
		this.street = street;
		this.postalCode = postalCode;
		this.city = city;
	}
}
