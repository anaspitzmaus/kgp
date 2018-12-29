package com.rose.kgp.db;

import java.io.File;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.kgp.allocator.Allocator;
import com.rose.kgp.allocator.Clinical_Institution;
import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Patient;
import com.rose.kgp.personnel.Physician;




public class SQL_SELECT {
	static ResultSet rs = null;
	static Statement stmt;
	
	/**
	 * find a physician by its alias
	 * @param alias the alias name (String)
	 * @return the physician, if alias matches a physician, null if not
	 */
	public static Physician physicianByAlias(String alias, LocalDate date){//hand over the physicians exists
		stmt = DB.getStatement();
		Physician physician = null;
		try {
			rs = stmt.executeQuery(
					"SELECT physician.idstaff AS idstaff, idphysician, surname, firstname, onset, sex, status, title "
							+ "FROM physician "
							+ "INNER JOIN "
							+ "(Select MAX(idphysician) as LatestID, idstaff "
							       + "FROM physician "
							       + "Group By idstaff) SubMax "
							+ "ON physician.idphysician = SubMax.LatestID "
							+ "AND physician.idstaff = SubMax.idstaff "
							+ "INNER JOIN staff "
							+ "ON physician.idstaff = staff.idstaff "
							+ "WHERE physician.alias = '" + alias + "' " 
							+ "AND onset <= '" + Date.valueOf(date) + "' "
							+ "AND (expiry IS NULL OR expiry > '" + Date.valueOf(date) + "')");

			while(rs.next()){
				physician = new Physician(rs.getString("surname"), rs.getString("firstname"));
				physician.setAlias(alias);
				physician.setTitle(rs.getString("title"));
				physician.setStatus(rs.getString("status"));
				physician.setOnset(rs.getDate("onset").toLocalDate());
				physician.setId(rs.getInt("idstaff"));
				physician.setSexCode(rs.getInt("sex"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}	
		
		return physician;
	}
	
	/**
	 * find a nurse by its alias
	 * @param alias the alias name (String)
	 * @return the nurse, if alias matches a nurse, null if not
	 */
	public static Nurse nurseByAlias(String alias, LocalDate date){//hand over the nurse exists
		stmt = DB.getStatement();
		Nurse nurse = null;
		try {
			rs = stmt.executeQuery(
					"SELECT nurse.id_staff AS idstaff, nurse.idnurse AS idnurse, nurse.surname AS surname, staff.firstname AS firstname, "
							+ "staff.onset AS onset, staff.sex AS sex, nurse.status AS status "
							+ "FROM nurse "
							+ "INNER JOIN "
							+ "(Select MAX(idnurse) as LatestID, id_staff "
							       + "FROM nurse "
							       + "Group By id_staff) SubMax "
							+ "ON nurse.idnurse = SubMax.LatestID "
							+ "AND nurse.id_staff = SubMax.id_staff "
							+ "INNER JOIN staff "
							+ "ON nurse.id_staff = staff.idstaff "
							+ "WHERE nurse.alias = '" + alias + "' " 
							+ "AND staff.onset <= '" + Date.valueOf(date) + "' "
							+ "AND (staff.expiry IS NULL OR staff.expiry > '" + Date.valueOf(date) + "')");

			while(rs.next()){
				nurse = new Nurse(rs.getString("surname"), rs.getString("firstname"));
				nurse.setAlias(alias);
				nurse.setStatus(rs.getString("status"));
				nurse.setOnset(rs.getDate("onset").toLocalDate());
				nurse.setId(rs.getInt("idstaff"));
				nurse.setSexCode(rs.getInt("sex"));
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}	
		
		return nurse;
	}
	
	/**
	 * returns all physicians that are active at a defined date
	 * @param date the defined LocalDate to check
	 * @return an ArrayList of physicians, if the query failed returns an empty ArrayList
	 */
	public static ArrayList<Physician> activePhysicians(LocalDate date){
		stmt = DB.getStatement();
		ArrayList<Physician> physicians = new ArrayList<Physician>();
		try {
			rs = stmt.executeQuery(
					"SELECT physician.idstaff AS idstaff, idphysician, surname, firstname, physician.alias AS alias, birth, onset, sex, status, title "
					+ "FROM physician "
					+ "INNER JOIN "
					+ "(Select MAX(idphysician) as LatestID, idstaff "
					       + "FROM physician "
					       + "Group By idstaff) SubMax "
					+ "ON physician.idphysician = SubMax.LatestID "
					+ "AND physician.idstaff = SubMax.idstaff "
					+ "INNER JOIN staff "
					+ "ON physician.idstaff = staff.idstaff "
					+ "WHERE onset <= '" + Date.valueOf(date) + "' "
					+ "AND (expiry IS NULL OR expiry > '" + Date.valueOf(date) + "')");
			
			while(rs.next()){
				Physician physician = new Physician(rs.getString("surname"), rs.getString("firstname"));
				physician.setTitle(rs.getString("title"));
				physician.setStatus(rs.getString("status"));
				physician.setId(rs.getInt("idstaff"));
				physician.setSexCode(rs.getInt("sex"));
				physician.setBirthday(rs.getDate("birth").toLocalDate());
				physician.setOnset(rs.getDate("onset").toLocalDate());
				physician.setAlias(rs.getString("alias"));
				physicians.add(physician);
			} 
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\ngetActivePhysicians(LocalDate date)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}	
		
		return physicians;

	}
	
	/**
	 * returns all nurses that are active at a defined date
	 * @param date the defined LocalDate to check
	 * @return an ArrayList of nurses, if the query failed returns an empty ArrayList
	 */
	public static ArrayList<Nurse> activeNurses(LocalDate date){
		stmt = DB.getStatement();
		ArrayList<Nurse> nurses = new ArrayList<Nurse>();
		try {
			rs = stmt.executeQuery(
					"SELECT nurse.id_staff AS idstaff, idnurse, surname, firstname, nurse.alias AS alias, onset, sex, birth "
							+ "FROM nurse "
							+ "INNER JOIN "
							+ "(Select MAX(idnurse) as LatestID, id_staff "
							       + "FROM nurse "
							       + "Group By id_staff) SubMax "
							+ "ON nurse.idnurse = SubMax.LatestID "
							+ "AND nurse.id_staff = SubMax.id_staff "
							+ "INNER JOIN staff "
							+ "ON nurse.id_staff = staff.idstaff "
							+ "WHERE onset <= '" + Date.valueOf(date) + "' "
							+ "AND (expiry IS NULL OR expiry > '" + Date.valueOf(date) + "')");
					
					
			
			while(rs.next()){
				Nurse nurse = new Nurse(rs.getString("surname"), rs.getString("firstname"));
				nurse.setId(rs.getInt("idstaff"));
				nurse.setSexCode(rs.getInt("sex"));
				nurse.setOnset(rs.getDate("onset").toLocalDate());
				nurse.setAlias(rs.getString("alias"));
				nurse.setBirthday(rs.getDate("birth").toLocalDate());
				nurses.add(nurse);
			} 
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\ngetActiveNurses(LocalDate date)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}	
		
		return nurses;

	}

	public static boolean isExamFileStored(File file) {
		stmt = DB.getStatement();
		try {
			rs = stmt.executeQuery(
					 "SELECT * "
					+ "FROM examination "
					+ "WHERE filename = '" + file.getName() + "'");

			if(rs.isBeforeFirst()){
				return true;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}	
		return false;
	}
	
	/**
	 * get a patient by its outpatient id;
	 * @param id, the id of the patient
	 * @return the patient, if no patient matches the id, then return null
	 */
	public static Patient OutPatient(Integer id){
		stmt = DB.getStatement();
		Patient patient = null;
		try {
			rs = stmt.executeQuery(
					 "SELECT patient_extended.surname AS surname, patient.firstname AS firstname "
					+ "FROM patient "
					+ "INNER JOIN patient_extended "
					+ "ON patient.idpatient = patient_extended.id_patient "
					+ "WHERE patient.id_ambulance = " + id + "");

			if(rs.isBeforeFirst()){
				patient = new Patient(rs.getString("surname"), rs.getString("firstname")); 
			
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}	
		return patient;
	}
	
	/**
	 * get a patient by its inpatient id;
	 * @param id, the id of the patient
	 * @return the patient, if no patient matches the id, then return null
	 */
	public static Patient InPatient (Integer id){
		stmt = DB.getStatement();
		Patient patient = null;
		try {
			rs = stmt.executeQuery(
					 "SELECT patient_extended.surname AS surname, patient.firstname AS firstname "
					+ "FROM patient "
					+ "INNER JOIN patient_extended "
					+ "ON patient.idpatient = patient_extended.id_patient "
					+ "WHERE patient.id_stationary = " + id + "");

			if(rs.isBeforeFirst()){
				patient = new Patient(rs.getString("surname"), rs.getString("firstname")); 
			
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}	
		return patient;
	}
	
	/**
	 * get an arrayList of all active allocators
	 * @param onset date (LocalDate) to check if allocator is still active
	 * @return an arrayList of the selected allocators
	 */
	public static ArrayList<Allocator> Allocators (LocalDate onset){
		stmt = DB.getStatement();
		ArrayList<Allocator> allocators = new ArrayList<Allocator>();
		try {
			rs = stmt.executeQuery(
					 "SELECT notation AS notation, short_notation AS shortNotation, city AS city, postal_code AS postalCode, street AS street "
					+ "FROM clinical_institution "
					+ "WHERE allocator = 1 "
					+ "AND onset <= '" + Date.valueOf(onset) + "'");

			if(rs.isBeforeFirst()){
				while(rs.next()){
					Clinical_Institution institution = new Clinical_Institution(rs.getString("notation"), rs.getString("shortNotation"), rs.getString("street"), rs.getString("postalCode"), rs.getString("city"));
					Allocator allocator = new Allocator(institution); 
					allocators.add(allocator);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\nAllocators(LocalDate onset)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}
		return allocators;
	}
	
	/**
	 * get an arrayList of all active clinical institutions
	 * @param onset date (LocalDate) to check if clinical institution is still active
	 * @return an arrayList of the selected clinical institutions
	 */
	public static ArrayList<Clinical_Institution> ClinicalInstitutions (LocalDate onset){
		stmt = DB.getStatement();
		ArrayList<Clinical_Institution> clinicalInstitutions = new ArrayList<Clinical_Institution>();
		try {
			rs = stmt.executeQuery(
					 "SELECT id_clinical_institution AS id, notation AS notation, short_notation AS shortNotation, city AS city, postal_code AS postalCode, street AS street "
					+ "FROM clinical_institution "
					+ "WHERE onset <= '" + Date.valueOf(onset) + "'");

			if(rs.isBeforeFirst()){
				while(rs.next()){
					Clinical_Institution institution = new Clinical_Institution(rs.getString("notation"), rs.getString("shortNotation"), rs.getString("street"), rs.getString("postalCode"), rs.getString("city"));
					institution.setId(rs.getInt("id"));
					clinicalInstitutions.add(institution);
				}
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\nClinicalInstitutions(LocalDate onset)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}
		return clinicalInstitutions;
	}
	
	/**
	 * check if a sensis file is stored in schema examination, independent on its path  
	 * @param file to be checked
	 * @return true if the file is already stored in schema examination, false if not
	 */
	public static Boolean IsFileStored(File file) {
		stmt = DB.getStatement();
		try {
			rs = stmt.executeQuery(
					 "SELECT filename "
					 + "FROM examination "
					 + "WHERE filename = '" + file.getName() + "'");	
			
			if(rs.isBeforeFirst()) {//if there is a resultSet
				return true;				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName() + "\n\nIsFileStored(File file)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}
		return false;
	}
}
