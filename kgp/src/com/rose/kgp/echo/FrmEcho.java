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
		
		
		
		PnlMMAoLA pnlMMAoLA = new PnlMMAoLA();
		pnlValues.add(pnlMMAoLA);

		PnlMMLV pnlMMLV = new PnlMMLV();
		pnlValues.add(pnlMMLV);
		
		PnlResult pnlResult = new PnlResult();
		
		
		contentPane.add(pnlValues, BorderLayout.NORTH);
		contentPane.add(new JSeparator(), BorderLayout.CENTER);
		contentPane.add(pnlResult, BorderLayout.SOUTH);
		
		this.pack();
		
		
	}

}
