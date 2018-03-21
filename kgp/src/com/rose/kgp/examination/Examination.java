package com.rose.kgp.examination;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Physician;

public abstract class Examination {
	protected String notation;
	protected LocalDateTime start, end;
	protected Physician physician;
	protected ArrayList<Physician> physicians_assist;
	protected ArrayList<Nurse>	nurses;
	
	public String getNotation() {
		return notation;
	}

	public void setNotation(String notation) {
		this.notation = notation;
	}

	protected LocalDateTime getStart() {
		return start;
	}

	protected void setStart(LocalDateTime start) {
		this.start = start;
	}

	protected LocalDateTime getEnd() {
		return end;
	}

	protected void setEnd(LocalDateTime end) {
		this.end = end;
	}

	public Examination(String notation){
		this.notation = notation;
	}
	
	
}
