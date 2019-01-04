package com.rose.kgp.examination;

import java.util.ArrayList;
import java.util.HashMap;



public class PM_Implant extends PM_Intervention {
	
	ArrayList<ElectrodeImplant> electrodesImplant;
	
	public PM_Implant(HashMap<String, HashMap<String, ArrayList<String>>> studyValues) {
		super(studyValues);
		electrodesImplant = new ArrayList<ElectrodeImplant>();
	}

	@Override
	public void setStaff() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean storeExamToDB(Integer treatmentCaseId) {
		return null;
		// TODO Auto-generated method stub
		
	}
	
	

}
