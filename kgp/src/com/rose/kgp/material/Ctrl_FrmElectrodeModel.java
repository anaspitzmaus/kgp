package com.rose.kgp.material;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;

import com.rose.kgp.db.DB;
import com.rose.kgp.db.Dlg_DBSettings;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;

public class Ctrl_FrmElectrodeModel {

	FrmElectrodeModel frame;
	private Boolean changeMode = false; //false means new electrode, true means: changing an existing electrode
	ModeChangeListener modeChangeListener;
	FixModeListener fixModeListener;
	ElectrodeModel electrodeModel;
	NotationListener notationListener;
	ManufacturerModel manufacturerModel;
	ManufacturerRenderer manufacturerRenderer;
	ManufacturerListener manufacturerListener;
	LengthListener lengthListener;
	MRIListener mriListener;
	NoticeListener noticeListener;
	CreateListener createListener;
	TblElectrodesModel tblElectrodesModel;
	TblElectrodeModelIDRenderer tblElectrodeModelIDRenderer;
	TblElectrodeModelEMRenderer tblElectrodeModelEMRenderer;
	TblElectrodeModelStringRenderer tblElectrodeModelStringRenderer;
	TblElectrodeModelBooleanRenderer tblElectrodeModelBooleanRenderer;
	
	public static void main(String[] args) {
		connectDB();
		if (DB.getConnection() != null) {
			new Ctrl_FrmElectrodeModel();
		}
	}
	
