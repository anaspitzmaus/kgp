package com.rose.kgp.material;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;

import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.db.SQL_UPDATE;
import com.rose.kgp.examination.Examination;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

public class CtrlPnlElectrodes implements ICtrlPnl{
	PnlElectrodes pnlElectrodes;
	Ctrl_PnlSetDate ctrlPnlSetDate;
	TblElectrodesModel tblElectrodesModel;
	TypeModel typeModel;
	TypeRenderer typeRenderer;
	TypeListener typeListener;
	ElectrodeModel electrodeModel;
	Electrode electrode;
	String serialNr;
	CreateListener createListener;
	Boolean modusChange = false;
	SerialNrListener serialNrListener;
	ModeChangeListener modeChangeListener;
	JToggleButton tglMode;
	JButton btnDelete;
	DeleteListener deleteListener;
	TblElectrodeIDRenderer tblElectrodeIDRenderer;
	TblElectrodeElectrodeRenderer tblElectrodeElectrodeRenderer;
	TblElectrodeDateRenderer tblElectrodeDateRenderer;
	TblElectrodeStringRenderer tblElectrodeStringRenderer;
	TblElectrodesExamRenderer tblElectrodesExamRenderer;
	TblElectrodesModelRenderer tblElectrodesModelRenderer;
	NoticeListener noticeListener;
	TblElectrodeRowSelectionListener tblElectrodeRowSelectionListener;
	
	
	public JPanel getPanel() {
		return pnlElectrodes;
	}
	
	protected TypeModel getTypeModel() {
		return this.typeModel;
	}


	public CtrlPnlElectrodes(JButton btnDelete, JToggleButton tglMode) {
		
		this.tglMode = tglMode;
		this.btnDelete = btnDelete;
		pnlElectrodes = new PnlElectrodes();
		ctrlPnlSetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(0));
		ctrlPnlSetDate.getPanel().setLabelDateText("");
		
		pnlElectrodes.setDatePnl(ctrlPnlSetDate.getPanel());
		pnlElectrodes.setLblExpireDateText("Verfallsdatum");
		pnlElectrodes.setLblModelText("Modell");
		pnlElectrodes.setLblSerialNrText("Seriennummer");
		pnlElectrodes.setLblNoticeText("Anmerkung");
		
		
		
		typeModel = new TypeModel();
		pnlElectrodes.setCbxTypeModel(typeModel);
		typeRenderer = new TypeRenderer();
		pnlElectrodes.setTypeRenderer(typeRenderer);
		typeListener = new TypeListener();
		pnlElectrodes.addTypeListener(typeListener);
		noticeListener = new NoticeListener();
		pnlElectrodes.addNoticeListener(noticeListener);
		
		ArrayList<Electrode> electrodes = SQL_SELECT.electrodes();
		tblElectrodesModel = new TblElectrodesModel(electrodes);
		pnlElectrodes.setTblModel(tblElectrodesModel);
		
		createListener = new CreateListener();
		pnlElectrodes.addCreateListener(createListener);
		
		serialNrListener = new SerialNrListener();
		pnlElectrodes.addSerialNrListener(serialNrListener);
		
		removeModeListener();
		addModeListener();
		changeDeleteListener();
		
		
		tblElectrodeIDRenderer = new TblElectrodeIDRenderer();
		pnlElectrodes.setTblElectrodesIDRenderer(tblElectrodeIDRenderer);
		
		tblElectrodeElectrodeRenderer = new TblElectrodeElectrodeRenderer();
		pnlElectrodes.setTblElectrodesElectrodeRenderer(tblElectrodeElectrodeRenderer);
		
		tblElectrodeDateRenderer = new TblElectrodeDateRenderer();
		pnlElectrodes.setTblElectrodesDateRenderer(tblElectrodeDateRenderer);
		
		tblElectrodeStringRenderer = new TblElectrodeStringRenderer();
		pnlElectrodes.setTblElectrodesStringRenderer(tblElectrodeStringRenderer);
		
		tblElectrodesExamRenderer = new TblElectrodesExamRenderer();
		pnlElectrodes.setTblElectrodesExamRenderer(tblElectrodesExamRenderer);
		
