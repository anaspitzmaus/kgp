package com.rose.kgp.examination;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Physician;
import com.rose.kgp.personnel.Useful;

public class LeftHeartCatheter extends Catheter_Intervention{
	protected HashMap<InterventionalTaskPhysician, Physician> examiners;
	protected HashMap<InterventionalTaskNurse, Nurse> nurses;
	final static StudyType examType = StudyType.LeftHeartCatheter;

	
	public LeftHeartCatheter(HashMap<String, HashMap<String, ArrayList<String>>> studyValues) {
		super(studyValues);
		
		this.nurses = new HashMap<InterventionalTaskNurse, Nurse>();
		this.nurses.put(InterventionalTaskNurse.Assistenz_Steril, study.nurseSterile());
		this.nurses.put(InterventionalTaskNurse.Assistenz_Unsteril, study.nurseUnsterile());
		this.nurses.put(InterventionalTaskNurse.Registrierung, study.nurseRegistration());
		this.examiners = new HashMap<InterventionalTaskPhysician, Physician>();
		this.examiners.put(InterventionalTaskPhysician.SecondExaminer, study.examinerAssistant());
		// TODO Auto-generated constructor stub
	}
	
	public LeftHeartCatheter(){
		super();
	}

	
	public void setStaff() {
//		HashMap<String, ArrayList<String>> staff_hm = values.get("PN");
//		for(int i = 0; i<staff_hm.get("STAFF").size(); i++){
//			try {
//				examiners.put(InterventionalTaskPhysician.valueOf(staff_hm.get("STAFF").get(i)), new Physician(staff_hm.get("PNNAME").get(i)));
//			} catch (IllegalArgumentException|NullPointerException ep) {
//				try {
//					String n = staff_hm.get("STAFF").get(i).replaceAll(" ", "_");
//					nurses.put(InterventionalTaskNurse.valueOf(n), new Nurse(staff_hm.get("PNNAME").get(i)));
//				} catch (IllegalArgumentException|NullPointerException en) {
//					System.out.println(en.getMessage());
//				}			
//			}			
//		}		
	}
	
	
	

//	@Override
//	public void storeExamToDB() {
//		// TODO Auto-generated method stub
//		
//	}
	
	
	
	

}
