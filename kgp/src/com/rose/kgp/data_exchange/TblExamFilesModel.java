package com.rose.kgp.data_exchange;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import com.rose.kgp.useful.DateMethods;

public class TblExamFilesModel extends AbstractTableModel{

	
	private static final long serialVersionUID = 4671536849207726819L;

	protected ArrayList<String> columnNames;
	private ArrayList<FileContent> examFiles;
	private int rowCount;
	
	public TblExamFilesModel(ArrayList<FileContent> examFiles) {
		this.examFiles = examFiles;
		columnNames = new ArrayList<String>();
		setColumnNames();
		rowCount = examFiles.size();
	}
	
	/**
	 * set the columnNames
	 */
	private void setColumnNames() {
		if(columnNames instanceof ArrayList<?>){
			columnNames.clear();
			columnNames.add("Nr.");
			columnNames.add("Datum");
			columnNames.add("Patient");
			columnNames.add("Status");
		}
	}

	@Override
	public int getColumnCount() {
		return columnNames.size();
	}

	@Override
	public int getRowCount() {
		return rowCount;
	}
	
	@Override
	public String getColumnName(int column) {
        return columnNames.get(column);
    }
	
	@Override
    public Class<?> getColumnClass(int column) {
		if (examFiles.isEmpty()) {
	        return Object.class;
	    }
		return getValueAt(0, column).getClass(); 
		
	}

	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
//		if(this.getColumnName(colIndex) == "Datum"){
//			BasicFileAttributes attr = null;
//			try {
//				attr = Files.readAttributes(examFiles.get(rowIndex).toPath(), BasicFileAttributes.class);
//				Instant instant = attr.creationTime().toInstant();
//				ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
//				LocalDate ldate= zdt.toLocalDate();
//				return ldate;
//			} catch (IOException e) {
//				return null;
//			}			
		
					
		if(this.getColumnName(colIndex) == "Nr."){
			return rowIndex + 1;
		}else if(this.getColumnName(colIndex) =="Datum"){
			return DateMethods.getDateFromTimestamp(examFiles.get(rowIndex).getFile().lastModified());
		}else if(this.getColumnName(colIndex) =="Patient"){
			return examFiles.get(rowIndex).getPatient().getSurname() + ", " + examFiles.get(rowIndex).getPatient().getFirstname();
		}
		
			return examFiles.get(rowIndex);	
		
	}
	
	public void updateTableModel(ArrayList<FileContent> examFiles){
		this.examFiles = examFiles;
		rowCount = examFiles.size();
		fireTableDataChanged();
	}

}
