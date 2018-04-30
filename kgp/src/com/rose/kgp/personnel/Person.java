package com.rose.kgp.personnel;

import java.time.LocalDate;

public abstract class Person {

	protected String surname; 
	protected String firstname;
	protected LocalDate birthday;
	protected Integer sex; // can be 0 for indifferent, 1 for female and 2 for male 
	
	
	public String getSurname() {
		return surname;
	}



	public void setSurname(String surname) {
		this.surname = surname;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public LocalDate getBirthday() {
		return birthday;
	}



	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}


	

	public String getSex() {
		switch (sex) {
		case 0:
			return "indifferent";
			
		case 1:
			return "female";
			
		case 2:
			return "male";
		default:
			return null;
			
		}		
	}
	
	
/**
 * can be 0 for indifferent, 1 for female and 2 for male
 * 
 */
	public void setSex(String sex) {
		switch (sex){
		case "indifferent":
			this.sex = 0;
			break;
		case "female":
			this.sex = 1;
			break;
		case "male":
			this.sex = 2;
			break;
		default:
			this.sex = null;
		}
		
	}



	public Person(String surname, String firstname){
		this.surname = surname; 
		this.firstname = firstname;
	}
	
	
}
