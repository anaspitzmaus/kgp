package com.rose.kgp.echo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;


public class PnlMMAoLA extends JPanel {

	
	private static final long serialVersionUID = 2648573428366932353L;
	private NumberFormat integerFormat;
	JFormattedTextField ftxtAoRoot;
	
	/*
	 * setter und getter
	 */
	
	protected JFormattedTextField getftxtAoRoot() {
		return ftxtAoRoot;
	}
	
	
	/**
	 * Create the panel.
	 */
	public PnlMMAoLA() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(189, 183, 107));
		FlowLayout flowLayout = (FlowLayout) pnlMenu.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(pnlMenu, BorderLayout.NORTH);
		
		
		
		JLabel lblAoLA = new JLabel("Ao/LA");
		lblAoLA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMenu.add(lblAoLA);
		
		
		
		JPanel pnlValues = new JPanel();
		pnlValues.setLayout(new MigLayout("", "[][][]", "[][][]"));
		
		JScrollPane jsp = new JScrollPane(pnlValues);
		jsp.setPreferredSize(new Dimension(300,300));
	    jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jsp, BorderLayout.CENTER);
		
		
		JLabel lblAoRoot = new JLabel("Aortic Root:");
		lblAoRoot.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblAoRoot, "cell 0 0,alignx left");
		
		integerFormat = NumberFormat.getIntegerInstance();
		integerFormat.setMaximumIntegerDigits(2);
		
		ftxtAoRoot = new JFormattedTextField(integerFormat);
		ftxtAoRoot.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtAoRoot.setColumns(10);
		
		
		pnlValues.add(ftxtAoRoot, "cell 1 0,growx");
		
		JLabel lblAoRootUnit = new JLabel("mm");
		lblAoRootUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblAoRootUnit, "cell 2 0");
		
		JLabel lblAoAsc = new JLabel("Aorta ascendens:");
		lblAoAsc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblAoAsc, "cell 0 1,alignx left");
		
		JFormattedTextField ftxtAoAsc = new JFormattedTextField();
		ftxtAoAsc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtAoAsc.setColumns(10);
		pnlValues.add(ftxtAoAsc, "cell 1 1,growx");
		
		JLabel lblAoAscUnit = new JLabel("mm");
		lblAoAscUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblAoAscUnit, "cell 2 1");
		
		JLabel lblLA = new JLabel("LA:");
		lblLA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLA, "cell 0 2,alignx left");
		
		JFormattedTextField ftxtLA = new JFormattedTextField();
		ftxtLA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtLA, "cell 1 2,growx");
		
		JLabel lblLAUnit = new JLabel("mm");
		lblLAUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLAUnit, "cell 2 2");
		
		
		
		

	}

}
