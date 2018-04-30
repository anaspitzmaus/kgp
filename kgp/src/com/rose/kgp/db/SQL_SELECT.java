package com.rose.kgp.db;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.kgp.personnel.Patient;
import com.rose.kgp.personnel.Physician;


public class SQL_SELECT {
	static ResultSet rs = null;
	static Statement stmt;
	
	public static Physician getPhysicianByAlias(String alias){//hand over the physicians exists
		stmt = DB.getStatement();
		Physician physician = null;
		try {
			rs = stmt.executeQuery(
					 "SELECT firstname, surname, title, status "
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
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_SELECT.class.getSimpleName(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
		}	
		
		return physician;
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
