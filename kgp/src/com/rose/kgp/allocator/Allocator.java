package com.rose.kgp.allocator;

import java.time.LocalDate;

public class Allocator {
	
	LocalDate onset;
	Clinical_Institution clinicalInstitution;


	public LocalDate getOnset() {
		return onset;
	}



	public void setOnset(LocalDate onset) {
		this.onset = onset;
	}

	

	public Clinical_Institution getClinicalInstitution() {
		return clinicalInstitution;
	}



	public Allocator(Clinical_Institution clinicalInstituition) {
		this.clinicalInstitution = clinicalInstituition;
	}
}
