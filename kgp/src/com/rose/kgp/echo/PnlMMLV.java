package com.rose.kgp.echo;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import java.awt.Color;
import java.awt.Dimension;

public class PnlMMLV extends JPanel {

	
	private static final long serialVersionUID = -4287633535547590374L;

	/**
	 * Create the panel.
	 */
	public PnlMMLV() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMenu = new JPanel();
		pnlMenu.setBackground(new Color(189, 183, 107));
		FlowLayout flowLayout = (FlowLayout) pnlMenu.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(pnlMenu, BorderLayout.NORTH);
		
		JLabel lblLV = new JLabel("LV");
		lblLV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMenu.add(lblLV);
		
		JPanel pnlValues = new JPanel();
		pnlValues.setLayout(new MigLayout("", "[][][]", "[][][][][][]"));
		
		JScrollPane jsp = new JScrollPane(pnlValues);
		jsp.setPreferredSize(new Dimension(300,300));
	    jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		add(jsp, BorderLayout.CENTER);
		
		JLabel lblIVSd = new JLabel("IVSd:");
		lblIVSd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblIVSd, "flowx,cell 0 0,alignx left");
		
		JFormattedTextField ftxtIVSd = new JFormattedTextField();
		ftxtIVSd.setColumns(10);
		ftxtIVSd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtIVSd, "cell 1 0");
		
		JLabel lblIVSdUnit = new JLabel("mm");
		lblIVSdUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblIVSdUnit, "cell 2 0");
		
		JLabel lblLVIDd = new JLabel("LVIDd:");
		lblLVIDd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVIDd, "cell 0 1,alignx left");
		
		JFormattedTextField ftxtLVIDd = new JFormattedTextField();
		ftxtLVIDd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtLVIDd.setColumns(10);
		pnlValues.add(ftxtLVIDd, "cell 1 1");
		
		JLabel lblLVIDdUnit = new JLabel("mm");
		lblLVIDdUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVIDdUnit, "cell 2 1");
		
		JLabel lblLVPWd = new JLabel("LVPWd:");
		lblLVPWd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVPWd, "cell 0 2,alignx left");
		
		JFormattedTextField ftxtLVPWd = new JFormattedTextField();
		ftxtLVPWd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtLVPWd.setColumns(10);
		pnlValues.add(ftxtLVPWd, "cell 1 2,alignx left");
		
		JLabel lblLVPWdUnit = new JLabel("mm");
		lblLVPWdUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVPWdUnit, "cell 2 2");
		
		JLabel lblIVSs = new JLabel("IVSs:");
		lblIVSs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblIVSs, "cell 0 3,alignx left");
		
		JFormattedTextField ftxtIVSs = new JFormattedTextField();
		ftxtIVSs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtIVSs.setColumns(10);
		pnlValues.add(ftxtIVSs, "cell 1 3,alignx left");
		
		JLabel lblIVSsUnit = new JLabel("mm");
		lblIVSsUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblIVSsUnit, "cell 2 3");
		
		JLabel lblLVIDs = new JLabel("LVIDs:");
		lblLVIDs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVIDs, "cell 0 4,alignx left");
		
		JFormattedTextField ftxtLVIDs = new JFormattedTextField();
		ftxtLVIDs.setColumns(10);
		ftxtLVIDs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtLVIDs, "cell 1 4,alignx left");
		
		JLabel lblLVIDsUnit = new JLabel("mm");
		lblLVIDsUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVIDsUnit, "cell 2 4");
		
		JLabel lblLVPWs = new JLabel("LVPWs:");
		lblLVPWs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVPWs, "cell 0 5,alignx left");
		
		JFormattedTextField ftxtLVPWs = new JFormattedTextField();
		ftxtLVPWs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtLVPWs.setColumns(10);
		pnlValues.add(ftxtLVPWs, "cell 1 5,alignx left,aligny top");
		
		JLabel lblLVPWsUnit = new JLabel("mm");
		lblLVPWsUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLVPWsUnit, "cell 2 5");

	}

}
