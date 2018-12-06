package com.rose.kgp.personnel;



import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.event.ListDataListener;

import com.rose.kgp.ui.Ctrl_PnlSetDate;
import com.rose.kgp.ui.Pnl_SetDate;

public class Ctrl_PnlPhysician extends Ctrl_PnlStaff{

	
	
	
	private Ctrl_PnlSetDate ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate;
	
	
	protected Ctrl_PnlSetDate getCtrlPnlSetBirthDate() {
		return ctrlPnlSetBirthDate;
	}

	protected Ctrl_PnlSetDate getCtrlPnlSetOnsetDate() {
		return ctrlPnlSetOnsetDate;
	}
	
	public Ctrl_PnlPhysician(Ctrl_PnlSetDate ctrlPnlSetBirthDate, Ctrl_PnlSetDate ctrlPnlSetOnsetDate) {		
		
		this.panel = new Pnl_Physician();
		this.ctrlPnlSetBirthDate = ctrlPnlSetBirthDate;
		this.ctrlPnlSetOnsetDate = ctrlPnlSetOnsetDate;
		//add the date panels to the basic panel
		addPnlSetOnsetDate(this.ctrlPnlSetOnsetDate.getPanel());
		addPnlSetBirthDate(this.ctrlPnlSetBirthDate.getPanel());		
				
		((Pnl_Physician) panel).getComboTitle().setEnabled(false);
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * prepares the panel before a new physician is to be created
	 * therefore the data fields are enabled and the right dates are set
	 */
	protected void prepareForNewPhysician(){
		super.prepareForNewStaff(new Physician(""));
		
		((Pnl_Physician) panel).getComboTitle().setEnabled(true);		
		((Pnl_Physician) panel).getComboTitle().setSelectedItem(null);
		((Pnl_Physician) panel).getComboTitle().repaint();
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
