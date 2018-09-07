package com.rose.kgp.ui;



import com.rose.kgp.administration.Ctrl_ActivityKind;
import com.rose.kgp.data_exchange.Controller_PnlTblExamFiles;

/**
 * controller of the tab 'activity record'
 * @author Administrator
 *
 */
public class Controller_ActivityRecord {
	Controller_PnlTblExamFiles ctrlPnlTblExamFiles;
	Ctrl_ActivityKind ctrlActivityKind;
	
	public Controller_ActivityRecord() {
		ctrlPnlTblExamFiles = new Controller_PnlTblExamFiles();
		ctrlActivityKind = new Ctrl_ActivityKind();
		ctrlPnlTblExamFiles.getTblRowSelectionListener().addObserver(ctrlActivityKind);
	}

	public Controller_PnlTblExamFiles getCtrlPnlTblExamFiles() {
		return ctrlPnlTblExamFiles;
	}

	public Ctrl_ActivityKind getCtrlActivityKind() {
		return ctrlActivityKind;
	}
	
	
}
