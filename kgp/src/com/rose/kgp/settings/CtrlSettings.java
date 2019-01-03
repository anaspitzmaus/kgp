package com.rose.kgp.settings;

public class CtrlSettings {
	PnlSettings pnlSettings;
	CtrlSetSensisPath ctrlSetSensisPath;
	CtrlSetSensisCopyPath ctrlSetSensisCopyPath;
	
	public PnlSettings getPnlSettings() {
		return pnlSettings;
	}


	public CtrlSettings() {
		ctrlSetSensisPath = new CtrlSetSensisPath();
		ctrlSetSensisCopyPath = new CtrlSetSensisCopyPath();
		pnlSettings = new PnlSettings(); //create the main panel
		pnlSettings.getPnlCenter().add(ctrlSetSensisPath.getPnlSetSensisPath(), "cell 0 0 3 1,alignx left");//add the sensis path panel to the main panel
		pnlSettings.getPnlCenter().add(ctrlSetSensisCopyPath.getPnlSetSensisCopyPath(), "cell 0 1 3 1,alignx left");//add the sensis path copy panel to the main panel
		
	}
}
