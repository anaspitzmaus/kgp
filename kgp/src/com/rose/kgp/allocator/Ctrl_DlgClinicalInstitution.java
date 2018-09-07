package com.rose.kgp.allocator;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.db.SQL_UPDATE;


/**
 * controller class of the dialog for input the allocator
 * @author Administrator
 *
 */
public class Ctrl_DlgClinicalInstitution {

	Dlg_ClinicalInstitution dlgClinicalInstitution;
	NotationDocumentListener notationDocumentListener;
	ShortNotationDocumentListener shortNotationDocumentListener;
	StreetDocumentListener streetDocumentListener;
	CityDocumentListener cityDocumentListener;
	PostalCodeDocumentListener postalCodeDocumentListener;
	ConfirmNewClinicalInstitutionActionListener confirmNewClinicalInstitutionListener;
	String notation, shortNotation, street, postalCode, city;
	TblClinicalInstitutionModel tblModel;
	ArrayList<Clinical_Institution> clinicalInstitutions;
	TblColumnRenderer tblColumnRenderer;
	TblRowSelectionListener tblRowSelectionListener;
	Border redline, blackline;
	DlgMode mode;
	SetNewClinicalInstitutionActionListener setNewClinicalInstitutionListener;
	Clinical_Institution institutionSel = null;
	
	public Ctrl_DlgClinicalInstitution() {
		dlgClinicalInstitution = new Dlg_ClinicalInstitution();
		clinicalInstitutions = SQL_SELECT.ClinicalInstitutions(LocalDate.now());				
		tblModel = new TblClinicalInstitutionModel(clinicalInstitutions);
		dlgClinicalInstitution.getTblAllocators().setModel(tblModel);
		dlgClinicalInstitution.getTblAllocators().getColumnModel().getColumn(0).setPreferredWidth(20);
		dlgClinicalInstitution.getTblAllocators().getColumnModel().getColumn(1).setPreferredWidth(200);
		setRenderer();
		setListener();
		redline = BorderFactory.createLineBorder(Color.red);
		blackline = BorderFactory.createLineBorder(Color.black);
		for (Component component: dlgClinicalInstitution.getPnlInput().getComponents()){
			if(component instanceof JTextField){
				((JTextField)component).setBorder(redline);
			}
		}
		setMode(DlgMode.CREATE);
	}
	
	private void setRenderer(){
		tblColumnRenderer = new TblColumnRenderer();
		for(int i = 1; i<=3; i++){
			dlgClinicalInstitution.getTblAllocators().getColumnModel().getColumn(i).setCellRenderer(tblColumnRenderer);
		}
	}
	
	private void setMode(DlgMode mode){
		this.mode = mode;
		switch (this.mode) {
		case CREATE:
			dlgClinicalInstitution.getBtnAddAllocator().setText("Hinzufügen");
			dlgClinicalInstitution.getBtnNewInstitution().setVisible(false);
			renewDialog();
			break;
		case CHANGE:
			dlgClinicalInstitution.getBtnAddAllocator().setText("Übernehmen");
			dlgClinicalInstitution.getBtnNewInstitution().setVisible(true);
			break;
		default:
			break;
		}
	}
	
	private void setListener(){
		notationDocumentListener = new NotationDocumentListener();
		dlgClinicalInstitution.addNotationDocumentListener(notationDocumentListener);
		shortNotationDocumentListener = new ShortNotationDocumentListener();
		dlgClinicalInstitution.addShortNotationDocumentListener(shortNotationDocumentListener);
		streetDocumentListener = new StreetDocumentListener();
		dlgClinicalInstitution.addStreetDocumentListener(streetDocumentListener);
		cityDocumentListener = new CityDocumentListener();
		dlgClinicalInstitution.addCityDocumentListener(cityDocumentListener);
		postalCodeDocumentListener = new PostalCodeDocumentListener();
		dlgClinicalInstitution.addPostalCodeDocumentListener(postalCodeDocumentListener);
		confirmNewClinicalInstitutionListener = new ConfirmNewClinicalInstitutionActionListener();
		dlgClinicalInstitution.addConfirmNewAllocatorListener(confirmNewClinicalInstitutionListener);
		tblRowSelectionListener = new TblRowSelectionListener();
		dlgClinicalInstitution.addRowSelectionListener(tblRowSelectionListener);
		setNewClinicalInstitutionListener = new SetNewClinicalInstitutionActionListener();
		dlgClinicalInstitution.addNewClinicalInstitutionListener(setNewClinicalInstitutionListener);
	}
	
	public void showDialog(){		
		dlgClinicalInstitution.setVisible(true);
	}
	
	private void renewDialog(){
		dlgClinicalInstitution.getTxtCity().setText("");
		dlgClinicalInstitution.getTxtNotation().setText("");
		dlgClinicalInstitution.getTxtShortNotation().setText("");
		dlgClinicalInstitution.getTxtStreet().setText("");
		dlgClinicalInstitution.getTxtPostalCode().setText("");
		institutionSel = null;
	}
	
