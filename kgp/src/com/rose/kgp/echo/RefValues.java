package com.rose.kgp.echo;

import java.util.prefs.Preferences;


public class RefValues {

	Preferences prefs;
	
	public RefValues() {
		prefs = Preferences.userRoot().node(this.getClass().getName());
		
		String IVSdMNormal = "IVSdMNormal";
		String IVSdMMild = "IVSdMMild";
		String IVSdMModerate = "IVSdMModerate";
		String IVSdMSevere = "IVSdMSevere";
		
		prefs.putDouble(IVSdMNormal, 6.0);
		prefs.putDouble(IVSdMMild, 10.5);
		prefs.putDouble(IVSdMModerate, 13.5);
		prefs.putDouble(IVSdMSevere, 16.5);
		
		String LVPWdMNormal = "LVPWdMNormal";
		String LVPWdMMild = "LVPWdMMild";
		String LVPWdMModerate = "LVPWdMModerate";
		String LVPWdMSevere = "LVPWdMSevere";
		
		prefs.putDouble(LVPWdMNormal, 6.0);
		prefs.putDouble(LVPWdMMild, 10.5);
		prefs.putDouble(LVPWdMModerate, 13.5);
		prefs.putDouble(LVPWdMSevere, 16.5);
		
		String IVSdFNormal = "IVSdFNormal";
		String IVSdFMild = "IVSdFMild";
		String IVSdFModerate = "IVSdFModerate";
		String IVSdFSevere = "IVSdFSevere";
		
		prefs.putDouble(IVSdFNormal, 6.0);
		prefs.putDouble(IVSdFMild, 10.5);
		prefs.putDouble(IVSdFModerate, 13.5);
		prefs.putDouble(IVSdFSevere, 16.5);
		
		String LVPWdFNormal = "LVPWdFNormal";
		String LVPWdFMild = "LVPWdFMild";
		String LVPWdFModerate = "LVPWdFModerate";
		String LVPWdFSevere = "LVPWdFSevere";
		
		prefs.putDouble(LVPWdFNormal, 6.0);
		prefs.putDouble(LVPWdFMild, 10.5);
		prefs.putDouble(LVPWdFModerate, 13.5);
		prefs.putDouble(LVPWdFSevere, 16.5);
		
		/**
		 * left ventricular enddiastolic volume in men
		 */
		String LVVolDiaMNormal = "LVVolDiaMNormal";
		String LVVolDiaMMild = "LVVolDiaMMild";
		String LVVolDiaMModerate = "LVVolDiaMModerate";
		String LVVolDiaMSevere = "LVVolDiaMSevere";
		
		prefs.putDouble(LVVolDiaMNormal, 62);
		prefs.putDouble(LVVolDiaMMild, 151);
		prefs.putDouble(LVVolDiaMModerate, 175);
		prefs.putDouble(LVVolDiaMSevere, 200);
		
		/**
		 * left ventricular enddiastolic volume in women
		 */
		String LVVolDiaFNormal = "LVVolDiaFNormal";
		String LVVolDiaFMild = "LVVolDiaFMild";
		String LVVolDiaFModerate = "LVVolDiaFModerate";
		String LVVolDiaFSevere = "LVVolDiaFSevere";
		
		prefs.putDouble(LVVolDiaFNormal, 46);
		prefs.putDouble(LVVolDiaFMild, 107);
		prefs.putDouble(LVVolDiaFModerate, 121);
		prefs.putDouble(LVVolDiaFSevere, 130);
		
		
		/**
		 * left ventricular endsystolic volume in men
		 */
		String LVVolSysMNormal = "LVVolSysMNormal";
		String LVVolSysMMild = "LVVolSysMMild";
		String LVVolSysMModerate = "LVVolSysMModerate";
		String LVVolSysMSevere = "LVVolSysMSevere";
		
		prefs.putDouble(LVVolSysMNormal, 21);
		prefs.putDouble(LVVolSysMMild, 62);
		prefs.putDouble(LVVolSysMModerate, 74);
		prefs.putDouble(LVVolSysMSevere, 85);
		
		/**
		 * left ventricular endsystolic volume in women
		 */
		String LVVolSysFNormal = "LVVolSysFNormal";
		String LVVolSysFMild = "LVVolSysFMild";
		String LVVolSysFModerate = "LVVolSysFModerate";
		String LVVolSysFSevere = "LVVolSysFSevere";
		
		prefs.putDouble(LVVolSysFNormal, 14);
		prefs.putDouble(LVVolSysFMild, 43);
		prefs.putDouble(LVVolSysFModerate, 56);
		prefs.putDouble(LVVolSysFSevere, 67);
	}
	
}
