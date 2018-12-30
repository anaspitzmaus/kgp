package com.rose.kgp.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

import com.rose.kgp.administration.TreatmentCase;
import com.rose.kgp.data_exchange.Study;
import com.rose.kgp.data_exchange.Sensis;
import com.rose.kgp.db.DB;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.examination.AngioPeri;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.examination.LeftHeartCatheter;
import com.rose.kgp.examination.StudyType;
import com.rose.kgp.personnel.Patient;
import com.rose.kgp.settings.CtrlSetSensisPath;

public class StartCore {

	static Preferences prefs;
	Sensis sensis;
	
	public static void main(String[] args) {
		if(DB.createConnection() != null){	
			StartCore startCore = new StartCore();
			startCore.getSensisFiles();
			
		}else {
			System.out.println("no database connection");
		}
	}
	
	private void getSensisFiles() {
		prefs = Preferences.userNodeForPackage(CtrlSetSensisPath.class);
		sensis = new Sensis(prefs.get("Sensis_Path", null));
		if(sensis.getFolderPath() instanceof Path) {
			ArrayList<File> files = sensis.listFilesForFolder();
			FilesAndDB filesAndDB = new FilesAndDB();
			for(File file: files) {
				if(filesAndDB.IsFileStoredInDB(file)) {
					//if file is already stored in database
					//change the directory of that file 
					changeFileDirectory(file);
				}else {
					//if file is not stored in database
					
					//read the file and store the basic data in database schema sensis_files
					HashMap<String, HashMap<String, ArrayList<String>>> studyValues = null;
					try {
						studyValues = sensis.readExamFile(file.getName());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					TreatmentCase treatmentCase = new TreatmentCase(studyValues);
					try {
						if(treatmentCase.getPatient().getInID() != null | treatmentCase.getPatient().getOutID() != null){
							//if patient has either an inID or an outID
							treatmentCase.getPatient().storePatientToDB(); //store patient to DB
						}
					} catch (SQLException e) {
						//check sqlException
						System.out.println(e.getErrorCode());
						if(e.getErrorCode() == 1062){//check duplicate entry error
							//if duplicate entry (patient already exists in schema patient
							//insert treatmentCase to database
						}							 
					}
					
					storeTreatmentCaseToDB(treatmentCase);//store the treatmentCase to the database
					
					Study study = new Study(studyValues);
					StudyType studyType = study.studyType();
					Examination examination = null;;
					switch (studyType){
						case Koronar_Diagnostisch:
							examination = new LeftHeartCatheter(studyValues);
							break;
						case Peripher_Diagnostisch:
							examination = new AngioPeri(studyValues);
							break;
						default:
							break;							
					}
					if(examination instanceof Examination){
						examination.setTreatmentCase(treatmentCase);//set the treatmentCase to the examination
						treatmentCase.getExaminations().add(examination);//add the examination to the treatmentCase
						
					}
					
					
//					TreatmentCase treatmentCase = new TreatmentCase(studyValues);
//					storeTreatmentCaseToDB(treatmentCase);
//					Examination examination = 
//					storeExaminationToDB();
					//change the directory of that file
					changeFileDirectory(file);
				}
			}
		}
	}
	
	private void changeFileDirectory(File file) {
		
	}
	
	private void storeTreatmentCaseToDB(TreatmentCase treatmentCase) {
		
			try {
									
				Integer treatment_id = SQL_INSERT.TreatmentCase(treatmentCase);//insert the treatment_case (returns the id of the treatment case)
				if(treatment_id != null){//if treatment case could be inserted 
					//insert the examination
					//insertExamination(treatmentCase.getE)
				}
				//SQL_INSERT.BasicSensisData()
			} catch (SQLException e) {
				//check the exception
				//if treatment_case already exists (case_number already exists)
				//insert the examination 
			}
		}
	
	
	private void storeExaminationToDB(Examination exam){
		
	}
	
	
}
