package com.rose.kgp.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

public class Controller_FrmMain {
	Frm_Main frmMain;
	Controller_MenuMain conMenuMain;
	
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
	public void build(){
		frmMain = new Frm_Main();
		conMenuMain = new Controller_MenuMain();
		frmMain.add(conMenuMain.getPanel(), BorderLayout.NORTH);
		frmMain.setVisible(true);
	}
}
