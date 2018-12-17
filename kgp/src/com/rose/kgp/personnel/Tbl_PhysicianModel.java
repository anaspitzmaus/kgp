package com.rose.kgp.personnel;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;


public class Tbl_PhysicianModel extends Tbl_PersonnelModel {

	
	private static final long serialVersionUID = 2666815155549333593L;
	
	
	//private ArrayList<Physician> physicians;
	
	public Tbl_PhysicianModel(ArrayList<Physician> physicians) {
		super(physicians);
		
		columnNames.add("ID");
		columnNames.add("Titel");
		columnNames.add("Name");
		columnNames.add("Vorname");
		columnNames.add("aktiv seit");
		
	}
	
	
	
	

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 4){
			return staff.get(rowIndex).getOnset();
		}else{
			return staff.get(rowIndex);
		}
	}	
	
	
	 @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
		 
    }
	 
//	protected ArrayList<Physician> getPhysicians(){
//		return this.physicians;
//	}

}
