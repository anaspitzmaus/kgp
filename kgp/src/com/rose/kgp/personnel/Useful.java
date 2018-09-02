package com.rose.kgp.personnel;

import java.time.LocalDate;

import com.rose.kgp.db.SQL_SELECT;

public class Useful {

	public static Physician getPhyscianByAlias(String alias, LocalDate date){
		Physician physician = SQL_SELECT.physicianByAlias(alias, date);
		return physician;
	}
	
}
