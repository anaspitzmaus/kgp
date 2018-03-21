package com.rose.kgp.material;

public abstract class Stent extends Material{
	protected Integer length;
	protected Double width;
	
	
	public Integer getLength() {
		return length;
	}


	public Double getWidth() {
		return width;
	}	


	public Stent(String notation, Integer length, Double width) {
		super(notation);
		this.length = length;
		this.width = width;
		// TODO Auto-generated constructor stub
	}	
	
}


