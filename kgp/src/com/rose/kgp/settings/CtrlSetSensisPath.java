package com.rose.kgp.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CtrlSetSensisPath {
	PnlSetSensisPath pnlSetSensisPath;
	Preferences prefs;
	OpenFileChooserListener openFileChooserListener;
	String fileName;
	
	
	public PnlSetSensisPath getPnlSetSensisPath() {
		return pnlSetSensisPath;
	}

	public CtrlSetSensisPath() {
		prefs = Preferences.userNodeForPackage(this.getClass());
		pnlSetSensisPath = new PnlSetSensisPath();
		setListener();
	}
	
	private void setListener(){		
		
		openFileChooserListener = new OpenFileChooserListener();
		pnlSetSensisPath.addFileChooserListener(openFileChooserListener);
	}
	
	
	
	class OpenFileChooserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			int returnValue = chooser.showDialog(null, "Auswählen");
			if(returnValue == JFileChooser.APPROVE_OPTION){
				fileName = chooser.getSelectedFile().getParent();
				pnlSetSensisPath.getTxtPath().setText(fileName);
				prefs.put("Sensis_Path", fileName);
			}
			
		}
		
	}
}
