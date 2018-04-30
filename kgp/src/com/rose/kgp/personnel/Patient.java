package com.rose.kgp.personnel;

import java.time.LocalDate;

public class Patient extends Person{
	
	public Patient(String surname, String firstname) {
		super(surname, firstname);
		
	}

	protected String midname;
	protected Integer number;
	protected LocalDate birth;
	protected Integer height;
	protected Double weight, bsa;
	protected Integer age;
	protected Integer id, ambulant_id;
	protected String stationary_id;
	
	
	
	public Integer getAmbulant_id() {
		return ambulant_id;
	}

	public void setAmbulant_id(Integer ambulant_id) {
		this.ambulant_id = ambulant_id;
	}

	public String getStationary_id() {
		return stationary_id;
	}

	public void setStationary_id(String stationary_id) {
		this.stationary_id = stationary_id;
	}

	public Double getBsa() {
		return bsa;
	}

	public void setBsa(Double bsa) {
		this.bsa = bsa;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMidname() {
		return midname;
	}

	public void setMidname(String midname) {
		this.midname = midname;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public LocalDate getBirth() {
		return birth;
	}

	public void setBirth(LocalDate birth) {
		this.birth = birth;
	}

	public void setHeight(Integer height) {
		this.height = height;		
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Double getBSA() {
		return bsa;
	}

	public void setBSA(Double bsa) {
		this.bsa = bsa;
	}
	
	
	
	
	
	
	
	
	
	
}
