package com.rose.kgp.echo;

import java.awt.EventQueue;

public class Start {	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {	
					new CtrlEcho();										
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	
}
