package com.rose.kgp.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import com.rose.kgp.examination.Examination;

public class Pnl_Billing_Complete extends JPanel {

	protected Pnl_Patient pnlPatient;
	protected Pnl_Billing pnlBilling;
	/**
	 * 
	 */
	private static final long serialVersionUID = -3278737976523266184L;

	/**
	 * Create the panel.
	 */
	public Pnl_Billing_Complete() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel pnlCenter = new JPanel();		
		pnlCenter.setLayout(new MigLayout("", "[]", "[]"));
		
		JPanel pnlExam = new JPanel();
		pnlExam.setLayout(new FlowLayout());
		JLabel lblExam = new JLabel("Untersuchung:");
		pnlExam.add(lblExam);
		JComboBox<Examination> comboExam = new JComboBox<Examination>();
		pnlExam.add(comboExam);
		pnlCenter.add(pnlExam, "cell 0 0");
		pnlPatient = new Pnl_Patient();
		pnlCenter.add(pnlPatient, "cell 0 1");
		
		pnlBilling = new Pnl_Billing();
		pnlCenter.add(pnlBilling, "cell 0 2");
		
		add(pnlCenter, BorderLayout.CENTER);

	}

}
