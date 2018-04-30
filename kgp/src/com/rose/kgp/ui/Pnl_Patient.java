package com.rose.kgp.ui;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class Pnl_Patient extends JPanel {
	private JTextField txtSurname;
	private JTextField txtFirstname;
	private JTextField txtID;

	/**
	 * Create the panel.
	 */
	public Pnl_Patient() {
		setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		
		JLabel lblSurame = new JLabel("Nachname:");
		lblSurame.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblSurame, "cell 0 0,alignx left");
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtSurname, "cell 1 0,alignx left");
		txtSurname.setColumns(10);
		
		JLabel lblFirstname = new JLabel("Vorname:");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblFirstname, "cell 0 1,alignx left");
		
		txtFirstname = new JTextField();
		txtFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtFirstname, "cell 1 1,alignx left");
		txtFirstname.setColumns(10);
		
		JLabel lblBirth = new JLabel("Geburtsdatum:");
		lblBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblBirth, "cell 0 2,alignx left");
		
		JFormattedTextField ftxtBirth = new JFormattedTextField();
		ftxtBirth.setColumns(10);
		ftxtBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(ftxtBirth, "cell 1 2,alignx left");
		
		JLabel lblID = new JLabel("ID:");
		lblID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblID, "cell 0 3,alignx left");
		
		txtID = new JTextField();
		txtID.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(txtID, "cell 1 3,alignx left");
		txtID.setColumns(10);

	}

}
