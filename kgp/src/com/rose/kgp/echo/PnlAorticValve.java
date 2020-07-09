package com.rose.kgp.echo;

import java.awt.Font;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.MutableComboBoxModel;

import com.rose.heart.structures.Regurgitation;
import com.rose.heart.structures.StenosisValve;

import net.miginfocom.swing.MigLayout;

public class PnlAorticValve extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7587262799193143408L;
	JComboBox<StenosisValve> cbxStenosis;
	JComboBox<Regurgitation> cbxReg;
	/**
	 * Create the panel.
	 */
	public PnlAorticValve() {
		setLayout(new MigLayout("", "[][][][][][]", "[][][][]"));
		
		JLabel lblPGMean = new JLabel("PGmean:");
		lblPGMean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPGMean, "cell 0 0,alignx left");
		
		JFormattedTextField ftxtPGMean = new JFormattedTextField();
		ftxtPGMean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtPGMean.setColumns(5);
		add(ftxtPGMean, "cell 1 0,alignx left");
		
		JLabel lblPGMax = new JLabel("PGmax:");
		lblPGMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPGMax, "cell 2 0,alignx trailing");
		
		JFormattedTextField ftxtPGMax = new JFormattedTextField();
		ftxtPGMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtPGMax.setColumns(5);
		add(ftxtPGMax, "cell 3 0,alignx left");
		
		JLabel lblAÖF = new JLabel("A\u00D6F");
		lblAÖF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblAÖF, "cell 4 0,alignx trailing");
		
		JFormattedTextField ftxtAÖF = new JFormattedTextField();
		ftxtAÖF.setColumns(5);
		ftxtAÖF.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(ftxtAÖF, "cell 5 0");
		
		JLabel lblStenosis = new JLabel("Stenosegrad:");
		lblStenosis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblStenosis, "cell 0 1,alignx left");
		
		cbxStenosis = new JComboBox<StenosisValve>();
		cbxStenosis.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxStenosis, "cell 1 1,alignx left");
		
		JLabel lblPHT = new JLabel("PHT:");
		lblPHT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblPHT, "cell 0 2,alignx left");
		
		JFormattedTextField ftxtPHT = new JFormattedTextField();
		ftxtPHT.setColumns(5);
		ftxtPHT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(ftxtPHT, "cell 1 2,alignx left");
		
		JLabel lblReg = new JLabel("Insuffizienzgrad:");
		lblReg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblReg, "cell 0 3,alignx trailing");
		
		cbxReg = new JComboBox<Regurgitation>();
		cbxReg.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(cbxReg, "cell 1 3,alignx left");
		
		

	}
	
	protected void setStenosisModel(MutableComboBoxModel<StenosisValve> model) {
		cbxStenosis.setModel(model);
	}
	
	protected void setRegurgitationModel(MutableComboBoxModel<Regurgitation> model) {
		cbxReg.setModel(model);
	}
	
	protected void addStenosisListener(ItemListener listener) {
		cbxStenosis.addItemListener(listener);
	}
	
	protected void addRegurgitationListener(ItemListener listener) {
		cbxReg.addItemListener(listener);
	}
	
	

}
