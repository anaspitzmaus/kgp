package com.rose.kgp.settings;

import java.awt.BorderLayout;
import javax.swing.JPanel;


import net.miginfocom.swing.MigLayout;


public class PnlSettings extends JPanel {
	
	private static final long serialVersionUID = 1245453464609591333L;
	
	private PnlSetSensisPath pnlSetSensisPath;
	private JPanel pnlCenter;
	
	
	
	public JPanel getPnlCenter() {
		return pnlCenter;
	}



	/**
	 * Create the panel.
	 */
	public PnlSettings() {
		setLayout(new BorderLayout(0, 0));
		
		pnlCenter = new JPanel();
		add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new MigLayout("", "[][][grow]", "[]"));
		
		
		
		
		
		
		
	}

}
