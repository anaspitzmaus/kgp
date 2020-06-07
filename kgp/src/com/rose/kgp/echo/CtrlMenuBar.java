package com.rose.kgp.echo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CtrlMenuBar {

	protected MenuBar menuBar;
	protected SettingsListener settingsListener;
	protected CtrlDlgSettings ctrlDlgSettings;
	
	
	//setters and getters
	
	public MenuBar getMenuBar() {
		return menuBar;
	}

	protected void addCreateTextListener(ActionListener l) {
		menuBar.addCreateTextListener(l);
	}

	public CtrlMenuBar() {
		menuBar = new MenuBar();
		setListener();
	}
	
	private void setListener() {
		settingsListener = new SettingsListener();
		menuBar.addSettingsListener(settingsListener);
	}
	
	class SettingsListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			ctrlDlgSettings = new CtrlDlgSettings();
			ctrlDlgSettings.showDialog();
			
		}
		
	}
	
	
}
