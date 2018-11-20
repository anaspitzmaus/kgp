package com.rose.kgp.data_exchange;


import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;



class TblExamFiles extends JTable {

	
	private static final long serialVersionUID = 304478728319462137L;

	public TblExamFiles() {
		setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		setFont(new Font("Tahoma", Font.PLAIN, 18));
		setRowHeight(18);       
		setIntercellSpacing(new Dimension(1, 10));
		setFillsViewportHeight(true); 
		setRowHeight(30);
		setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		setAutoCreateRowSorter(true);
	}
	
	protected void addRowSelectionListener(ListSelectionListener l){
		getSelectionModel().addListSelectionListener(l);
	}
	
	 
	
	
}
