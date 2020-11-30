package com.rose.kgp.material;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;

import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.db.SQL_UPDATE;


public class CtrlPnlElectrodeModel implements ICtrlPnl{

	PnlElectrodeModel pnlElectrodeModel;
	ElectrodeModel electrodeModel;
	FixModeListener fixModeListener;
	ModeChangeListener modeChangeListener;
	TblElectrodesModel tblElectrodesModel;
	JToggleButton tglMode;
	JButton btnDelete;
	Boolean modusChange = false;
	NotationListener notationListener;
	ManufacturerModel manufacturerModel;
	ManufacturerRenderer manufacturerRenderer;
	ManufacturerListener manufacturerListener;
	LengthListener lengthListener;
	MRIListener mriListener;
	NoticeListener noticeListener;
	CreateListener createListener;
	DeleteListener deleteListener; 
	
	TblElectrodeModelIDRenderer tblElectrodeModelIDRenderer;
	TblElectrodeModelEMRenderer tblElectrodeModelEMRenderer;
	TblElectrodeModelStringRenderer tblElectrodeModelStringRenderer;
	TblElectrodeModelBooleanRenderer tblElectrodeModelBooleanRenderer;
	TblEMRowSelectionListener tblEMRowSelectionListener;
	
	/**
	 * constructor
	 * @param btnDelete
	 * @param tglMode
	 */
	public CtrlPnlElectrodeModel(JButton btnDelete, JToggleButton tglMode) {
		this.tglMode = tglMode;
		this.btnDelete = btnDelete;
		pnlElectrodeModel = new PnlElectrodeModel();
		fixModeListener = new FixModeListener();
		modeChangeListener = new ModeChangeListener(btnDelete);
		
		tblElectrodesModel = new TblElectrodesModel(SQL_SELECT.electrodeModels());
		manufacturerModel = new ManufacturerModel(SQL_SELECT.manufacturers());
		manufacturerRenderer = new ManufacturerRenderer();
		tblElectrodeModelIDRenderer = new TblElectrodeModelIDRenderer();
		tblElectrodeModelEMRenderer = new TblElectrodeModelEMRenderer();
		tblElectrodeModelStringRenderer = new TblElectrodeModelStringRenderer();
		tblElectrodeModelBooleanRenderer = new TblElectrodeModelBooleanRenderer();
		
		pnlElectrodeModel.setManufacturerModel(manufacturerModel);
		pnlElectrodeModel.addElectrodesModelTableModel(tblElectrodesModel);
		pnlElectrodeModel.setTblElectrodeModelIDRenderer(tblElectrodeModelIDRenderer);
		pnlElectrodeModel.setTblElectrodeModelEMRenderer(tblElectrodeModelEMRenderer);
		pnlElectrodeModel.setTblElectrodeModelStringRenderer(tblElectrodeModelStringRenderer);
		pnlElectrodeModel.setTblElectrodeModelBooleanRenderer(tblElectrodeModelBooleanRenderer);
		setListener();
		setBaseline();
		removeModeListener();
		addModeListener();
		changeDeleteListener();
		
	}
	
	private void setListener() {
		pnlElectrodeModel.addFixModeListener(fixModeListener);
		notationListener = new NotationListener();	
		manufacturerListener = new ManufacturerListener();
		lengthListener = new LengthListener();
		mriListener = new MRIListener();
		noticeListener = new NoticeListener();
		tblEMRowSelectionListener = new TblEMRowSelectionListener();
		createListener = new CreateListener();
		
		
		pnlElectrodeModel.addLengthListener(lengthListener);
		pnlElectrodeModel.addNotationListener(notationListener);
		
		pnlElectrodeModel.setManufacturerRender(manufacturerRenderer);
		pnlElectrodeModel.addManufacturerListener(manufacturerListener);
		pnlElectrodeModel.addMRIListener(mriListener);
		pnlElectrodeModel.addNoticeListener(noticeListener);
		
		pnlElectrodeModel.addTblEMRowSelectionListener(tblEMRowSelectionListener);
		
		pnlElectrodeModel.addCreateListener(createListener);
		
		
		
	}
	
	@Override
	public void removeModeListener() {
		for(ActionListener l: tglMode.getActionListeners()) {
			tglMode.removeActionListener(l);
		}
		
	}

	@Override
	public void addModeListener() {
		//add a new ActionListener to the Mode Button
		modeChangeListener = new ModeChangeListener(btnDelete);
		tglMode.addActionListener(modeChangeListener);
		
	}
	
	@Override
	public void changeDeleteListener() {
		for(ActionListener l: btnDelete.getActionListeners()) {
			btnDelete.removeActionListener(l);
		}
		deleteListener = new DeleteListener();
		btnDelete.addActionListener(deleteListener);
		
	}

	public JPanel getPanel() {
		return pnlElectrodeModel;
	}
	
	/**
	 * set the initial values of the electrodeModel 
	 */
	private void setBaseline() {
		electrodeModel = new ElectrodeModel("");		
		electrodeModel.setNotice("");			
		electrodeModel.setFixMode("Schraube");
		electrodeModel.setMri(false);
		electrodeModel.setLength((Integer) pnlElectrodeModel.getSpinLengthModel().getValue());		
		electrodeModel.setNotation("");
		
		pnlElectrodeModel.setBaselineValues();
		
	}
	
	class FixModeListener implements ActionListener{

