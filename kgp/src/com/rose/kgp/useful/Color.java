package com.rose.kgp.useful;


import java.util.HashMap;

public class Color {
	private HashMap<String, HashMap<String, Integer>> color;
	private HashMap<String, Integer> rgb;
	private String colorName;
	
	public Color(String colorName) {
		this.colorName = colorName;
		color = new HashMap<String, HashMap<String, Integer>>();
		rgb = new HashMap<String, Integer>();
		rgb.put("r", 255);
		rgb.put("g", 0);
		rgb.put("b", 0);
		color.put("RED", rgb);
	}
	
	public HashMap<String, Integer> getColor(String colorName){
		return color.get(colorName);
	}
}