	private Boolean verifyInput(){
		if (notation.length() > 0 && shortNotation.length() > 0 && city.length() > 0 && street.length() > 0 && postalCode.length() > 0){
			return true;
		}
		return false;
	}
	
	private void updateTable(ArrayList<Clinical_Institution> institutions){
		tblModel.setClinicalInstitutions(institutions);
		tblModel.fireTableDataChanged();
	}
	
	/**
	 * document listener for the textField with input of the clinical institutions notation
	 * @author Administrator
	 *
	 */
	class NotationDocumentListener implements DocumentListener{
		
		
		@Override
		public void changedUpdate(DocumentEvent de) {
			notation = dlgClinicalInstitution.getTxtNotation().getText();
			if(notation.length() == 0){				
				dlgClinicalInstitution.getTxtNotation().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtNotation().setBorder(blackline);
			}
		}

		@Override
		public void insertUpdate(DocumentEvent de) {
			notation = dlgClinicalInstitution.getTxtNotation().getText();	
			if(notation.length() == 0){
				
				dlgClinicalInstitution.getTxtNotation().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtNotation().setBorder(blackline);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent de) {
			notation = dlgClinicalInstitution.getTxtNotation().getText();
			if(notation.length() == 0){
				
				dlgClinicalInstitution.getTxtNotation().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtNotation().setBorder(blackline);
			}
		}
		
	}
	
	/**
	 * document listener for the textField with input of the clinical institutions short notation
	 * @author Administrator
	 *
	 */
	class ShortNotationDocumentListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent de) {
			shortNotation = dlgClinicalInstitution.getTxtShortNotation().getText();
			if(shortNotation.length() == 0){				
				dlgClinicalInstitution.getTxtShortNotation().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtShortNotation().setBorder(blackline);
			}
		}

		@Override
		public void insertUpdate(DocumentEvent de) {
			shortNotation = dlgClinicalInstitution.getTxtShortNotation().getText();
			if(shortNotation.length() == 0){				
				dlgClinicalInstitution.getTxtShortNotation().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtShortNotation().setBorder(blackline);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent de) {
			shortNotation = dlgClinicalInstitution.getTxtShortNotation().getText();
			if(shortNotation.length() == 0){				
				dlgClinicalInstitution.getTxtShortNotation().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtShortNotation().setBorder(blackline);
			}
		}
		
	}
	
