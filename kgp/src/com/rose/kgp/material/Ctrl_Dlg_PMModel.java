package com.rose.kgp.material;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.MutableComboBoxModel;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import com.rose.kgp.db.DB;
import com.rose.kgp.db.Dlg_DBSettings;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;

public class Ctrl_Dlg_PMModel {
	Dlg_PM_Model dlgPM;
	TypeModel typeModel;
	ManufacturerModel manufacturerModel;
	//ArrayList<Manufacturer> manufacturers;
	ManufacturerRenderer manufacturerRenderer;
	TblPMKindModel tblPMKindModel;
	RAListener raListener;
	RVListener rvListener;
	LVListener lvListener;
	Boolean ra = false;
	Boolean rv = false;
	Boolean lv = false;
	Boolean mri = false;
	PM_Type[] pmTest;
	TypeRenderer typeRenderer;
	TypeListener typeListener;
	String notation;
	NotationListener notationListener;
	Manufacturer manufacturer;
	ManufacturerListener manufacturerListener;
	CreateListener createListener;
	TablePMTypeRenderer tablePMTypeRenderer;
	TblPMIDRenderer tblPMIDRenderer;
	TblStringRenderer tblStringRenderer;
	TblPMRowSelectionListener tblPMRowSelectionListener;
	MRIListener mriListener;
	Boolean modusPM = false;
	AggregatModel aggSel;
	TblBooleanRenderer tblBooleanRenderer;
	Boolean icd = false;
	MenuItemPMListener menuItemPMListener;
	Boolean modeChange = false;
	ModeListener modeListener;
	
