package com.rose.kgp.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;

/**
 * class to control the panel to input the path where files of Sensis protocols can be copied to
 * @author Administrator
 *
 */

public class CtrlSetSensisCopyPath {

	PnlSetSensisCopyPath pnlSetSensisCopyPath;
	Preferences prefs;
	OpenFileChooserListener openFileChooserListener;
	Path pathName;
	
	
	public PnlSetSensisCopyPath getPnlSetSensisCopyPath() {
		return pnlSetSensisCopyPath;
	}

	public CtrlSetSensisCopyPath() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		pnlSetSensisCopyPath = new PnlSetSensisCopyPath();
		pnlSetSensisCopyPath.getLblPath().setText("Dateikopie_Pfad");
		setListener();
	}
	
	private void setListener(){		
		
		openFileChooserListener = new OpenFileChooserListener();
		pnlSetSensisCopyPath.addFileChooserListener(openFileChooserListener);
	}
	
	
	
	class OpenFileChooserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnValue = chooser.showDialog(null, "Auswählen");
			if(returnValue == JFileChooser.APPROVE_OPTION){
				pathName = chooser.getSelectedFile().toPath();
				pnlSetSensisCopyPath.getTxtPath().setText(pathName.toString());
				prefs.put("Sensis_Copy_Path", pathName.toString());
			}
			
		}
		
	}
}
