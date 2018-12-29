package com.rose.kgp.administration;


import java.util.ArrayList;
import java.util.HashMap;
import com.rose.kgp.data_exchange.DataConversion;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.personnel.Patient;


public class TreatmentCase {
	private Patient patient;
	private Integer caseNr;//Fallnummmer
	private AccountingType accountingType; //same as billing type	
	private Integer inPatientID, outPatientID;
	//private Integer patientID;//as a patient can have different IDs depending on whether he is treated as in- or outPatient (cardioIntegral is kind of inPatient)
	private ArrayList<Examination> examinations;
	private DataConversion dataConversion;

	
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
		dataConversion = new DataConversion(values);
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
	 * declares a patient as inPatient or outPatient depending on the id
	 * @param id
	 */
	public void setPatient_In_Or_Out(Integer caseNr){
		if(caseNr instanceof Integer && caseNr > 0){//if there is a caseNr (can be inPatient or CardioIntegral)
			
			
			if(this.getPatient().getNumber() > 100000){ //inPatient
				//declare as inPatient
				this.accountingType = AccountingType.stationär;
				this.inPatientID = this.getPatient().getNumber(); //set the inPatient id
				this.getPatient().setInID(this.getPatient().getNumber());
			}else if(this.getPatient().getNumber() < 100000){
				//declare as CardioIntegral
				this.accountingType = AccountingType.integrierte_Versorgung;
				this.inPatientID = this.getPatient().getNumber(); //set the inPatient id
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
