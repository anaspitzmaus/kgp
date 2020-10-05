package com.rose.kgp.material;


import java.awt.Font;


import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import javax.swing.JCheckBox;
import javax.swing.JToolBar;
import javax.swing.JToggleButton;

public class Pnl_PM_Model extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8222722839362830451L;
	private JTextField txtNotation;
	private JTable tblPM1;
	
	JComboBox<PM_Type> cbxType;
	JComboBox<Manufacturer> cbxManufacturer;
	JCheckBox checkRA, checkRV, checkLV;
	JButton btnCreate;
	private JCheckBox cbxMRI;
	private JToolBar toolBar;
	private JLabel lblMode;
	private JToggleButton tglMode;
	

	
	protected JComboBox<PM_Type> getCbxType() {
		return cbxType;
	}
	protected JComboBox<Manufacturer> getCbxManufacturer() {
		return cbxManufacturer;
	}
	
	protected JTable getPMTable() {
		return tblPM1;
	}
	
	protected JCheckBox getCheckRA() {
		return checkRA;
	}

	protected JCheckBox getCheckRV() {
		return checkRV;
	}
	protected JCheckBox getCheckLV() {
		return checkLV;
	}
	
	protected JTextField getTxtNotation() {
		return this.txtNotation;
	}	
	
	
	protected JButton getBtnCreate() {
		return btnCreate;
	}
	
	
	protected JCheckBox getCbxMRI() {
		return cbxMRI;
	}
	
	protected JToggleButton getTglMode() {
		return this.tglMode;
	}
	/**
	 * Create the panel.
	 */
	public Pnl_PM_Model() {
		setLayout(new MigLayout("", "[grow][grow][grow][]", "[][][][][][][]"));		
		
		toolBar = new JToolBar();
		add(toolBar, "cell 0 0");
		
		lblMode = new JLabel("Modus:");
		lblMode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(lblMode);
		
		tglMode = new JToggleButton("Neu");
		tglMode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		toolBar.add(tglMode);
		
		
		JLabel lblNotation = new JLabel("Bezeichnung:");
		lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNotation, "cell 0 1");
		
		JLabel lblManufacturer = new JLabel("Hersteller:");
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblManufacturer, "cell 1 1");
		
		JLabel lblType = new JLabel("Typ:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblType, "cell 2 1");
		
		txtNotation = new JTextField();
		txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtNotation, "cell 0 2,alignx left");
		txtNotation.setColumns(10);
		
		cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxManufacturer, "cell 1 2,growx");
		
		cbxType = new JComboBox<PM_Type>();
		cbxType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxType, "cell 2 2,growx");
		
		btnCreate = new JButton("Erstellen");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnCreate, "cell 3 2");
		
		checkRA = new JCheckBox("Rechter Vorhof");
		checkRA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(checkRA, "cell 2 3");
		
		cbxMRI = new JCheckBox("MRT-f\u00E4hig");
		cbxMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxMRI, "cell 1 4");
		
		checkRV = new JCheckBox("Rechter Ventrikel");
		checkRV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(checkRV, "cell 2 4");
		
		checkLV = new JCheckBox("Linker Ventrikel");
		checkLV.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(checkLV, "cell 2 5");
		
		JScrollPane scrTbl1 = new JScrollPane();
		add(scrTbl1, "cell 0 6 4 1,growx");
		
		tblPM1 = new JTable();
		tblPM1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrTbl1.setViewportView(tblPM1);
		
	

	}


	
	
	

}
