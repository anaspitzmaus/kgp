package com.rose.kgp.examination;

import java.util.ArrayList;
import java.util.HashMap;

import com.rose.heart.structures.Cavum;
import com.rose.kgp.material.PM;

public abstract class PM_Intervention extends Examination{

	protected PM pm;
	
	
	public PM_Intervention(HashMap<String, HashMap<String, ArrayList<String>>> studyValues) {
		super(studyValues);
		// TODO Auto-generated constructor stub
	}

	
}
