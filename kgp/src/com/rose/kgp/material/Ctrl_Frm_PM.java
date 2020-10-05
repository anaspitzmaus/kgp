package com.rose.kgp.material;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.AbstractButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.kgp.db.DB;


import com.rose.kgp.db.Dlg_DBSettings;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.db.SQL_UPDATE;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.examination.PM_Implant;
import com.rose.kgp.material.Ctrl_Dlg_PMModel.TblBooleanRenderer;
import com.rose.kgp.material.Ctrl_Dlg_PMModel.TblPMKindModel;
import com.rose.kgp.ui.Ctrl_PnlSetDate;




public class Ctrl_Frm_PM {

	FrmPM frmPM;
	TblPMKindModel tblPMKindModel;
	//TblICDKindModel tblICDKindModel;
	TablePMTypeRenderer tablePMTypeRenderer;
	TblPMModelIDRenderer tblPMModelIDRenderer;
	TblStringRenderer tblStringRenderer;
	TblPMModelRowSelectionListener tblPMModelRowSelectionListener;
	String serialNr;
	Ctrl_PnlSetDate ctrlPnlSetDate;
	AggregatModel pmModelSel;
	NewPMListener newPmListener;
	SerialNrListener serialNumberListener;
	TblPMModel tblPMModel;
	TblPMExamRenderer tblPMExamRenderer;
	TblPMIDRenderer tblPMIDRenderer;
	TblPMDateRenderer tblPMDateRenderer;
	TblPMDefaultRenderer tblPMDefaultRenderer;
	String notice;
	NoticeListener noticeListener;
	ShowAllListener showAllListener;
	TblBooleanRenderer tblBooleanRenderer;
	Boolean modeChange = false;
	ModeListener modeListener;
	PM pmSel;
	TblPMSelectionListener tblPMSelectionListener;
	MenuItemPMListener menuItemPMListener;
	Boolean icd = false;
	
	
	
	
	public Ctrl_Frm_PM() {
		connectDB();
		if (DB.getConnection() != null) {
			frmPM = new FrmPM();
			frmPM.setVisible(true);
			tblPMKindModel = new TblPMKindModel(SQL_SELECT.pacemakerKinds());
			frmPM.setPMModelTableModel(tblPMKindModel);
			tablePMTypeRenderer = new TablePMTypeRenderer();
			frmPM.setTablePMTypeRenderer(PM_Type.class, tablePMTypeRenderer);
			tblPMModelIDRenderer = new TblPMModelIDRenderer();
			frmPM.setTblPMModelIDRenderer(AggregatModel.class, tblPMModelIDRenderer);
			tblStringRenderer = new TblStringRenderer();
			frmPM.setTblStringRenderer(String.class, tblStringRenderer);
			tblPMModelRowSelectionListener = new TblPMModelRowSelectionListener();
			frmPM.addTblPMModelRowSelectionListener(tblPMModelRowSelectionListener);
			ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(0));
			ctrlPnlSetDate.getPanel().setLabelDateText("");
			frmPM.setDatePnl(ctrlPnlSetDate.getPanel());
			serialNumberListener = new SerialNrListener();
			frmPM.addSerialNrListener(serialNumberListener);
			newPmListener = new NewPMListener();
			frmPM.addNewPmListener(newPmListener);
			tblPMExamRenderer = new TblPMExamRenderer();
			frmPM.setTblPMExamRenderer(PM_Implant.class, tblPMExamRenderer);
			tblPMIDRenderer = new TblPMIDRenderer();
			frmPM.setTblPMIDRenderer(PM.class, tblPMIDRenderer);
			tblPMDateRenderer = new TblPMDateRenderer();
			frmPM.setTblPMDateRenderer(LocalDate.class, tblPMDateRenderer);
			tblPMDefaultRenderer = new TblPMDefaultRenderer();
			frmPM.setTblPMDefaultRenderer(String.class,  tblPMDefaultRenderer);
			tblPMModel = new TblPMModel(SQL_SELECT.pacemakers(null));
			frmPM.setPMTableModel(tblPMModel);	
			noticeListener = new NoticeListener();
			frmPM.addNoticeListener(noticeListener);
			showAllListener = new ShowAllListener();
			frmPM.addShowAllListener(showAllListener);
			tblBooleanRenderer = new TblBooleanRenderer();
			frmPM.setBooleanRenderer(Boolean.class, tblBooleanRenderer);
			modeListener = new ModeListener();
			frmPM.addModeListener(modeListener);
			tblPMSelectionListener = new TblPMSelectionListener();
			frmPM.addPMSelectionListener(tblPMSelectionListener);
			menuItemPMListener = new MenuItemPMListener();
			frmPM.addMenuItemPMListener(menuItemPMListener);
			frmPM.addMenuItemICDListener(menuItemPMListener);
		}
	}
	

	private void connectDB() {
		if (DB.getConnection() == null) {		
			if(DB.createConnection() != null){			
				 //go further on only if a connection to the database could be established
				if(SQL_INSERT.Admin()){		//insert the administrator to database (necessary for first login)
					//start the login dialog
					try {
//						Dlg_LogIn dialog = new Dlg_LogIn();
//						dialog.setLocationRelativeTo(null); //show in the center of the screen
//						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						//dialog.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JFrame(),
							    "The Application couldn't be started!", "Fatal Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(new JFrame(),
						    "The Application couldn't be started!", "Fatal Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}else{ //if there couldn't be created a database connection
				//open the Dialog with the database settings
				Dlg_DBSettings dlgDBSettings = new Dlg_DBSettings();
				dlgDBSettings.setLocationRelativeTo(null);
				dlgDBSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				dlgDBSettings.setModal(true);
				dlgDBSettings.setVisible(true);	
			}
		}
	}



class TblPMKindModel extends AbstractTableModel{

		
		private static final long serialVersionUID = -186195291092053661L;
		protected ArrayList<String> columnNames;
		ArrayList<? extends AggregatModel> aggregates;
		PM_Type type;
		
		public TblPMKindModel(ArrayList<? extends AggregatModel> aggregates) {
			this.aggregates = aggregates;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
			columnNames.add("Typ");
			columnNames.add("MRT");
			
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames.size();
		}
		
		@Override
		public String getColumnName(int column) {
	        return columnNames.get(column);
	    }

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return this.aggregates.size();
		}
		
		@Override
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			setType(aggregates.get(rowIndex));
			switch(columnIndex) {
			case 0: return aggregates.get(rowIndex);
			
			case 1: return aggregates.get(rowIndex).getNotation();
			
			case 2: return aggregates.get(rowIndex).getManufacturer().getNotation();
			
			case 3: return aggregates.get(rowIndex).getType();
			
			case 4: return aggregates.get(rowIndex).getMri();
			
			case 5: return ((ICD_Model)aggregates.get(rowIndex)).getAtp();//only if ICD_Model
			
			default: return null;
			
			}			
		}
		
		protected void setType(AggregatModel pm) {
			if(pm.getRa() && pm.getRv() && pm.getLv()) {
				pm.setType(PM_Type.CRT);
			}else if(pm.getRa() && pm.getRv() || pm.getRa() && pm.getLv() || pm.getRv() && pm.getLv()) {
				pm.setType(PM_Type.Double_Chamber);
			}else if(pm.getRa() || pm.getRv() || pm.getLv()) {
				pm.setType(PM_Type.Single_Chamber);
			}else {
				pm.setType(null);
			}
		}
		
		/**
		 * 
		 * @param pm
		 */
		protected void setAggregats(ArrayList<? extends AggregatModel> pm) {
			this.aggregates = pm;
			if(icd) {
				if(!columnNames.contains("ATP")) {
					columnNames.add("ATP");//add columns for ICD-Model
				}
			}else {//if pacemaker
				columnNames.remove("ATP");
			}
			
		}
		
	}

