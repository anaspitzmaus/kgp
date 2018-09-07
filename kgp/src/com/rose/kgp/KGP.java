package com.rose.kgp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;

import com.rose.kgp.administration.Ctrl_ActivityKind;

public class KGP {

	private JFrame frame;
	private String git;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KGP window = new KGP();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public KGP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Ctrl_ActivityKind ctrlActivityKind = new Ctrl_ActivityKind();
		frame.add(ctrlActivityKind.getPanel(), BorderLayout.CENTER);
	}

}
