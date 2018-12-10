package com.rose.kgp.personnel;


import java.awt.Component;
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
import com.rose.kgp.ui.Pnl_SetDate;

/**
 * abstract class that controls an inherited panel of the abstract panel 'Pnl_NewStaff' 
 * @author Ekkehard Rose
 *
 */
public abstract class Ctrl_PnlStaff {

	protected Pnl_SetDate pnlSetBirthDate, pnlSetOnsetDate;	
	protected Pnl_Staff panel;
	protected Staff staff; 
	

	
//	protected Pnl_Staff getPanel(){
//		return this.panel;
//	}
	
//	protected Pnl_SetDate getPnlSetBirthDate(){
//		return this.pnlSetBirthDate;
//	}
//	
//	protected Pnl_SetDate getPnlSetOnsetDate(){
//		return this.pnlSetOnsetDate;
//	}
	
	
	
	
	
	/**
	 * standard constructor
	 * @param ctrlPnlSetBirthDate
	 * @param ctrlPnlSetOnsetDate
	 * @param panel the JPanel that has to be controlled by this controller class
	 */
	public Ctrl_PnlStaff() {
		staff = null;			
		

//		initializeListeners();
//		setListener();
	}
	
//	private void initializeListeners(){
//		surnameListener = new SurnameListener();
//		firstnameListener = new FirstnameListener();
//		aliasListener = new AliasListener();
//		
//	}
	
	/**
	 * add all listeners of input fields of that panel
	 */
//	protected void setListener(){
//		
//		panel.addSurnameListener(surnameListener);
//		panel.addFirstnameListener(firstnameListener);		
//		
//		panel.addAliasListener(aliasListener);
//		
//	}
//	
//	/**
//	 * removes all listeners of input fields of that panel
//	 */
//	protected void removeListener(){
//		panel.removeSurnameListener(surnameListener);
//		panel.removeFirstnameListener(firstnameListener);
//		panel.removeAliasListener(aliasListener);		
//			
//	}
	
//	protected void addPnlSetOnsetDate(Pnl_SetDate pnlSetOnsetDate){
//		if(this.pnlSetOnsetDate instanceof Pnl_SetDate){
//			this.pnlSetOnsetDate = pnlSetOnsetDate;
//			panel.add(this.pnlSetOnsetDate, "cell 1 4,growx,aligny top");
//		}
//	}
//	
//	protected void addPnlSetBirthDate(Pnl_SetDate pnlSetBirthDate){
//		if(this.pnlSetBirthDate instanceof Pnl_SetDate){
//			this.pnlSetBirthDate = pnlSetBirthDate;
//			panel.add(this.pnlSetBirthDate, "cell 1 5,growx,aligny top");
//		}
//	}
	
	/**
	 * prepares the panel before a new staff is to be created
	 * therefore the data fields are enabled and the right dates are set
	 */
	protected void prepareForNewStaff(Staff staff){
		panel.getTxtFirstname().setEnabled(true);
		panel.getTxtSurname().setEnabled(true);
		panel.getComboSex().setEnabled(true);
		panel.getTxtAlias().setEnabled(true);
		panel.getBtnSetStaff().setEnabled(true);
//		this.ctrlPnlSetBirthDate.setPnlEnabled(true);
//		this.ctrlPnlSetOnsetDate.setPnlEnabled(true);
		//when activating the dateSetPnl: set the Date of the DateSetPanel to a specific date
//		this.ctrlPnlSetBirthDate.setDate(LocalDate.now().minusYears(40));
//		this.ctrlPnlSetOnsetDate.setDate(LocalDate.now());
		panel.getTxtSurname().setText("");
		panel.getTxtFirstname().setText("");
		panel.getTxtAlias().setText("");
		panel.getComboSex().setSelectedItem(null);
		panel.getComboSex().repaint();
		this.staff = staff;
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
