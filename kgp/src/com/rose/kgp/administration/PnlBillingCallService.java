package com.rose.kgp.administration;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import java.awt.Font;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import com.rose.kgp.personnel.Physician;

import javax.swing.JButton;

public class PnlBillingCallService extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6958883817683567493L;
	private JTextField txtNr;
	private JTextField txtYear;
	private JTextField txtMonth;
	private JTextField txtSalaryPerCoro;
	private JTextField txtSalaryCoroAll;
	private JTextField txtSalaryPerPCI;
	private JTextField txtSalaryPCIAll;

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
		
		JFormattedTextField ftxtDate = new JFormattedTextField();
		ftxtDate.setColumns(5);
		ftxtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(ftxtDate);
		
		JLabel lblNr = new JLabel("Nummer:");
		lblNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(lblNr);
		
		txtNr = new JTextField();
		txtNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(txtNr);
		txtNr.setColumns(3);
		
		JLabel lblSepYear = new JLabel("/");
		lblSepYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(lblSepYear);
		
		txtYear = new JTextField();
		txtYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(txtYear);
		txtYear.setColumns(3);
		
		JLabel lblMonth = new JLabel("Monat:");
		lblMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(lblMonth);
		
		txtMonth = new JTextField();
		txtMonth.setFont(new Font("Tahoma", Font.PLAIN, 14));
		MenuBarHead.add(txtMonth);
		txtMonth.setColumns(5);
		
		JPanel PnlCenter = new JPanel();
		add(PnlCenter, BorderLayout.CENTER);
		PnlCenter.setLayout(new MigLayout("", "[][grow]", "[][][][][]"));
		
		JLabel lblPhysician = new JLabel("Arzt:");
		lblPhysician.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblPhysician, "cell 0 0,alignx left");
		
		JComboBox<Physician> comboPhysician = new JComboBox<Physician>();
		comboPhysician.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(comboPhysician, "cell 1 0");
		
		JLabel lblCoroCount = new JLabel("Anzahl Coro:");
		lblCoroCount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblCoroCount, "cell 0 1");
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		spinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(spinner, "flowx,cell 1 1");
		
		JLabel lblPCICount = new JLabel("Anzahl PCI:");
		lblPCICount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblPCICount, "cell 0 2,alignx left");
		
		JSpinner spinPCICount = new JSpinner();
		spinPCICount.setModel(new SpinnerNumberModel(0, 0, 30, 1));
		spinPCICount.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(spinPCICount, "flowx,cell 1 2");
		
		JLabel label = new JLabel("St\u00FCckpreis:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(label, "cell 1 1");
		
		txtSalaryPerCoro = new JTextField();
		txtSalaryPerCoro.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(txtSalaryPerCoro, "cell 1 1");
		txtSalaryPerCoro.setColumns(5);
		
		JLabel lblSalaryCoroAll = new JLabel("Gesamt:");
		lblSalaryCoroAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblSalaryCoroAll, "cell 1 1");
		
		txtSalaryCoroAll = new JTextField();
		txtSalaryCoroAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(txtSalaryCoroAll, "cell 1 1");
		txtSalaryCoroAll.setColumns(5);
		
		JLabel lblSalaryPerPCI = new JLabel("St\u00FCckpreis:");
		lblSalaryPerPCI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblSalaryPerPCI, "cell 1 2");
		
		txtSalaryPerPCI = new JTextField();
		txtSalaryPerPCI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSalaryPerPCI.setColumns(5);
		PnlCenter.add(txtSalaryPerPCI, "cell 1 2");
		
		JLabel lblSalaryPCIAll = new JLabel("Gesamt:");
		lblSalaryPCIAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(lblSalaryPCIAll, "cell 1 2");
		
		txtSalaryPCIAll = new JTextField();
		txtSalaryPCIAll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSalaryPCIAll.setColumns(5);
		PnlCenter.add(txtSalaryPCIAll, "cell 1 2");
		
		JButton btnBillCreate = new JButton("Rechnung erstellen");
		btnBillCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		PnlCenter.add(btnBillCreate, "cell 1 4,alignx right,aligny top");

	}

}