/**
 * renderer for the table that displays the kinds of pacemakers
 * @author user2
 *
 */
class TablePMTypeRenderer extends JLabel implements TableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4054908565583961074L;
	private String notation;
	
	public TablePMTypeRenderer() {
		super.setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
		if(value instanceof PM_Type) {
			changeChamberNotation((PM_Type) value);
			setText(notation);
		}else {
			setText("");
		}
		
		if(isSelected) {
			setBackground(Color.ORANGE);
		}else {
			setBackground(Color.WHITE);
		}
			
		//}
		return this;
	}
	
	private void changeChamberNotation(PM_Type type) {
		switch(type) {
		case Single_Chamber:
			notation = "Einkammer";
			break;
		case Double_Chamber:
			notation = "Doppelkammer";
			break;
		case CRT:
			notation = "CRT";
			break;
		default:
				notation = "";
		}
	}
	
}

class TblBooleanRenderer extends JCheckBox implements TableCellRenderer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6237739721842901289L;

	public TblBooleanRenderer() {
		super.setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int column,
			int row) {
		setSelected((boolean) value);
		setHorizontalAlignment(CENTER);
		if(isSelected) {
			setBackground(Color.ORANGE);
		}else {
			setBackground(Color.WHITE);
		}
		return this;
	}
	
}

class TblPMModelIDRenderer extends JLabel implements TableCellRenderer{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7687161924946698926L;

	public TblPMModelIDRenderer() {
		super.setOpaque(true);
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int column,
			int row) {
		Integer id = ((AggregatModel)value).getId();
		setText(id.toString());
		if(isSelected) {
			setBackground(Color.ORANGE);
		}else {
			setBackground(Color.WHITE);
		}
		return this;
	}
	
}


