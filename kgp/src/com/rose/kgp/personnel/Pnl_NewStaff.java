package com.rose.kgp.personnel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import net.miginfocom.swing.MigLayout;

public abstract class Pnl_NewStaff extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4454238429902436587L;
	private JTextField txtId;
	private JLabel lblId;
	private JLabel lblSex;
	private JComboBox<Sex> comboSex;
	private JLabel lblSurname, lblFirstname;
	private JTextField txtSurname;
	private JTextField txtFirstname;
	private JLabel lblAlias;
	private JTextField txtAlias;
	private JLabel lblBirth;
	private JLabel lblOnsetDate;
	private JButton btnStaffSet;
	
	protected JTextField getTxtId() {
		return this.txtId;
	}	
	
	protected JLabel getLblId() {
		return lblId;
	}
	
	protected JLabel getLblSex() {
		return this.lblSex;
	}
	
	protected JComboBox<Sex> getComboSex() {
		return this.comboSex;
	}	

	protected JLabel getLblSurname() {
		return this.lblSurname;
	}

	protected JLabel getLblFirstname() {
		return this.lblFirstname;
	}
	
	
	protected JTextField getTxtSurname() {
		return this.txtSurname;
	}

	protected JTextField getTxtFirstname() {
		return this.txtFirstname;
	}	
	
	protected JLabel getLblAlias() {
		return lblAlias;
	}
	
	protected JTextField getTxtAlias() {
		return txtAlias;
	}
	
	protected JLabel getLblBirth() {
		return lblBirth;
	}
	
	protected JLabel getLblOnsetDate() {
		return lblOnsetDate;
	}
	
	protected JButton getBtnSetStaff() {
		return this.btnStaffSet;
	}

	/**
	 * Create the panel.
	 */
	public Pnl_NewStaff() {
		setLayout(new MigLayout("", "[][grow]", "[][][][][][][][][grow]"));
		
		lblId = new JLabel("Id:");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 12));		
		
		txtId = new JTextField();
		txtId.setEnabled(false);
		txtId.setFont(new Font("Tahoma", Font.PLAIN, 12));		
		txtId.setColumns(5);
		
		lblSex = new JLabel("Anrede:");
		lblSex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		comboSex = new JComboBox<Sex>();
		comboSex.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblSurname = new JLabel("Nachname:");
		lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblFirstname = new JLabel("Vorname:");
		lblFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtSurname = new JTextField();
		txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtSurname.setColumns(10);
		
		txtFirstname = new JTextField();
		txtFirstname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFirstname.setColumns(10);
		
		lblAlias = new JLabel("Alias:");
		lblAlias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		txtAlias = new JTextField();
		txtAlias.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtAlias.setColumns(10);
		
		lblBirth = new JLabel("geb am:");
		lblBirth.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		lblOnsetDate = new JLabel("seit:");
		lblOnsetDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		btnStaffSet = new JButton("\u00DCbernehmen");
	}

	protected void addSurnameListener(DocumentListener l){
		txtSurname.getDocument().addDocumentListener(l);
	}
	
	protected void removeSurnameListener(DocumentListener l){
		txtSurname.getDocument().removeDocumentListener(l);
	}

	protected void addFirstnameListener(DocumentListener l) {
		txtFirstname.getDocument().addDocumentListener(l);		
	}
	
	protected void removeFirstnameListener(DocumentListener l){
		txtFirstname.getDocument().removeDocumentListener(l);
	}
	
	protected void addSexListener(ItemListener l){
		comboSex.addItemListener(l);
	}
	
	protected void removeSexListener(ItemListener l){
		comboSex.removeItemListener(l);
	}
	
	protected void addAliasListener(DocumentListener l){
		txtAlias.getDocument().addDocumentListener(l);
	}
	
	protected void removeAliasListener(DocumentListener l){
		txtAlias.getDocument().removeDocumentListener(l);
	}
	
	protected void addSetNewStaffListener(ActionListener l){
		btnStaffSet.addActionListener(l);
	}
	
	protected void removeSetNewStaffListener(ActionListener l){
		btnStaffSet.removeActionListener(l);
	}
}
