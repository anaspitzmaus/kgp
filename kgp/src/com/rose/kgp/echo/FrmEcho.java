package com.rose.kgp.echo;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;

public class FrmEcho extends JFrame {

	
	private static final long serialVersionUID = 5365072135029013507L;
	
	
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmEcho frame = new FrmEcho();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmEcho() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JPanel pnlValues = new JPanel();
		pnlValues.setLayout(new GridLayout(0,2));
		
		JPanel pnlRe = new JPanel();
		pnlRe.setLayout(new GridLayout(0,1));
		
		PnlMMAoLA pnlMMAoLA = new PnlMMAoLA();
		pnlValues.add(pnlMMAoLA);

		PnlMMLV pnlMMLV = new PnlMMLV();
		pnlValues.add(pnlMMLV);
		
		PnlResult pnlResult = new PnlResult();
		pnlRe.add(pnlResult);
		
		contentPane.add(pnlValues, BorderLayout.NORTH);
		contentPane.add(new JSeparator(), BorderLayout.CENTER);
		contentPane.add(pnlRe, BorderLayout.SOUTH);
		
		this.pack();
		
		
	}

}
