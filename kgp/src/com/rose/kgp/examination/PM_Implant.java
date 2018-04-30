package com.rose.kgp.examination;

import java.util.ArrayList;
import java.util.HashMap;



public class PM_Implant extends PM_Intervention {
	
	ArrayList<ElectrodeImplant> electrodesImplant;
	
	public PM_Implant(ExamType type) {
		super(type);
		electrodesImplant = new ArrayList<ElectrodeImplant>();
	}

	@Override
	public void setStaff(
			HashMap<String, HashMap<String, ArrayList<String>>> values) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void storeExamToDB() {
		// TODO Auto-generated method stub
		
	}
	
	

}
