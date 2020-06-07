package com.rose.kgp.echo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import javax.swing.JList;

public class PnlMMLV extends JPanel {

	
	private static final long serialVersionUID = -4287633535547590374L;

	JFormattedTextField ftxtIVSd;
	JFormattedTextField ftxtLVIDd;
	JFormattedTextField ftxtLVPWd;
	JFormattedTextField ftxtIVSs;
	JFormattedTextField ftxtLVIDs;
	JFormattedTextField ftxtLVPWs;
	DecimalFormat doubleFormat, integerFormat;
	private JFormattedTextField ftxtEF;
	private JFormattedTextField ftxtLVMass;
	private JFormattedTextField ftxtLVMassBSA;
	JFormattedTextField ftxtRWT;
	JFormattedTextField ftxtVolDia;
	JFormattedTextField ftxtVolSys;
	JFormattedTextField ftxtLVEFSimpson;
	JFormattedTextField ftxtVolDiaSimpson;
	JList<Study> listStudies;
	
	//getter and setter
	protected JFormattedTextField getFtxtIVSd() {
		return ftxtIVSd;
	}


	protected JFormattedTextField getFtxtLVIDd() {
		return ftxtLVIDd;
	}


	protected JFormattedTextField getFtxtLVPWd() {
		return ftxtLVPWd;
	}


	protected JFormattedTextField getFtxtIVSs() {
		return ftxtIVSs;
	}


	protected JFormattedTextField getFtxtLVIDs() {
		return ftxtLVIDs;
	}


	protected JFormattedTextField getFtxtLVPWs() {
		return ftxtLVPWs;
	}
	
	protected JFormattedTextField getFtxtLVMass() {
		return ftxtLVMass;
	}
	
		
	protected JFormattedTextField getFtxtRWT() {
		return ftxtRWT;
	}


	protected JFormattedTextField getFtxtVolDia() {
		return ftxtVolDia;
	}

	protected JFormattedTextField getFtxtVolSys() {
		return ftxtVolSys;
	}	


	protected JFormattedTextField getFtxtEFTeich() {
		return ftxtEF;
	}


	protected JFormattedTextField getFtxtLVEFSimpson() {
		return ftxtLVEFSimpson;
	}
	
	protected JList getStudyList() {
		return listStudies;
	}


