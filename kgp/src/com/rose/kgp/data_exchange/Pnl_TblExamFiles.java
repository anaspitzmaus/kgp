package com.rose.kgp.data_exchange;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Pnl_TblExamFiles extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3499616821959090753L;
	private TblExamFiles tblExamFiles;
		
	protected TblExamFiles getTblExamFiles(){
		return this.tblExamFiles;
	}

	/**
	 * Create the panel.
	 */
	public Pnl_TblExamFiles() {
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		tblExamFiles = new TblExamFiles();
		scrollPane.setViewportView(tblExamFiles);

	}

}
