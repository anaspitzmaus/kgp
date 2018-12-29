package com.rose.kgp.administration;


import java.util.ArrayList;
import java.util.HashMap;
import com.rose.kgp.data_exchange.Study;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.personnel.Patient;


public class TreatmentCase {
	private Patient patient;
	private Integer caseNr;//Fallnummmer
	private AccountingType accountingType; //same as billing type	
	private Integer inPatientID, outPatientID;
	//private Integer patientID;//as a patient can have different IDs depending on whether he is treated as in- or outPatient (cardioIntegral is kind of inPatient)
	private ArrayList<Examination> examinations;
	private Study dataConversion;

	
	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Integer getCaseNr() {
		return caseNr;
	}

	public void setCaseNr(Integer caseNr) {
		this.caseNr = caseNr;
	}
	
	public AccountingType getAccountingType() {
		return accountingType;
	}

	public void setAccountingType(AccountingType accountingType) {
		this.accountingType = accountingType;
	}
	
	public ArrayList<Examination> getExaminations() {
		return examinations;
	}

	/**
	 * standard constructor
	 * @param patient
	 */
	public TreatmentCase(HashMap<String, HashMap<String, ArrayList<String>>> values){
		dataConversion = new Study(values);
		this.patient = dataConversion.patient();
		this.caseNr = dataConversion.caseNr();
		setPatient_In_Or_Out(this.caseNr);
		examinations = new ArrayList<Examination>();
	}
	
	/**
	 * constructor, if patient data are not known yet
	 */
	public TreatmentCase(){
		examinations = new ArrayList<Examination>();
	}
	
	
//	/**
//	 * do not be confounded by the patient ID
//	 * @return
//	 */
//	public Integer getPatientID() {
//		return patientID;
//	}
//
//	public void setPatientID(Integer patientID) {
//		this.patientID = patientID;
//	}

	/**
	 * declares a patient as inPatient or outPatient depending on the case number and the number
	 * if a case number exists, the patient can be treated as in patient (number corresponds to in patient id) 
	 * or as integrated patient (number corresponds to out patient id)
	 * if there is no case number, patient is treated as out patient and the number corresponds to out patient id)
	 * @param id
	 */
	public void setPatient_In_Or_Out(Integer caseNr){
		if(caseNr instanceof Integer && caseNr > 0){//if there is a caseNr (can be inPatient or CardioIntegral)
			
			
			if(this.getPatient().getNumber() > 100000){ //inPatient
				//declare as inPatient
				this.accountingType = AccountingType.station�r;
				this.inPatientID = this.getPatient().getNumber(); //set the inPatient id
				this.getPatient().setInID(this.getPatient().getNumber());
			}else if(this.getPatient().getNumber() < 100000){
				//declare as CardioIntegral
				this.accountingType = AccountingType.integrierte_Versorgung;
				this.outPatientID = this.getPatient().getNumber(); //set the outPatient id
				this.getPatient().setOutID(this.getPatient().getNumber());//caution!! outPatient id
			}
		}else if(this.getPatient().getNumber() < 100000){
			//declare as outPatient
			this.accountingType = AccountingType.ambulant;
			this.outPatientID = this.getPatient().getNumber(); //set the outPatient id
			this.getPatient().setOutID(this.getPatient().getNumber());
		}
	}	
}
