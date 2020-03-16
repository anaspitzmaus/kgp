package com.rose.kgp.echo;

public class CtrlPnlMMLV {
	protected PnlMMLV pnlMMLV;
	
	//getter
	protected PnlMMLV getPanel(){
		return pnlMMLV;
	}
	
	//constructor
	public CtrlPnlMMLV() {
		pnlMMLV = new PnlMMLV();
	}
}
