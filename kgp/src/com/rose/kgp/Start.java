package com.rose.kgp;

import java.io.File;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import com.rose.kgp.activityInput.BillingType;
import com.rose.kgp.db.DB;
import com.rose.kgp.db.Dlg_DBSettings;
import com.rose.kgp.db.SQL_INSERT;
import com.rose.kgp.examination.LeftHeartCatheter;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.personnel.Patient;
import com.rose.kgp.ui.Dlg_LogIn;

/**
 * entry point of the application 
 * first: a connection to the database / schema is built
 * second: the administrator is inserted to database (necessary for first login)
 * third: the login-dialog is opened, that starts the main frame
 * @author Ekkehard Rose
 * @version 1.0
 *
 */
public class Start {

	public static void main(String[] args) {
		checkDBConnection();		
	}
	
	/**
	 * get the connection tom the database, insert the administrator to the appropriate schema of that database and opens the login-dialog
	 */
	public static void checkDBConnection(){
		Examination exam = null;
		
		Sensis sensis = new Sensis("C:\\Users\\Ekki\\Documents\\Praxis Kaltofen\\Sensis\\");
		
		
		if(DB.createConnection() != null){			
		 //go further on only if a connection to the database could be established
			if(SQL_INSERT.Admin()){		//insert the administrator to database (necessary for first login)
				//start the login dialog
				try {
//					Dlg_LogIn dialog = new Dlg_LogIn();
//					dialog.setLocationRelativeTo(null); //show in the center of the screen
//					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
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
		
		try {
			sensis.readExamFile("99_2241_2401.his");
		} catch (IOException e1) {
			//handle the IOException
		}
		
		switch (sensis.getExamType()) {
		case "Koronar^Diagnostisch":
			exam = new LeftHeartCatheter();
			exam.setPatientProperties(sensis.getExamination());
			exam.setPhysician(sensis.getExamination());
			exam.setStaff(sensis.getExamination());
			exam.setDateTimes(sensis.getExamination());
			exam.getPhysician().setId(1);
			exam.getPatient().setId(1);
			exam.setBillingType(BillingType.ambulant);
			exam.setDataFile(new File("99_2241_2401.his"));
			
			break;

		default:
			break;
		}
		
		//exam.storeExamToDB();
		Patient patient = new Patient("Schubert", "Willy");
		patient.setId(10);
		SQL_INSERT.Patient_Changes(patient);
		
	}

}
