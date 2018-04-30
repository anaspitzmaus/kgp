package com.rose.kgp.examination;

import java.util.ArrayList;
import java.util.HashMap;

import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Physician;
import com.rose.kgp.personnel.Useful;

public class LeftHeartCatheter extends Catheter_Intervention{
	protected HashMap<Physician, InterventionalTaskPhysician> examiners;
	protected HashMap<Nurse, InterventionalTaskNurse> nurses;
	final static ExamType examType = ExamType.LeftHeartCatheter;

	public LeftHeartCatheter() {
		super(examType);
		this.nurses = new HashMap<Nurse, InterventionalTaskNurse>();
		this.examiners = new HashMap<Physician, InterventionalTaskPhysician>();
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setStaff(HashMap<String, HashMap<String, ArrayList<String>>> values) {
		HashMap<String, ArrayList<String>> staff_hm = values.get("PN");
		for(int i = 0; i<staff_hm.get("STAFF").size(); i++){
			try {
				examiners.put(new Physician(staff_hm.get("PNNAME").get(i)), InterventionalTaskPhysician.valueOf(staff_hm.get("STAFF").get(i)));
			} catch (IllegalArgumentException|NullPointerException ep) {
				try {
					String n = staff_hm.get("STAFF").get(i).replaceAll(" ", "_");
					nurses.put(new Nurse(staff_hm.get("PNNAME").get(i)), InterventionalTaskNurse.valueOf(n));
				} catch (IllegalArgumentException|NullPointerException en) {
					System.out.println(en.getMessage());
				}			
			}
			
				
			
		}
		
//		Useful.getPhyscianByAlias(examiner.getAlias());
//		
//		nurses.put(new Nurse(staff_hm.get("PNNAME").get(staff_hm.get("STAFF").indexOf("Untersucher")));	
		
		
	}

//	@Override
//	public void storeExamToDB() {
//		// TODO Auto-generated method stub
//		
//	}
	
	
	
	

}
