package com.rose.kgp.personnel;

import com.rose.kgp.ui.Controller_PnlSetDate;

public class Controller_PnlNewNurse extends Controller_PnlNewStaff{
	
	protected Pnl_NewNurse pnlNewNurse;
	
	protected Pnl_NewNurse getPanel(){
		return this.pnlNewNurse;
	}
	
	public Controller_PnlNewNurse(Controller_PnlSetDate conPnlSetBirthDate, Controller_PnlSetDate conPnlSetOnsetDate) {
		super(conPnlSetBirthDate, conPnlSetOnsetDate, new Pnl_NewNurse());
		pnlNewNurse = new Pnl_NewNurse();
		this.pnlNewNurse.add(this.conPnlSetBirthDate.getPanel(), "cell 1 4,growx,aligny top");
		this.pnlNewNurse.add(this.conPnlSetOnsetDate.getPanel(), "cell 1 5,growx,aligny top");
	}

	public void prepareForNewPhysician() {
		// TODO Auto-generated method stub
		
	}
	
	
}
