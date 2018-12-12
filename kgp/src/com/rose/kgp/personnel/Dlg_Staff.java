package com.rose.kgp.personnel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;

public abstract class Dlg_Staff extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7734136032168559220L;
	protected final JPanel contentPanel = new JPanel();
	protected JButton btnNewStaff;
	protected JTable tblPersonnel;
	private Pnl_Staff pnlStaff;
	
	
	
	protected Pnl_Staff getPnlStaff() {
		return pnlStaff;
	}
	
	protected void setPnlStaff(Pnl_Staff pnlStaff) {
		this.pnlStaff = pnlStaff;
	}



	/**
	 * Create the dialog.
	 */
	public Dlg_Staff() {
		setBounds(100, 100, 580, 590);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPaneTblPersonnel = new JScrollPane();
		
		contentPanel.add(scrollPaneTblPersonnel, BorderLayout.CENTER);			
		
		tblPersonnel = new JTable();
		tblPersonnel.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		tblPersonnel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		tblPersonnel.setRowHeight(18);       
		tblPersonnel.setIntercellSpacing(new Dimension(1, 10));
		tblPersonnel.setFillsViewportHeight(true); 
		tblPersonnel.setRowHeight(30);
		//tblPersonnel.getColumnModel().getColumn(0).setPreferredWidth(120);
						
		scrollPaneTblPersonnel.setViewportView(tblPersonnel);
		
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnNewStaff = new JButton("Personal anlegen");
				buttonPane.add(btnNewStaff);
				
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
	
	protected void addNewStaffMemberListener(ActionListener l){
		btnNewStaff.addActionListener(l);
	}
	
	protected JTable getTblPersonnel(){
		return this.tblPersonnel;
	}

	protected void addRowSelectionListener(ListSelectionListener l){
		getTblPersonnel().getSelectionModel().addListSelectionListener(l);
	}
	
	protected void removeRowSelectionListener(ListSelectionListener l){
		getTblPersonnel().getSelectionModel().removeListSelectionListener(l);
	}
	
	protected void addSexListener(ItemListener l) {
		pnlStaff.getComboSex().addItemListener(l);
	}
	
	protected void setSexComboRenderer(ListCellRenderer<Sex> r) {
		pnlStaff.getComboSex().setRenderer(r);
	}
	
	

}
