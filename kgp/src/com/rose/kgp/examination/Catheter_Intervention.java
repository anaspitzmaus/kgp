package com.rose.kgp.examination;

import java.util.ArrayList;
import java.util.HashMap;

import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Physician;

public abstract class Catheter_Intervention extends Examination{

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


	public Catheter_Intervention(HashMap<String, HashMap<String, ArrayList<String>>> values) {
		super(values);
		super.setStudyData();//set the basic study data of this examination
		setStudyData();//set the specific study data of this examination
	}
	
	/**
	 * set the specific study data of this catheter intervention
	 */
	@Override
	protected void setStudyData(){
		this.setSecondExaminer(dataConversion.examinerAssistant());
		this.setNurseSterile(dataConversion.nurseSterile());
		this.setNurseUnsterile(dataConversion.nurseUnsterile());
		this.setNurseRegistration(dataConversion.nurseRegistration());
	}
	
	

}
