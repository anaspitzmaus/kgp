package com.rose.kgp.core;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.prefs.Preferences;

import com.rose.kgp.data_exchange.Sensis;
import com.rose.kgp.db.DB;
import com.rose.kgp.settings.CtrlSetSensisPath;

public class StartCore {

	static Preferences prefs;
	
	public static void main(String[] args) {
		if(DB.createConnection() != null){	
			prefs = Preferences.userNodeForPackage(CtrlSetSensisPath.class);
			Sensis sensis = new Sensis(prefs.get("Sensis_Path", null));
			if(sensis.getFolderPath() instanceof Path) {
				ArrayList<File> files = sensis.listFilesForFolder();
				
			}
		}else {
			System.out.println("no database connection");
		}
	}
}
