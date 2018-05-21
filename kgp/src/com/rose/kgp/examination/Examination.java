package com.rose.kgp.examination;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.rose.kgp.activityInput.BillingType;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Patient;
import com.rose.kgp.personnel.Physician;

public abstract class Examination {
	
	protected Physician physician;
	protected ArrayList<Physician> physicians_assist;
	protected HashMap<Nurse, String> nurses;
	protected Patient patient;
	protected ExamType examType;
	protected BillingType billingType;
	protected File dataFile;
	protected LocalTime startTime, endTime;
	protected LocalDate date;
	protected Integer refNo;
	
	
	public LocalDateTime getStart() {
		LocalDateTime start = LocalDateTime.of(date, startTime);
		return start;
	}

	public LocalDateTime getEnd() {		
		LocalDateTime end = LocalDateTime.of(date, endTime);
		return end;
	}

	public Integer getRefNo() {
		return refNo;
	}

	public void setRefNo(Integer refNo) {
		this.refNo = refNo;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public File getDataFile() {
		return dataFile;
	}

	public void setDataFile(File dataFile) {
		this.dataFile = dataFile;
	}

	public ExamType getType() {
		return examType;
	}

	public void setType(ExamType type) {
		this.examType = type;
	}

	
	public Examination(ExamType type){
		this.examType = type;
	}
	
	public ExamType getExamType() {
		return examType;
	}

	public void setExamType(ExamType examType) {
		this.examType = examType;
	}

	public BillingType getBillingType() {
		return billingType;
	}

	public void setBillingType(BillingType billingType) {
		this.billingType = billingType;
	}

	public void setPatient(Patient patient){
		this.patient = patient;
	}
	
	public Patient getPatient(){
		return this.patient;
	}	
	
	public Physician getPhysician() {
		return physician;
	}

	public void setPhysician(Physician physician) {
		this.physician = physician;
	}

	public void setPatientProperties(HashMap<String, HashMap<String, ArrayList<String>>> values){
			
		HashMap<String, ArrayList<String>> patient_hm = values.get("PATIENT");
		HashMap<String, ArrayList<String>> patientData_hm = values.get("PD");
		
		this.setPatient(new Patient(patient_hm.get("LASTNAME").get(0), patient_hm.get("FIRNAME").get(0)));
		
		this.getPatient().setMidname(patient_hm.get("MIDNAME").get(0));
		
		try{
			this.getPatient().setNumber(Integer.parseInt(patient_hm.get("PATNO").get(0)));
		}catch(NumberFormatException nfe){
			//no number format
		}
		
		//as the birth of the patient is coded as "yyyy-MM-dd HH:mm:ss.SSS"		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		try {
			Date localDateTime = formatter.parse(patient_hm.get("PATBIRTH").get(0));
			LocalDate date = localDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			this.getPatient().setBirth(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try{
			this.getPatient().setHeight(Integer.parseInt(patientData_hm.get("HEIGHT").get(0)));
		}catch(NumberFormatException nfe){
			//no number format
		}
		
		try{
			this.getPatient().setWeight(Double.parseDouble(patientData_hm.get("WEIGHT").get(0)));
		}catch(NumberFormatException nfe){
			//no number format
		}
		
		try{
			this.getPatient().setAge(Integer.parseInt(patientData_hm.get("AGE").get(0)));
		}catch(NumberFormatException nfe){
			//no number format
		}
		
		try{
			this.getPatient().setBSA(Double.parseDouble(patientData_hm.get("BSA").get(0)));
		}catch(NumberFormatException nfe){
			//no number format
		}
		
		try{
			this.getPatient().setSexCode(Integer.parseInt(patientData_hm.get("SEX").get(0)));
		}catch(NumberFormatException nfe){
			//no number format
		}
		
	}
	
	public void setPhysician(HashMap<String, HashMap<String, ArrayList<String>>> values){
		HashMap<String, ArrayList<String>> staffStudy_hm = values.get("STUDY");		
		Physician physician = new Physician(staffStudy_hm.get("PERPHYS1").get(0));
		this.setPhysician(physician);
		this.setRefNo(Integer.parseInt(staffStudy_hm.get("REFNO").get(0)));
		
	}
	
	/**
	 * sets the staff out of the data protocol
	 * @param values
	 */
	public abstract void setStaff(HashMap<String, HashMap<String, ArrayList<String>>> values);
	
	public void setDateTimes(HashMap<String, HashMap<String, ArrayList<String>>> values){
		HashMap<String, ArrayList<String>> examTime_hm = values.get("ID");
		SimpleDateFormat formatterDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		SimpleDateFormat formatterTime = new SimpleDateFormat("HH:mm:ss");
		Date localDateTime = null;
		//set the date of the examination
		try {
			localDateTime = formatterDate.parse(examTime_hm.get("EXAMDATE").get(0));
			LocalDate date = localDateTime.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			this.setDate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get the start-time of the examination
		try {
			Date start = formatterTime.parse(examTime_hm.get("STATIME").get(0));
			LocalTime startTime = start.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
			this.setStartTime(startTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//get the end-time of the examination
		try {
			Date end = formatterTime.parse(examTime_hm.get("ENDTIME").get(0));
			LocalTime endTime = end.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
			this.setEndTime(endTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void storeExamToDB(){
		System.out.println("Hallo");
		SQL_INSERT.Examination(this);
	}
	
	
	
}
