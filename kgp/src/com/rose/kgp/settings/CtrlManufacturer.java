package com.rose.kgp.settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import com.rose.kgp.db.DB;
import com.rose.kgp.db.Dlg_DBSettings;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.material.Manufacturer;

public class CtrlManufacturer {
	DlgManufacturer dlgManufacturer;
	Manufacturer manufacturer;
	String notation, contactPerson, mobil;
	NotationListener notationListener;
	ContactPersonListener contactPersonListener;
	CreateListener createListener;
	ManufacturerTblModel manufacturerTblModel;
	ArrayList<Manufacturer> manufacturers;
	
	public CtrlManufacturer() {
		dlgManufacturer = new DlgManufacturer();
		connectDB();
		if (DB.getConnection() != null) {
			setListener();
			manufacturers = SQL_SELECT.manufacturer();
			manufacturerTblModel = new ManufacturerTblModel(manufacturers);
			dlgManufacturer.setTableModel(manufacturerTblModel);
			showDialog();
		}
		
	}
	
	private void setListener() {
		notationListener = new NotationListener();
		contactPersonListener = new ContactPersonListener();
		createListener = new CreateListener();
		dlgManufacturer.addNotationListener(notationListener);
		dlgManufacturer.addContactPersonListener(contactPersonListener);
		dlgManufacturer.addCreateListener(createListener);
	}
	
	protected void showDialog() {
		dlgManufacturer.setVisible(true);
	}
	
	private void connectDB() {
		if (DB.getConnection() == null) {		
			if(DB.createConnection() != null){			
				 //go further on only if a connection to the database could be established
				if(SQL_INSERT.Admin()){		//insert the administrator to database (necessary for first login)
					//start the login dialog
					try {
//						Dlg_LogIn dialog = new Dlg_LogIn();
//						dialog.setLocationRelativeTo(null); //show in the center of the screen
//						dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						//dialog.setVisible(true);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(new JFrame(),
							    "The Application couldn't be started!", "Fatal Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(new JFrame(),
						    "The Application couldn't be started!", "Fatal Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}else{ //if there couldn't be created a database connection
				//open the Dialog with the database settings
				Dlg_DBSettings dlgDBSettings = new Dlg_DBSettings();
				dlgDBSettings.setLocationRelativeTo(null);
				dlgDBSettings.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				dlgDBSettings.setModal(true);
				dlgDBSettings.setVisible(true);	
			}
		}
	}
	
	class CreateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			manufacturer = new Manufacturer(notation);
			manufacturer.setContact_person(contactPerson);
			manufacturer.setMobil(mobil);
			
			SQL_INSERT.Manufacturer(manufacturer);
			
		}
		
	}
	
	class NotationListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent arg0) {
			notation = dlgManufacturer.getTxtNotation().getText();	
		}

		@Override
		public void insertUpdate(DocumentEvent arg0) {
			notation = dlgManufacturer.getTxtNotation().getText();
			
		}

		@Override
		public void removeUpdate(DocumentEvent arg0) {
			notation = dlgManufacturer.getTxtNotation().getText();
			
		}
		
	}
	
	class ContactPersonListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			contactPerson = dlgManufacturer.getTxtContact().getText();
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			contactPerson = dlgManufacturer.getTxtContact().getText();
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			contactPerson = dlgManufacturer.getTxtContact().getText();
			
		}
		
	}
	
	class MobileListener implements DocumentListener{

		@Override
		public void changedUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void insertUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void removeUpdate(DocumentEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	class ManufacturerTblModel extends AbstractTableModel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 5630441403441681314L;
		protected ArrayList<String> columnNames;
		protected ArrayList<Manufacturer> manufacturer;
		
		public ManufacturerTblModel(ArrayList<Manufacturer> manufacturer) {
			this.manufacturer = manufacturer;
			columnNames = new ArrayList<String>();
			columnNames.add("Firma");
			columnNames.add("Vertreter");
			columnNames.add("Mobil");
			
		}
		
		@Override
		public int getColumnCount() {
			// TODO Auto-generated method stub
			return columnNames.size();
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return manufacturer.size();
		}

		
		
		public String getColumnName(int column) {
	        return columnNames.get(column);
	    }
		
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			
			switch(columnIndex) {
			case 0: return manufacturer.get(rowIndex).getNotation();
			
			case 1: return manufacturer.get(rowIndex).getContact_person();
			
			case 2: return manufacturer.get(rowIndex).getMobil();
			
			default: return null;
			
			}
			
		}	
		
	}
}
