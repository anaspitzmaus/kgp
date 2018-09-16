package com.rose.kgp.settings;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class DlgPath extends JDialog {

	
	private static final long serialVersionUID = -292577558105000625L;
	private CtrlSetSensisPath ctrlSetSensisPath;
	
	/**
	 * Create the dialog.
	 */
	public DlgPath() {
		setBounds(100, 100, 450, 161);
		getContentPane().setLayout(new BorderLayout());
		
		ctrlSetSensisPath = new CtrlSetSensisPath();			
		getContentPane().add(ctrlSetSensisPath.getPnlSetSensisPath(), BorderLayout.CENTER);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
