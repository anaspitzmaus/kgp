package com.rose.kgp.echo;

import java.awt.EventQueue;

public class CtrlEcho {

	static FrmEcho frmEcho;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmEcho = new FrmEcho();
					frmEcho.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