	public Ctrl_FrmElectrodeModel() {
		modeChangeListener = new ModeChangeListener();
		fixModeListener = new FixModeListener();
		notationListener = new NotationListener();		
		manufacturerModel = new ManufacturerModel(SQL_SELECT.manufacturers());
		manufacturerRenderer = new ManufacturerRenderer();
		manufacturerListener = new ManufacturerListener();
		lengthListener = new LengthListener();
		mriListener = new MRIListener();
		noticeListener = new NoticeListener();
		tblElectrodesModel = new TblElectrodesModel(SQL_SELECT.electrodeModels());
		tblElectrodeModelIDRenderer = new TblElectrodeModelIDRenderer();
		tblElectrodeModelEMRenderer = new TblElectrodeModelEMRenderer();
		tblElectrodeModelStringRenderer = new TblElectrodeModelStringRenderer();
		tblElectrodeModelBooleanRenderer = new TblElectrodeModelBooleanRenderer();
		createListener = new CreateListener();
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new FrmElectrodeModel();
					frame.setVisible(true);					
					frame.addModeChangeListener(modeChangeListener);
					frame.setTglModeText("Neu");
					frame.addFixModeListener(fixModeListener);
					frame.setTglFixtext("Schraube");
					frame.addLengthListener(lengthListener);
					frame.addNotationListener(notationListener);
					frame.setManufacturerModel(manufacturerModel);
					frame.setManufacturerRender(manufacturerRenderer);
					frame.addManufacturerListener(manufacturerListener);
					frame.addMRIListener(mriListener);
					frame.addNoticeListener(noticeListener);
					frame.addElectrodesModelTableModel(tblElectrodesModel);
					frame.setTblElectrodeModelIDRenderer(tblElectrodeModelIDRenderer);
					frame.setTblElectrodeModelEMRenderer(tblElectrodeModelEMRenderer);
					frame.setTblElectrodeModelStringRenderer(tblElectrodeModelStringRenderer);
					frame.setTblElectrodeModelBooleanRenderer(tblElectrodeModelBooleanRenderer);
					frame.addCreateListener(createListener);
					electrodeModel = new ElectrodeModel("");
					electrodeModel.setFixMode("Schraube");
					electrodeModel.setMri(false);
					electrodeModel.setLength((Integer) frame.getSpinLengthModel().getValue());
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	void clearSelectionOfTbl() {
		
	}
	
	
	class ModeChangeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        if(selected) {
				abstractButton.setText("Ändern");
				changeMode = true;
				clearSelectionOfTbl();				
				frame.setBtnAddText("Änderungen übernehmen");
				electrodeModel = null;
			}else {
				abstractButton.setText("Neu");
				changeMode = false;
				clearSelectionOfTbl();
				frame.setBtnAddText("Hinzufügen");
				electrodeModel = new ElectrodeModel("");
			}
		}
		
	}
	
	class FixModeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        if(selected) {
				abstractButton.setText("Anker");						
				electrodeModel.setFixMode("Anker");
			}else {
				abstractButton.setText("Schraube");			
				electrodeModel.setFixMode("Schraube");
			}
			
		}
		
	}
	
	/**
	 * listener for the notation of the new created or selected electrode
	 * @author user2
	 *
	 */
	class NotationListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent event) {
			setText(event);
			
		}

		@Override
		public void insertUpdate(DocumentEvent event) {
			setText(event);
			
		}

		@Override
		public void removeUpdate(DocumentEvent event) {
			setText(event);
			
		}
		
		private void setText(DocumentEvent event) {
	
			try {
				electrodeModel.setNotation(event.getDocument().getText(0, event.getDocument().getLength()));
				
			} catch (BadLocationException e) {
				electrodeModel.setNotation("");
			}
			
		}
		
	}
	
	class ManufacturerModel extends AbstractListModel<Manufacturer> implements ComboBoxModel<Manufacturer>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 2991920514817740412L;
		ArrayList<Manufacturer> manufacturers;
		Manufacturer manufacturerSel;
		Integer indexSel = 0;
		
		public ManufacturerModel(ArrayList<Manufacturer> manufacturers) {
			this.manufacturers = manufacturers;
		}
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Manufacturer getElementAt(int index) {
			// TODO Auto-generated method stub
			return manufacturers.get(index);
		}

		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return manufacturers.size();
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getSelectedItem() {
			// TODO Auto-generated method stub
			return manufacturerSel;
		}

		@Override
		public void setSelectedItem(Object manufacturer) {
			manufacturerSel = (Manufacturer) manufacturer;
			
		}
		
		protected ArrayList<Manufacturer> getManufacturers(){
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
			if(value instanceof Manufacturer) {
				setText(((Manufacturer) value).getNotation());
			}else {
				setText("");
				
			}
			return this;
		}
		
	}
	
	class ManufacturerListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			try {
				electrodeModel.setManufacturer((Manufacturer) event.getItem());
			} catch (NullPointerException e) {
				electrodeModel.setManufacturer(null);
			}
			
		}
		
	}
	
	class LengthListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent event) {
			electrodeModel.setLength((Integer) frame.getSpinLengthModel().getValue());
			
		}
		
	}
	
	class MRIListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			electrodeModel.setMri(((JCheckBox)event.getSource()).isSelected());			
		}		
	}
	
	class NoticeListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			setText(e);	
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			setText(e);			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			setText(e);			
		}
		
		private void setText(DocumentEvent e) {
			try {
				electrodeModel.setNotice(e.getDocument().getText(0, e.getDocument().getLength()));
			} catch (BadLocationException e1) {
				electrodeModel.setNotice("");
			}
		}
	}
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(electrodeModel.getNotation() != "" && electrodeModel.getManufacturer() instanceof Manufacturer) {
				SQL_INSERT.electrodeModel(electrodeModel);
			}
		}
		
	}
	
	class TblElectrodesModel extends AbstractTableModel{		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -5397061877255389159L;
		protected ArrayList<String> columnNames;
		ArrayList<ElectrodeModel> electrodeModels;
		PM_Type type;
		
		public TblElectrodesModel(ArrayList<ElectrodeModel> electrodeModels) {
			this.electrodeModels = electrodeModels;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Bezeichnung");
			columnNames.add("Hersteller");
			columnNames.add("Länge");
			columnNames.add("Fixierung");
			columnNames.add("MRI");
			columnNames.add("Anmerkung");
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
			return this.electrodeModels.size();
		}
		
		@Override
		public Class getColumnClass(int col) {
			return getValueAt(0, col).getClass();
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			
			
				switch(columnIndex) {
				case 0: return electrodeModels.get(rowIndex);
				
				case 1: return electrodeModels.get(rowIndex).getNotation();
				
				case 2: return electrodeModels.get(rowIndex).getManufacturer().getNotation();
				
				case 3: return electrodeModels.get(rowIndex).getLength();
				
				case 4: return electrodeModels.get(rowIndex).getFixMode();
				
				case 5: return electrodeModels.get(rowIndex).getMri();

				case 6: return electrodeModels.get(rowIndex).getNotice();
				
				default: return null;
				
				}	
		}
			
	}
	
class TblElectrodeModelEMRenderer extends JLabel implements TableCellRenderer{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7893845222434501963L;

	public TblElectrodeModelEMRenderer() {
		super.setOpaque(true);
	}
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int column,
			int row) {
		Integer id = ((ElectrodeModel) value).getId();
		setText(id.toString());
		if(isSelected) {
			setBackground(Color.ORANGE);
		}else {
			setBackground(Color.WHITE);
		}
		return this;
	}
}

class TblElectrodeModelIDRenderer extends JLabel implements TableCellRenderer{

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 8941880047794362140L;
		public TblElectrodeModelIDRenderer() {
			super.setOpaque(true);
		}
		
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

class TblElectrodeModelStringRenderer extends JLabel implements TableCellRenderer{

	
	
		/**
	 * 
	 */
	private static final long serialVersionUID = 3150560739954111797L;

		public TblElectrodeModelStringRenderer() {
			super.setOpaque(true);
		}
		
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

class TblElectrodeModelBooleanRenderer extends JCheckBox implements TableCellRenderer{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -7591172567469251176L;

	public TblElectrodeModelBooleanRenderer() {
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

	private static void connectDB() {
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
}
