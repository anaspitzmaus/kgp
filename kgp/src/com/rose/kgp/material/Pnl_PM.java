package com.rose.kgp.material;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

public class Pnl_PM extends JPanel {
	private JTextField txtNotation;
	private JTable tblPM1;
	private JTextField txtSerialNr;
	private JTable tblPM2;

	/**
	 * Create the panel.
	 */
	public Pnl_PM() {
		setLayout(new BorderLayout(0, 0));
		
		JSplitPane splitPane = new JSplitPane();
		splitPane.setResizeWeight(0.5);
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		add(splitPane, BorderLayout.CENTER);
		
		JPanel pnlInputTbl1 = new JPanel();
		splitPane.setLeftComponent(pnlInputTbl1);
		pnlInputTbl1.setLayout(new MigLayout("", "[grow][grow][grow][]", "[][][]"));
		
		JLabel lblNotation = new JLabel("Bezeichnung:");
		lblNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl1.add(lblNotation, "cell 0 0");
		
		JLabel lblManufacturer = new JLabel("Hersteller:");
		lblManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl1.add(lblManufacturer, "cell 1 0");
		
		JLabel lblType = new JLabel("Typ:");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl1.add(lblType, "cell 2 0");
		
		JButton btnCreate = new JButton("Erstellen");
		btnCreate.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl1.add(btnCreate, "cell 3 0 1 2");
		
		txtNotation = new JTextField();
		txtNotation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl1.add(txtNotation, "cell 0 1,alignx left");
		txtNotation.setColumns(10);
		
		JComboBox cbxManufacturer = new JComboBox();
		cbxManufacturer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl1.add(cbxManufacturer, "cell 1 1,growx");
		
		JComboBox cbxType = new JComboBox();
		cbxType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl1.add(cbxType, "cell 2 1,growx");
		
		JScrollPane scrTbl1 = new JScrollPane();
		pnlInputTbl1.add(scrTbl1, "cell 0 2 4 1");
		
		tblPM1 = new JTable();
		tblPM1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrTbl1.setViewportView(tblPM1);
		
		JPanel pnlInputTbl2 = new JPanel();
		splitPane.setRightComponent(pnlInputTbl2);
		pnlInputTbl2.setLayout(new MigLayout("", "[grow][grow][]", "[][grow]"));
		
		JLabel lblSerialNr = new JLabel("Seriennummer:");
		lblSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl2.add(lblSerialNr, "cell 0 0,alignx left");
		
		txtSerialNr = new JTextField();
		txtSerialNr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl2.add(txtSerialNr, "cell 1 0,alignx left");
		txtSerialNr.setColumns(10);
		
		JButton btnAdd = new JButton("Hinzuf\u00FCgen");
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pnlInputTbl2.add(btnAdd, "cell 2 0");
		
		JScrollPane scrTbl2 = new JScrollPane();
		pnlInputTbl2.add(scrTbl2, "cell 0 1 3 1,grow");
		
		tblPM2 = new JTable();
		tblPM2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrTbl2.setViewportView(tblPM2);

	}

}
