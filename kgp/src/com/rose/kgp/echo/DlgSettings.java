package com.rose.kgp.echo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class DlgSettings extends JDialog {

	private final JPanel contentPanelMale = new JPanel();
	private final JPanel contentPanelFemale = new JPanel();
	private SettingsTableModels settingsTableModel;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DlgSettings dialog = new DlgSettings();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DlgSettings() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new FlowLayout());
		contentPanelMale.setLayout(new BorderLayout(10,10));
		contentPanelMale.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelMale);
		
		contentPanelFemale.setLayout(new BorderLayout(10,10));
		contentPanelFemale.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanelFemale);
		
		settingsTableModel = new SettingsTableModels();
		
		JTable tblLVMale = new JTable(settingsTableModel);		
		tblLVMale.setFillsViewportHeight(true);
		JScrollPane scrollMale = new JScrollPane(tblLVMale);
		contentPanelMale.add(BorderLayout.CENTER, scrollMale);
		JLabel lblMale = new JLabel("männlich");
		contentPanelMale.add(BorderLayout.NORTH, lblMale);
		
		JTable tblLVFemale = new JTable(settingsTableModel);
		tblLVFemale.setFillsViewportHeight(true);
		JScrollPane scrollFemale = new JScrollPane(tblLVFemale);		
		contentPanelFemale.add(BorderLayout.CENTER, scrollFemale);
		JLabel lblFemale = new JLabel("weiblich");
		contentPanelFemale.add(BorderLayout.NORTH, lblFemale);
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
		
		pack();
		
	}

}
