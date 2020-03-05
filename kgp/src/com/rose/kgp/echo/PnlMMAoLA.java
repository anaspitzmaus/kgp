package com.rose.kgp.echo;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import java.awt.Font;
import java.awt.FlowLayout;
import javax.swing.JFormattedTextField;
import javax.swing.SwingConstants;

public class PnlMMAoLA extends JPanel {

	/**
	 * Create the panel.
	 */
	public PnlMMAoLA() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlMenu = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pnlMenu.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		add(pnlMenu, BorderLayout.NORTH);
		
		JLabel lblAoLA = new JLabel("Ao/LA");
		lblAoLA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlMenu.add(lblAoLA);
		
		JMenuBar menuBar = new JMenuBar();
		pnlMenu.add(menuBar);
		
		JPanel pnlValues = new JPanel();
		add(pnlValues, BorderLayout.CENTER);
		pnlValues.setLayout(new MigLayout("", "[][][]", "[][][]"));
		
		JLabel lblAoRoot = new JLabel("Aortic Root:");
		lblAoRoot.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblAoRoot, "cell 0 0,alignx left");
		
		JFormattedTextField ftxtAoRoot = new JFormattedTextField();
		ftxtAoRoot.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtAoRoot.setColumns(10);
		pnlValues.add(ftxtAoRoot, "cell 1 0,growx");
		
		JLabel lblAoRootUnit = new JLabel("mm");
		lblAoRootUnit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblAoRootUnit, "cell 2 0");
		
		JLabel lblAoAsc = new JLabel("Aorta ascendens:");
		lblAoAsc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblAoAsc, "cell 0 1,alignx left");
		
		JFormattedTextField ftxtAoAsc = new JFormattedTextField();
		ftxtAoAsc.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ftxtAoAsc.setColumns(10);
		pnlValues.add(ftxtAoAsc, "cell 1 1,growx");
		
		JLabel label = new JLabel("mm");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(label, "cell 2 1");
		
		JLabel lblLA = new JLabel("LA:");
		lblLA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(lblLA, "cell 0 2,alignx trailing");
		
		JFormattedTextField ftxtLA = new JFormattedTextField();
		ftxtLA.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlValues.add(ftxtLA, "cell 1 2,growx");

	}

}
