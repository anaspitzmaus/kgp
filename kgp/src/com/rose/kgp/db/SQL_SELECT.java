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

import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Patient;
import com.rose.kgp.personnel.Physician;




public class SQL_SELECT {
	static ResultSet rs = null;
	static Statement stmt;
	
	public static Physician physicianByAlias(String alias){//hand over the physicians exists
		stmt = DB.getStatement();
		Physician physician = null;
		try {
			rs = stmt.executeQuery(
					 "SELECT firstname, surname, title, status, onset "
					+ "FROM physician "
					+ "INNER JOIN staff " //Right join??
					+ "ON physician.idstaff = staff.idstaff "
					+ "WHERE idphysician = (SELECT MAX(idphysician) AS idphysician "
						+ "FROM physician "	
						+ "WHERE alias = '" + alias + "')");

			while(rs.next()){
				physician = new Physician(rs.getString("surname"), rs.getString("firstname"));
				physician.setAlias(alias);
				physician.setTitle(rs.getString("title"));
				physician.setStatus(rs.getString("status"));
				physician.setOnset(rs.getDate("onset").toLocalDate());
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}	
		
		return physician;
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
					 			"SELECT staff.idstaff AS idstaff, firstname, surname, title, status, sex, onset "
								+ "FROM physician "
								+ "INNER JOIN staff " //Right join??
								+ "ON physician.idstaff = staff.idstaff "
								+ "WHERE onset <= '" + Date.valueOf(date) + "' "
								+ "AND (expiry IS NULL OR expiry > '" + Date.valueOf(date) + "')");
			
			while(rs.next()){
				Physician physician = new Physician(rs.getString("surname"), rs.getString("firstname"));
				physician.setTitle(rs.getString("title"));
				physician.setStatus(rs.getString("status"));
				physician.setId(rs.getInt("idstaff"));
				physician.setSexCode(rs.getInt("sex"));
				physician.setOnset(rs.getDate("onset").toLocalDate());
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
					 			"SELECT staff.idstaff AS idstaff, firstname, surname, status, sex, onset "
								+ "FROM nurse "
								+ "INNER JOIN staff " //Right join??
								+ "ON nurse.id_staff = staff.idstaff "
								+ "WHERE onset <= '" + Date.valueOf(date) + "' "
								+ "AND (expiry IS NULL OR expiry > '" + Date.valueOf(date) + "')");
			
			while(rs.next()){
				Nurse nurse = new Nurse(rs.getString("surname"), rs.getString("firstname"));
				nurse.setId(rs.getInt("idstaff"));
				nurse.setSexCode(rs.getInt("sex"));
				nurse.setOnset(rs.getDate("onset").toLocalDate());
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
}
