package com.rose.kgp.data_exchange;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.rose.kgp.ui.Ctrl_PnlSetDate;

public class Pnl_CtrlTblExamFiles extends JPanel {
	JButton btnActualize;
	private static final long serialVersionUID = -1384988843107051445L;	
	
	protected JButton getBtnActualize(){
		return this.btnActualize;
	}
	
	public Pnl_CtrlTblExamFiles(Ctrl_PnlSetDate ctrlStartDate, Ctrl_PnlSetDate ctrlEndDate) {
		FlowLayout flowLayout = (FlowLayout) getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		ctrlStartDate.getPanel().setLabelDateText("von:");
		ctrlEndDate.getPanel().setLabelDateText("bis:");
		add(ctrlStartDate.getPanel());
		add(ctrlEndDate.getPanel());
		
		btnActualize = new JButton("Aktualisieren");
		btnActualize.setHorizontalAlignment(SwingConstants.LEFT);
		btnActualize.setFont(new Font("Tahoma", Font.PLAIN, 14));
		add(btnActualize);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnActualize}));
		
	}

	protected void addActualizeListener(ActionListener l) {
		btnActualize.addActionListener(l);		
	}

}
