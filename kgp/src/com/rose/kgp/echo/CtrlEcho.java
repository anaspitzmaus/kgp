package com.rose.kgp.echo;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.rose.heart.construct.Normal_Heart;
import com.rose.kgp.personnel.Patient;
import com.rose.kgp.personnel.Sex;


public class CtrlEcho {

	FrmEcho frmEcho;
	CtrlPnlMMAoLA ctrlPnlMMAoLA;
	CtrlPnlMMLV ctrlPnlMMLV;
	CtrlPnlRV ctrlPnlRV;
	CtrlMenuBar ctrlMenuBar;
	CtrlPnlResult ctrlPnlResult;
	Normal_Heart heart, heartSys, heartDia;
	Patient patient;
	Text text;
	CreateTextListener txtListener;
	
	
	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public CtrlEcho() {
		
		setPatient(new Patient("Test", "Test"));
		getPatient().setSex(Sex.FEMALE);
		heart = new Normal_Heart();			
		heart.build();
		heart.createDiastolicState();
		heart.createSystolicState();
		
		text = new Text(heart, patient);
		
		buildFrame();
		setListener();
		
		
		
	}
	
	private void setListener() {
		txtListener = new CreateTextListener();
		ctrlMenuBar.addCreateTextListener(txtListener);
	}
	
	protected void buildFrame() {
		
		ctrlPnlMMAoLA = new CtrlPnlMMAoLA(heart);
		ctrlPnlMMLV = new CtrlPnlMMLV(heart);
		ctrlPnlRV = new CtrlPnlRV(heart);
		ctrlMenuBar = new CtrlMenuBar();
		ctrlPnlResult = new CtrlPnlResult();
		frmEcho = new FrmEcho(ctrlPnlMMAoLA.getPanel(), ctrlPnlMMLV.getPanel(), ctrlPnlRV.getPanel(), ctrlMenuBar.getMenuBar(), ctrlPnlResult.getPanel());
		
		frmEcho.setVisible(true);
	}
	
	class CreateTextListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			text.setCavities();
			
		}
		
	}

}
