package com.rose.kgp.data_exchange;

import java.time.LocalDate;

import javax.swing.JPanel;

import com.rose.kgp.ui.Controller_PnlSetDate;

public class Pnl_CtrlTblExamFiles extends JPanel {

	private static final long serialVersionUID = -1384988843107051445L;
	Controller_PnlSetDate ctrlStartDate;
	Controller_PnlSetDate ctrlEndDate;
	
	public Pnl_CtrlTblExamFiles() {
		ctrlStartDate = new Controller_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusMonths(100));
		ctrlEndDate = new Controller_PnlSetDate("dd.MM.yyyy", LocalDate.now(), LocalDate.now().minusMonths(100));
		ctrlStartDate.getPanel().setLabelDateText("von:");
		ctrlEndDate.getPanel().setLabelDateText("bis:");
		add(ctrlStartDate.getPanel());
		add(ctrlEndDate.getPanel());
	}

}
