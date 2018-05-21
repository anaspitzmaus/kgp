package com.rose.kgp.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JToolBar;

public class Pnl_MenuMain extends JPanel {

	
	private static final long serialVersionUID = 6751573636779076145L;
	JMenuItem itemDoctor, itemNurse;
	/**
	 * Create the panel.
	 */
	public Pnl_MenuMain() {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar);
		
		JToolBar toolBar = new JToolBar();
		add(toolBar);
		
		JMenu mPersonal = new JMenu("Personal");
		menuBar.add(mPersonal);
		
		itemDoctor = new JMenuItem("Ärzte");
		mPersonal.add(itemDoctor);
		
		itemNurse = new JMenuItem("Schwestern");
		mPersonal.add(itemNurse);
	}
	
	protected void addDoctorListener(ActionListener l){
		itemDoctor.addActionListener(l);
	}
	
	protected void addNurseListener(ActionListener l){
		itemNurse.addActionListener(l);
	}

}
