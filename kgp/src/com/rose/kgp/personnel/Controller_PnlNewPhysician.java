package com.rose.kgp.personnel;

import java.time.LocalDate;

import com.rose.kgp.ui.Controller_PnlSetDate;

public class Controller_PnlNewPhysician {
	protected Pnl_NewPhysician pnlNewPhysician;
	
	public Controller_PnlNewPhysician(Controller_PnlSetDate conPnlSetBirthDate, Controller_PnlSetDate conPnlSetOnsetDate) {
		
		pnlNewPhysician = new Pnl_NewPhysician();
		this.pnlNewPhysician.add(conPnlSetBirthDate.getPanel(), "cell 1 3,growx,aligny top");
		this.pnlNewPhysician.add(conPnlSetOnsetDate.getPanel(), "cell 1 4,growx,aligny top");
	}
	
	public Pnl_NewPhysician getPanel(){
		return this.pnlNewPhysician;
	}
	
	
}
