package com.rose.kgp.material;

import java.time.LocalDate;
import java.util.Date;

import com.rose.kgp.examination.Examination;


public class PM {
	
	String serialNr;
	LocalDate expireDate;
	Examination exam;
	AggregatModel aggregatModel;
	Integer id;
	String notice;
	
	
	
	
	
	public String getNotice() {
		return notice;
	}



	public void setNotice(String notice) {
		this.notice = notice;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public PM(AggregatModel pmModel) {		
		this.aggregatModel = pmModel;
	}		
	
	

	public AggregatModel getAggregatModel() {
		return aggregatModel;
	}


	public String getSerialNr() {
		
		return this.serialNr;
	}


	public void setSerialNr(String serialNr) {
		this.serialNr = serialNr;
		
	}


	public LocalDate getExpireDate() {
		return this.expireDate;
	}


	public void setExpireDate(LocalDate date) {
		this.expireDate = date;
		
	}



	public Examination getExam() {
		return exam;
	}
	
	public void setExam(Examination exam) {
		this.exam = exam;
	}
	
	

}
