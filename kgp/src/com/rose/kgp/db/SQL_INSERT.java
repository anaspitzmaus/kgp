package com.rose.kgp.db;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.kgp.MD5;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.personnel.Patient;
import com.rose.kgp.personnel.Physician;




public class SQL_INSERT {
	
	static Statement stmt;
	static ResultSet rs;
	/**
	 * inserts an administrator if no administrator is stored in DB
	 * necessary for running the application because the login needs a registered user
	 * @return true if an administrator is already registered or the administrator could be registered now; false if there is no registered administrator and the standard administrator couldn't be registered 
	 */
	public static Boolean Admin(){
		stmt = DB.getStatement();
		//get the MD5-hash of admin username and password
		String hashUN = MD5.getMD5("admin");		
		String hashPW = MD5.getMD5("master");	
		
		try{
			rs = stmt.executeQuery("SELECT * "
					+ "FROM staff "
					+ "WHERE admin = '" + 1 + "' "
					+ "AND expiry IS NULL");
			
			if(!rs.isBeforeFirst()){				
				try {
					stmt.executeUpdate("INSERT INTO staff (firstname, sex, alias, onset, username, password, admin) VALUES ('Ekkehard', '0', 'OA Rose', '2015-06-30', '" + hashUN +"' , '" + hashPW + "', '1')");
					stmt.executeUpdate("INSERT INTO physician(idstaff, surname, title, status) VALUES ((SELECT last_insert_id()), 'Rose', 'Dr. med.', 'Oberarzt')");
					
					return true;
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_INSERT.class.getSimpleName(), "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
					
					return false;
				}
			}else{
				return true;
			}				
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_INSERT.class.getSimpleName(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		
	}
	
	public static Boolean Examination(Examination exam){
		stmt = DB.getStatement();
		try {
			stmt.executeUpdate("INSERT INTO examination (id_examtype, id_physician, id_patient, id_billing_type, filename, startDateTime, endDateTime) "
								+ "VALUES ("
									+ "(SELECT idexamination_type FROM examination_type WHERE notation = '" + exam.getType().name() + "'), "
									+ "'" + exam.getPhysician().getId() + "', "
									+ "'" + exam.getPatient().getId() + "', "
									+ "(SELECT idbilling_type FROM billing_type WHERE notation = '" + exam.getBillingType().name() + "'), "
									+ "'" + exam.getDataFile().getPath() + "', "
									+ "'" + java.sql.Timestamp.valueOf(exam.getStart()) + "', " 
									+ "'" + java.sql.Timestamp.valueOf(exam.getEnd()) + "')");
			return true;
								
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public static Boolean Patient(Patient patient){
		stmt = DB.getStatement();
		try {
			DB.getConnection().setAutoCommit(false);
			stmt.executeUpdate("INSERT INTO patient (id_ambulance, id_stationary, firstname) "
								+ "VALUES (" + patient.getAmbulant_id() + ", " //Integer must not be enclosed into parenthesis
								+ patient.getStationary_id() + ", '"
								+ patient.getFirstname() + "')");
								
			stmt.executeUpdate("INSERT INTO patient_extended (id_patient, surname) VALUES (LAST_INSERT_ID(), '" + patient.getSurname() + "')");						
			return true;
								
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    e.getMessage(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		} finally {
			try {
				DB.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Message: failure while setting autoCommit to true /n Class: SQL_INSERT patient", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	
	public static Boolean Patient_Changes(Patient patient){
		stmt = DB.getStatement();
		try {
			
			stmt.executeUpdate("INSERT INTO patient_extended (id_patient, surname) "
								+ "VALUES (" + patient.getId() + ", '" + patient.getSurname() +	"')");								
								
			return true;
								
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    e.getErrorCode() + ": "+ e.getMessage(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		} 
	}
	
/**
 * insert a new physician	
 * @param physician
 * @param onset
 * @return
 */
	public static Integer Physician (Physician physician, LocalDate onset){
		stmt = DB.getStatement();
		
//		String hashUN = MD5.getMD5(physician.getUsername());		
//		String hashPW = MD5.getMD5(physician.getPassword());
		
		try {
			DB.getConnection().setAutoCommit(false);
			stmt.executeUpdate("INSERT INTO staff (firstname, birth, sex, onset) "
								+ "VALUES ('" + physician.getFirstname() + "', '" + Date.valueOf(physician.getBirthday()) +	"', " 
								+ physician.getSexCode() + ", '" + Date.valueOf(onset) + "')");
			
			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
			rs.next();
			Integer id = rs.getInt("ID");
			
			stmt.executeUpdate("INSERT INTO physician(idstaff, surname, title, status, alias) "
								+ "VALUES (LAST_INSERT_ID(), '" + physician.getSurname() + "', '" + physician.getTitle() + "', '"
								+ physician.getStatus() + "', '" + physician.getAlias() + "')");
			
			
								
			return id;
								
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Error_Code: " + e.getErrorCode() + "/n"+ e.getMessage() + "/n Class: SQL_INSERT Physician", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
			return null;
		} finally {
			try {
				DB.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Message: failure while setting autoCommit to true /n Class: SQL_INSERT physician", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
	}
}
