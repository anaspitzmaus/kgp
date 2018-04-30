package com.rose.kgp.personnel;

import java.awt.Font;


import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;


public class Pnl_NewPhysician extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5495745236150149390L;
	private JTextField textSurname;
	private JTextField txtFirstname;

	/**
	 * Create the panel.
	 */
	public Pnl_NewPhysician() {
		setLayout(new MigLayout("", "[][grow]", "[][][][][99.00,grow]"));
		
		JLabel lblTitel = new JLabel("Titel:");
		lblTitel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblTitel, "cell 0 0,alignx trailing");
		
		JComboBox<String> comboTitle = new JComboBox<String>();
		comboTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(comboTitle, "cell 1 0,growx");
		
		JLabel lblSurname = new JLabel("Nachname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSurname, "cell 0 1,alignx trailing");
		
		textSurname = new JTextField();
		textSurname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(textSurname, "cell 1 1,growx");
		textSurname.setColumns(10);
		
		JLabel lblFirstname = new JLabel("Vorname:");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblFirstname, "cell 0 2,alignx trailing");
		
		txtFirstname = new JTextField();
		txtFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtFirstname, "cell 1 2,growx");
		txtFirstname.setColumns(10);
		
		JLabel lblBirth = new JLabel("geb am:");
		lblBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblBirth, "cell 0 3");		
		
		JLabel lblOnsetDate = new JLabel("seit:");
		lblOnsetDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblOnsetDate, "cell 0 4,alignx left,aligny center");
		
		

	}

}
