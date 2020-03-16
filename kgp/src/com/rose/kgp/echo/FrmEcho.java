package com.rose.kgp.echo;


import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.GridLayout;

import javax.swing.JSeparator;

public class FrmEcho extends JFrame {

	
	private static final long serialVersionUID = 5365072135029013507L;
	
	
	private JPanel contentPane;
	JPanel pnlValues;
	/**
	 * setter and getter
	 */
	
	protected JPanel getPnlValues() {
		return this.pnlValues;
	}

	/**
	 * Create the frame.
	 */
	public FrmEcho(PnlMMAoLA pnlMMAoLA, PnlMMLV pnlMMLV) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		pnlValues = new JPanel();
		pnlValues.setLayout(new GridLayout(0,2));	
		
		pnlValues.add(pnlMMAoLA);
		pnlValues.add(pnlMMLV);
		
		PnlResult pnlResult = new PnlResult();
		
		
		contentPane.add(pnlValues, BorderLayout.NORTH);
		contentPane.add(new JSeparator(), BorderLayout.CENTER);
		contentPane.add(pnlResult, BorderLayout.SOUTH);
		
		this.pack();
		
		
	}

}
