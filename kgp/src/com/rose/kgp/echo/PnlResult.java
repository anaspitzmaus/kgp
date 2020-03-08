package com.rose.kgp.echo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;

public class PnlResult extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6761246251438756576L;

	/**
	 * Create the panel.
	 */
	public PnlResult() {
		setLayout(new BorderLayout(0, 0));
		
		JTextArea textArea = new JTextArea();
		add(textArea, BorderLayout.CENTER);

	}

}
