package com.rose.kgp.examination;

import java.util.ArrayList;

import com.rose.kgp.material.Stent;

public class PCI extends Catheter_Intervention{

	protected ArrayList<Stent> stents;
	
	public PCI(String notation) {
		super(notation);
		stents = new ArrayList<Stent>();
		
	}
	
	
	

}
