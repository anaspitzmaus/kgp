package com.rose.kgp.material;

import javax.swing.JButton;

public interface ICtrlPnl {
	/**
	 * remove all actionListeners of the Mode Button
	 */
	void removeModeListener();
	
	/**
	 * addActionListener to the Delete-Button
	 * @param btnDelete
	 * 
	 */
	void addModeListener();
	
	/**
	 * removes all Listeners of the button and adds an new listner
	 * @param btnDelete
	 */
	void changeDeleteListener();
}