	/**
	 * Create the panel.
	 */
	public PnlMMLV() {
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(440, 480));
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(189, 183, 107));
		FlowLayout flowLayout = (FlowLayout) pnlMenu.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(pnlMenu, BorderLayout.NORTH);
		
		JLabel lblLV = new JLabel("LV");
		lblLV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMenu.add(lblLV);
		
		JPanel pnlValues = new JPanel();
		pnlValues.setLayout(new MigLayout("", "[][grow][]", "[][][][][][][][][][][][][][grow]"));
		
		JScrollPane jsp = new JScrollPane(pnlValues);
		jsp.setPreferredSize(new Dimension(300,300));
	    jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jsp, BorderLayout.CENTER);
		
		JLabel lblIVSd = new JLabel("IVSd:");
		lblIVSd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblIVSd, "flowx,cell 0 0,alignx left");
		
		doubleFormat = new DecimalFormat();
		doubleFormat.setMaximumFractionDigits(1);
		doubleFormat.setMinimumFractionDigits(1);
		
		integerFormat = new DecimalFormat();
		integerFormat.setMaximumFractionDigits(0);
		integerFormat.setMinimumFractionDigits(0);
		integerFormat.setMaximumIntegerDigits(2);
		integerFormat.setMinimumIntegerDigits(1);
		
		ftxtIVSd = new JFormattedTextField(doubleFormat);
		ftxtIVSd.setColumns(10);
		ftxtIVSd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtIVSd, "cell 1 0");
		
		JLabel lblIVSdUnit = new JLabel("mm");
		lblIVSdUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblIVSdUnit, "cell 2 0");
		
		JLabel lblLVIDd = new JLabel("LVIDd:");
		lblLVIDd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVIDd, "cell 0 1,alignx left");
		
		ftxtLVIDd = new JFormattedTextField(doubleFormat);
		ftxtLVIDd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtLVIDd.setColumns(10);
		pnlValues.add(ftxtLVIDd, "cell 1 1");
		
		JLabel lblLVIDdUnit = new JLabel("mm");
		lblLVIDdUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVIDdUnit, "cell 2 1");
		
		JLabel lblLVPWd = new JLabel("LVPWd:");
		lblLVPWd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVPWd, "cell 0 2,alignx left");
		
		ftxtLVPWd = new JFormattedTextField(doubleFormat);
		ftxtLVPWd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtLVPWd.setColumns(10);
		pnlValues.add(ftxtLVPWd, "cell 1 2,alignx left");
		
		JLabel lblLVPWdUnit = new JLabel("mm");
		lblLVPWdUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVPWdUnit, "cell 2 2");
		
		JLabel lblIVSs = new JLabel("IVSs:");
		lblIVSs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblIVSs, "cell 0 3,alignx left");
		
		ftxtIVSs = new JFormattedTextField(doubleFormat);
		ftxtIVSs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtIVSs.setColumns(10);
		pnlValues.add(ftxtIVSs, "cell 1 3,alignx left");
		
		JLabel lblIVSsUnit = new JLabel("mm");
		lblIVSsUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblIVSsUnit, "cell 2 3");
		
		JLabel lblLVIDs = new JLabel("LVIDs:");
		lblLVIDs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVIDs, "cell 0 4,alignx left");
		
		ftxtLVIDs = new JFormattedTextField(doubleFormat);
		ftxtLVIDs.setColumns(10);
		ftxtLVIDs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtLVIDs, "cell 1 4,alignx left");
		
		JLabel lblLVIDsUnit = new JLabel("mm");
		lblLVIDsUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVIDsUnit, "cell 2 4");
		
		JLabel lblLVPWs = new JLabel("LVPWs:");
		lblLVPWs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVPWs, "cell 0 5,alignx left");
		
		ftxtLVPWs = new JFormattedTextField(doubleFormat);
		ftxtLVPWs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtLVPWs.setColumns(10);
		pnlValues.add(ftxtLVPWs, "cell 1 5,alignx left,aligny top");
		
		JLabel lblLVPWsUnit = new JLabel("mm");
		lblLVPWsUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVPWsUnit, "cell 2 5");
		
		JLabel lblEF = new JLabel("EF Teich/Simpson:");
		lblEF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblEF, "cell 0 6,alignx left");
		
		ftxtEF = new JFormattedTextField(integerFormat);
		ftxtEF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtEF, "flowx,cell 1 6,alignx left");
		ftxtEF.setColumns(5);
		
		JLabel lblEFUnit = new JLabel("%");
		lblEFUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblEFUnit, "cell 2 6,alignx right");
		
		JLabel lblLVMass = new JLabel("LV Masse:");
		lblLVMass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVMass, "cell 0 7,alignx left");
		
		ftxtLVMass = new JFormattedTextField(doubleFormat);
		ftxtLVMass.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtLVMass, "cell 1 7,alignx left");
		ftxtLVMass.setColumns(10);
		
		JLabel LVMassUnit = new JLabel("g");
		LVMassUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(LVMassUnit, "cell 2 7,alignx right");
		
		JLabel lblLVMass_BSA = new JLabel("LV Masse/BSA:");
		lblLVMass_BSA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVMass_BSA, "cell 0 8,alignx left");
		
		ftxtLVMassBSA = new JFormattedTextField();
		ftxtLVMassBSA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtLVMassBSA, "cell 1 8,alignx left");
		ftxtLVMassBSA.setColumns(10);
		
		JLabel lblLVMassBSAUnit = new JLabel("g/m\u00B2");
		lblLVMassBSAUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVMassBSAUnit, "cell 2 8,alignx right");
		
		JLabel lblRWT = new JLabel("RWT:");
		lblRWT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblRWT, "cell 0 9,alignx left");
		
		ftxtRWT = new JFormattedTextField();
		ftxtRWT.setColumns(10);
		ftxtRWT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtRWT, "cell 1 9,alignx left");
		
		JLabel lblVold = new JLabel("Volume_d Teich/Simpson:");
		lblVold.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblVold, "cell 0 10,alignx left");
		
		ftxtVolDia = new JFormattedTextField();
		ftxtVolDia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtVolDia.setColumns(5);
		pnlValues.add(ftxtVolDia, "flowx,cell 1 10,alignx left");
		
		JLabel lblVolDUnit = new JLabel("ml");
		lblVolDUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblVolDUnit, "cell 2 10,alignx right");
		
		JLabel lblVolSys = new JLabel("Volume_sys:");
		lblVolSys.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblVolSys, "cell 0 11,alignx left");
		
		ftxtVolSys = new JFormattedTextField();
		ftxtVolSys.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtVolSys.setColumns(10);
		pnlValues.add(ftxtVolSys, "cell 1 11,alignx left");
		
		JLabel lblLVEFDiv = new JLabel("/");
		lblLVEFDiv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVEFDiv, "cell 1 6");
		
		ftxtLVEFSimpson = new JFormattedTextField(integerFormat);
		ftxtLVEFSimpson.setColumns(5);
		ftxtLVEFSimpson.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtLVEFSimpson, "cell 1 6");
		
		JLabel lblVolDiaDiv = new JLabel("/");
		lblVolDiaDiv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblVolDiaDiv, "cell 1 10");
		
		ftxtVolDiaSimpson = new JFormattedTextField();
		ftxtVolDiaSimpson.setColumns(5);
		ftxtVolDiaSimpson.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtVolDiaSimpson, "cell 1 10");
		
		JLabel lblStudies = new JLabel("Studien:");
		lblStudies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblStudies, "cell 0 12,alignx left");
		
		listStudies = new JList<Study>();
		listStudies.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(listStudies, "cell 1 12,grow");
		
		

	}
	
	

}
