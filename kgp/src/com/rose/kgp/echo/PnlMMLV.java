package com.rose.kgp.echo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import java.awt.Color;

public class PnlMMLV extends JPanel {

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
		add(pnlValues, BorderLayout.CENTER);
		pnlValues.setLayout(new MigLayout("", "[][][]", "[][]"));
		
		JLabel lblIVSd = new JLabel("IVSd:");
		lblIVSd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblIVSd, "flowx,cell 0 0,alignx left");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setColumns(10);
		formattedTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(formattedTextField, "cell 1 0");
		
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

	}

}
