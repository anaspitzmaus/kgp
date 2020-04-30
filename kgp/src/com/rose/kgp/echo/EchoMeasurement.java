package com.rose.kgp.echo;

import com.rose.heart.structures.DIAMKIND;
import com.rose.heart.structures.IMeasurableStructure;
import com.rose.heart.structures.Measurement;

public class EchoMeasurement extends Measurement{

	ProbeLocation probeLocation;
	Modus modus;
	String notation;	
	
	
	public EchoMeasurement(ProbeLocation location, Modus modus, DIAMKIND diamkind, IMeasurableStructure structure) {
		super(diamkind, structure);
		this.probeLocation = location;
		this.modus = modus;		
	}
	
	public EchoMeasurement(ProbeLocation location, Modus modus, DIAMKIND diamkind) {
		super(diamkind, null);
		this.probeLocation = location;
		this.modus = modus;		
	}
	
	
	
	
	
}
