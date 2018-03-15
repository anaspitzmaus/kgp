package com.rose.kgp.examination;

public abstract class Examination {
	protected String notation;
	
	
	public String getNotation() {
		return notation;
	}

	public void setNotation(String notation) {
		this.notation = notation;
	}


	public Examination(String notation){
		this.notation = notation;
	}
}
