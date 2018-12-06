package com.rose.kgp.personnel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentListener;

import com.rose.kgp.ui.Pnl_SetDate;

import net.miginfocom.swing.MigLayout;


public class Pnl_Physician extends Pnl_Staff {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5495745236150149390L;
	
	private JComboBox<String> comboTitle;
	
	

	/**
	 * Create the panel.
	 */
	public Pnl_Physician() {
		
		add(getLblId(), "cell 0 0,alignx left");
		add(getTxtId(), "cell 1 0,alignx left");		
		add(getLblSex(), "cell 0 1,alignx left");		
		add(getComboSex(), "cell 1 1,alignx left");
		
		JLabel lblTitel = new JLabel("Titel:");
		lblTitel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblTitel, "cell 0 2,alignx left");
		
		comboTitle = new JComboBox<String>();
		comboTitle.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(comboTitle, "cell 1 2,alignx left");		
		
		add(getLblSurname(), "cell 0 3,alignx left");		
		
		add(getTxtSurname(), "cell 1 3,growx");			
		
		add(getLblFirstname(), "cell 0 4,alignx left");		
		
		add(getTxtFirstname(), "cell 1 4,growx");		
		
		add(getLblBirth(), "cell 0 5");			
		
		add(getLblOnsetDate(), "cell 0 6");		
		
		add(getLblAlias(), "cell 0 7,alignx left");		
		
		add(getTxtAlias(), "cell 1 7,growx");		
		
		add(getBtnSetStaff(), "cell 1 8,alignx right");
	}
	
	protected JComboBox<String> getComboTitle(){
		return this.comboTitle;
	}		
	
	protected void addTitleListener(ItemListener l) {
		comboTitle.addItemListener(l);		
	}
	
	protected void removeTitleListener(ItemListener l){
		comboTitle.removeItemListener(l);
	}	
	
	@Override
	protected void addBirthDatePanel(Pnl_SetDate datePanel){		
		if(!(birthPanel instanceof Pnl_SetDate)){
			this.birthPanel = datePanel;
			add(datePanel, "cell 1 6,growx,aligny top");
		}
	}
	
	@Override
	protected void addOnsetDatePanel(Pnl_SetDate datePanel){
		if(!(onsetPanel instanceof Pnl_SetDate)){
			this.onsetPanel = datePanel;
			add(datePanel, "cell 1 5,growx,aligny top");
		}
	}
	
	
 
	

	

	
	
	

}
