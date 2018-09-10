package com.rose.kgp.settings;

public class CtrlSettings {
	PnlSettings pnlSettings;
	CtrlSetSensisPath ctrlSetSensisPath;
	
	public PnlSettings getPnlSettings() {
		return pnlSettings;
	}


	public CtrlSettings() {
		ctrlSetSensisPath = new CtrlSetSensisPath();
		pnlSettings = new PnlSettings(); //create the main panel
		pnlSettings.getPnlCenter().add(ctrlSetSensisPath.getPnlSetSensisPath(), "cell 0 0 3 1,alignx left");//add the sensis path panel to the main panel
	}
}
