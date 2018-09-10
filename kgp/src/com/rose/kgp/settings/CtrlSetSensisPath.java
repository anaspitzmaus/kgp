package com.rose.kgp.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.prefs.Preferences;

import javax.swing.JFileChooser;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class CtrlSetSensisPath {
	PnlSetSensisPath pnlSetSensisPath;
	PathDocumentListener pathDocumentListener;
	OpenFileChooserListener openFileChooserListener;
	String fileName;
	
	
	public PnlSetSensisPath getPnlSetSensisPath() {
		return pnlSetSensisPath;
	}

	public CtrlSetSensisPath() {
		Preferences prefs = Preferences.userNodeForPackage(this.getClass());
		prefs.put("Sensis_Path", "a string");
		pnlSetSensisPath = new PnlSetSensisPath();
		setListener();
	}
	
	private void setListener(){
		pathDocumentListener = new PathDocumentListener();
		pnlSetSensisPath.addPathDocumentListener(pathDocumentListener);
		openFileChooserListener = new OpenFileChooserListener();
		pnlSetSensisPath.addFileChooserListener(openFileChooserListener);
	}
	
	class PathDocumentListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class OpenFileChooserListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JFileChooser chooser = new JFileChooser();
			int returnValue = chooser.showDialog(null, "Ausw�hlen");
			if(returnValue == JFileChooser.APPROVE_OPTION){
				fileName = chooser.getSelectedFile().getAbsolutePath();
				pnlSetSensisPath.getTxtPath().setText(fileName);
			}
			
		}
		
	}
}