class TblStringRenderer extends JLabel implements TableCellRenderer{

/**
 * 
 */

public TblStringRenderer() {
	super.setOpaque(true);
}
private static final long serialVersionUID = -523838024295879261L;

@Override
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int column,
		int row) {
	setText(value.toString());
	if(isSelected) {
		setBackground(Color.ORANGE);
	}else {
		setBackground(Color.WHITE);
	}
	return this;
}

}
	class TblPMModelRowSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			
			if (frmPM.getTblPMModel().getSelectedRow() > -1) {
				
					int row = frmPM.getTblPMModel().getSelectedRow();
					pmModelSel = (AggregatModel) frmPM.getTblPMModel().getValueAt(row, 0); //get the pacemaker model from the first column
					frmPM.getLblPMSel().setText(pmModelSel.getNotation() + " von " + pmModelSel.getManufacturer().getNotation());
					tblPMModel.setPacemakers(SQL_SELECT.pacemakers(pmModelSel));
					tblPMModel.fireTableDataChanged();
					frmPM.getTxtSerialNr().setText("");
					frmPM.getTxtNotice().setText("");
					ctrlPnlSetDate.setDate(null);
					pmSel = null;
		           // Manufacturer m = (Manufacturer) dlgPM.getManufacturerModel().getSelectedItem();
		           
	//	            for(Manufacturer m: manufacturers) {
	//	            	if (pmSel.getManufacturer().getNotation().equals(m.getNotation())) {
	//	            		 dlgPM.getManufacturerModel().setSelectedItem(m);
	//	            	}
	//	            }
		           
	//	            pmSel.setId((Integer) dlgPM.getTblPM().getValueAt(row, 0));
	//	            pmSel.setManufacturer(manufacturer);(dlgManufacturer.getTblManufacturer().getValueAt(row, 2).toString());
	//	            manufacturer.setMobile(dlgManufacturer.getTblManufacturer().getValueAt(row, 3).toString());
	//	            //fill text fields with data of selected manufacturer
	//	            dlgPM.getTNotation().setText(manufacturer.getNotation());
	//	            dlgManufacturer.getTxtContact().setText(manufacturer.getContact_person());
	//	            dlgManufacturer.getFtxtMobile().setValue(manufacturer.getMobile());
		           	           
	        }
			
		}
	
	}
	
	class TblPMSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if(frmPM.getTblPM().getSelectedRow() > -1 && modeChange) {//if row is selected and mode is on change
				int row = frmPM.getTblPM().getSelectedRow();
				pmSel = (PM) frmPM.getTblPM().getValueAt(row, 0); //get the pacemaker from the first column
				frmPM.getTxtSerialNr().setText(pmSel.getSerialNr());
				frmPM.getTxtNotice().setText(pmSel.getNotice());
				ctrlPnlSetDate.setDate(pmSel.getExpireDate());
			}
			
		}
		
	}
	
	class SerialNrListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			serialNr = frmPM.getSerialNr();
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			serialNr = frmPM.getSerialNr();
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			serialNr = frmPM.getSerialNr();
			
		}
		
	}
	
	class NoticeListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			notice = frmPM.getNotice();
			
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			notice = frmPM.getNotice();
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			notice = frmPM.getNotice();
			
		}
		
	}
	
	class NewPMListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(modeChange == false) {//for a new created pacemaker
				if(serialNr != "" && ctrlPnlSetDate.getDate() != null && pmModelSel instanceof AggregatModel) {
					PM pm = new PM(pmModelSel);
					pm.setSerialNr(serialNr);
					pm.setExpireDate(ctrlPnlSetDate.getDate());
					pm.setNotice(notice);
					SQL_INSERT.pacemaker(pm);
					tblPMModel.setPacemakers(SQL_SELECT.pacemakers(pmModelSel));
					tblPMModel.fireTableDataChanged();
				}
			}else {//for changing the data of a selected pacemaker
				if(serialNr != "" && ctrlPnlSetDate.getDate() != null && pmSel instanceof PM) {
					pmSel.setSerialNr(serialNr);
					pmSel.setNotice(notice);
					pmSel.setExpireDate(ctrlPnlSetDate.getDate());
					SQL_UPDATE.Pacemaker(pmSel);
					tblPMModel.setPacemakers(SQL_SELECT.pacemakers(pmModelSel));
					tblPMModel.fireTableDataChanged();
				}
			}
		}
		
	}
	
class TblPMModel extends AbstractTableModel{

		
		private static final long serialVersionUID = -186195291092053661L;
		protected ArrayList<String> columnNames;
		ArrayList<PM> paceMakers;
		PM_Type type;
		
