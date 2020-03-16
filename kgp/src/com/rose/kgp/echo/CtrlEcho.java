package com.rose.kgp.echo;

public class CtrlEcho {

	FrmEcho frmEcho;
	CtrlPnlMMAoLA ctrlPnlMMAoLA;
	CtrlPnlMMLV ctrlPnlMMLV;
	
	public CtrlEcho() {
		buildFrame();
	}
	
	
	protected void buildFrame() {
		
		ctrlPnlMMAoLA = new CtrlPnlMMAoLA();
		ctrlPnlMMLV = new CtrlPnlMMLV();
		frmEcho = new FrmEcho(ctrlPnlMMAoLA.getPanel(), ctrlPnlMMLV.getPanel());
		
		frmEcho.setVisible(true);
	}

}