	public Ctrl_Dlg_PMModel() {
		connectDB();
		if (DB.getConnection() != null) {
		dlgPM = new Dlg_PM_Model();
		
		
		pmTest = new PM_Type[]{PM_Type.Single_Chamber, PM_Type.Double_Chamber, PM_Type.CRT};
		//typeModel = new TypeModel(pmTest);
//		pmTest = new ArrayList<String>();
//		pmTest.add("Einkammer");
//		pmTest.add("Doppelkammer");
//		pmTest.add("CRT");
		
		dlgPM.setTypeModel(new DefaultComboBoxModel<PM_Type>(pmTest));
		typeRenderer = new TypeRenderer();
		dlgPM.setTypeRenderer(typeRenderer);
		raListener = new RAListener();
		rvListener = new RVListener();
		lvListener = new LVListener();
		dlgPM.addRAListener(raListener);
		dlgPM.addRVListener(rvListener);
		dlgPM.addLVListener(lvListener);
		mriListener = new MRIListener();
		dlgPM.addMRIListener(mriListener);
		//manufacturers = new ArrayList<Manufacturer>();
		
		manufacturerModel = new ManufacturerModel();
		dlgPM.setManufacturerModel(manufacturerModel);		
		manufacturerRenderer = new ManufacturerRenderer();
		dlgPM.setManufacturerRenderer(manufacturerRenderer);
		tblPMKindModel = new TblPMKindModel(SQL_SELECT.pacemakerKinds());
		dlgPM.setPMTableModel(tblPMKindModel);
		tablePMTypeRenderer = new TablePMTypeRenderer();
		dlgPM.setTablePMTypeRenderer(PM_Type.class, tablePMTypeRenderer);
		tblPMIDRenderer = new TblPMIDRenderer();
		dlgPM.setTblPMIDRenderer(AggregatModel.class, tblPMIDRenderer);
		tblStringRenderer = new TblStringRenderer();
		dlgPM.setTblStringRenderer(String.class, tblStringRenderer);
		typeListener = new TypeListener();
		dlgPM.addTypeListener(typeListener);
		notationListener = new NotationListener();
		dlgPM.addNotationListener(notationListener);
		manufacturerListener = new ManufacturerListener();
		dlgPM.addManufacturerListener(manufacturerListener);
		tblPMRowSelectionListener = new TblPMRowSelectionListener();
		dlgPM.addTblPMRowSelectionListener(tblPMRowSelectionListener);
		createListener = new CreateListener();
		dlgPM.addCreateListener(createListener);
		tblBooleanRenderer = new TblBooleanRenderer();
		dlgPM.setBooleanRenderer(Boolean.class, tblBooleanRenderer);
		menuItemPMListener = new MenuItemPMListener();
		dlgPM.addMenuItemAggregatListener(menuItemPMListener);
		dlgPM.setVisible(true);
		modeListener = new ModeListener();
		dlgPM.addModeListener(modeListener);
		
		
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
	
	protected void checkChambers() {
		if(ra && rv && lv) {
			//if crt
			dlgPM.getTypeModel().setSelectedItem(pmTest[2]);
			return;
		}else if(ra && rv || ra && lv || rv && lv) {
			//if double chamber
			dlgPM.getTypeModel().setSelectedItem(pmTest[1]);
			return;
		}else if(ra || rv || lv) {
			// if single chamber
			dlgPM.getTypeModel().setSelectedItem(pmTest[0]);
			return;
		}
	}
	
	protected void selectChamber(PM_Type type) {
		if(type instanceof PM_Type) {
			 switch (type) {
				case Single_Chamber:
					dlgPM.setRASelection(false);
					dlgPM.setRVSelection(true);
					dlgPM.setLVSelection(false);
					ra = false; rv = true; lv = false;
					break;
				case Double_Chamber:
					dlgPM.setRASelection(true);
					dlgPM.setRVSelection(true);
					dlgPM.setLVSelection(false);
					ra = true; rv = true; lv = false;
					break;
				case CRT:
					dlgPM.setRASelection(true);
					dlgPM.setRVSelection(true);
					dlgPM.setLVSelection(true);
					ra = true; rv = true; lv = true;
					break;
				default:
					ra = false; rv = false; lv = false;
					break;
			}
		}else {
			dlgPM.setRASelection(false);
			dlgPM.setRVSelection(false);
			dlgPM.setLVSelection(false);
			ra = false; rv = false; lv = false;
		}
	}
	
	/**
	 * not in use
	 * @author user2
	 *
	 */
	class TypeModel implements ComboBoxModel<PM_Type>{
//		ArrayList<PM_Type> pmTypes;
		int indexSel;
		PM_Type[] types;		
		public TypeModel(PM_Type[] types) {
			this.types = types;
//			pmTypes = new ArrayList<String>();
//			pmTypes.add("Einkammer");
//			pmTypes.add("Zweikammer");
//			pmTypes.add("CRT");
//			for(PM_Type type: PM_Type.values()) {
//				pmTypes.add(type);
//			}
			
		}
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public PM_Type getElementAt(int index) {
			// TODO Auto-generated method stub
			return types[index];
		}

		@Override
		public int getSize() {
			return types.length;
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getSelectedItem() {
			return types[indexSel];
		}

		@Override
		public void setSelectedItem(Object pmType) {
			
			for(int i=0; i<types.length; i++) {
				if(types[i].equals(pmType)) {
					indexSel = i;
				}
			}
			
		}
		
	}
	
	/**
	 * model for the comboBox that displays the manufacturers
	 * @author Ekkehard Rose
	 *
	 */
	class ManufacturerModel extends AbstractListModel<Manufacturer> implements MutableComboBoxModel<Manufacturer>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7897970324843810587L;
		int indexSel;
		ArrayList<Manufacturer> manufacturers;
		
		public ManufacturerModel() {	
			//manufacturers = manus;
			manufacturers = SQL_SELECT.manufacturers();
		}
		
		@Override
		public Manufacturer getElementAt(int i) {			
			return manufacturers.get(i);
		}
		
		@Override
		public int getSize() {
			
			return manufacturers.size();
		}
		@Override
		public Object getSelectedItem() {
			
			if (indexSel >= 0) {
				return manufacturers.get(indexSel);
			}else {
				return "";
			}
		}
		@Override
		public void setSelectedItem(Object anItem) {
			for(int i = 0; i<manufacturers.size(); i++) {
				if(anItem.equals(manufacturers.get(i))) {
					indexSel = i;
					break;
				}
			}
			
		}
		@Override
		public void addElement(Manufacturer arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void insertElementAt(Manufacturer arg0, int arg1) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void removeElement(Object arg0) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void removeElementAt(int arg0) {
			// TODO Auto-generated method stub
			
		}
		
		public ArrayList<Manufacturer> getManufacturers(){
			return this.manufacturers;
		}
		
		
		
		
		
		
		
	}
	
	class ManufacturerRenderer extends JLabel implements ListCellRenderer<Manufacturer>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 6240666262007840856L;

		public ManufacturerRenderer() {
			setOpaque(true);
	        setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}
	
		@Override
		public Component getListCellRendererComponent(JList<? extends Manufacturer> list, Manufacturer value, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(((Manufacturer) value).getNotation());
			return this;
		}
		
	}
	
	class ManufacturerListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<Manufacturer> cb = (JComboBox<Manufacturer>)e.getSource();
	        manufacturer = (Manufacturer)cb.getSelectedItem();		
		}
	}
	
	
	class TblPMKindModel extends AbstractTableModel{

		
		private static final long serialVersionUID = -186195291092053661L;
		protected ArrayList<String> columnNames;
		ArrayList<? extends AggregatModel> aggregates;
		PM_Type type;
		
