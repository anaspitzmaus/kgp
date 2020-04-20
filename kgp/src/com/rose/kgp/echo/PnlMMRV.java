package com.rose.kgp.echo;

import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JFormattedTextField;

public class PnlMMRV extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnlMMRV() {
		setLayout(new MigLayout("", "[][grow][]", "[][][][]"));
		
		JLabel lblRVBasalDiam = new JLabel("RV basal Diam:");
		lblRVBasalDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblRVBasalDiam, "cell 0 0,alignx left");
		
		JFormattedTextField ftxtRVBasalDiam = new JFormattedTextField();
		ftxtRVBasalDiam.setColumns(10);
		ftxtRVBasalDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(ftxtRVBasalDiam, "cell 1 0,alignx left");
		
		JLabel lblRVBasalDiamUnit = new JLabel("mm");
		lblRVBasalDiamUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblRVBasalDiamUnit, "cell 2 0");
		
		JLabel lblRVMidDiam = new JLabel("RV mid Diam:");
		lblRVMidDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblRVMidDiam, "cell 0 1,alignx left");
		
		JFormattedTextField ftxtRVMidDiam = new JFormattedTextField();
		ftxtRVMidDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtRVMidDiam.setColumns(10);
		add(ftxtRVMidDiam, "cell 1 1,alignx left");
		
		JLabel lblRVMidDiamUnit = new JLabel("mm");
		lblRVMidDiamUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblRVMidDiamUnit, "cell 2 1");
		
		JLabel lblRVLongDiam = new JLabel("RV long Diam:");
		lblRVLongDiam.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblRVLongDiam, "cell 0 2,alignx left");
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextField.setColumns(10);
		add(formattedTextField, "cell 1 2,alignx left");
		
		JLabel lblRVLongDiamUnit = new JLabel("mm");
		lblRVLongDiamUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblRVLongDiamUnit, "cell 2 2,alignx left,aligny bottom");
		
		JLabel lblRVOT_PLAX = new JLabel("RVOT PLAX:");
		lblRVOT_PLAX.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblRVOT_PLAX, "cell 0 3,alignx left,aligny bottom");
		
		JFormattedTextField formattedTextField_1 = new JFormattedTextField();
		formattedTextField_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		formattedTextField_1.setColumns(10);
		add(formattedTextField_1, "cell 1 3,alignx left");
		
		JLabel lblRVOT_PLAX_Unit = new JLabel("mm");
		lblRVOT_PLAX_Unit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(lblRVOT_PLAX_Unit, "cell 2 3");

	}

}
