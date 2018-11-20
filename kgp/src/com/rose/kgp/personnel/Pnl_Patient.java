package com.rose.kgp.personnel;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

import com.rose.kgp.useful.EFormattedTextField;
import javax.swing.SwingConstants;
/**
 * JPanel that shows the patient data:
 * surname, first name, birth, sex and number
 * @author Administrator
 *
 */
public class Pnl_Patient extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8017239586861242364L;
	private JTextField txtName;
	private JTextField txtPatNr;
	private EFormattedTextField eTxtBirth;
	private JLabel lblBirth;
	private JLabel lblSex;
	private JTextField textSex;
	
	

	protected JTextField getTxtName() {
		return txtName;
	}



	protected JTextField getTxtPatNr() {
		return txtPatNr;
	}



	protected EFormattedTextField getETxtBirth() {
		return eTxtBirth;
	}


	protected JTextField getTxtSex() {
		return textSex;
	}



	/**
	 * Create the panel.
	 */
	public Pnl_Patient() {
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Patient", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		setLayout(new MigLayout("", "[][grow]", "[][][][]"));
		
		txtPatNr = new JTextField();
		txtPatNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtPatNr, "cell 1 0,alignx left");
		txtPatNr.setColumns(10);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblName, "cell 0 1,alignx left,aligny center");
		
		JLabel lblNummer = new JLabel("Nummer:");
		lblNummer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNummer, "cell 0 0,alignx left,aligny center");
		
		txtName = new JTextField();
		txtName.setHorizontalAlignment(SwingConstants.LEFT);
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtName, "cell 1 1,alignx left");
		txtName.setColumns(20);
		
		lblBirth = new JLabel("Geburtsdatum:");
		lblBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblBirth, "cell 0 2,alignx left,aligny center");
		
		eTxtBirth = new EFormattedTextField();
		eTxtBirth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(eTxtBirth, "cell 1 2,alignx left");
		
		lblSex = new JLabel("Geschlecht:");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblSex, "cell 0 3,alignx left");
		
		textSex = new JTextField();
		textSex.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(textSex, "cell 1 3,alignx left");
		textSex.setColumns(10);
		
	}

}
