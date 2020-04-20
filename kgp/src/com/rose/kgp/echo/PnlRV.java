package com.rose.kgp.echo;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;

public class PnlRV extends JPanel {
	
	private static final long serialVersionUID = 8038540572486055609L;
	private JFormattedTextField ftxtRVBasalDiam;
	private JFormattedTextField ftxtRVMidDiam;
	private JFormattedTextField ftxtRVLongDiam;
	private JFormattedTextField ftxtRVOT_PLAX;	
	private DecimalFormat doubleFormat;
	private JFormattedTextField ftxtTAPSE;

	protected Object getRVBasalDiamValue() {
		return ftxtRVBasalDiam.getValue();
	}
	
	protected Object getRVMidDiamValue() {
		return ftxtRVMidDiam.getValue();
	}
	
	protected Object getRVLongDiamValue() {
		return ftxtRVLongDiam.getValue();
	}
	
	protected Object getRVOT_PLAXValue() {
		return ftxtRVOT_PLAX.getValue();
	}
	
	protected Object getTAPSEValue() {
		return ftxtTAPSE.getValue();
	}
	/**
	 * Create the panel.
	 */
	public PnlRV() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(189, 183, 107));
		FlowLayout flowLayout = (FlowLayout) pnlMenu.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(pnlMenu, BorderLayout.NORTH);
		
		JLabel lblRV = new JLabel("RV");
		lblRV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMenu.add(lblRV);
		
		JPanel pnlValues = new JPanel();
		pnlValues.setLayout(new MigLayout("", "[][grow][]", "[][][][][]"));
		
		JScrollPane jsp = new JScrollPane(pnlValues);
		jsp.setPreferredSize(new Dimension(300,300));
	    jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jsp, BorderLayout.CENTER);
		
		doubleFormat = new DecimalFormat();
		doubleFormat.setMaximumFractionDigits(1);
		doubleFormat.setMinimumFractionDigits(1);
		
		JLabel lblRVBasalDiam = new JLabel("RV basal Diam:");
		lblRVBasalDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblRVBasalDiam, "cell 0 0,alignx left");
		
		ftxtRVBasalDiam = new JFormattedTextField(doubleFormat);
		ftxtRVBasalDiam.setColumns(10);
		ftxtRVBasalDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtRVBasalDiam, "cell 1 0,alignx left");
		
		JLabel lblRVBasalDiamUnit = new JLabel("mm");
		lblRVBasalDiamUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblRVBasalDiamUnit, "cell 2 0");
		
		JLabel lblRVMidDiam = new JLabel("RV mid Diam:");
		lblRVMidDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblRVMidDiam, "cell 0 1,alignx left");
		
		ftxtRVMidDiam = new JFormattedTextField(doubleFormat);
		ftxtRVMidDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtRVMidDiam.setColumns(10);
		pnlValues.add(ftxtRVMidDiam, "cell 1 1,alignx left");
		
		JLabel lblRVMidDiamUnit = new JLabel("mm");
		lblRVMidDiamUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblRVMidDiamUnit, "cell 2 1");
		
		JLabel lblRVLongDiam = new JLabel("RV long Diam:");
		lblRVLongDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblRVLongDiam, "cell 0 2,alignx left");
		
		ftxtRVLongDiam = new JFormattedTextField(doubleFormat);
		ftxtRVLongDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtRVLongDiam.setColumns(10);
		pnlValues.add(ftxtRVLongDiam, "cell 1 2,alignx left");
		
		JLabel lblRVLongDiamUnit = new JLabel("mm");
		lblRVLongDiamUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblRVLongDiamUnit, "cell 2 2,alignx left,aligny bottom");
		
		JLabel lblRVOT_PLAX = new JLabel("RVOT PLAX:");
		lblRVOT_PLAX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblRVOT_PLAX, "cell 0 3,alignx left,aligny bottom");
		
		ftxtRVOT_PLAX = new JFormattedTextField(doubleFormat);
		ftxtRVOT_PLAX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtRVOT_PLAX.setColumns(10);
		pnlValues.add(ftxtRVOT_PLAX, "cell 1 3,alignx left");
		
		JLabel lblRVOT_PLAX_Unit = new JLabel("mm");
		lblRVOT_PLAX_Unit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblRVOT_PLAX_Unit, "cell 2 3");
		
		JLabel lblTAPSE = new JLabel("TAPSE:");
		lblTAPSE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblTAPSE, "cell 0 4,alignx left");
		
		ftxtTAPSE = new JFormattedTextField(doubleFormat);
		ftxtTAPSE.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtTAPSE.setColumns(10);
		pnlValues.add(ftxtTAPSE, "cell 1 4,alignx left");
		
		JLabel lblTAPSEUnit = new JLabel("mm");
		lblTAPSEUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblTAPSEUnit, "cell 2 4");

	}
	
	public void addRVBasalDiamListener(PropertyChangeListener l) {
		ftxtRVBasalDiam.addPropertyChangeListener(l);
	}
	
	public void addRVMidDiamListener(PropertyChangeListener l) {
		ftxtRVMidDiam.addPropertyChangeListener(l);
	}
	
	public void addRVLongDiamListener(PropertyChangeListener l) {
		ftxtRVLongDiam.addPropertyChangeListener(l);
	}
	
	public void addRVOT_PLAX_Listener(PropertyChangeListener l) {
		ftxtRVOT_PLAX.addPropertyChangeListener(l);
	}
	
	public void addTAPSEListener(PropertyChangeListener l) {
		ftxtTAPSE.addPropertyChangeListener(l);
	}

}
