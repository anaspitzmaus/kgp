package com.rose.kgp.useful;

public enum MyColor {
	RED(255, 0, 0), BLUE(0, 0, 255), GREEN(0, 255, 0), SALMON(250, 128, 114);
	
	int r, g, b;
	
	MyColor(Integer r, Integer g, Integer b){
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public int getR(){
		return r;
	}
	
	public int getG(){
		return g;
	}
	
	public int getB(){
		return b;
	}
}
