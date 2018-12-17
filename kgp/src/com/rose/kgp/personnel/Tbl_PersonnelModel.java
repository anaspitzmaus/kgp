package com.rose.kgp.personnel;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.table.AbstractTableModel;

abstract class Tbl_PersonnelModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -621423949115650923L;
	
	protected ArrayList<String> columnNames;
	protected ArrayList<? extends Staff> staff;
	
	public Tbl_PersonnelModel(ArrayList<? extends Staff> staff) {
		this.staff = staff;
		columnNames = new ArrayList<String>();
	}
	
	public ArrayList<? extends Staff> getStaffMembers(){
		return this.staff;
	}
	
	@Override
	public int getRowCount() {
		return this.staff.size();
	}
	
	@Override
	public int getColumnCount() {		
		return columnNames.size();
	}

	public String getColumnName(int column) {
        return columnNames.get(column);
    }
	
	@Override
    public Class<?> getColumnClass(int column) {		
    	return getValueAt(0, column).getClass(); 
	}
	
	protected ArrayList<? extends Staff> getStaff(){
		return this.staff;
	}
}
