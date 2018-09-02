package com.rose.kgp.examination;

import java.util.ArrayList;
import java.util.HashMap;

import com.rose.kgp.material.Stent;

public class PCI extends Catheter_Intervention{

	protected ArrayList<Stent> stents;
	
	public PCI(HashMap<String, HashMap<String, ArrayList<String>>> studyValues) {
		super(studyValues);
		stents = new ArrayList<Stent>();
		
	}

	@Override
	public void setStaff(){
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeExamToDB() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
