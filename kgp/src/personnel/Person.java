package personnel;

import java.time.LocalDate;

public abstract class Person {

	protected String surname; 
	protected String firstname;
	protected LocalDate birthday;
	
	
	
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



	public Person(String surname, String firstname){
		this.surname = surname; 
		this.firstname = firstname;
	}
}
