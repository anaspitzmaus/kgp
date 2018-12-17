package com.rose.kgp.personnel;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.rose.kgp.ui.Ctrl_PnlSetDate;
import com.rose.kgp.useful.MyColor;

abstract class Ctrl_DlgNewStaff {
	protected Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	protected Dlg_NewStaff dialog;
	protected Staff staffMember;
	protected SexListener sexListener;
	protected SurnameListener surnameListener;
	protected FirstnameListener firstnameListener;
	protected AliasListener aliasListener;
	protected SexComboRenderer sexComboRenderer;
	protected SexModel sexModel;
	protected NewStaffMemberListener newStaffMemberListener;
	
	public Ctrl_DlgNewStaff() {
		
		ctrlPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
		ctrlPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
		sexComboRenderer = new SexComboRenderer();
		sexModel = new SexModel();
		
	}
	
	abstract void setListener();
	
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
	
	/**
	 * Document Listener for changing the surname of the staff member
	 * @author Administrator
	 *
	 */
	class SurnameListener implements DocumentListener{
		Staff staff;
		
		public SurnameListener(Staff staff) {
			this.staff = staff;
		}
		
		@Override
		public void changedUpdate(DocumentEvent evt) {
			staff.setSurname(getText(evt));			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			staff.setSurname(getText(evt));			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			staff.setSurname(getText(evt));			
		}
		
		private String getText(DocumentEvent event){
			Document source = event.getDocument();
			int length = source.getLength();
			String txt = "";
			try {
				txt = source.getText(0, length);
			} catch (BadLocationException e) {
				txt = "";
			}
			return txt;
		}
		
	}
	
	/**
	 * Document Listener for changing the firstname of the staff member
	 * @author Administrator
	 *
	 */
	class FirstnameListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent evt) {
			staffMember.setFirstname(getText(evt));			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			staffMember.setFirstname(getText(evt));			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			staffMember.setFirstname(getText(evt));			
		}
		
		private String getText(DocumentEvent event){
			Document source = event.getDocument();
			int length = source.getLength();
			String txt = "";
			try {
				txt = source.getText(0, length);
			} catch (BadLocationException e) {
				txt = "";
			}
			return txt;
		}
		
	}
	
	/**
	 * listener when creating the alias of the staff
	 * the alias is a short form of the full staffs name
	 * like 'Dr. Rose' instead of 'Dr. med. Ekkehard Rose'
	 * @author Ekkehard Rose
	 *
	 */
	class AliasListener implements DocumentListener{
		@Override
		public void changedUpdate(DocumentEvent evt) {
			staffMember.setAlias(getText(evt));			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			staffMember.setAlias(getText(evt));			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			staffMember.setAlias(getText(evt));			
		}
		
		private String getText(DocumentEvent event){
			Document source = event.getDocument();
			int length = source.getLength();
			String txt = "";
			try {
				txt = source.getText(0, length);
			} catch (BadLocationException e) {
				txt = "";
			}
			return txt;
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
					renderer.setText("Divers");
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
	
	class NewStaffMemberListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		/**
		 * check if data of new staff member are valid for being stored in database
		 * a new staff member must not have an id and need to have a valid surname and a valid firstname
		 * @return true if data are valid, false if not
		 */
		protected Boolean dataReadyToStore(){
			if(checkSurname() && checkFirstname()){//if a new staff member has to be added
				staffMember.setBirthday(ctrlPnlSetBirthDate.getDate());
				staffMember.setOnset(ctrlPnlSetOnsetDate.getDate());				
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

	
	
	
}
