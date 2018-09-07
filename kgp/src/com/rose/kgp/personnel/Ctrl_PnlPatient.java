package com.rose.kgp.personnel;

import java.time.LocalDate;

import com.rose.kgp.useful.DateMethods;

/**
 * controller of the panel that shows patient data
 * @author Administrator
 *
 */
public class Ctrl_PnlPatient {
	Pnl_Patient pnlPatient;
	Patient patient;
	
	
	public Pnl_Patient getPanel() {
		return pnlPatient;
	}
	
	public void setPatient(Patient patient){
		this.patient = patient;
		fillComponents();
	}

	/**
	 * constructor if a patient is already kown
	 * @param patient
	 */
	public Ctrl_PnlPatient(Patient patient) {
		this.patient = patient;
		pnlPatient = new Pnl_Patient();
		//fill the components if patient is an instance of 'Patient' class
		if(this.patient instanceof Patient){
			fillComponents();
		}
	}
	/**
	 * standard constructor
	 */
	public Ctrl_PnlPatient(){
		pnlPatient = new Pnl_Patient();
	}
	
	private void fillComponents(){
		pnlPatient.getTxtName().setText(this.patient.getSurname() + ", " + this.patient.getFirstname());		
		pnlPatient.getTxtPatNr().setText(Integer.toString(this.patient.getNumber()));//depends on whether patient is treated as in patient or out patient 
		pnlPatient.getETxtBirth().setValue(DateMethods.ConvertLocalDateToDate(this.patient.getBirthday()));
		pnlPatient.getTxtSex().setText(this.patient.getSex().name());		
	}
	
	
	
	
}
