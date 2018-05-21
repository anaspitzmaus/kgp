package com.rose.kgp.personnel;

import java.util.ArrayList;

public class Tbl_NurseModel extends Tbl_PersonnelModel{

private static final long serialVersionUID = 2666815155549333593L;
	
	
	private ArrayList<Nurse> nurses;
	
	public Tbl_NurseModel(ArrayList<Nurse> nurses) {
		super(nurses);
		
		columnNames.add("ID");
		columnNames.add("Name");
		columnNames.add("Vorname");
		columnNames.add("aktiv seit");
		
	}	
	

	@Override
	public int getRowCount() {
		return this.nurses.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 3){
			return nurses.get(rowIndex).getOnset();
		}else{
			return nurses.get(rowIndex);
		}
	}	
	
	
	 @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
		 
    }
	 
	protected ArrayList<Nurse> getNurses(){
		return this.nurses;
	}
}
