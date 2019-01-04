package com.rose.kgp.db;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.kgp.MD5;
import com.rose.kgp.administration.TreatmentCase;
import com.rose.kgp.allocator.Clinical_Institution;
import com.rose.kgp.examination.Examination;
import com.rose.kgp.personnel.Nurse;
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
			DB.getConnection().setAutoCommit(true);
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
	
	/**
	 * insert an examination 
	 * @param exam instance of an examination
	 * @param treatmentCaseId the id of the corresponding treatment case
	 * @return true, if examination could be inserted, otherwise false
	 */
	public static Boolean Examination(Examination exam, Integer treatmentCaseId){
		stmt = DB.getStatement();
		
		 Calendar calendarStart = Calendar.getInstance();
		 Calendar calendarEnd = Calendar.getInstance();
	      calendarStart.clear();
	      calendarEnd.clear();
	      //assuming year/month/date information is not important
	      calendarStart.set(0, 0, 0, exam.getStart().getHour(), exam.getStart().getMinute(), exam.getStart().getSecond());
	      calendarEnd.set(0, 0, 0, exam.getEnd().getHour(), exam.getEnd().getMinute(), exam.getEnd().getSecond());
	      java.sql.Timestamp timestampStart = new java.sql.Timestamp(calendarStart.getTimeInMillis());
	      java.sql.Timestamp timestampEnd = new java.sql.Timestamp(calendarEnd.getTimeInMillis());
	      if(exam.getExaminer() instanceof Physician && exam.getDataFile() instanceof File && exam.getStart() instanceof LocalDateTime && exam.getEnd() instanceof LocalDateTime){
	    	  try {
	  			DB.getConnection().setAutoCommit(true);
	  			stmt.executeUpdate("INSERT INTO examination (id_examtype, id_physician, id_treatmentCase, filename, startDateTime, endDateTime) "
	  								+ "VALUES ("
	  									+ "(SELECT idexamination_type FROM examination_type WHERE notation = '" + exam.getStudyType().name() + "'), "
	  									+ "'" + exam.getExaminer().getId() + "', "
	  									+ "" + treatmentCaseId + ", "
	  									+ "'" + exam.getDataFile().getPath() + "', "
	  									+ "'" + timestampStart + "', " 
	  									+ "'" + timestampEnd + "')");
	  			return true;
	  								
	  		} catch (SQLException e) {
	  			
	  			return false;
	  		} 
	      }else{
	    	  return false;
	      }
		
	}
	
	/**
	 * insert a patient 
	 * the primary key id of the patient is set to the patient
	 * @param patient
	 * @return
	 */
	public static Integer Patient(Patient patient) throws SQLException{
		stmt = DB.getStatement();
		Integer lastInsertID = null;
		
			DB.getConnection().setAutoCommit(false);
			stmt.executeUpdate("INSERT INTO patient (id_outpatient, id_inpatient, firstname, birthday) "
								+ "VALUES (" + patient.getOutID() + ", " //Integer must not be enclosed into parenthesis
								+ patient.getInID() + ", '"//Integer must not be enclosed into parenthesis
								+ patient.getFirstname() + "', '"
								+ Date.valueOf(patient.getBirthday()) + "')");
								
			stmt.executeUpdate("INSERT INTO patient_extended (id_patient, surname) VALUES (LAST_INSERT_ID(), '" + patient.getSurname() + "')");						
			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
			rs.next();
			lastInsertID = rs.getInt("ID");
			patient.setId(lastInsertID); //set the inserted id as id of the patient
							
			DB.getConnection().setAutoCommit(true);
			return lastInsertID;		
	}
	
	
	
	public static Boolean Patient_Changes(Patient patient){
		stmt = DB.getStatement();
		
		try {
			DB.getConnection().setAutoCommit(true);
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
			
			
			physician.setId(id);					
			return id;
								
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "Error_Code: " + e.getErrorCode() + "/n"+ e.getMessage() + "/n Class: SQL_INSERT Physician(Physician physician, LocalDate onset)", "SQL Exception warning",
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
	
	/**
	 * insert a new nurse	
	 * @param nurse
	 * @param onset
	 * @return
	 */
		public static Integer Nurse (Nurse nurse, LocalDate onset){
			stmt = DB.getStatement();
			
//			String hashUN = MD5.getMD5(physician.getUsername());		
//			String hashPW = MD5.getMD5(physician.getPassword());
			
			try {
				DB.getConnection().setAutoCommit(false);
				stmt.executeUpdate("INSERT INTO staff (firstname, birth, sex, onset) "
									+ "VALUES ('" + nurse.getFirstname() + "', '" + Date.valueOf(nurse.getBirthday()) +	"', " 
									+ nurse.getSexCode() + ", '" + Date.valueOf(onset) + "')");
				
				ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
				rs.next();
				Integer id = rs.getInt("ID");
				
				stmt.executeUpdate("INSERT INTO nurse(id_staff, surname, status, alias) "
									+ "VALUES (LAST_INSERT_ID(), '" + nurse.getSurname() + "', '" 
									+ nurse.getStatus() + "', '" + nurse.getAlias() + "')");
				
				
				nurse.setId(id);					
				return id;
									
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Error_Code: " + e.getErrorCode() + "/n"+ e.getMessage() + "/n Class: SQL_INSERT Nurse", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				return null;
			} finally {
				try {
					DB.getConnection().setAutoCommit(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
						    "Message: failure while setting autoCommit to true /n Class: SQL_INSERT Nurse", "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
				}
			}
		}

	public static Boolean ClinicalInstitution(Clinical_Institution institution) {
		stmt = DB.getStatement();
		try {
			DB.getConnection().setAutoCommit(true);
			stmt.executeUpdate("INSERT INTO clinical_institution (notation, onset, short_notation, street, postal_code, city, allocator) "
								+ "VALUES ('" + institution.getNotation() + "', "
										+ "'" + Date.valueOf(LocalDate.now()) +	"', "
										+ "'" + institution.getShortNotation() + "', "
										+ "'" + institution.getStreet() + "', "
										+ "'" + institution.getPostalCode() + "', "
										+ "'" + institution.getCity() + "', "
										+ "'" + 1 + "')");								
								
			return true;
								
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    e.getErrorCode() + ": "+ e.getMessage()+ "/n/n Class: SQL_INSERT Boolean ClinicalInstitution(Clinical_Institution institution)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		}
	}

	/**
	 * insert a treatmentCase
	 * a treatmentCase is inserted at database only if the ttreatmentCase contains a patient and the patient has an id
	 * @param treatmentCase
	 */
	
	public static Integer TreatmentCase(TreatmentCase treatmentCase) throws SQLException{
		
		Integer treatment_id = null;
		DB.getConnection().setAutoCommit(true);
		//insert treatmentCase
		
		if(treatmentCase.getPatient() instanceof Patient && treatmentCase.getPatient().getId() != null){//check, if treatmentCase contains a patient
			stmt = DB.getStatement();
			
			stmt.executeUpdate("INSERT INTO treatment_case (case_nr, id_patient, id_billing_type) "
									+ "VALUES (" + treatmentCase.getCaseNr() + ", "
									+ treatmentCase.getPatient().getId() + ", "
									+ "(SELECT idbilling_type FROM billing_type WHERE notation = '" + treatmentCase.getAccountingType().name() + "'))");	
			ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID() AS ID");
			if(rs.isBeforeFirst()){
				rs.next();
				treatment_id = rs.getInt("ID");
			}
			//DB.getConnection().setAutoCommit(true);
	}
			return treatment_id;	  
		
	}

	/**
	 * insert the file of incomplete or corrupt sensis studies
	 * @param file the file of the study
	 */
	public static void corruptSensisStudy(File file) {
		
		stmt = DB.getStatement();
		try {
			DB.getConnection().setAutoCommit(true);
			stmt.executeUpdate("INSERT INTO corruptstudy (file) "
								+ "VALUES ('" + file.getName() + "')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
	}
	
	
}
