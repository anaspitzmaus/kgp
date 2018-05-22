package com.rose.kgp.personnel;

import java.util.ArrayList;

public class Tbl_NurseModel extends Tbl_PersonnelModel{

private static final long serialVersionUID = 2666815155549333593L;
	
	
	public Tbl_NurseModel(ArrayList<Nurse> nurses) {
		super(nurses);
		
		columnNames.add("ID");
		columnNames.add("Name");
		columnNames.add("Vorname");
		columnNames.add("aktiv seit");
		
	}	
	

	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 3){
			return staff.get(rowIndex).getOnset();
		}else{
			return staff.get(rowIndex);
		}
	}	
	
	
	 @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
		 
    }
	 
	
}