		public TblPMModel(ArrayList<PM> paceMakers) {
			this.paceMakers = paceMakers;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Seriennummer");
			columnNames.add("Verfalldatum");
			columnNames.add("Untersuchungsnummer");
			columnNames.add("Notiz");
		}
		
		protected void setPacemakers(ArrayList<PM> pms) {
			this.paceMakers = pms;
		}
		
		@Override
		public int getColumnCount() {
			return columnNames.size();
		}
		
		@Override
		public String getColumnName(int column) {
	        return columnNames.get(column);
	    }

		@Override
		public int getRowCount() {
			return this.paceMakers.size();
		}
		
		@Override
		public Class getColumnClass(int col) {
			switch(col) {
			case 3: return PM_Implant.class;
			case 4: return String.class;
			default:	return getValueAt(0, col).getClass();
			}
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			switch(columnIndex) {
			case 0: return paceMakers.get(rowIndex);
			
			case 1: return paceMakers.get(rowIndex).getSerialNr();
			
			case 2: return paceMakers.get(rowIndex).getExpireDate();
			
			case 3: return paceMakers.get(rowIndex).getExam();
			
			case 4: return paceMakers.get(rowIndex).getNotice();
			
			default: return null;
			
			}			
		}
		
				
		
		
	}

/**
 * renderer for the column examination (class: PM_Implant) of the pacemaker table 
 * @author user2
 * if the pacemaker is not implanted, the examination is null, else the number of the examination is shown
 */
	class TblPMExamRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 2034566943136916210L;

		public TblPMExamRenderer() {
			setOpaque(true);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object exam,  boolean isSelected, boolean hasFocus, int column,
				int row) {
			if(exam == null) {
				setText("");				
			}else {
				setText(((PM_Implant) exam).getRefNo().toString());
			}
			
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(Color.WHITE);
			}
			return this;
		}
		
	}
	
	
	class TblPMIDRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8963479365620609203L;

		public TblPMIDRenderer() {
			setOpaque(true);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value,  boolean isSelected, boolean hasFocus, int column,
				int row) {
			
			Integer id = ((PM)value).getId();
			setText(id.toString());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(Color.WHITE);
			}
			return this;
		}
		
	}
	
	class TblPMDateRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7421293031977663350L;

		public TblPMDateRenderer() {
			setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object date, boolean isSelected, boolean hasFocus, int column,
				int row) {
			LocalDate ld = (LocalDate) date;
			setText(ld.getDayOfMonth() + "." + ld.getMonthValue() + "." + ld.getYear());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(Color.WHITE);
			}
			return this;
		}
		
	}
	
	class TblPMDefaultRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 5843664124534875776L;
		public TblPMDefaultRenderer() {
			setOpaque(true);
		}
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(value instanceof String) {
				setText((String)value);
			}else {
				setText("");
			}
			
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(Color.WHITE);
			}
			return this;
		}
		
	}
	
	class ShowAllListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			pmModelSel = null;
			tblPMModel.setPacemakers(SQL_SELECT.pacemakers(pmModelSel));
			tblPMModel.fireTableDataChanged();
			frmPM.clearSelectionOfTblPMKind();
		}
		
	}
	
	class ModeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			if(selected) {
				abstractButton.setText("Ändern");
				modeChange = true;
				frmPM.clearSelectionOfTblPM();
				ctrlPnlSetDate.setDate(null);
				pmSel = null;
				frmPM.getBtnAdd().setText("Änderungen übernehmen");
			}else {
				abstractButton.setText("Neu");
				modeChange = false;
				frmPM.clearSelectionOfTblPM();
				frmPM.getBtnAdd().setText("Hinzufügen");
			}
		}
		
	}
	
	class MenuItemPMListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == frmPM.getMntmPM()) {
				//Show tblPacemaker
				icd = false;
				pmModelSel = null;
				tblPMKindModel.setAggregats(SQL_SELECT.pacemakerKinds());
				//tblPMKindModel = new TblPMKindModel(SQL_SELECT.pacemakerKinds());
				tblPMKindModel.fireTableStructureChanged();
				frmPM.clearSelectionOfTblPMKind();
				pmSel = null;
				tblPMModel.setPacemakers(SQL_SELECT.pacemakers(pmModelSel));
				tblPMModel.fireTableStructureChanged();
				frmPM.clearSelectionOfTblPM();
				
			}else if(event.getSource() == frmPM.getMntmICD()){
				//show TblICD
				icd = true;
				pmModelSel = null;
				tblPMKindModel.setAggregats(SQL_SELECT.ICD_Kinds());
			//	tblPMKindModel = new TblPMKindModel(SQL_SELECT.ICD_Kinds());
				tblPMKindModel.fireTableStructureChanged();
				frmPM.clearSelectionOfTblPMKind();
			};
			
			
		}
		
	}
}


