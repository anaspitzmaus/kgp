package com.rose.kgp.data_exchange;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Observable;
import java.util.prefs.Preferences;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

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
	private Ctrl_PnlCtrlTblExamFiles ctrl_PnlCtrlTblExamFiles;
	private ActualizeListener actualizeListener;
	ArrayList<FileContent> filesContent;
	
	
	public TblRowSelectionListener getTblRowSelectionListener() {
		return tblRowSelectionListener;
	}

	public Controller_PnlTblExamFiles() {
		prefs = Preferences.userNodeForPackage(CtrlSetSensisPath.class);
		String sensisPath = prefs.get("Sensis_Path", prefs.get("Sensis_Path", ""));
		
		//create and add the control panel to the tables panel
		ctrl_PnlCtrlTblExamFiles = new Ctrl_PnlCtrlTblExamFiles();
		ctrl_PnlCtrlTblExamFiles.getCtrlStartDate().setDate(LocalDate.now().minusDays(300));//set the start date
		ctrl_PnlCtrlTblExamFiles.getCtrlEndDate().setDate(LocalDate.now().minusDays(0));//set the end date
		pnlTblExamFiles = new Pnl_TblExamFiles(ctrl_PnlCtrlTblExamFiles.getPnlCtrlTblExamFiles());
		
		//create the table and its model
		sensis = new Sensis(sensisPath);
		if(sensis instanceof Sensis) {
			LocalDate startDate = ctrl_PnlCtrlTblExamFiles.getCtrlStartDate().getDate();
			LocalDate endDate = ctrl_PnlCtrlTblExamFiles.getCtrlEndDate().getDate();
			ArrayList<File> files = sensis.getFilesForFolder(".HIS", startDate, endDate);
			filesContent = new ArrayList<FileContent>();
			for(File file: files){
				FileContent fileContent= new FileContent(file);
				filesContent.add(fileContent);
			}
			tblModel = new TblExamFilesModel(filesContent);
			
			//pnlTblExamFiles.getTblExamFiles().setDefaultRenderer(LocalDate.class, new ColumnDateRenderer());		
			pnlTblExamFiles.getTblExamFiles().setModel(tblModel);
			 // Der TableRowSorter wird die Daten des Models sortieren
	        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>();
	        
	        // Der Sorter muss dem JTable bekannt sein
	        pnlTblExamFiles.getTblExamFiles().setRowSorter( sorter );
	        
	        // ... und der Sorter muss wissen, welche Daten er sortieren muss
	        sorter.setModel(tblModel);
	        // Den Comparator für die 2. Spalte (mit den Points) setzen.
	       // sorter.setComparator( 1, new LocalDateComparator());
			setRenderer();
			setListener();
		}
		
	}
	
	private void setListener(){
		tblRowSelectionListener = new TblRowSelectionListener();
		pnlTblExamFiles.getTblExamFiles().addRowSelectionListener(tblRowSelectionListener);
		actualizeListener = new ActualizeListener();
		ctrl_PnlCtrlTblExamFiles.getPnlCtrlTblExamFiles().addActualizeListener(actualizeListener);
	}
	
	private void setRenderer(){
		ColumnFileRenderer fileRenderer = new ColumnFileRenderer();
		ColumnDateRenderer dateRenderer = new ColumnDateRenderer();
		PatientRenderer patientRenderer = new PatientRenderer();
		pnlTblExamFiles.getTblExamFiles().getColumnModel().getColumn(1).setCellRenderer(dateRenderer);
		pnlTblExamFiles.getTblExamFiles().getColumnModel().getColumn(2).setCellRenderer(patientRenderer);
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
			int rowSel = table.convertRowIndexToModel(row);
			value = ((TblExamFilesModel)table.getModel()).getValueAt(rowSel, column);
			// simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
			 if(value != null){
				 LocalDate date = (LocalDate)value;
				 setText(String.format("%02d", date.getDayOfMonth()) + "." + String.format("%02d", date.getMonthValue()) + "." + date.getYear());
				 setFont(new Font("Tahoma", Font.PLAIN, 18));
				 setHorizontalAlignment(CENTER);
				 if(isSelected){
					 setBackground(UIManager.getColor("Table.selectionBackground"));
				 }else{
					 setBackground(Color.WHITE);
				 }
//				 BasicFileAttributes attr = null;
//					try {
//						attr = Files.readAttributes(((FileContent)value).getFile().toPath(), BasicFileAttributes.class);
//						Instant instant = attr.creationTime().toInstant();
//						ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
//						LocalDate ldate= zdt.toLocalDate();
//						value = simpleDateFormat.format(DateMethods.ConvertLocalDateToDate(ldate));
//						setHorizontalAlignment(JLabel.CENTER);
//						
//					} catch (IOException e) {
//						value = null;
//					}			
				
			 }	
			 return this;
			 //return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
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
			int rowSel = table.convertRowIndexToModel(row);
			value = table.getModel().getValueAt(rowSel, column);
			switch (table.getColumnName(column) ){
			case "Patient":
				value = ((FileContent)value).getPatient().getSurname() + ", " + ((FileContent)value).getPatient().getFirstname();
				break;
			case "Status":
				value = ((FileContent)value).getFile().getName();//has to be changed
			default:
				break;
			} 
					 
			 return super.getTableCellRendererComponent(table, value, isSelected,
		                hasFocus, rowSel, column);
		}
	}
	
	class PatientRenderer extends DefaultTableCellRenderer{

		private static final long serialVersionUID = 6002067683300989766L;
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			int rowSel = table.convertRowIndexToModel(row);
			value = table.getModel().getValueAt(rowSel, column);
			setText((String) value);
			//value = ((FileContent)value).getPatient().getSurname() + ", " + ((FileContent)value).getPatient().getFirstname();
			return this;
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
				FileContent fileContent = (FileContent) pnlTblExamFiles.getTblExamFiles().getModel().getValueAt(pnlTblExamFiles.getTblExamFiles().getSelectedRow(), 3);
				File fileSel = fileContent.getFile();
				String sensisCopyPath = prefs.get("Sensis_Copy_Path", prefs.get("Sensis_Copy_Path", ""));
				Path pathTarget = Paths.get(sensisCopyPath + "/SensisCopy.txt");
				File targetFile = pathTarget.toFile();
				try {
					Files.copy(fileSel.toPath(), pathTarget, StandardCopyOption.REPLACE_EXISTING);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				Sensis sensis = new Sensis(targetFile.getParent()); 
				try {
					values = sensis.readExamFile(targetFile.getName());
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
	
	/**
	 * listener for actualizing the exam table
	 * @author Administrator
	 *
	 */
	class ActualizeListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(sensis instanceof Sensis) {
				LocalDate startDate = ctrl_PnlCtrlTblExamFiles.getCtrlStartDate().getDate();
				LocalDate endDate = ctrl_PnlCtrlTblExamFiles.getCtrlEndDate().getDate();
				ArrayList<File> files = sensis.getFilesForFolder(".HIS", startDate, endDate);
				filesContent.clear();
				for(File file: files){
					FileContent fileContent= new FileContent(file);
					filesContent.add(fileContent);
				}
				tblModel.updateTableModel(filesContent);;
			}
		}
		
	}
	
	class LocalDateComparator implements Comparator<FileContent>{

		@Override
		public int compare(FileContent fc_1, FileContent fc_2) {
			Timestamp a = new Timestamp(fc_1.getFile().lastModified());
			Timestamp b = new Timestamp(fc_2.getFile().lastModified());
			return a.compareTo(b);
		}
		
	}
}
