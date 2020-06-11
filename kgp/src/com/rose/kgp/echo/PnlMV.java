package com.rose.kgp.echo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.beans.PropertyChangeListener;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import net.miginfocom.swing.MigLayout;

public class PnlMV extends JPanel {
	JFormattedTextField ftxtPGMean;

	/**
	 * Create the panel.
	 */
	public PnlMV() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		add(splitPane, BorderLayout.CENTER);
		
		JPanel pnlValues = new JPanel();
		splitPane.setLeftComponent(pnlValues);
		pnlValues.setLayout(new MigLayout("", "[][100.00,grow][]", "[][][][]"));
		
		JLabel lblPGMean = new JLabel("PGmean:");
		lblPGMean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblPGMean, "cell 0 0,alignx left");
		
		ftxtPGMean = new JFormattedTextField();
		ftxtPGMean.setColumns(5);
		ftxtPGMean.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtPGMean, "cell 1 0,alignx left");
		
		JLabel lblPGMeanUnit = new JLabel("mmHg");
		lblPGMeanUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblPGMeanUnit, "cell 2 0");
		
		JLabel lblPGMax = new JLabel("PGmax:");
		lblPGMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblPGMax, "cell 0 1,alignx left");
		
		JFormattedTextField ftxtPGMax = new JFormattedTextField();
		ftxtPGMax.setColumns(5);
		ftxtPGMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtPGMax, "cell 1 1,alignx left");
		
		JLabel lblPGmaxUnit = new JLabel("mmHg");
		lblPGmaxUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblPGmaxUnit, "cell 2 1");
		
		JLabel lblEmax = new JLabel("Emax:");
		lblEmax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblEmax, "cell 0 2,alignx left");
		
		JFormattedTextField ftxtEmax = new JFormattedTextField();
		ftxtEmax.setColumns(5);
		ftxtEmax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtEmax, "cell 1 2,alignx left");
		
		JLabel lblEmaxUnit = new JLabel("cm/s");
		lblEmaxUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblEmaxUnit, "cell 2 2");
		
		JLabel lblAMax = new JLabel("Amax:");
		lblAMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblAMax, "cell 0 3,alignx left");
		
		JFormattedTextField ftxtAMax = new JFormattedTextField();
		ftxtAMax.setColumns(5);
		ftxtAMax.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtAMax, "cell 1 3,alignx left");
		
		JLabel lblAMaxUnit = new JLabel("cm/s");
		lblAMaxUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblAMaxUnit, "cell 2 3");
		
		JPanel pnlPicture = new JPanel();
		splitPane.setRightComponent(pnlPicture);
		pnlPicture.setLayout(new BorderLayout(0, 0));
	}
	
	protected void setPGMeanListener(PropertyChangeListener l) {
		ftxtPGMean.addPropertyChangeListener(l);
	}

}
