package com.rose.kgp.material;

public class PM extends Material
{

	String serialNr;
	PM_Type type;
	
	
	protected String getSerialNr() {
		return serialNr;
	}


	protected void setSerialNr(String serialNr) {
		this.serialNr = serialNr;
	}


	protected PM_Type getType() {
		return type;
	}


	protected void setType(PM_Type type) {
		this.type = type;
	}


	public PM(String notation) {
		super(notation);
		// TODO Auto-generated constructor stub
	}

}
