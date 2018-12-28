package com.rose.kgp.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.prefs.Preferences;

import com.rose.kgp.administration.TreatmentCase;
import com.rose.kgp.data_exchange.DataConversion;
import com.rose.kgp.data_exchange.Sensis;
import com.rose.kgp.db.DB;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.personnel.Patient;
import com.rose.kgp.settings.CtrlSetSensisPath;

public class StartCore {

	static Preferences prefs;
	
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
		Sensis sensis = new Sensis(prefs.get("Sensis_Path", null));
		if(sensis.getFolderPath() instanceof Path) {
			ArrayList<File> files = sensis.listFilesForFolder();
			FilesAndDB filesAndDB = new FilesAndDB();
			for(File file: files) {
				if(filesAndDB.IsFileStoredInDB(file)) {
					//if file is already stored in database
					//switch the file to another folder
				}else {
					//if file is not stored in database
					//read the file and store the basic data in database schema sensis_files
					try {
						HashMap<String, HashMap<String, ArrayList<String>>> values = sensis.readExamFile(file.getName());
						TreatmentCase treatmentCase = new TreatmentCase(values);					
						SQL_INSERT.Patient(treatmentCase.getPatient());
						
						//SQL_INSERT.BasicSensisData()
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//switch the file to another folder
				}
			}
		}
	}
}
