package com.rose.kgp.personnel;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

abstract class Ctrl_DlgNewStaff {
	protected Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	protected Dlg_Staff dialog;
	protected Staff staffMember;
	protected SexListener sexListener;
	protected SurnameListener surnameListener;
	protected FirstnameListener firstnameListener;
	protected AliasListener aliasListener;
	
	
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

		@Override
		public void changedUpdate(DocumentEvent evt) {
			staffMember.setSurname(getText(evt));			
		}

		@Override
		public void insertUpdate(DocumentEvent evt) {
			staffMember.setSurname(getText(evt));			
		}

		@Override
		public void removeUpdate(DocumentEvent evt) {
			staffMember.setSurname(getText(evt));			
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
	
	
}
