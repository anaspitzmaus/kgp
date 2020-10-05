package com.rose.kgp.material;


public class Electrode{
	
	Integer resistance;
	String serialNr;
	ElectrodeModel electrodeModel;
	
	
	public Integer getResistance() {
		return resistance;
	}


	public void setResistance(Integer resistance) {
		this.resistance = resistance;
	}


	protected String getSerialNr() {
		return serialNr;
	}


	protected void setSerialNr(String serialNr) {
		this.serialNr = serialNr;
	}


	protected ElectrodeModel getElectrodeModel() {
		return electrodeModel;
	}


	protected void setElectrodeModel(ElectrodeModel electrodeModel) {
		this.electrodeModel = electrodeModel;
	}


	public Electrode(ElectrodeModel electrodeModel) {
		this.electrodeModel = electrodeModel;
	}

	
	
	
	
}
