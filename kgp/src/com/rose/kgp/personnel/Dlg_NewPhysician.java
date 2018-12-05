package com.rose.kgp.personnel;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

public class Dlg_NewPhysician extends JDialog {

	
	private static final long serialVersionUID = 1347322373688853431L;
	private Pnl_Physician pnlPhysician;
	
	protected Pnl_Physician getPnlPhysician(){
		return this.pnlPhysician;
	}
	
	/**
	 * Create the dialog.
	 */
	public Dlg_NewPhysician(Pnl_Physician pnlPhysician) {
		this.pnlPhysician = pnlPhysician;
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		
		getContentPane().add(pnlPhysician, BorderLayout.CENTER);
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
