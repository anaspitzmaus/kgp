package com.rose.kgp.administration;

import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JMenuBar;
import javax.swing.JLabel;

import java.awt.FlowLayout;

import javax.swing.JFormattedTextField;

import java.awt.Font;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.LinkedList;

import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.rose.kgp.personnel.Physician;
import com.rose.kgp.ui.Pnl_SetDate;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;

public class PnlBillingCallService extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6958883817683567493L;
	private JTextField txtBillNr;
	private JTextField txtSalaryPerCoro;
	private JTextField txtSalaryCoroAll;
	private JTextField txtSalaryPerPCI;
	private JTextField txtSalaryPCIAll;
	private JComboBox<Physician> comboPhysician;
	//private JFormattedTextField ftxtDate;
	private Pnl_SetDate pnlSetDate;
	private JSpinner spinCoroCount, spinPCICount;
	private JButton btnBillCreate;
	private JComboBox<Month> cbxMonth;
	private JComboBox<Year> cbxYear;
	private JTextField txtIBAN;
	private JTextField txtBIC;
	private JTextField txtBank;

	
	protected JTextField getTxtIBAN() {
		return txtIBAN;
	}

	protected JTextField getTxtBIC() {
		return txtBIC;
	}

	protected JTextField getTxtBank() {
		return txtBank;
	}

	protected JTextField getTxtBillNumber(){
		return txtBillNr;
	}
	
	protected JComboBox<Year> getComboYear(){
		return cbxYear;
	}
	
	protected JComboBox<Month> getComboMonth(){
		return cbxMonth;
	}
	
	protected JButton getBtnBillCreate(){
		return btnBillCreate;
	}
	
	protected JComboBox<Physician> getComboPhysician(){
		return comboPhysician;
	}
	
	protected Pnl_SetDate getPnlSetDate() {
		return pnlSetDate;
	}
	
	protected JTextField getTxtSalaryPerCoro(){
		return txtSalaryPerCoro;
	}
	
	protected JTextField getTxtSalaryPerPCI(){
		return txtSalaryPerPCI;
	}
	
	protected JSpinner getSpinCoroCount(){
		return spinCoroCount;
	}
	
	protected JSpinner getSpinPCICount(){
		return spinPCICount;
	}
	
	protected JTextField getTxtCoroSalary(){
		return txtSalaryCoroAll;
	}
	
	protected JTextField getTxtPCISalary(){
		return txtSalaryPCIAll;
	}
	/**
	 * Create the panel.
	 */
	public PnlBillingCallService() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel PnlNorth = new JPanel();
		FlowLayout flowLayout = (FlowLayout) PnlNorth.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(PnlNorth, BorderLayout.NORTH);
		
		JMenuBar MenuBarHead = new JMenuBar();
		PnlNorth.add(MenuBarHead);
		
		JLabel lblDate = new JLabel("Datum:");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(lblDate);
		
		pnlSetDate = new Pnl_SetDate();
		MenuBarHead.add(pnlSetDate);
		
		JLabel lblNr = new JLabel("Nummer:");
		lblNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(lblNr);
		
		txtBillNr = new JTextField();
		txtBillNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(txtBillNr);
		txtBillNr.setColumns(3);
		
		JLabel lblSepYear = new JLabel("/");
		lblSepYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(lblSepYear);
		
		cbxYear = new JComboBox<Year>();
		cbxYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(cbxYear);
		
		JLabel lblMonth = new JLabel("Monat:");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(lblMonth);
		
		cbxMonth = new JComboBox<Month>();
		cbxMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(cbxMonth);
		
		JPanel PnlCenter = new JPanel();
		add(PnlCenter, BorderLayout.CENTER);
		PnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][][][][][]"));
		
		JLabel lblPhysician = new JLabel("Arzt:");
		lblPhysician.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblPhysician, "cell 0 0,alignx left");
		
		comboPhysician = new JComboBox<Physician>();
		comboPhysician.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(comboPhysician, "cell 1 0");
		
		JLabel lblIBAN = new JLabel("IBAN:");
		lblIBAN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblIBAN, "cell 0 1,alignx left");
		
		txtIBAN = new JTextField();
		txtIBAN.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(txtIBAN, "cell 1 1,growx");
		txtIBAN.setColumns(10);
		
		JLabel lblBIC = new JLabel("BIC:");
		lblBIC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblBIC, "cell 0 2,alignx left");
		
		txtBIC = new JTextField();
		txtBIC.setForeground(Color.BLACK);
		txtBIC.setBackground(Color.WHITE);
		txtBIC.setHorizontalAlignment(SwingConstants.LEADING);
		txtBIC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBIC.setEnabled(true);
		txtBIC.setEditable(true);
		txtBIC.setText("");
		PnlCenter.add(txtBIC, "cell 1 2,growx,aligny top");
		txtBIC.setColumns(10);
		
		JLabel lblBank = new JLabel("Bank:");
		lblBank.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblBank, "cell 0 3,alignx left");
		
		txtBank = new JTextField();
		txtBank.setBackground(Color.WHITE);
		txtBank.setHorizontalAlignment(SwingConstants.LEFT);
		txtBank.setForeground(Color.BLACK);
		txtBank.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtBank.setEnabled(true);
		txtBank.setEditable(true);
		txtBank.setText("");
		PnlCenter.add(txtBank, "cell 1 3,growx");
		txtBank.setColumns(10);
		
		JLabel lblCoroCount = new JLabel("Anzahl Coro:");
		lblCoroCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblCoroCount, "cell 0 4");
		
		spinCoroCount = new JSpinner();
		spinCoroCount.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		spinCoroCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(spinCoroCount, "flowx,cell 1 4");
		
		JLabel lblPCICount = new JLabel("Anzahl PCI:");
		lblPCICount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblPCICount, "cell 0 5,alignx left");
		
		spinPCICount = new JSpinner();
		spinPCICount.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		spinPCICount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(spinPCICount, "flowx,cell 1 5");
		
		btnBillCreate = new JButton("Rechnung erstellen");
		btnBillCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(btnBillCreate, "cell 1 6,alignx right,aligny top");
		
		JLabel lblSalaryPerPCI = new JLabel("St\u00FCckpreis:");
		lblSalaryPerPCI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblSalaryPerPCI, "cell 1 5");
		
		txtSalaryPerPCI = new JTextField();
		txtSalaryPerPCI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSalaryPerPCI.setColumns(5);
		PnlCenter.add(txtSalaryPerPCI, "cell 1 5");
		
		JLabel lblSalaryPCIAll = new JLabel("Gesamt:");
		lblSalaryPCIAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblSalaryPCIAll, "cell 1 5");
		
		txtSalaryPCIAll = new JTextField();
		txtSalaryPCIAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSalaryPCIAll.setColumns(5);
		PnlCenter.add(txtSalaryPCIAll, "cell 1 5");
		
		JLabel label = new JLabel("St\u00FCckpreis:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(label, "cell 1 4");
		
		txtSalaryPerCoro = new JTextField();
		txtSalaryPerCoro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(txtSalaryPerCoro, "cell 1 4");
		txtSalaryPerCoro.setColumns(5);
		
		JLabel lblSalaryCoroAll = new JLabel("Gesamt:");
		lblSalaryCoroAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblSalaryCoroAll, "cell 1 4");
		
		txtSalaryCoroAll = new JTextField();
		txtSalaryCoroAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(txtSalaryCoroAll, "cell 1 4");
		txtSalaryCoroAll.setColumns(5);

	}
	
	

	

}
