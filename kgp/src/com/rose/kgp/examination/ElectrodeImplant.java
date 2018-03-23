package com.rose.kgp.examination;

import com.rose.heart.structures.Cavum;
import com.rose.kgp.material.Electrode;

public class ElectrodeImplant {
	Double potential;
	Double threshold;
	Cavum cavum;
	Boolean active;
	Electrode electrode;
	
	public ElectrodeImplant(Electrode electrode) {
		this.electrode = electrode;
	}
}
