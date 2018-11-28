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

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Ctrl_PnlSetDate ctrlPnlSetBirthDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusYears(60));
			Ctrl_PnlSetDate ctrlPnlSetOnsetDate = new Ctrl_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusDays(7));
			Ctrl_PnlNewPhysician ctrlPnlNewPhysician = new Ctrl_PnlNewPhysician(ctrlPnlSetBirthDate, ctrlPnlSetOnsetDate);
			Dlg_NewPhysician dialog = new Dlg_NewPhysician((Pnl_NewPhysician) ctrlPnlNewPhysician.getPanel());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.pack();
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Dlg_NewPhysician(Pnl_NewPhysician pnlNewPhysician) {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		
		
		getContentPane().add(pnlNewPhysician, BorderLayout.CENTER);
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
