package com.rose.kgp.data_exchange;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.rose.kgp.administration.TreatmentCase;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.examination.Catheter_Intervention;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.personnel.Patient;


public class SensisStudy extends Study{
	Sensis sensis;
	File file;
	TreatmentCase treatmentCase;
	Examination examination;
	
	final static Charset ENCODING_ISO_8859_1 = StandardCharsets.ISO_8859_1;
	final static Charset ENCODING_UTF_16 = StandardCharsets.UTF_16;
	
	
	
	public SensisStudy(File file){
		this.file = file;
		dataValues = new HashMap<String, HashMap<String, ArrayList<String>>>();
	}
	
	public void readFile() throws IOException{
		String[] fields = null;
		String [] group = null;
		
		dataValues.clear();
		
		try (BufferedReader reader = Files.newBufferedReader(this.file.toPath(), ENCODING_UTF_16)){
		      String line = null;
		      while ((line = reader.readLine()) != null) {
		    	 if(line.startsWith("Group")){	
		    		 group = line.split(":");
		    		 dataValues.put(group[1], new HashMap<String, ArrayList<String>>());
		    		
		    	 }else if(line.startsWith("Fields")){
		    		 fields = line.split("³", -2);
		    		 for(int i = 1; i<fields.length; i++){
		    			 dataValues.get(group[1]).put(fields[i], new ArrayList<String>()); 	    			 
		    		 }
		    	 }else if(!line.isEmpty()){
		    		 String[] fieldValues = line.split("³", -2);
		    		 for(int i = 1; i<fieldValues.length; i++){
		    			 dataValues.get(group[1]).get(fields[i]).add(fieldValues[i]);
		    		 }
		    	 }
		      }      	
		    }catch(MalformedInputException e){
		    	try(BufferedReader reader = Files.newBufferedReader(this.file.toPath(), ENCODING_ISO_8859_1)){
		    		 String line = null;
		   	      while ((line = reader.readLine()) != null) {
		   	    	 if(line.startsWith("Group")){	
		   	    		 group = line.split(":");
		   	    		 dataValues.put(group[1], new HashMap<String, ArrayList<String>>());
		   	    		
		   	    	 }else if(line.startsWith("Fields")){
		   	    		 fields = line.split("³", -2);
		   	    		 for(int i = 1; i<fields.length; i++){
		   	    			 dataValues.get(group[1]).put(fields[i], new ArrayList<String>()); 	    			 
		   	    		 }
		   	    	 }else if(!line.isEmpty()){
		   	    		 String[] fieldValues = line.split("³", -2);
		   	    		 for(int i = 1; i<fieldValues.length; i++){
		   	    			 dataValues.get(group[1]).get(fields[i]).add(fieldValues[i]);
		   	    		 }
		   	    	 }
		   	      }      	
		    	}
		    }
	}

/**
 * creates a treatmentCase out of the study data	
 * the treatmentCase includes the patient and the examination as given in the study protocol
 * The treatmentCase is created only if a corresponding patient exists
 * @return the treatmentCase or null if the treatmentCase could not be created
 */
public void  createStudy() {
	treatmentCase = null;
	examination = null;
	Patient patient = getPatient();
	if(patient instanceof Patient){ //
		treatmentCase = getTreatmentCase(patient);
		if(treatmentCase instanceof TreatmentCase) {
			examination = getExamination();
			if(examination instanceof Catheter_Intervention) {
				((Catheter_Intervention)examination).setNurseRegistration(this.nurseRegistration());
				((Catheter_Intervention)examination).setNurseSterile(this.nurseSterile());
				((Catheter_Intervention)examination).setNurseUnsterile(this.nurseUnsterile());
				((Catheter_Intervention)examination).setExaminer(this.examiner());
				((Catheter_Intervention)examination).setSecondExaminer(this.examinerAssistant());
				((Catheter_Intervention)examination).setDataFile(file);
				((Catheter_Intervention)examination).setDate(this.examDate());
				((Catheter_Intervention)examination).setStartTime(this.startTime());
				((Catheter_Intervention)examination).setEndTime(this.endTime());
//				if(examination instanceof SensisExam){
//					((SensisExam)examination).addExamData(dataValues);
//					
//				}
				treatmentCase.getExaminations().add(examination);
			}
		}
	}
}

public void storeToDB() {
	if(treatmentCase instanceof TreatmentCase) {
		if(insertPatient()) {
			if(insertTreatmentCase()) {
				if(!insertExamination()){
					insertCorruptStudy();
				};
			}else{
				insertCorruptStudy();
			}
		}else{
			insertCorruptStudy();
		}
		
//		try {
//			treatmentCase.storeToDB();
//		} catch (SQLException e) {
//			if(e.getErrorCode() == 1062){
//				treatmentCase.setId(SQL_SELECT.TreatmentCaseId(treatmentCase));
//			}
//		}		
	}
}

private void insertCorruptStudy() {
	SQL_INSERT.corruptSensisStudy(file);
	
}

private Boolean insertExamination() {
	if(examination instanceof Examination){
		return (examination.storeExamToDB(treatmentCase.getId()));
	}else{
		return false;
	}
	
}

private Boolean insertTreatmentCase() {
	if(treatmentCase instanceof TreatmentCase) {
		try {
			treatmentCase.storeToDB();
			return true;
		} catch (SQLException e) {
			System.out.println(e);
			if(e.getErrorCode() == 1062) {					
				treatmentCase.setId(SQL_SELECT.TreatmentCaseId(treatmentCase));
				if(treatmentCase.getId() != null) {
					return true;
				}else {
					return false;
				}
				
			}
			return false;
		}
	}
	return false;
}

/**
 * insert the patient to the database
 * @return true if patient could be inserted to database, otherwise false
 */
private Boolean insertPatient() {	
	try {
		treatmentCase.getPatient().storePatientToDB();
		return true;
	} catch (SQLException e) {
		if(e.getErrorCode() == 1062){
			treatmentCase.getPatient().setId(SQL_SELECT.PatientId(treatmentCase.getPatient()));	
			if(treatmentCase.getPatient().getId() != null) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
}

	
	
	
}
