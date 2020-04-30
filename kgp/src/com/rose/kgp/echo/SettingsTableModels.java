package com.rose.kgp.echo;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.prefs.Preferences;

import javax.swing.table.AbstractTableModel;



public class SettingsTableModels extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -697805776138327039L;
	Integer rowCount, colCount;
	HashMap<String, ArrayList<Double>> valuesRef;
	Preferences prefs;
	ArrayList<String> columnNames;
	ArrayList<String> rowNames;
	
	public SettingsTableModels() {
		valuesRef = new HashMap<String, ArrayList<Double>>();
		prefs = Preferences.userRoot().node(RefValues.class.getName());
		columnNames = new ArrayList<String>();
		columnNames.add("Messwert");
		columnNames.add("Normal");
		columnNames.add("Mild");
		columnNames.add("Moderate");
		columnNames.add("Severe");
		
		rowNames = new ArrayList<String>();
		rowNames.add("septal wall thickness");
		rowNames.add("posterior wall thickness");
		
		for(int i = 0; i<rowNames.size(); i++) {
			ArrayList<Double> list = new ArrayList<Double>();
			switch (i) {
			case 0:
				list.add(prefs.getDouble("IVSdMNormal", 6.0));
				list.add(prefs.getDouble("IVSdMMild", 10.0));
				list.add(prefs.getDouble("IVSdMModerate", 12.0));
				list.add(prefs.getDouble("IVSdMSevere", 14.0));
				break;
			case 1:
				list.add(prefs.getDouble("LVPWdMNormal", 6.0));
				list.add(prefs.getDouble("LVPWdMMild", 10.0));
				list.add(prefs.getDouble("LVPWdMModerate", 12.0));
				list.add(prefs.getDouble("LVPWdMSevere", 14.0));
				break;
			default:
				break;
			}
			
			valuesRef.put(rowNames.get(i), list);			
		}
		
		 
	}
	
	@Override
	public int getRowCount() {
		return rowNames.size();
	}

	@Override
	public int getColumnCount() {		
		return columnNames.size();
	}
	
	public String getColumnName(int column) {		
        return columnNames.get(column);
    }

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if(columnIndex == 0) {
			return rowNames.get(rowIndex);
		}else {
		ArrayList<Double> list = valuesRef.get(rowNames.get(rowIndex));
		return list.get(columnIndex - 1);
		}
	}

}
