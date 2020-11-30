package com.rose.kgp.material;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.kgp.material.CtrlPnlElectrodeModel.ManufacturerModel;

import net.miginfocom.swing.MigLayout;

public class PnlElectrodeModel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7414126287565112513L;
	private JTextField txtNotation;
	private JTextField txtNotice;
	private JTable tblElectrodeModel;
	JComboBox<Manufacturer> cbxManufacturer;
	private JSpinner spinLength;
	private JCheckBox checkMRI;
	JToggleButton tglFix;
	JButton btnAdd;
	private JLabel lblModeText;
	
	protected JTextField getTxtNotation() {
		return txtNotation;
	}

	protected void setTxtNotation(JTextField txtNotation) {
		this.txtNotation = txtNotation;
	}
	
	

	protected JTable getTblElectrodeModel() {
		return tblElectrodeModel;
	}
	
	

	protected JComboBox<Manufacturer> getCbxManufacturer() {
		return cbxManufacturer;
	}
	
	

	protected JTextField getTxtNotice() {
		return txtNotice;
	}
	
	protected Integer getSelectedRow() {
		return tblElectrodeModel.getSelectedRow();
	}
	
	protected ListSelectionModel getTblListSelectionModel() {
		return tblElectrodeModel.getSelectionModel();
	}
	
	protected void setLblModeText(String txt) {
		lblModeText.setText(txt);
	}
	/**
	 * Create the panel.
	 */
	public PnlElectrodeModel() {
		setLayout(new MigLayout("", "[][grow][][][][][][][][][grow]", "[][][][grow]"));
		
		JLabel lblMode = new JLabel("Modus:");
		lblMode.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblMode, "cell 0 0");
		
		lblModeText = new JLabel("lblModeText");
		lblModeText.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblModeText, "cell 1 0,growx");
		
		JLabel lblNotation = new JLabel("Bezeichnung:");
		lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNotation, "cell 0 1,alignx trailing");
		
		txtNotation = new JTextField();
		txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtNotation, "cell 1 1,growx");
		txtNotation.setColumns(10);
		
		JLabel lblManufacturer = new JLabel("Hersteller:");
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblManufacturer, "cell 2 1");
		
		cbxManufacturer = new JComboBox<Manufacturer>();
		cbxManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxManufacturer, "cell 3 1");
		
		JLabel lblLength = new JLabel("L\u00E4nge:");
		lblLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblLength, "cell 4 1");
		
		
		spinLength = new JSpinner();
		spinLength.setModel(new SpinnerNumberModel(52, 48, 62, 1));
		spinLength.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(spinLength, "cell 5 1");
		
		JLabel lblLengthUnit = new JLabel("cm");
		lblLengthUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblLengthUnit, "cell 6 1");
		
		tglFix = new JToggleButton("tglFix");
		tglFix.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(tglFix, "cell 7 1");
		
		checkMRI = new JCheckBox("MRT-f\u00E4hig");
		checkMRI.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(checkMRI, "cell 8 1");
		
		JLabel lblNotice = new JLabel("Anmerkung:");
		lblNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblNotice, "cell 9 1");
		
		
		txtNotice = new JTextField();
		txtNotice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(txtNotice, "flowx,cell 10 1,growx");
		txtNotice.setColumns(10);
		
		
		
		btnAdd = new JButton("btnAdd");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnAdd, "cell 10 2,growx");
		
		JScrollPane scrollTbl = new JScrollPane();
		add(scrollTbl, "cell 0 3 11 1,grow");
		
		tblElectrodeModel = new JTable();
		tblElectrodeModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblElectrodeModel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollTbl.setViewportView(tblElectrodeModel);
		
		

	}
	
	protected void setBtnAddText(String text) {
		btnAdd.setText(text);
	}
	
	

	protected void addFixModeListener(ActionListener fixModeListener) {
		tglFix.addActionListener(fixModeListener);
		
	}
	
	protected void setTglFixText(String text) {
		tglFix.setText(text);
	}
	
	
	
	protected void addNotationListener(DocumentListener l) {
		txtNotation.getDocument().addDocumentListener(l);
	}
	
	protected void setManufacturerModel(ManufacturerModel manufacturerModel) {
		cbxManufacturer.setModel(manufacturerModel);
	}	
	
	protected void setManufacturerRender(ListCellRenderer<Manufacturer> manufacturerRenderer) {
		cbxManufacturer.setRenderer(manufacturerRenderer);		
	}
	
	protected void addCreateListener(ActionListener l) {
		btnAdd.addActionListener(l);
	}

	protected void addManufacturerListener(ItemListener manufacturerListener) {
		cbxManufacturer.addItemListener(manufacturerListener);
		
	}
	
	protected SpinnerModel getSpinLengthModel() {
		return spinLength.getModel();
	}

	protected void addLengthListener(ChangeListener lengthListener) {
		spinLength.addChangeListener(lengthListener);
		
	}

	protected void addMRIListener(ActionListener mriListener) {
		checkMRI.addActionListener(mriListener);
		
	}

	protected void addNoticeListener(DocumentListener noticeListener) {
		txtNotice.getDocument().addDocumentListener(noticeListener);
		
	}

	protected void addElectrodesModelTableModel(AbstractTableModel model) {
		tblElectrodeModel.setModel(model);
		
	}

	protected void setTblElectrodeModelIDRenderer(TableCellRenderer renderer) {
		tblElectrodeModel.setDefaultRenderer(Integer.class,renderer);
		
	}
	
	protected void setTblElectrodeModelEMRenderer(TableCellRenderer renderer) {
		tblElectrodeModel.setDefaultRenderer(ElectrodeModel.class ,renderer);
		
	}
	
	protected void setTblElectrodeModelStringRenderer(TableCellRenderer renderer) {
		tblElectrodeModel.setDefaultRenderer(String.class, renderer);
		
	}
	
	protected void setTblElectrodeModelBooleanRenderer(TableCellRenderer renderer) {
		tblElectrodeModel.setDefaultRenderer(Boolean.class, renderer);
		
	}

	protected void addTblEMRowSelectionListener(ListSelectionListener listener) {
		tblElectrodeModel.getSelectionModel().addListSelectionListener(listener);
		
	}
	
		

	/**
	 * set all components to standard and clear selection of the table
	 */
	protected void setBaselineValues() {
		txtNotation.setText("");
		txtNotice.setText("");
		checkMRI.setSelected(false);
		cbxManufacturer.setSelectedIndex(-1);
		tglFix.setSelected(false);
		tblElectrodeModel.getSelectionModel().clearSelection();
	}
	
	protected void setMRI(Boolean mri) {
		checkMRI.setSelected(mri);
	}

	protected void setFixMode(String fixMode) {
		switch (fixMode) {
		case "Schraube": 
			tglFix.setSelected(false);			
			break;
		case "Anker":
			tglFix.setSelected(true);
			break;
		default:
			break;
		}
		tglFix.setText(fixMode);
		
	}
	
	
	
	public void setBtnCreateText(String string) {
		btnAdd.setText(string);
		
	}

}
