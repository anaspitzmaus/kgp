package com.rose.kgp.echo;


import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import java.awt.GridLayout;

import javax.swing.JSeparator;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.Font;

public class FrmEcho extends JFrame {

	
	private static final long serialVersionUID = 5365072135029013507L;
	
	
	private JPanel contentPane;
	JPanel pnlValues;
	private JMenuBar menuBar;
	private JButton btnSettings;
	/**
	 * setter and getter
	 */
	
	protected JPanel getPnlValues() {
		return this.pnlValues;
	}

	/**
	 * Create the frame.
	 */
	public FrmEcho(PnlMMAoLA pnlMMAoLA, PnlMMLV pnlMMLV, MenuBar menuBar) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 418);
		
		setJMenuBar(menuBar);
		
		
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
