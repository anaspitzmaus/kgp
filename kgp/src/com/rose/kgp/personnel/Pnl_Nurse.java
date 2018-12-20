package com.rose.kgp.personnel;

import com.rose.kgp.ui.Pnl_SetDate;



public class Pnl_Nurse extends Pnl_Staff{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8199762126164526397L;

	/**
	 * Create the panel.
	 */
	public Pnl_Nurse() {
		add(getLblId(), "cell 0 0,alignx left");
		add(getTxtId(), "cell 1 0,alignx left");		
		add(getLblSex(), "cell 0 1,alignx left");		
		add(getComboSex(), "cell 1 1,alignx left");
		add(getLblSurname(), "cell 0 2,alignx left");		
		
		add(getTxtSurname(), "cell 1 2,growx");			
		
		add(getLblFirstname(), "cell 0 3,alignx left");		
		
		add(getTxtFirstname(), "cell 1 3,growx");		
		
		add(getLblBirth(), "cell 0 4");			
		
		add(getLblOnsetDate(), "cell 0 5");		
		
		add(getLblAlias(), "cell 0 6,alignx left");		
		
		add(getTxtAlias(), "cell 1 6,growx");		
		
		add(getBtnSetStaff(), "cell 1 7,alignx right");
	}

	@Override
	protected void addBirthDatePanel(Pnl_SetDate datePanel){		
		if(!(birthPanel instanceof Pnl_SetDate)){
			this.birthPanel = datePanel;
			add(datePanel, "cell 1 4,growx,aligny top");
		}
	}
	
	@Override
	protected void addOnsetDatePanel(Pnl_SetDate datePanel){
		if(!(onsetPanel instanceof Pnl_SetDate)){
			this.onsetPanel = datePanel;
			add(datePanel, "cell 1 5,growx,aligny top");
		}
	}

}