		tblElectrodesModelRenderer = new TblElectrodesModelRenderer();
		pnlElectrodes.setTblElectrodesModelRenderer(tblElectrodesModelRenderer);
		
		tblElectrodeRowSelectionListener = new TblElectrodeRowSelectionListener();
		pnlElectrodes.addTblElectrodesRowSelectionListener(tblElectrodeRowSelectionListener);
		
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
	
	
	
	/**
	 * set the initial values of the electrode 
	 */
	private void setBaseline() {
		electrode = new Electrode(null);		
		electrode.setSerialNr("");
		electrode.setNotice("");		
		pnlElectrodes.setBaselineValues(-1);
		electrode.setExpireDate(ctrlPnlSetDate.getDate());
	}
	
	

class TblElectrodesModel extends AbstractTableModel{		
		/**
	 * 
	 */
	private static final long serialVersionUID = -2544338865204914435L;
		protected ArrayList<String> columnNames;
		ArrayList<Electrode> electrodes;
		ElectrodeModel electrodeModel;
		
		public TblElectrodesModel(ArrayList<Electrode> electrodes) {
			this.electrodes = electrodes;
			columnNames = new ArrayList<String>();
			columnNames.add("Id");
			columnNames.add("Modell");
			columnNames.add("Seriennummer");
			columnNames.add("Verfalldatum");
			columnNames.add("Untersuchungsnummer");
			columnNames.add("Notiz");
		}
		
		protected void setElectrodes(ArrayList<Electrode> e) {
			this.electrodes = e;
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
			return this.electrodes.size();
		}
		
		@Override
		public Class getColumnClass(int col) {	
			if(col == 4) {
				return Examination.class;
			}else {
				return getValueAt(0, col).getClass();
			}
			
			
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			switch(columnIndex) {
			case 0: return electrodes.get(rowIndex);
			
			case 1: return electrodes.get(rowIndex).getElectrodeModel();
			
			case 2: return electrodes.get(rowIndex).getSerialNr();
			
			case 3: return electrodes.get(rowIndex).getExpireDate();
			
			case 4: return electrodes.get(rowIndex).getExam();
			
			case 5: return electrodes.get(rowIndex).getNotice();
			
			default: return null;
			
			}			
		}

		protected ArrayList<Electrode> getElectrodes() {
			return this.electrodes;
		}
		
				
		
		
	}

	class TypeModel extends AbstractListModel<ElectrodeModel> implements ComboBoxModel<ElectrodeModel>{
	
		/**
		 * 
		 */
		private static final long serialVersionUID = 8450636692996139410L;
		private ArrayList<ElectrodeModel> electrodeModels;
		private ElectrodeModel electrodeModelSel;
		
		public TypeModel() {
			electrodeModels = SQL_SELECT.electrodeModels();
		}
	
		@Override
		public ElectrodeModel getElementAt(int i) {
			// TODO Auto-generated method stub
			return electrodeModels.get(i);
		}
	
		@Override
		public int getSize() {
			// TODO Auto-generated method stub
			return electrodeModels.size();
		}
	
		@Override
		public Object getSelectedItem() {
			// TODO Auto-generated method stub
			return electrodeModelSel;
		}
		
		public Integer getSelectedIndex() {
			for(int i = 0; i<electrodeModels.size(); i++) {
				if(electrodeModels.get(i).equals(this.getSelectedItem())) {
					return i;
				}
			}
			return -1;
		}
	
		@Override
		public void setSelectedItem(Object e) {
			electrodeModelSel = (ElectrodeModel) e;
			
		}
		
		/**
		 * actualizes the active types of electrodes
		 */
		protected void actualize() {
			electrodeModels = SQL_SELECT.electrodeModels();
		}
		
	}
	
	class TypeRenderer extends JLabel implements ListCellRenderer<ElectrodeModel>{

		/**
		 * 
		 */
		private static final long serialVersionUID = 5282608643108225470L;

		public TypeRenderer() {
			setOpaque(true);
			setHorizontalAlignment(CENTER);
	        setVerticalAlignment(CENTER);
		}
		@Override
		public Component getListCellRendererComponent(JList<? extends ElectrodeModel> list, ElectrodeModel value, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(value instanceof ElectrodeModel) {
				setText(((ElectrodeModel) value).getNotation());
			}else {
				setText("");
				
			}
			return this;
		}
	}	
	
