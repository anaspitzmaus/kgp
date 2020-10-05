package com.rose.kgp.db;


import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.kgp.allocator.Clinical_Institution;
import com.rose.kgp.material.Manufacturer;
import com.rose.kgp.material.PM;
import com.rose.kgp.personnel.Nurse;
import com.rose.kgp.personnel.Physician;



public class SQL_UPDATE {
	
	static Statement stmt;
	
	public static Boolean ClinicalInstitution(Clinical_Institution institution){
		
			stmt = DB.getStatement();
			try {
											
				stmt.executeUpdate("UPDATE clinical_institution SET "
						+ "notation = '" + institution.getNotation() + "', "
						+ "short_notation = '" + institution.getShortNotation() + "', "
						+ "city = '" + institution.getCity() + "', "
						+ "postal_code = '" + institution.getPostalCode() + "', "
						+ "street = '" + institution.getStreet() + "'"
						+ "WHERE id_clinical_institution = " + institution.getId() + "");
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean ClinicalInstitution(Clinical_Institution institution)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				return false;
			}
		
	}

	/**
	 * update a physicians data
	 * @param physician
	 * @return
	 */
	public static Boolean Physician(Physician physician) {
		stmt = DB.getStatement();
		try {
			DB.getConnection().setAutoCommit(false);		
			stmt.executeUpdate("UPDATE staff SET "
					+ "firstname = '" + physician.getFirstname() + "', "
					+ "birth = '" + Date.valueOf(physician.getBirthday()) + "', "
					+ "sex = '" + physician.getSexCode() + "', "
					+ "onset = '" + Date.valueOf(physician.getOnset()) + "', "
					+ "alias = '" + physician.getAlias() + "'"
					+ "WHERE idstaff = " + physician.getId() + "");
			
			stmt.executeUpdate("INSERT INTO physician(idstaff, surname, title, status, alias) "
					+ "VALUES (" + physician.getId() + ", '" + physician.getSurname() + "', '" + physician.getTitle() + "', '"
					+ physician.getStatus() + "', '" + physician.getAlias() + "')");
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean Physician(Physician physician)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		} finally {
			try {
				DB.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Message: failure while setting autoCommit to true /n Class: SQL_Update physician", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
	}
	/**
	 * update a nurses data
	 * @param nurse
	 * @return
	 */
	public static Boolean Nurse(Nurse nurse) {
		stmt = DB.getStatement();
		try {
			DB.getConnection().setAutoCommit(false);		
			stmt.executeUpdate("UPDATE staff SET "
					+ "firstname = '" + nurse.getFirstname() + "', "
					+ "birth = '" + Date.valueOf(nurse.getBirthday()) + "', "
					+ "sex = '" + nurse.getSexCode() + "', "
					+ "onset = '" + Date.valueOf(nurse.getOnset()) + "', "
					+ "alias = '" + nurse.getAlias() + "'"
					+ "WHERE idstaff = " + nurse.getId() + "");
			
			stmt.executeUpdate("INSERT INTO nurse(id_staff, surname, status, alias) "
					+ "VALUES (" + nurse.getId() + ", '" + nurse.getSurname() + "', '"
					+ nurse.getStatus() + "', '" + nurse.getAlias() + "')");
			return true;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(new JFrame(),
					"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean Nurse(Nurse nurse)", "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		} finally {
			try {
				DB.getConnection().setAutoCommit(true);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(new JFrame(),
					    "Message: failure while setting autoCommit to true /n Class: SQL_Update nurse", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}

	/**
	 * udate a manufacturer
	 * @param manufacturer
	 * @return true, if manufacturer could be updated, else false
	 */
	public static Boolean Manufacturer(Manufacturer manufacturer) {
		stmt = DB.getStatement();
		if(manufacturer.getId() != null) {
			try {
				stmt.executeUpdate("UPDATE manufacturer SET "
						+ "notation = '" + manufacturer.getNotation() + "', "
						+ "contact_person = '" + manufacturer.getContact_person() + "', "
						+ "mobil = '" + manufacturer.getMobile() + "'"
						+ "WHERE idmanufacturer = " + manufacturer.getId() + "");
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean Manufacturer(Manufacturer manufacturer)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			return false;
		}
	}
	
	/**
	 * update a pacemaker
	 * @param pmSel, the pacemaker, that has to be updated
	 * @return true if update was successful, else return false
	 */

	public static Boolean Pacemaker(PM pmSel) {
		stmt = DB.getStatement();
		if(pmSel.getId() != null) {
			try {
				stmt.executeUpdate("UPDATE pm_implant SET "
						+ "serialNr = '" + pmSel.getSerialNr() + "', "
						+ "expiry = '" + Date.valueOf(pmSel.getExpireDate()) + "', "
						+ "notice = '" + pmSel.getNotice() + "'"
						+ "WHERE id_pm_implant = " + pmSel.getId() + "");
				return true;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(new JFrame(),
						"Message:\n" +  e.getMessage() + "\n\nClass:\n" + SQL_UPDATE.class.getSimpleName() + "\n\nBoolean Pacemaker(PM pmSel)", "SQL Exception warning",
					    JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}else {
			return false;
		}
		
	}
}
