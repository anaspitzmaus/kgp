package com.rose.kgp.activityInput;

/**
 * class that represents the kind of activity
 * of an examination
 * @author Ekki
 *
 */
public class ActivityKind {
	protected String notation;
	
	
	protected String getNotation() {
		return notation;
	}


	protected void setNotation(String notation) {
		this.notation = notation;
	}


	public ActivityKind(String notation){
		this.notation = notation;
	}
	
	
}
