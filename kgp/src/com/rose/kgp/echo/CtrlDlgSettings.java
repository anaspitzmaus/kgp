package com.rose.kgp.echo;

import javax.swing.JDialog;

public class CtrlDlgSettings {
	DlgSettings dlgSettings;
	
	
	
	protected DlgSettings getDialog() {
		return dlgSettings;
	}

	public CtrlDlgSettings() {
		if(!(dlgSettings instanceof DlgSettings)) {
			dlgSettings = new DlgSettings();
		}
	}
	
	protected void showDialog() {
		dlgSettings.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dlgSettings.setVisible(true);
	}
}
