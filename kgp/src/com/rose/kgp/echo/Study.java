package com.rose.kgp.echo;

import java.util.LinkedList;

public class Study {

	StudyType type;
	Integer count;
	
	LinkedList<EchoMeasurement> measurements; 
	
	protected LinkedList<EchoMeasurement> getMeasurements() {
		return measurements;
	}


	protected StudyType getType() {
		return type;
	}	


	protected Integer getCount() {
		return count;
	}

	protected void setCount(Integer count) {
		this.count = count;
	}
	
	

	public Study(StudyType type, LinkedList<EchoMeasurement> measurements) {
		this.type = type;
		this.measurements = measurements;		
	}
}
