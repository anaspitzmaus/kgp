package com.rose.kgp.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

import com.rose.kgp.administration.TreatmentCase;
import com.rose.kgp.data_exchange.Sensis;
import com.rose.kgp.db.DB;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.examination.Examination;
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
					storeFileToDB(file);
					//change the directory of that file
					changeFileDirectory(file);
				}
			}
		}
	}
	
	private void changeFileDirectory(File file) {
		
	}
	
	private void storeFileToDB(File file) {
		if(sensis instanceof Sensis) {
			try {
				HashMap<String, HashMap<String, ArrayList<String>>> values = sensis.readExamFile(file.getName());
				TreatmentCase treatmentCase = new TreatmentCase(values);					
				Integer treatment_id = SQL_INSERT.TreatmentCase(treatmentCase);//insert the treatment_case (returns the id of the treatment case)
				if(treatment_id != null){//if treatment case could be inserted 
					//insert the examination
					//insertExamination(treatmentCase.getE)
				}
				//SQL_INSERT.BasicSensisData()
			} catch (IOException | SQLException e) {
				//check the exception
				//if treatment_case already exists (case_number already exists)
				//insert the examination 
			}
		}
	}
	
	private void insertExamination(Examination exam){
		
	}
}
