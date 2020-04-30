package com.rose.kgp.echo;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import com.rose.heart.construct.Normal_Heart;
import com.rose.kgp.personnel.Patient;


public class Text {
	Normal_Heart heart;	
	Preferences prefs;
	Patient patient;
	
	public Text(Normal_Heart heart, Patient patient) {
		this.heart = heart;
		this.patient = patient;
		prefs = Preferences.userRoot().node(RefValues.class.getName());
	}
	
	protected void setCavities() {
		//put the reference values to an arrayList
		//left ventricular volume
		ArrayList<Double> refValLVVolDia = new ArrayList<Double>();
		switch (patient.getSex()) {
		case FEMALE: 
			refValLVVolDia.add(prefs.getDouble("LVVolDiaFNormal", 46));
			refValLVVolDia.add(prefs.getDouble("LVVolDiaFMild", 107));
			refValLVVolDia.add(prefs.getDouble("LVVolDiaFModerate", 121));
			refValLVVolDia.add(prefs.getDouble("LVVolDiaFSevere", 130));
			break;

		default:
			refValLVVolDia.add(prefs.getDouble("LVVolDiaMNormal", 62));
			refValLVVolDia.add(prefs.getDouble("LVVolDiaMMild", 151));
			refValLVVolDia.add(prefs.getDouble("LVVolDiaMModerate", 175));
			refValLVVolDia.add(prefs.getDouble("LVVolDiaMSevere", 200));
			break;
		}
		
		
		
	}
	
}
