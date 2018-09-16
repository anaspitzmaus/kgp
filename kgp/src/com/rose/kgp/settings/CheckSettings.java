package com.rose.kgp.settings;

import java.awt.Dialog.ModalityType;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.prefs.Preferences;



public class CheckSettings {
	Preferences prefs; 
	
	public Boolean sensisPath(){
		prefs = Preferences.userNodeForPackage(this.getClass());
		String path = prefs.get("Sensis_Path", null);
		if(path != null && pathExists(Paths.get(path))){
			return true;
		}else{
			//open a dialog to ask the path
			DlgPath dlgPath = new DlgPath();
			dlgPath.setModalityType(ModalityType.APPLICATION_MODAL);
			dlgPath.setVisible(true);	
			return false;
		}
	}
	
	private Boolean pathExists(Path path){
		if(Files.exists(path, LinkOption.NOFOLLOW_LINKS)){
			File f = path.toFile();
			if(f.isDirectory()) {return true;};
		};
		return false;
	}
	
	
}
