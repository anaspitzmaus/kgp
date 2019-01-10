package com.rose.kgp.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

import com.rose.kgp.administration.TreatmentCase;
import com.rose.kgp.data_exchange.SensisStudy;
import com.rose.kgp.data_exchange.Study;
import com.rose.kgp.data_exchange.Sensis;
import com.rose.kgp.db.DB;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
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
//		sensis = new Sensis(prefs.get("Sensis_Path", null));
//		if(sensis.getFolderPath() instanceof Path) {
//			ArrayList<File> files = sensis.listFilesForFolder();
//			FilesAndDB filesAndDB = new FilesAndDB();
//			for(File file: files) {
//				if(filesAndDB.IsFileStoredInDB(file)) {
//					//if file is already stored in database
//					//change the directory of that file 
//					changeFileDirectory(file);
//				}else {
//					//if file is not stored in database
//					
//					//read the file and store the basic data in database schema sensis_files
//					HashMap<String, HashMap<String, ArrayList<String>>> studyValues = null;
//					try {
//						studyValues = sensis.readExamFile(file.getName());
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//					
//					TreatmentCase treatmentCase = new TreatmentCase(studyValues);
//					try {
//						if(treatmentCase.getPatient().getInID() != null | treatmentCase.getPatient().getOutID() != null){
//							//if patient has either an inID or an outID
//							treatmentCase.getPatient().storePatientToDB(); //store patient to DB
//						}
//					} catch (SQLException e) {
//						//check sqlException
//						System.out.println(e.getErrorCode());
//						if(e.getErrorCode() == 1062){//check duplicate entry error
//							//if duplicate entry (patient already exists in schema patient)
//							//get the id of that patient as stored in the database and
//							//insert treatmentCase to database
//						}							 
//					}
//					
//					storeTreatmentCaseToDB(treatmentCase);//store the treatmentCase to the database
					Path sensisFolderPath = Paths.get(prefs.get("Sensis_Path", null));
					
					Sensis sensis = new Sensis(sensisFolderPath);
					for(File sensisFile: sensis.getFiles()){
						SensisStudy sensisStudy = new SensisStudy(sensisFile);
						
							
						try {
							sensisStudy.readFile();
							sensisStudy.createStudy();							
							sensisStudy.storeToDB();
							
							
//							Patient patient = sensisStudy.getPatient();
//							if(patient instanceof Patient){
//								treatmentCase = sensisStudy.getTreatmentCase(patient);
//								try {
//									patient.storePatientToDB();
//									storeTreatmentCaseToDB(treatmentCase);
//								} catch (SQLException e) {
//									if(e.getErrorCode() == 1062){
//										patient.setId(SQL_SELECT.PatientId(patient));										
//										storeTreatmentCaseToDB(treatmentCase);
//									}else{
//										
//									}
//								}
//							}
							
							
							
//							if(sensisStudy.getFile().getName().equals("0041276370_3214_3457.HIS")) {
//								System.out.println("");
//							}else {
//								System.out.println("");
//							}
//							Examination examination = sensisStudy.getExamination();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					Study study = new Study(studyValues);
//					StudyType studyType = study.studyType();
//					Examination examination = null;;
//					switch (studyType){
//						case Koronar_Diagnostisch:
//							examination = new LeftHeartCatheter(studyValues);
//							break;
//						case Peripher_Diagnostisch:
//							examination = new AngioPeri(studyValues);
//							break;
//						default:
//							break;							
//					}
//					if(examination instanceof Examination){
//						examination.setTreatmentCase(treatmentCase);//set the treatmentCase to the examination
//						treatmentCase.getExaminations().add(examination);//add the examination to the treatmentCase
//						
//					}
					
					
//					TreatmentCase treatmentCase = new TreatmentCase(studyValues);
//					storeTreatmentCaseToDB(treatmentCase);
//					Examination examination = 
//					storeExaminationToDB();
					//change the directory of that file
					//changeFileDirectory(file);
				//}
			//}
		//}
	}
	
	private void changeFileDirectory(File file) {
		
	}
	
	
	
	
	private void storeExaminationToDB(Examination exam){
		
	}
	
	
}
