package com.rose.kgp.personnel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Observable;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListDataListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import com.rose.kgp.useful.*;

import com.rose.kgp.ui.Controller_PnlSetDate;

/**
 * abstract class that controls an inherited panel of the abstract panel 'Pnl_NewStaff' 
 * @author Ekkehard Rose
 *
 */
public abstract class Controller_PnlNewStaff extends Observable{

	protected Controller_PnlSetDate conPnlSetBirthDate, conPnlSetOnsetDate;
	protected SexModel sexModel;
	protected Pnl_NewStaff pnlNewStaff;
	protected Staff staff; 
	protected SurnameListener surnameListener;
	protected FirstnameListener firstnameListener;
	protected AliasListener aliasListener;
	protected SexListener sexListener;
	
	protected Pnl_NewStaff getPanel(){
		return this.pnlNewStaff;
	}
	
	
	
	/**
	 * standard constructor
	 * @param conPnlSetBirthDate
	 * @param conPnlSetOnsetDate
	 * @param pnlNewStaff the JPanel that has to be controlled by this controller class
	 */
	public Controller_PnlNewStaff(Controller_PnlSetDate conPnlSetBirthDate, Controller_PnlSetDate conPnlSetOnsetDate, Pnl_NewStaff pnlNewStaff) {
		staff = null;
		this.pnlNewStaff = pnlNewStaff;
		this.conPnlSetBirthDate = conPnlSetBirthDate;
		this.conPnlSetOnsetDate = conPnlSetOnsetDate;
		this.pnlNewStaff.getTxtFirstname().setEnabled(false);
		this.pnlNewStaff.getTxtSurname().setEnabled(false);
		this.pnlNewStaff.getComboSex().setEnabled(false);
		sexModel = new SexModel();
		this.pnlNewStaff.getComboSex().setModel(this.sexModel);
		this.pnlNewStaff.getTxtAlias().setEnabled(false);
		this.conPnlSetBirthDate.setPnlEnabled(false);
		this.conPnlSetOnsetDate.setPnlEnabled(false);
		this.pnlNewStaff.getBtnSetStaff().setEnabled(false);
		initializeListeners();
		setListener();
	}
	
	private void initializeListeners(){
		surnameListener = new SurnameListener();
		firstnameListener = new FirstnameListener();
		aliasListener = new AliasListener();
		sexListener = new SexListener();
	}
	
	/**
	 * add all listeners of input fields of that panel
	 */
	protected void setListener(){
		
		pnlNewStaff.addSurnameListener(surnameListener);
		pnlNewStaff.addFirstnameListener(firstnameListener);		
		pnlNewStaff.addSexListener(sexListener);
		pnlNewStaff.addAliasListener(aliasListener);
		
	}
	
	/**
	 * removes all listeners of input fields of that panel
	 */
	protected void removeListener(){
		pnlNewStaff.removeSurnameListener(surnameListener);
		pnlNewStaff.removeFirstnameListener(firstnameListener);
		pnlNewStaff.removeAliasListener(aliasListener);		
		pnlNewStaff.removeSexListener(sexListener);
		
	}
	
	/**
	 * prepares the panel before a new staff is to be created
	 * therefore the data fields are enabled and the right dates are set
	 */
	protected void prepareForNewStaff(Staff staff){
		pnlNewStaff.getTxtFirstname().setEnabled(true);
		pnlNewStaff.getTxtSurname().setEnabled(true);
		pnlNewStaff.getComboSex().setEnabled(true);
		pnlNewStaff.getTxtAlias().setEnabled(true);
		pnlNewStaff.getBtnSetStaff().setEnabled(true);
		this.conPnlSetBirthDate.setPnlEnabled(true);
		this.conPnlSetOnsetDate.setPnlEnabled(true);
		//when activating the dateSetPnl: set the Date of the DateSetPanel to a specific date
		this.conPnlSetBirthDate.setDate(LocalDate.now().minusYears(40));
		this.conPnlSetOnsetDate.setDate(LocalDate.now());
		pnlNewStaff.getTxtSurname().setText("");
		pnlNewStaff.getTxtFirstname().setText("");
		pnlNewStaff.getComboSex().setSelectedItem(null);
		pnlNewStaff.getComboSex().repaint();
		this.staff = staff;
		
	}
	
	/**
	 * model for the comboBox that shows the address of a new physician
	 * @author Ekkehard Rose
	 *
	 */
	class SexModel extends AbstractListModel<String> implements ComboBoxModel<String>{

		/**
		 * 
		 */
		private static final long serialVersionUID = -5723909430414256587L;
		String[] sex = {"Frau", "Herr"};
		String selection = null;
		
		@Override
		public void addListDataListener(ListDataListener arg0) {
			// TODO Auto-generated method stub			
		}

		@Override
		public String getElementAt(int index) {
			return sex[index];
		}

		@Override
		public int getSize() {
			return sex.length;
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
			selection= (String) sex;
			
		}
		
	}
	
	/**
	 * Document Listener for changing the surname of the staff member
	 * @author Administrator
	 *
	 */
	class SurnameListener implements DocumentListener{

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
			staff.setFirstname(getText(evt));			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			staff.setFirstname(getText(evt));			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			staff.setFirstname(getText(evt));			
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
			staff.setAlias(getText(evt));			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			staff.setAlias(getText(evt));			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			staff.setAlias(getText(evt));			
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
	 * listener for setting the sex of the staff member depending on its address 
	 * @author Ekkehard Rose
	 *
	 */
	class SexListener implements ItemListener{

		@SuppressWarnings("unchecked")
		@Override
		public void itemStateChanged(ItemEvent evt) {
			JComboBox<String> comboSex;
			if(evt.getSource() instanceof JComboBox<?>){
				comboSex = (JComboBox<String>) evt.getSource();
				switch ((String) comboSex.getModel().getSelectedItem()) {
				case "Frau":
					staff.setSexCode(1);
					break;				
				case "Herr":
					staff.setSexCode(2);
					break;
				case "":
					staff.setSexCode(0);
					break;
				default:
					staff.setSexCode(9);
					break;
				}				
			}			
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
				staff.setBirthday(conPnlSetBirthDate.getDate());
				staff.setOnset(conPnlSetOnsetDate.getDate());				
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
			if(staff.getSurname().length() > 0){
				return true;
			}
			MyColor myColor = MyColor.RED;
			pnlNewStaff.getTxtSurname().setBackground(new Color(myColor.getR(), myColor.getG(), myColor.getB()));
			return false;
			
		}
		
		/**
		 * check if firstname of the new staff member is valid (need to have at least 1 character)
		 * @return true if firstname is valid, else return false
		 */
		private Boolean checkFirstname(){
			if(staff.getFirstname().length() > 0){
				return true;
			}
			MyColor myColor = MyColor.RED;
			pnlNewStaff.getTxtFirstname().setBackground(new Color(myColor.getR(), myColor.getG(), myColor.getB()));
			return false;
			
		}
		
		
		
	}
	
	
}