		public TblPMKindModel(ArrayList<? extends AggregatModel> paceMakers) {
			this.aggregates = paceMakers;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
			columnNames.add("Typ");
			columnNames.add("MRI");
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
		
		private void setType(AggregatModel pm) {
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
	
	class RAListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			ra = selected;
			checkChambers();
		}
		
	}
	
	class RVListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			rv = selected;
			checkChambers();
		}
		
	}
	
	class LVListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
			lv = selected;
			checkChambers();
		}
		
	}
	
	class MRIListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        mri = selected;			
		}
		
	}
	
	/**
	 * renderer for the comboBox that displays the types of pacemakers (Single-, double-chamber or CRT)
	 * @author user2
	 *
	 */
	class TypeRenderer extends JLabel implements ListCellRenderer<PM_Type>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -1801801106601869172L;

		@Override
		public Component getListCellRendererComponent(JList<? extends PM_Type> list, PM_Type type, int index,
				boolean isSelected, boolean hasFocus) {
			if(type instanceof PM_Type) {
				switch(type) {
					case Single_Chamber:
						setText("Einkammer");
						break;
					case Double_Chamber:
						setText("Doppelkammer");
						break;
					case CRT:
						setText("CRT");
						break;
					default:
						setText("Einkammer");					
				}
			}else {
				setText("");
			}
			return this;
		}
		
	}
	
	
	
	class TypeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox<PM_Type> cb = (JComboBox<PM_Type>)e.getSource();
	        PM_Type type = (PM_Type)cb.getSelectedItem();
	       selectChamber(type);
			
		}
		
	}
	
	/**
	 * listener for the JTextField txtNotation
	 * @author user2
	 *
	 */
	class NotationListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			
			notation = dlgPM.getNotationText();		
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			notation = dlgPM.getNotationText();
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			notation = dlgPM.getNotationText();
		}
		
	}
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			
			if(manufacturer != null && notation != null && checkElectrodes()) {
				if(icd) {
					ICD_Model aggModel = new ICD_Model(notation);
					aggModel.setManufacturer(manufacturer);
					aggModel.setRa(ra);
					aggModel.setRv(rv);
					aggModel.setLv(lv);
					aggModel.setMri(mri);
					SQL_INSERT.icd_Model((ICD_Model) aggModel);
					tblPMKindModel.setAggregats(SQL_SELECT.ICD_Kinds());
				}else {
					AggregatModel aggModel = new AggregatModel(notation);
					aggModel.setManufacturer(manufacturer);
					aggModel.setRa(ra);
					aggModel.setRv(rv);
					aggModel.setLv(lv);
					aggModel.setMri(mri);
					SQL_INSERT.pacemakerModel(aggModel);
					tblPMKindModel.setAggregats(SQL_SELECT.pacemakerKinds());
				}	
				
				tblPMKindModel.fireTableDataChanged();
				dlgPM.emptyTextFields();
			}
			
		}
		
		/**
		 * check if at least one Electrode was selected
		 * @return
		 */
		Boolean checkElectrodes() {
			if(ra || rv || lv) {
				return true;
			}
			return false;
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
	
class TblPMIDRenderer extends JLabel implements TableCellRenderer{

		
		/**
		 * 
		 */
		private static final long serialVersionUID = -7687161924946698926L;

		public TblPMIDRenderer() {
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

	class TblPMRowSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (dlgPM.getTblPM().getSelectedRow() > -1) {
				if(modeChange){//if changes of a aggregate model have to be done
					int row = dlgPM.getTblPM().getSelectedRow();
		            aggSel = (AggregatModel) dlgPM.getTblPM().getValueAt(row, 0); //get the aggregate from the first column
		            dlgPM.getTxtNotation().setText(aggSel.getNotation());
		            for(int i = 0; i < dlgPM.getManufacturerModel().getSize(); i++ ) {//get the manufacturer of the selected aggregate and display it in the comboBox
		            	if(dlgPM.getManufacturerModel().getElementAt(i).getNotation().equals(aggSel.getManufacturer().getNotation())){
		            		
		            		 dlgPM.getManufacturerModel().setSelectedItem( dlgPM.getManufacturerModel().getElementAt(i));
		            		
		            		 dlgPM.getCbxManufacturer().repaint();
		            	}
		            }
		            
		            //set the type of the aggregate (one/two or three chamber)
		            
		           
		            	dlgPM.setRASelection(aggSel.getRa());	
		            	ra = aggSel.getRa();
		            	dlgPM.setRVSelection(aggSel.getRv());
		            	rv = aggSel.getRv();
		            	dlgPM.setLVSelection(aggSel.getLv());
		            	lv = aggSel.getLv();
		           
		          checkChambers();
		            //Manufacturer m = (Manufacturer) dlgPM.getManufacturerModel().getSelectedItem();
		           
//		            for(Manufacturer m: manufacturers) {
//		            	if (pmSel.getManufacturer().getNotation().equals(m.getNotation())) {
//		            		 dlgPM.getManufacturerModel().setSelectedItem(m);
//		            	}
//		            }
		           
//		            pmSel.setId((Integer) dlgPM.getTblPM().getValueAt(row, 0));
//		            pmSel.setManufacturer(manufacturer);(dlgManufacturer.getTblManufacturer().getValueAt(row, 2).toString());
//		            manufacturer.setMobile(dlgManufacturer.getTblManufacturer().getValueAt(row, 3).toString());
//		            //fill text fields with data of selected manufacturer
	//	            dlgPM.getTNotation().setText(manufacturer.getNotation());
//		            dlgManufacturer.getTxtContact().setText(manufacturer.getContact_person());
//		            dlgManufacturer.getFtxtMobile().setValue(manufacturer.getMobile());
		        }    	           
	        }
			
		}
		
	}
	
	class MenuItemPMListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == dlgPM.getMntmPM()) {
				//Show tblPacemaker
				icd = false;
				
				tblPMKindModel.setAggregats(SQL_SELECT.pacemakerKinds());
				//tblPMKindModel = new TblPMKindModel(SQL_SELECT.pacemakerKinds());
				tblPMKindModel.fireTableStructureChanged();
				dlgPM.clearSelectionOfTbl();
				dlgPM.getMntmICD().setEnabled(true);
				dlgPM.getMntmPM().setEnabled(false);
				
			}else if(event.getSource() == dlgPM.getMntmICD()){
				//show TblICD
				icd = true;
				
				tblPMKindModel.setAggregats(SQL_SELECT.ICD_Kinds());
			//	tblPMKindModel = new TblPMKindModel(SQL_SELECT.ICD_Kinds());
				tblPMKindModel.fireTableStructureChanged();
				dlgPM.clearSelectionOfTbl();
				dlgPM.getMntmICD().setEnabled(false);
				dlgPM.getMntmPM().setEnabled(true);
			};
			
			
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
				dlgPM.clearSelectionOfTbl();				
				dlgPM.getBtnCreate().setText("Änderungen übernehmen");
			}else {
				abstractButton.setText("Neu");
				modeChange = false;
				dlgPM.clearSelectionOfTbl();
				dlgPM.getBtnCreate().setText("Hinzufügen");
				dlgPM.clearCbxType();
				ra = false; rv = false; lv = false;
				dlgPM.getCbxType().setSelectedIndex(-1);
			}
		}
		
	}
	
}
