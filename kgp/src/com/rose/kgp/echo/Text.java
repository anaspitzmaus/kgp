package com.rose.kgp.echo;

import java.util.ArrayList;
import java.util.prefs.Preferences;

import com.rose.heart.construct.Normal_Heart;
import com.rose.heart.structures.DIAMKIND;
import com.rose.heart.structures.Measurement;
import com.rose.heart.structures.Ventricle;
import com.rose.kgp.personnel.Patient;


public class Text {
	Normal_Heart heart;	
	Preferences prefs;
	Patient patient;
	ArrayList<String> sizeLevel;
	
	public Text(Normal_Heart heart, Patient patient) {
		this.heart = heart;
		this.patient = patient;
		prefs = Preferences.userRoot().node(RefValues.class.getName());
		sizeLevel = new ArrayList<>();
		sizeLevel.add("normal groß");
		sizeLevel.add("gering vergrößert");
		sizeLevel.add("mäßig vergrößert");
		sizeLevel.add("deutlich vergrößert");
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
		
		Double size = 0.0;
		int count = 0;
		for(int i = 0; i<heart.getDiastolicState().getLeftVentricle().getMeasurements().size(); i++) {
			EchoMeasurement measurement = (EchoMeasurement) heart.getDiastolicState().getLeftVentricle().getMeasurements().get(i);
			if(measurement.getDiamkind() == DIAMKIND.WIDTH && measurement.getModus() == Modus.M_MODE) {
				size = size + (Double)measurement.getValue();
				count = count + 1;
			}
		}
		Double valMean;
		String txtSizeLevel = "";
		if(count > 0) {//do the following if mean value is > 0
			//set mean value
			valMean = size/count;
			//compare with reference values
			for(int i = 0; i<refValLVVolDia.size() - 1; i++) {				
				if(valMean >= refValLVVolDia.get(i) && valMean < refValLVVolDia.get(i+1)) {
					txtSizeLevel = sizeLevel.get(i);					
				}
			}
			if(valMean < refValLVVolDia.get(0)) {
				txtSizeLevel = "verkleinert";
			}else if(valMean >= refValLVVolDia.get(3)) {
				txtSizeLevel = sizeLevel.get(3);
			}
		}
		
		System.out.println(txtSizeLevel);
		
		
		
		
	}
	
	protected void setCaviText() {
		
	}
	
	private void checkVentricleSize(Ventricle ventricle) {
		
	}
}
