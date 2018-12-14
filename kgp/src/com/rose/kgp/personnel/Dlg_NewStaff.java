package com.rose.kgp.personnel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

abstract class Dlg_NewStaff extends JDialog {

	private static final long serialVersionUID = 8526325718327302007L;
	private final JPanel contentPanel = new JPanel();
	private Pnl_Staff pnlStaff;
		
	
	protected Pnl_Staff getPnlStaff() {
		return pnlStaff;
	}

	/**
	 * Create the dialog.
	 */
	public Dlg_NewStaff(Pnl_Staff pnlStaff) {
		this.pnlStaff = pnlStaff;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		getContentPane().add(this.pnlStaff, BorderLayout.CENTER);
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
