package com.rose.kgp.data_exchange;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.prefs.Preferences;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;

import com.rose.kgp.examination.Examination;
import com.rose.kgp.examination.LeftHeartCatheter;
import com.rose.kgp.examination.PM_Implant;
import com.rose.kgp.examination.StudyType;
import com.rose.kgp.settings.CtrlSetSensisPath;
import com.rose.kgp.useful.DateMethods;



public class Controller_PnlTblExamFiles {
	private TblExamFilesModel tblModel;
	private Pnl_TblExamFiles pnlTblExamFiles;
	private Sensis sensis;
	private TblRowSelectionListener tblRowSelectionListener;
	private Preferences prefs; 
	
	
	public TblRowSelectionListener getTblRowSelectionListener() {
		return tblRowSelectionListener;
	}

	public Controller_PnlTblExamFiles() {
		prefs = Preferences.userNodeForPackage(CtrlSetSensisPath.class);
		String sensisPath = prefs.get("Sensis_Path", prefs.get("Sensis_Path", ""));
		
		sensis = new Sensis(sensisPath);
		if(sensis instanceof Sensis) {
			tblModel = new TblExamFilesModel(sensis.getFilesForFolder(".HIS"));
			pnlTblExamFiles = new Pnl_TblExamFiles();
			//pnlTblExamFiles.getTblExamFiles().setDefaultRenderer(LocalDate.class, new ColumnDateRenderer());		
			pnlTblExamFiles.getTblExamFiles().setModel(tblModel);
			setRenderer();
			setListener();
		}
		
	}
	
	private void setListener(){
		tblRowSelectionListener = new TblRowSelectionListener();
		pnlTblExamFiles.getTblExamFiles().addRowSelectionListener(tblRowSelectionListener);
	}
	
	private void setRenderer(){
		ColumnFileRenderer fileRenderer = new ColumnFileRenderer();
		ColumnDateRenderer dateRenderer = new ColumnDateRenderer();
		pnlTblExamFiles.getTblExamFiles().getColumnModel().getColumn(1).setCellRenderer(dateRenderer);
		pnlTblExamFiles.getTblExamFiles().getColumnModel().getColumn(2).setCellRenderer(fileRenderer);
		pnlTblExamFiles.getTblExamFiles().getColumnModel().getColumn(3).setCellRenderer(fileRenderer);
	}
	
	public Pnl_TblExamFiles getPanel(){
		return this.pnlTblExamFiles;
	}
	
	/**
	 * renderer for the columns that show a date
	 * @author Ekkehard Rose
	 *
	 */
	class ColumnDateRenderer extends DefaultTableCellRenderer {
		
		private static final long serialVersionUID = 3498636126051341094L;
		SimpleDateFormat simpleDateFormat;
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			 
			 simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			 if(value != null){
				 BasicFileAttributes attr = null;
					try {
						attr = Files.readAttributes(((File)value).toPath(), BasicFileAttributes.class);
						Instant instant = attr.creationTime().toInstant();
						ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
						LocalDate ldate= zdt.toLocalDate();
						value = simpleDateFormat.format(DateMethods.ConvertLocalDateToDate(ldate));
						setHorizontalAlignment(JLabel.CENTER);
						
					} catch (IOException e) {
						value = null;
					}			
				
			 }			 
			 return super.getTableCellRendererComponent(table, value, isSelected,
		                hasFocus, row, column);
		}
	}
	
	/**
	 * renderer class for table columns that shows filenames
	 * @author Administrator
	 *
	 */
	class ColumnFileRenderer extends DefaultTableCellRenderer{

		private static final long serialVersionUID = -909986640885548467L;
		SimpleDateFormat simpleDateFormat;
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			switch (table.getColumnName(column) ){
			case "Filename":
				value = ((File)value).getAbsoluteFile().getName();
				break;
			case "Status":
				value = ((File)value).getName();//has to be changed
			default:
				break;
			} 
					 
			 return super.getTableCellRendererComponent(table, value, isSelected,
		                hasFocus, row, column);
		}
	}
	
	/**
	 * listener class for selecting a table row
	 * @author Ekkehard Rose
	 *
	 */
	public class TblRowSelectionListener extends Observable implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			HashMap<String, HashMap<String, ArrayList<String>>> values = null;
			Examination examSel = null;
			if(pnlTblExamFiles.getTblExamFiles().getSelectedRow() >= 0){				
				File fileSel = (File) pnlTblExamFiles.getTblExamFiles().getModel().getValueAt(pnlTblExamFiles.getTblExamFiles().getSelectedRow(), 1);
				Path pathTarget = Paths.get("C:/RoSoft/Temp/SensisFile");
				try {
					Files.copy(fileSel.toPath(), pathTarget, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Sensis sensis = new Sensis(fileSel.getParent());
				 
				try {
					values = sensis.readExamFile(fileSel.getName());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				switch(getExamType(values)){
				case Koronar_Diagnostisch:
					examSel = new LeftHeartCatheter(values);
					break;
				case LeftHeartCatheter:
					examSel = new LeftHeartCatheter(values);
					break;
				case PM_Implantation:
					examSel = new PM_Implant(values);
					break;
				}
				
								
				
				tellSelection(examSel); //tell the instance of the Ctrl_ActivityKind of the selection
			 }
			
		}
		
		public void tellSelection(Examination exam){
			setChanged();
			notifyObservers(exam);
		}
		
		/**
		 * returns the ExamType (enumeration) of the examination
		 * @param values: an HashMap<String, HashMap<String, ArrayList<String>>> that is built out of the transferred sensis file
		 * @return the type of the examination as ExamType (enumeration)
		 */
		private StudyType getExamType(HashMap<String, HashMap<String, ArrayList<String>>> values){
			HashMap<String, ArrayList<String>> exam_hm = values.get("STUDY");
						
			String examType = exam_hm.get("STUDESC").get(0);
			switch(examType){
			case "Koronar^Diagnostisch":
				return StudyType.Koronar_Diagnostisch;
			default:
				return null;
			}
		}
	}
}
