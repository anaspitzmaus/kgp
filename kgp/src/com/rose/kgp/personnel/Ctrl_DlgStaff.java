package com.rose.kgp.personnel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;


import com.rose.kgp.ui.Ctrl_PnlSetDate;
import com.rose.kgp.useful.DateMethods;
import com.rose.kgp.useful.MyColor;

abstract class Ctrl_DlgStaff {

	protected Dlg_Staff dialog;
	protected Tbl_PersonnelModel tblPersonnelModel;
	protected ArrayList<? extends Staff> staffMembers;
	protected Boolean newStaff = false;
	protected enum Modus {NEW, UPDATE};
	protected Modus modus;
	protected Staff staffMember; //the selected staffMember
	private Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	SexModel sexModel;
	SexComboRenderer sexComboRenderer;
	SexListener sexListener;
	
	
	
	protected Ctrl_PnlSetDate getCtrlPnlSetBirthDate() {
		return ctrlPnlSetBirthDate;
	}

	protected Ctrl_PnlSetDate getCtrlPnlSetOnsetDate() {
		return ctrlPnlSetOnsetDate;
	}

		
	public Ctrl_DlgStaff() {
		ctrlPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
		ctrlPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));		
		sexModel = new SexModel();
		sexComboRenderer = new SexComboRenderer();
		sexListener = new SexListener();
	}
	
	public void showDialog(){
		if(this.dialog instanceof Dlg_Staff){
			setFieldsDisabled();
			dialog.repaint();
			dialog.setModal(true);
			dialog.setVisible(true);			
		}
	}
	
	protected void setFieldsDisabled(){
		dialog.getPnlStaff().getComboSex().setEnabled(false);
		dialog.getPnlStaff().getTxtAlias().setEnabled(false);
		dialog.getPnlStaff().getTxtFirstname().setEnabled(false);
		dialog.getPnlStaff().getTxtSurname().setEnabled(false);
		dialog.getPnlStaff().getTxtId().setEnabled(false);
		this.ctrlPnlSetBirthDate.setPnlEnabled(false);
		this.ctrlPnlSetOnsetDate.setPnlEnabled(false);
	}
	
	abstract void setListener();
		
	
	
	abstract void removeListener();
	
	/**
	 * displays the selected staff member (selected in the table)
	 * @param staffMember
	 */
	protected void showSelectedStaff(Staff staffMember) {
		this.staffMember = staffMember;
		//enable all textFields and dropDownFields
		dialog.getPnlStaff().getComboSex().setEnabled(true);		
		dialog.getPnlStaff().getTxtSurname().setEnabled(true);
		dialog.getPnlStaff().getTxtFirstname().setEnabled(true);	
		dialog.getPnlStaff().getTxtAlias().setEnabled(true);
		dialog.getPnlStaff().getBtnSetStaff().setEnabled(true);
		
		
		//check the title of the selected physician and display at the comboBox
		for(int i = 0; i<sexModel.getSize(); i++){
			if(sexModel.getElementAt(i).equals(staffMember.getSex())){
				dialog.getPnlStaff().getComboSex().setSelectedIndex(i);
			}
		}	
		dialog.getPnlStaff().getTxtId().setText(staffMember.getId().toString());
		dialog.getPnlStaff().getTxtSurname().setText(staffMember.getSurname());
		dialog.getPnlStaff().getTxtFirstname().setText(staffMember.getFirstname());	
		dialog.getPnlStaff().getTxtAlias().setText(staffMember.getAlias());
		//ctrlPnlNewStaff.ctrlPnlSetOnsetDate.setDate(staffMember.getOnset());		
	}
	
	/**
	 * set the modus of the Dialog 
	 * modus can be to create a new staff member (NEW)
	 * or to update an already existing staff member (UPDATE)
	 * @param modus
	 */
	protected void setModus(Modus modus) {
		this.modus = modus;		
	}
	
	@SuppressWarnings("unchecked")
	public void addStaff(Staff staff) {
		((ArrayList<Staff>)tblPersonnelModel.getStaff()).add((Staff)staff);
		tblPersonnelModel.fireTableDataChanged();
		
	}
	/**
	 * listener class for selecting a table row
	 * @author Ekkehard Rose
	 *
	 */
	class TblRowSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			if(dialog.getTblPersonnel().getSelectedRow() >= 0){				
				Staff staffSel = (Staff) dialog.getTblPersonnel().getModel().getValueAt(dialog.getTblPersonnel().getSelectedRow(), 0);
				showSelectedStaff(staffSel);
				setModus(Modus.UPDATE);			
			 }
			
		}	
		
	}
	
	/**
	 * renderer for the columns that show a date
	 * @author Ekkehard Rose
	 *
	 */
	class ColumnDateRenderer extends DefaultTableCellRenderer {
		
		private static final long serialVersionUID = 3498636126051341094L;
		SimpleDateFormat simpleDateFormat;
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			 
			 simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			 if(value != null){
				 value = simpleDateFormat.format(DateMethods.ConvertLocalDateToDate((LocalDate)value));
			 }			 
			 return super.getTableCellRendererComponent(table, value, isSelected,
		                hasFocus, row, column);
		}
	}
	
	abstract class SetNewStaffListener implements ActionListener{
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
		}
		/**
		 * check if data of new staff member are valid for being stored in database
		 * a new staff member must not have an id and need to have a valid surname and a valid firstname
		 * @return true if data are valid, false if not
		 */
		protected Boolean dataReadyToStore(){
			if(checkSurname() && checkFirstname()){//if a new staff member has to be added
				staffMember.setBirthday(getCtrlPnlSetBirthDate().getDate());
				staffMember.setOnset(getCtrlPnlSetOnsetDate().getDate());				
				return true;					
			}else{
				return false;
			}
		}
		/**
		 * check if surname of the new staff member is valid (need to have at least 1 character)
		 * @return true if surname is valid, else return false
		 */
		private Boolean checkSurname(){
			if(staffMember.getSurname().length() > 0){
				return true;
			}
			MyColor myColor = MyColor.RED;
			dialog.getPnlStaff().getTxtSurname().setBackground(new Color(myColor.getR(), myColor.getG(), myColor.getB()));
			return false;
			
		}
		
		/**
		 * check if firstname of the new staff member is valid (need to have at least 1 character)
		 * @return true if firstname is valid, else return false
		 */
		private Boolean checkFirstname(){
			if(staffMember.getFirstname().length() > 0){
				return true;
			}
			MyColor myColor = MyColor.RED;
			dialog.getPnlStaff().getTxtFirstname().setBackground(new Color(myColor.getR(), myColor.getG(), myColor.getB()));
			return false;
			
		}		
		
	}
	
	/**
	 * model for the comboBox that shows the sex of the staff member
	 * @author Ekkehard Rose
	 *
	 */
	class SexModel extends AbstractListModel<Sex> implements ComboBoxModel<Sex>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -5723909430414256587L;
		
		Sex selection = null;
		List<Sex> sexList;
		
		public SexModel() {
			sexList = Arrays.asList(Sex.values());			
		}
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub			
		}

		@Override
		public Sex getElementAt(int index) {
			return sexList.get(index);
		}

		@Override
		public int getSize() {
			return sexList.size();
		}

		@Override
		public void removeListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public Object getSelectedItem() {
			return selection;
		}

		@Override
		public void setSelectedItem(Object sex) {
			selection= (Sex) sex;
			
		}
		
	}
	
	/**
	 * renderer for the comboBox that displays the sex
	 * @author Administrator
	 *
	 */
	class SexComboRenderer implements ListCellRenderer<Sex>{
		protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
		
		@Override
		public Component getListCellRendererComponent(
				JList<? extends Sex> list, Sex value, int index,
				boolean isSelected, boolean cellHasFocus) {
			// TODO Auto-generated method stub
			JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
			        isSelected, cellHasFocus);
			if(value instanceof Sex){
				switch(value){
				case FEMALE:
					renderer.setText("Frau");
					break;
				case MALE:
					renderer.setText("Herr");
					break;
				case INDIFFERENT:
					renderer.setText("Indifferent");
					break;
				case NOT_KNOWN:
					renderer.setText("unbekannt");
					break;
				default:
					renderer.setText("unbekannt");
					break;
				}
			}else{//if value is null
				renderer.setText("");
			}
			return renderer;
		}

		

			
		
	}
	
	/**
	 * listener for setting the sex of the staff member depending on its address 
	 * @author Ekkehard Rose
	 *
	 */
	class SexListener implements ItemListener{

		@SuppressWarnings("unchecked")
		@Override
		public void itemStateChanged(ItemEvent evt) {
			JComboBox<Sex> comboSex;
			if(evt.getSource() instanceof JComboBox<?>){
				comboSex = (JComboBox<Sex>) evt.getSource();
				
				try{
					switch ((Sex) comboSex.getModel().getSelectedItem()) {
					case FEMALE:
						staffMember.setSexCode(1);
						break;				
					case MALE:
						staffMember.setSexCode(2);
						break;
					case INDIFFERENT:
						staffMember.setSexCode(0);
						break;
					default:
					staffMember.setSexCode(9);
					break;
					}	
				}catch(NullPointerException e){
					staffMember.setSexCode(9);
				}
							
			}			
		}		
	}
	
	
	
}
