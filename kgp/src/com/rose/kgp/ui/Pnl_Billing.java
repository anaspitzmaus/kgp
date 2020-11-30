package com.rose.kgp.ui;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;

import com.rose.kgp.Ward;

import java.awt.Font;

public class Pnl_Billing extends JPanel {
	JComboBox<Ward> comboWard;
	/**
	 * Create the panel.
	 */
	public Pnl_Billing() {
		setLayout(new MigLayout("", "[][90.00,grow][][]", "[][][]"));
		
		JLabel lblBilling = new JLabel("Abrechnungsmodus:");
		lblBilling.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblBilling, "cell 0 0,alignx left");
		
		JRadioButton radioStationaer = new JRadioButton("station\u00E4r");
		radioStationaer.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(radioStationaer, "cell 1 0");
		
		JRadioButton radioAmbulant = new JRadioButton("ambulant");
		radioAmbulant.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(radioAmbulant, "cell 2 0");
		
		JRadioButton radioIntegral = new JRadioButton("CardioIntegral");
		radioIntegral.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(radioIntegral, "cell 3 0");
		
		JLabel lblOvernight = new JLabel("\u00DCbernachtung:");
		lblOvernight.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblOvernight, "cell 0 1,alignx left");
		
		JRadioButton radioOvernightYes = new JRadioButton("ja");
		radioOvernightYes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(radioOvernightYes, "cell 1 1");
		
		JRadioButton radioOvernightNo = new JRadioButton("nein");
		radioOvernightNo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(radioOvernightNo, "cell 2 1");
		
		JLabel lblWard = new JLabel("Station:");
		lblWard.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblWard, "cell 0 2,alignx left");
		
		comboWard = new JComboBox<Ward>();
		comboWard.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(comboWard, "cell 1 2,alignx left");

	}

}
