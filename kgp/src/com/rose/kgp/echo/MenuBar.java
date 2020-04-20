package com.rose.kgp.echo;

import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuBar;

public class MenuBar extends JMenuBar{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4714953853592532010L;
	private JButton btnSettings;
	
	public MenuBar() {
		btnSettings = new JButton("Einstellungen");
		btnSettings.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		add(btnSettings);
	}
	
	protected void addSettingsListener(ActionListener listener) {
		btnSettings.addActionListener(listener);
	}
	
}
