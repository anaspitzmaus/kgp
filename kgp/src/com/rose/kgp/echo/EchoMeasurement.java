package com.rose.kgp.echo;

import com.rose.heart.structures.DIAMKIND;
import com.rose.heart.structures.IMeasurableStructure;
import com.rose.heart.structures.Measurement;

public class EchoMeasurement extends Measurement{

	ProbeLocation probeLocation;
	Modus modus;
	Study study;
	
	protected ProbeLocation getProbeLocation() {
		return probeLocation;
	}

	protected void setProbeLocation(ProbeLocation probeLocation) {
		this.probeLocation = probeLocation;
	}

	protected Modus getModus() {
		return modus;
	}

	protected void setModus(Modus modus) {
		this.modus = modus;
	}

	public EchoMeasurement(ProbeLocation location, Modus modus, DIAMKIND diamkind, IMeasurableStructure structure) {
		super(diamkind, structure);
		this.probeLocation = location;
		this.modus = modus;		
	}
	
	public EchoMeasurement(Study study, ProbeLocation location, Modus modus, DIAMKIND diamkind, IMeasurableStructure structure) {
		super(diamkind, structure);
		this.study = study;
		this.probeLocation = location;
		this.modus = modus;		
	}
	
	public EchoMeasurement(ProbeLocation location, Modus modus, DIAMKIND diamkind) {
		super(diamkind, null);
		this.probeLocation = location;
		this.modus = modus;		
	}
	
	
	
	
	
}