		public FixModeListener() {
			pnlElectrodeModel.setTglFixText("Schraube");			
		}		
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        if(electrodeModel instanceof ElectrodeModel) {
		        if(selected) {
					abstractButton.setText("Anker");						
					electrodeModel.setFixMode("Anker");
				}else {
					abstractButton.setText("Schraube");			
					electrodeModel.setFixMode("Schraube");
				}
		        tblElectrodesModel.fireTableCellUpdated(pnlElectrodeModel.getSelectedRow(), 4);
	        }
		}
		
	}
	
	/**
	 * listener for changing the mode between 'new' (create a new electrode model) and 'change' (to change data of an already existing electrode model
	 * @author user2
	 *
	 */
	class ModeChangeListener implements ActionListener{

		JButton btnDelete;
	
		public ModeChangeListener(JButton btnDelete) {
			modusChange = false;
			this.btnDelete = btnDelete;
			this.btnDelete.setText("Löschen");
			this.btnDelete.setEnabled(false);
			tglMode.setSelected(false);
			tglMode.setText("Neu");
			pnlElectrodeModel.setLblModeText("Neu");
			pnlElectrodeModel.setBtnAddText("Hinzufügen");
			setBaseline();
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        if(selected) {
				abstractButton.setText("Ändern");
				pnlElectrodeModel.setLblModeText("Ändern");
				modusChange = true;
				pnlElectrodeModel.setBtnAddText("Änderungen übernehmen");
				electrodeModel = null;
				pnlElectrodeModel.setBaselineValues();	
				this.btnDelete.setEnabled(true);
			}else {
				abstractButton.setText("Neu");
				pnlElectrodeModel.setLblModeText("Neu");
				modusChange = false;
				pnlElectrodeModel.setBtnAddText("Hinzufügen");
				setBaseline();
				this.btnDelete.setEnabled(false);
			}
	        tblElectrodesModel.setElectrodeModels(SQL_SELECT.electrodeModels());
			tblElectrodesModel.fireTableDataChanged();
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
		
		
		
		protected void setElectrodeModels(ArrayList<ElectrodeModel> electrodeModels) {
			this.electrodeModels = electrodeModels;
		}
		
		protected ArrayList<ElectrodeModel> getElectrodeModels(){
			return this.electrodeModels;
		}

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
			tblElectrodesModel.fireTableCellUpdated(pnlElectrodeModel.getSelectedRow(), 1);
			
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
			tblElectrodesModel.fireTableCellUpdated(pnlElectrodeModel.getSelectedRow(), 2);
		}
		
	}
	
	class LengthListener implements ChangeListener{

		@Override
		public void stateChanged(ChangeEvent event) {
			electrodeModel.setLength((Integer) pnlElectrodeModel.getSpinLengthModel().getValue());
			tblElectrodesModel.fireTableCellUpdated(pnlElectrodeModel.getSelectedRow(), 3);
		}
		
	}
	
	class MRIListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			electrodeModel.setMri(((JCheckBox)event.getSource()).isSelected());	
			tblElectrodesModel.fireTableCellUpdated(pnlElectrodeModel.getSelectedRow(), 5);
			
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
			tblElectrodesModel.fireTableCellUpdated(pnlElectrodeModel.getSelectedRow(), 6);
			
		}
	}
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(electrodeModel.getNotation() != "" && electrodeModel.getManufacturer() instanceof Manufacturer) {
				if(!modusChange) {// in mode 'new'
					SQL_INSERT.electrodeModel(electrodeModel);	
					setBaseline();
				}else {//in mode 'change'
					for(ElectrodeModel model:tblElectrodesModel.electrodeModels) {
						if(model instanceof ElectrodeModel) {
							SQL_UPDATE.electrodeModel(model);						
						}
					}
					pnlElectrodeModel.setBaselineValues();					
					electrodeModel = null;
					
				}
				tblElectrodesModel.setElectrodeModels(SQL_SELECT.electrodeModels());
				tblElectrodesModel.fireTableDataChanged();
				
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

	class TblEMRowSelectionListener implements ListSelectionListener{
	
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (pnlElectrodeModel.getTblElectrodeModel().getSelectedRow() > -1) {
				if(modusChange) {
					int row = pnlElectrodeModel.getTblElectrodeModel().getSelectedRow();
					electrodeModel = (ElectrodeModel) pnlElectrodeModel.getTblElectrodeModel().getValueAt(row, 0); //get the electrodeModel from the first column
					pnlElectrodeModel.getTxtNotation().setText(electrodeModel.getNotation());
					
					for(int i = 0; i < manufacturerModel.getSize(); i++ ) {//get the manufacturer of the selected aggregate and display it in the comboBox
			            if(manufacturerModel.getElementAt(i).getNotation().equals(electrodeModel.getManufacturer().getNotation())){		            		
			            	 manufacturerModel.setSelectedItem( manufacturerModel.getElementAt(i));		            		
			            	 pnlElectrodeModel.getCbxManufacturer().repaint();
			            }
			        }
					
					pnlElectrodeModel.getSpinLengthModel().setValue(electrodeModel.getLength());
					pnlElectrodeModel.getTxtNotice().setText(electrodeModel.getNotice());
					pnlElectrodeModel.setMRI(electrodeModel.getMri());
					pnlElectrodeModel.setFixMode(electrodeModel.getFixMode());
				}
			}
			
		}
		
	}
	
	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(electrodeModel instanceof ElectrodeModel) {
				Boolean del = SQL_UPDATE.deleteElectrodeModel(electrodeModel);
				if(del) {
					if(tblElectrodesModel.getElectrodeModels().remove(electrodeModel)) {
						//System.out.println(frame.getTblListSelectionModel().getMinSelectionIndex());
						tblElectrodesModel.fireTableRowsDeleted(pnlElectrodeModel.getTblListSelectionModel().getMinSelectionIndex(), pnlElectrodeModel.getTblListSelectionModel().getMaxSelectionIndex()); 
					}
				}
			}
			pnlElectrodeModel.setBaselineValues();
			electrodeModel = null;
			
		}
		
	}
	
	


}
