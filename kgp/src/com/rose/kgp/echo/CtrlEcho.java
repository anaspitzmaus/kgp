package com.rose.kgp.echo;


import com.rose.heart.construct.Normal_Heart;


public class CtrlEcho {

	FrmEcho frmEcho;
	CtrlPnlMMAoLA ctrlPnlMMAoLA;
	CtrlPnlMMLV ctrlPnlMMLV;
	CtrlMenuBar ctrlMenuBar;
	Normal_Heart heart, heartSys, heartDia;
	
	
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
		ctrlMenuBar = new CtrlMenuBar();
		frmEcho = new FrmEcho(ctrlPnlMMAoLA.getPanel(), ctrlPnlMMLV.getPanel(), ctrlMenuBar.getMenuBar());
		
		frmEcho.setVisible(true);
	}

}
