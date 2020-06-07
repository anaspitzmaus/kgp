package com.rose.kgp.echo;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class PnlResult extends JPanel {

	
	private static final long serialVersionUID = 6761246251438756576L;
	JButton btnCreateTxt;
	/**
	 * Create the panel.
	 */
	public PnlResult() {
		setLayout(new BorderLayout(0, 0));
		
		
		JTextArea txtAreaResult = new JTextArea();
		txtAreaResult.setLineWrap(true);
		
		JPanel pnlResult = new JPanel();
		pnlResult.setLayout(new BorderLayout());
		pnlResult.add(txtAreaResult, BorderLayout.CENTER);
		
		
		JScrollPane jsp = new JScrollPane(pnlResult);
		jsp.setPreferredSize(new Dimension(600,200));
	    jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    add(jsp, BorderLayout.CENTER);
	    
		JPanel pnlNorth = new JPanel();
		pnlNorth.setBackground(new Color(189, 183, 107));
		FlowLayout flowLayout = (FlowLayout) pnlNorth.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(pnlNorth, BorderLayout.NORTH);
		
		JLabel lblResult = new JLabel("Befund:");
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlNorth.add(lblResult);
		
		btnCreateTxt = new JButton("Erstellen");
		btnCreateTxt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlNorth.add(btnCreateTxt);		
		
	}
	
	protected void addBtnCreateListener(ActionListener l) {
		btnCreateTxt.addActionListener(l);
	}

}
