package com.rose.kgp.examination;

import java.util.ArrayList;
import java.util.HashMap;

import com.rose.kgp.data_exchange.SensisExam;
import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Physician;

public abstract class Catheter_Intervention extends Examination {

	protected Physician secondExaminer;
	protected Nurse nurseSterile, nurseUnsterile, nurseRegistration;
	
	
	public Physician getSecondExaminer() {
		return secondExaminer;
	}


	public void setSecondExaminer(Physician secondExaminer) {
		this.secondExaminer = secondExaminer;
	}
	
	public Nurse getNurseSterile() {
		return nurseSterile;
	}


	public void setNurseSterile(Nurse nurseSterile) {
		this.nurseSterile = nurseSterile;
	}


	public Nurse getNurseUnsterile() {
		return nurseUnsterile;
	}


	public void setNurseUnsterile(Nurse nurseUnsterile) {
		this.nurseUnsterile = nurseUnsterile;
	}


	public Nurse getNurseRegistration() {
		return nurseRegistration;
	}


	public void setNurseRegistration(Nurse nurseRegistration) {
		this.nurseRegistration = nurseRegistration;
	}

	
	/**
	 * constructor for an examination with data transferred by a dataProtocol i.e. by Sensis (Siemens)
	 * @param values (hashMap)
	 */
	public Catheter_Intervention(HashMap<String, HashMap<String, ArrayList<String>>> values) {
		super(values);
		super.setStudyData();//set the basic study data of this examination
		setStudyData();//set the specific study data of this examination
	}
	
	public Catheter_Intervention() {
		// TODO Auto-generated constructor stub
	}


	
	
	

}
