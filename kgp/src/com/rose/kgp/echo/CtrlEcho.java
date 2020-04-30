package com.rose.kgp.echo;


import com.rose.heart.construct.Normal_Heart;
import com.rose.kgp.personnel.Patient;


public class CtrlEcho {

	FrmEcho frmEcho;
	CtrlPnlMMAoLA ctrlPnlMMAoLA;
	CtrlPnlMMLV ctrlPnlMMLV;
	CtrlPnlRV ctrlPnlRV;
	CtrlMenuBar ctrlMenuBar;
	Normal_Heart heart, heartSys, heartDia;
	Patient patient;
	Text text;
	
	
	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public CtrlEcho() {
		
		
		heart = new Normal_Heart();			
		heart.build();
		heart.createDiastolicState();
		heart.createSystolicState();
		
		
		buildFrame();
		ctrlPnlMMLV.setIVSSys(39.2);
	}
	
	
	protected void buildFrame() {
		
		ctrlPnlMMAoLA = new CtrlPnlMMAoLA(heart);
		ctrlPnlMMLV = new CtrlPnlMMLV(heart);
		ctrlPnlRV = new CtrlPnlRV(heart);
		ctrlMenuBar = new CtrlMenuBar();
		frmEcho = new FrmEcho(ctrlPnlMMAoLA.getPanel(), ctrlPnlMMLV.getPanel(), ctrlPnlRV.getPanel(), ctrlMenuBar.getMenuBar());
		
		frmEcho.setVisible(true);
	}

}
