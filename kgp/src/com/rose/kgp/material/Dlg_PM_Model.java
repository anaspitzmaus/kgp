package com.rose.kgp.material;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.kgp.material.Ctrl_Dlg_PMModel.ManufacturerModel;
import com.rose.kgp.material.Ctrl_Dlg_PMModel.TblPMIDRenderer;


public class Dlg_PM_Model extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1918969883666643124L;
	
	Pnl_PM_Model pnlPM;
	JMenuItem mntmPM;
	JMenuItem mntmICD;
	
	protected JButton getBtnCreate() {
		return pnlPM.getBtnCreate();
	}
	
	protected JTextField getTxtNotation() {
		return pnlPM.getTxtNotation();
	}
	
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Dlg_PM_Model dialog = new Dlg_PM_Model();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dlg_PM_Model() {
		setBounds(100, 100, 450, 431);
		getContentPane().setLayout(new BorderLayout());
		pnlPM = new Pnl_PM_Model();
		getContentPane().add(pnlPM, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JMenuBar menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			{
				JMenu mnAggregates = new JMenu("Aggregat");
				mnAggregates.setFont(new Font("Segoe UI", Font.PLAIN, 14));
				menuBar.add(mnAggregates);
				{
					mntmPM = new JMenuItem("Schrittmacher");
					mntmPM.setFont(new Font("Segoe UI", Font.PLAIN, 14));
					mnAggregates.add(mntmPM);
				}
				{
					mntmICD = new JMenuItem("ICD");
					mntmICD.setFont(new Font("Segoe UI", Font.PLAIN, 14));
					mnAggregates.add(mntmICD);
				}
			}
		}
	}
	
	
	
	protected JMenuItem getMntmPM() {
		return mntmPM;
	}

	protected JMenuItem getMntmICD() {
		return mntmICD;
	}

	protected void setTypeModel(ComboBoxModel<PM_Type> model) {
		pnlPM.getCbxType().setModel(model);
	}
	
	protected ComboBoxModel<PM_Type> getTypeModel() {
		return pnlPM.getCbxType().getModel();
	}
	
	protected JComboBox<Manufacturer> getCbxManufacturer() {
		return pnlPM.cbxManufacturer;
	}
	
	protected JComboBox<PM_Type> getCbxType() {
		return pnlPM.cbxType;
	}

	protected void setManufacturerModel(ManufacturerModel manufacturerModel) {
		pnlPM.getCbxManufacturer().setModel(manufacturerModel);
		
	}
	
	protected ComboBoxModel<Manufacturer> getManufacturerModel(){
		return pnlPM.getCbxManufacturer().getModel();
	}
	
	protected void setManufacturerRenderer(ListCellRenderer<? extends Manufacturer> renderer) {
		pnlPM.getCbxManufacturer().setRenderer((ListCellRenderer<? super Manufacturer>) renderer);
	}
	
	protected void setBooleanRenderer(Class<Boolean> colclass, TableCellRenderer r) {
		pnlPM.getPMTable().setDefaultRenderer(colclass, r);
	}
	
	protected void addRAListener(ActionListener l) {
		pnlPM.getCheckRA().addActionListener(l);
	}
	
	protected void addRVListener(ActionListener l) {
		pnlPM.getCheckRV().addActionListener(l);
	}
	
	protected void addLVListener(ActionListener l) {
		pnlPM.getCheckLV().addActionListener(l);
	}
	
	protected void addMRIListener(ActionListener l) {
		pnlPM.getCbxMRI().addActionListener(l);
	}
	
	protected void setTypeRenderer(ListCellRenderer<PM_Type> r) {
		pnlPM.getCbxType().setRenderer(r);
	}
	
	protected void setRASelection(Boolean sel) {
		pnlPM.getCheckRA().setSelected(sel);
	}
	
	protected void setRVSelection(Boolean sel) {
		pnlPM.getCheckRV().setSelected(sel);
	}
	
	protected void setLVSelection(Boolean sel) {
		pnlPM.getCheckLV().setSelected(sel);
	}
	
	protected void setMRISelection(Boolean sel) {
		pnlPM.getCbxMRI().setSelected(sel);
	}
	
	protected void addTypeListener(ActionListener l) {
		pnlPM.getCbxType().addActionListener(l);
	}
	
	protected void addNotationListener(DocumentListener l) {
		pnlPM.getTxtNotation().getDocument().addDocumentListener(l);
	}
	
	protected String getNotationText() {
		return pnlPM.getTxtNotation().getText();
	}
	
	protected void addManufacturerListener(ActionListener l) {
		pnlPM.cbxManufacturer.addActionListener(l);
	}
	
	protected void addCreateListener(ActionListener l) {
		pnlPM.getBtnCreate().addActionListener(l);
	}
	
	
	
	protected void setPMTableModel(AbstractTableModel model) {
		pnlPM.getPMTable().setModel(model);
	}
	
	protected void setTablePMTypeRenderer(Class<PM_Type> colclass, TableCellRenderer r) {
		pnlPM.getPMTable().setDefaultRenderer(colclass, r);
	}
	
	protected JTable getTblPM() {
		return pnlPM.getPMTable();
	}
	
	protected void emptyTextFields() {
		pnlPM.getTxtNotation().setText("");		
	}

	protected void setTblPMIDRenderer(Class<AggregatModel> idClass, TblPMIDRenderer r) {
		pnlPM.getPMTable().setDefaultRenderer(idClass, r);
		
	}
	protected void setTblStringRenderer(Class<String> stringClass, TableCellRenderer r) {
		pnlPM.getPMTable().setDefaultRenderer(stringClass, r);
	}
	
	protected void addTblPMRowSelectionListener (ListSelectionListener l) {
		pnlPM.getPMTable().getSelectionModel().addListSelectionListener(l);
	}

	protected void addMenuItemAggregatListener(ActionListener l) {
		mntmPM.addActionListener(l);
		mntmICD.addActionListener(l);
		
	}
	
	/**
	 * clears the selection of the table that shows the models of pacemakers
	 */
	protected void clearSelectionOfTbl() {
		pnlPM.getPMTable().getSelectionModel().clearSelection();
		
	}

	protected void addModeListener(ActionListener l) {
		pnlPM.getTglMode().addActionListener(l);
		
	}

	/**
	 * set the checkBoxes of the types to false
	 */
	protected void clearCbxType() {
		pnlPM.getCheckLV().setSelected(false);
		pnlPM.getCheckRA().setSelected(false);
		pnlPM.getCheckRV().setSelected(false);
		
	}

}
