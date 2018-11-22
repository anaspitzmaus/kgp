package com.rose.kgp.data_exchange;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import com.rose.kgp.ui.Controller_PnlSetDate;

public class Ctrl_PnlCtrlTblExamFiles {
	Pnl_CtrlTblExamFiles pnl_CtrlTblExamFiles;
	Controller_PnlSetDate ctrlStartDate;
	Controller_PnlSetDate ctrlEndDate;
	
	
	protected Pnl_CtrlTblExamFiles getPnlCtrlTblExamFiles(){
		return this.pnl_CtrlTblExamFiles;
	}
	
	protected Controller_PnlSetDate getCtrlStartDate(){
		return this.ctrlStartDate;
	}
	
	protected Controller_PnlSetDate getCtrlEndDate(){
		return this.ctrlEndDate;
	}
	
	public Ctrl_PnlCtrlTblExamFiles() {
		ctrlStartDate = new Controller_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusMonths(100));
		ctrlEndDate = new Controller_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusMonths(100));
		pnl_CtrlTblExamFiles = new Pnl_CtrlTblExamFiles(ctrlStartDate, ctrlEndDate);
		
	}
	
	
	
	
	
	
}
