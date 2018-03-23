package com.rose.kgp.examination;

import java.util.ArrayList;



public class PM_Implant extends PM_Intervention {
	
	ArrayList<ElectrodeImplant> electrodesImplant;
	
	public PM_Implant(String notation) {
		super(notation);
		electrodesImplant = new ArrayList<ElectrodeImplant>();
	}
	
	

}