	/**
	 * document listener for the textField with input of the clinical institutions street
	 * @author Administrator
	 *
	 */
	class StreetDocumentListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			street = dlgClinicalInstitution.getTxtStreet().getText();	
			if(street.length() == 0){				
				dlgClinicalInstitution.getTxtStreet().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtStreet().setBorder(blackline);
			}
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			street = dlgClinicalInstitution.getTxtStreet().getText();	
			if(street.length() == 0){				
				dlgClinicalInstitution.getTxtStreet().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtStreet().setBorder(blackline);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			street = dlgClinicalInstitution.getTxtStreet().getText();
			if(street.length() == 0){				
				dlgClinicalInstitution.getTxtStreet().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtStreet().setBorder(blackline);
			}
		}
		
	}
	
	class CityDocumentListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			city = dlgClinicalInstitution.getTxtCity().getText();
			if(city.length() == 0){				
				dlgClinicalInstitution.getTxtCity().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtCity().setBorder(blackline);
			}
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			city = dlgClinicalInstitution.getTxtCity().getText();
			if(city.length() == 0){				
				dlgClinicalInstitution.getTxtCity().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtCity().setBorder(blackline);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			city = dlgClinicalInstitution.getTxtCity().getText();	
			if(city.length() == 0){				
				dlgClinicalInstitution.getTxtCity().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtCity().setBorder(blackline);
			}
		}
		
	}
	
	class PostalCodeDocumentListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			postalCode = dlgClinicalInstitution.getTxtPostalCode().getText();
			if(postalCode.length() == 0){				
				dlgClinicalInstitution.getTxtPostalCode().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtPostalCode().setBorder(blackline);
			}
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			postalCode = dlgClinicalInstitution.getTxtPostalCode().getText();
			if(postalCode.length() == 0){				
				dlgClinicalInstitution.getTxtPostalCode().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtPostalCode().setBorder(blackline);
			}
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			postalCode = dlgClinicalInstitution.getTxtPostalCode().getText();
			if(postalCode.length() == 0){				
				dlgClinicalInstitution.getTxtPostalCode().setBorder(redline);
			}else{
				dlgClinicalInstitution.getTxtPostalCode().setBorder(blackline);
			}
		}		
	}
	
	class SetNewClinicalInstitutionActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {				
			setMode(DlgMode.CREATE);
		}
		
	}
	
	
	class ConfirmNewClinicalInstitutionActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent ae) {
			switch (mode) {
				case CREATE:
					if(verifyInput()){
						Clinical_Institution clinicalInstitution = new Clinical_Institution(notation, shortNotation, street, postalCode, city);
						if(SQL_INSERT.ClinicalInstitution(clinicalInstitution)){
							setMode(DlgMode.CREATE);
						};
						ArrayList<Clinical_Institution> clinicalInstitutions = SQL_SELECT.ClinicalInstitutions(LocalDate.now());
						updateTable(clinicalInstitutions);
					}
				break;
				case CHANGE:
					if(verifyInput()){
						Clinical_Institution clinicalInstitution = new Clinical_Institution(notation, shortNotation, street, postalCode, city);
						clinicalInstitution.setId(institutionSel.getId());
						if(SQL_UPDATE.ClinicalInstitution(clinicalInstitution)){
							setMode(DlgMode.CREATE);
						};
						ArrayList<Clinical_Institution> clinicalInstitutions = SQL_SELECT.ClinicalInstitutions(LocalDate.now());
						updateTable(clinicalInstitutions);
					}
				default:
				break;
			}
			
		}		
	}
	
	class TblClinicalInstitutionModel extends AbstractTableModel{

		private static final long serialVersionUID = -4232024737705559317L;

		protected ArrayList<String> columnNames;
		private ArrayList<Clinical_Institution> clinicalInstitutions;
		private int rowCount;
		
		
		
		protected ArrayList<Clinical_Institution> getClinicalInstitutions() {
			return clinicalInstitutions;
		}
		
		

		protected void setClinicalInstitutions(ArrayList<Clinical_Institution> clinicalInstitutions) {
			this.clinicalInstitutions = clinicalInstitutions;
		}


		public TblClinicalInstitutionModel(ArrayList<Clinical_Institution> clinicalInstitutions) {
			
				this.clinicalInstitutions = clinicalInstitutions;
				columnNames = new ArrayList<String>();
				setColumnNames();
				rowCount = this.clinicalInstitutions.size();
				
			
		}
		
		/**
		 * set the columnNames
		 */
		private void setColumnNames() {
			if(columnNames instanceof ArrayList<?>){
				columnNames.clear();
				columnNames.add("Nr.");
				columnNames.add("Bezeichnung");	
				columnNames.add("Kurzbezeichnung");
				columnNames.add("Anschrift");
			}
		}
		
		@Override
		public int getRowCount() {
			return this.clinicalInstitutions.size();
		}
		
			
		@Override
		public int getColumnCount() {
			return columnNames.size();
		}

				
		@Override
		public String getColumnName(int column) {
	        return columnNames.get(column);
	    }

		@Override
		public Object getValueAt(int rowIndex, int colIndex) {
			if(this.getColumnName(colIndex) == "Nr."){
				return rowIndex + 1;
			}
			return clinicalInstitutions.get(rowIndex);	
		}
		
		@Override
	    public Class<?> getColumnClass(int column) {		
	    	return getValueAt(0, column).getClass(); 
		}
		
	}
	
	/**
	 * renderer class for table columns
	 * @author Administrator
	 *
	 */
	class TblColumnRenderer extends DefaultTableCellRenderer{

		
		private static final long serialVersionUID = -3636352458337978878L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
			switch (table.getColumnName(column) ){
			case "Bezeichnung":
				value = ((Clinical_Institution)value).getNotation();
				break;
			case "Kurzbezeichnung":
				value = ((Clinical_Institution)value).getShortNotation();
				break;
			case "Anschrift":
				value = ((Clinical_Institution)value).getStreet() + ", " + ((Clinical_Institution)value).getPostalCode() + " " + ((Clinical_Institution)value).getCity();
				break;	
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
	public class TblRowSelectionListener implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			
			
			if(dlgClinicalInstitution.getTblAllocators().getSelectedRow() >= 0){
				setMode(DlgMode.CHANGE);
				institutionSel = (Clinical_Institution) dlgClinicalInstitution.getTblAllocators().getModel().getValueAt(dlgClinicalInstitution.getTblAllocators().getSelectedRow(), 1);
				setSelectedInstitution(institutionSel);					
			 }
			
		}
		
		private void setSelectedInstitution(Clinical_Institution institution){
			if(institution instanceof Clinical_Institution){
				dlgClinicalInstitution.getTxtNotation().setText(institution.getNotation());
				dlgClinicalInstitution.getTxtShortNotation().setText(institution.getShortNotation());
				dlgClinicalInstitution.getTxtCity().setText(institution.getCity());
				dlgClinicalInstitution.getTxtPostalCode().setText(institution.getPostalCode());
				dlgClinicalInstitution.getTxtStreet().setText(institution.getStreet());
				
			}
		}
	}
}
