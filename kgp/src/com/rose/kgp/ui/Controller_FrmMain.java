package com.rose.kgp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import com.rose.kgp.administration.Ctrl_ActivityKind;
import com.rose.kgp.data_exchange.Controller_PnlTblExamFiles;

public class Controller_FrmMain {
	Frm_Main frmMain;
	Controller_MenuMain conMenuMain;
	Controller_PnlTblExamFiles conPnlTblExamFiles;
	Ctrl_ActivityKind conActivityKind;
	Controller_ActivityRecord ctrlActivityRecord;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Controller_FrmMain conFrmMain = new Controller_FrmMain();
					conFrmMain.build();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * build the main frame
	 */
	public void build(){
		frmMain = new Frm_Main();
		conMenuMain = new Controller_MenuMain();
		frmMain.add(conMenuMain.getPanel(), BorderLayout.NORTH);
		ctrlActivityRecord = new Controller_ActivityRecord();
		frmMain.getPnlNorth().add(ctrlActivityRecord.getCtrlPnlTblExamFiles().getPanel(), BorderLayout.CENTER);
		frmMain.getPnlSouth().add(ctrlActivityRecord.getCtrlActivityKind().getPanel(), BorderLayout.CENTER);
		frmMain.setVisible(true);
	}
}
