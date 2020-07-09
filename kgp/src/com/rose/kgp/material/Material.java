package com.rose.kgp.material;

public abstract class Material {
	String notation;

	
	protected String getNotation() {
		return notation;
	}


	protected void setNotation(String notation) {
		this.notation = notation;
	}


	public Material(String notation) {
		this.notation = notation;
	}
}
