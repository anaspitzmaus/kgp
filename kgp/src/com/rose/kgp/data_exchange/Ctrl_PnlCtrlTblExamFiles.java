package com.rose.kgp.data_exchange;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

public class Ctrl_PnlCtrlTblExamFiles {
	Pnl_CtrlTblExamFiles pnl_CtrlTblExamFiles;
	Ctrl_PnlSetDate ctrlStartDate;
	Ctrl_PnlSetDate ctrlEndDate;
	
	
	protected Pnl_CtrlTblExamFiles getPnlCtrlTblExamFiles(){
		return this.pnl_CtrlTblExamFiles;
	}
	
	protected Ctrl_PnlSetDate getCtrlStartDate(){
		return this.ctrlStartDate;
	}
	
	protected Ctrl_PnlSetDate getCtrlEndDate(){
		return this.ctrlEndDate;
	}
	
	public Ctrl_PnlCtrlTblExamFiles() {
		ctrlStartDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusMonths(100));
		ctrlEndDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusMonths(100));
		pnl_CtrlTblExamFiles = new Pnl_CtrlTblExamFiles(ctrlStartDate, ctrlEndDate);
		
	}
	
	
	
	
	
	
}
