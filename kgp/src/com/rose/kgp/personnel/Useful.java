package com.rose.kgp.personnel;

import java.util.ArrayList;

import com.rose.kgp.db.SQL_SELECT;

public class Useful {

	public static Physician getPhyscianByAlias(String alias){
		Physician physician = SQL_SELECT.getPhysicianByAlias(alias);
		return physician;
	}
	
}