	/**
	 * listener for changing a type of electrode
	 * @author Ekkehard Rose
	 *
	 */
	class TypeListener implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent event) {
			JComboBox<ElectrodeModel> cb = (JComboBox<ElectrodeModel>)event.getSource();
			//ElectrodeModel type = (ElectrodeModel)cb.getSelectedItem();
			ElectrodeModel type = (ElectrodeModel) typeModel.getSelectedItem();
			if(modusChange) {//if modus is for changing or deleting electrodes
				if(type instanceof ElectrodeModel) {
					tblElectrodesModel.setElectrodes(SQL_SELECT.electrodes(type));
				}else {
					tblElectrodesModel.setElectrodes(SQL_SELECT.electrodes());//select all electrodes if no type of electrode is selected
				}
				
			}else{//if modus == new
				try {
					electrode.setElectrodeModel(type);
					tblElectrodesModel.setElectrodes(SQL_SELECT.electrodes(type));					
				} catch (NullPointerException e) {
					
				}
				
			}
			tblElectrodesModel.fireTableDataChanged();
		}
		
	}
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(electrode instanceof Electrode) {
				if(!modusChange) {//for a new electrode
					if(electrode.getElectrodeModel() instanceof ElectrodeModel && electrode.getSerialNr() != "") {
						electrode.setExpireDate(ctrlPnlSetDate.getDate());
						Integer id = SQL_INSERT.electrode(electrode);
						if(id instanceof Integer) {
							electrode.setId(id);						
							tblElectrodesModel.getElectrodes().add(electrode);
							tblElectrodesModel.fireTableRowsInserted(tblElectrodesModel.getRowCount() - 1, tblElectrodesModel.getRowCount() - 1);
						}
					}
				}else {//for changing data of a selected electrode
					electrode.setExpireDate(ctrlPnlSetDate.getDate());
					if(electrode.getId() instanceof Integer  && electrode.getSerialNr() != "") {
						SQL_UPDATE.electrode(electrode);
						Integer rowSel = tblElectrodeRowSelectionListener.getRowSelected();  
						tblElectrodesModel.fireTableRowsUpdated(rowSel, rowSel);
					}
				}
			}
			
		}
		
	}
	
	class SerialNrListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			setSerialNr(e);
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			setSerialNr(e);
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			setSerialNr(e);
		}
		
		private void setSerialNr(DocumentEvent e) {
			try {
				electrode.setSerialNr(e.getDocument().getText(0, e.getDocument().getLength()));
			} catch (BadLocationException e1) {
				electrode.setSerialNr("");
			} catch (NullPointerException e2) {
				//if no electrode is selected
			}
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
				electrode.setNotice(e.getDocument().getText(0, e.getDocument().getLength()));
			} catch (BadLocationException e1) {
				electrode.setNotice("");
			} catch (NullPointerException e2) {
				// if no electrode selected
			}
			tblElectrodesModel.fireTableCellUpdated(pnlElectrodes.getSelectedRow(), 5);
			
		}
	}
	
	
	class ModeChangeListener implements ActionListener{
		
		JButton btnDelete;
		
		public ModeChangeListener(JButton btnDelete) {
			modusChange = false;
			pnlElectrodes.setBtnCreateTxt("Hinzufügen");
			this.btnDelete = btnDelete;
			this.btnDelete.setText("Löschen");
			this.btnDelete.setEnabled(false);
			tglMode.setSelected(false);
			tglMode.setText("Neu");
			setBaseline();
		}
		
		@Override
		public void actionPerformed(ActionEvent event) {
			AbstractButton abstractButton = (AbstractButton) event.getSource();
	        boolean selected = abstractButton.getModel().isSelected();
	        if(selected) {//if change mode
				abstractButton.setText("Ändern");
				modusChange = true;
				pnlElectrodes.setBtnCreateTxt("Änderungen übernehmen");
				electrode = null;
				pnlElectrodes.setBaselineValues(-1);	
				this.btnDelete.setEnabled(true);
			}else {//if mode for creating new electrodes
				abstractButton.setText("Neu");
				modusChange = false;
				pnlElectrodes.setBtnCreateTxt("Hinzufügen");
				setBaseline();
				this.btnDelete.setEnabled(false);
			}
	        tblElectrodesModel.setElectrodes(SQL_SELECT.electrodes());
			tblElectrodesModel.fireTableDataChanged();
		}
		
	}
	
	
	class TblElectrodeRowSelectionListener implements ListSelectionListener{
		Integer rowSel;
		
		protected Integer getRowSelected() {
			return rowSel;
		}
		
		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if (pnlElectrodes.getTblElectrodes().getSelectedRow() > -1) {
				if(modusChange) {
					rowSel = pnlElectrodes.getTblElectrodes().getSelectedRow();
					electrode = (Electrode) pnlElectrodes.getTblElectrodes().getValueAt(rowSel, 0); //get the electrodeModel from the first column
					pnlElectrodes.getTxtSerialNr().setText(electrode.getSerialNr());
					pnlElectrodes.getTxtNotice().setText(electrode.getNotice());
					ctrlPnlSetDate.setDate(electrode.getExpireDate());
					for(int i = 0; i < typeModel.getSize(); i++ ) {//get the model of the selected electrode and display it in the comboBox
			            if(typeModel.getElementAt(i).getNotation().equals(electrode.getElectrodeModel().getNotation())){		            		
			            	 typeModel.setSelectedItem( typeModel.getElementAt(i));		            		
			            	 pnlElectrodes.getCbxModel().repaint();
			            }
			        }
				}			
			}			
		}		
	}

	class DeleteListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			if(electrode instanceof Electrode) {
				Boolean del = SQL_UPDATE.deleteElectrode(electrode);
				if(del) {
					if(tblElectrodesModel.getElectrodes().remove(electrode)) {
						tblElectrodesModel.fireTableRowsDeleted(pnlElectrodes.getTblListSelectionModel().getMinSelectionIndex(), pnlElectrodes.getTblListSelectionModel().getMaxSelectionIndex()); 
						
					}
				}
			}
			pnlElectrodes.setBaselineValues(typeModel.getSelectedIndex());			
			electrodeModel = null;
			
		}
		
	}
	
	class TblElectrodeIDRenderer extends JLabel implements TableCellRenderer{

		
		/**
		 * 
		 */
		private static final long serialVersionUID = -8665813293631886340L;

		/**
		 * 
		 */
		
			public TblElectrodeIDRenderer() {
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
	
	class TblElectrodeElectrodeRenderer extends JLabel implements TableCellRenderer{
		
		private static final long serialVersionUID = 789128078363222838L;

		/**
		 * standard constructor
		 */
		

		public TblElectrodeElectrodeRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int column,
				int row) {
			Integer id = ((Electrode) value).getId();
			setText(id.toString());
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(Color.WHITE);
			}
			return this;
		}
	}
	
	class TblElectrodeDateRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = -7421293031977663350L;

		public TblElectrodeDateRenderer() {
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
	
	class TblElectrodeStringRenderer extends JLabel implements TableCellRenderer{

		
		/**
		 * 
		 */
		private static final long serialVersionUID = -1801580825069899932L;

		public TblElectrodeStringRenderer() {
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
	
	class TblElectrodesExamRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 2903457611273130355L;

		public TblElectrodesExamRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if(value instanceof Examination) {
				setText(((Examination)value).getRefNo().toString());
			}else {
				setText("keine Untersuchung");
			}
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(Color.WHITE);
			}
			return this;
		}
		
	}
	
	class TblElectrodesModelRenderer extends JLabel implements TableCellRenderer{

		/**
		 * 
		 */
		private static final long serialVersionUID = 7583541703836845580L;

		public TblElectrodesModelRenderer() {
			super.setOpaque(true);
		}
		
		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			
			if(value instanceof ElectrodeModel) {
				setText(((ElectrodeModel)value).getNotation());
			}else {
				setText("unbekannt");
			}
			
			if(isSelected) {
				setBackground(Color.ORANGE);
			}else {
				setBackground(Color.WHITE);
			}
			return this;
		}
		
	}
	
	
	
}
