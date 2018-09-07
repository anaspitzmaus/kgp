package com.rose.kgp.db;


import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.kgp.allocator.Clinical_Institution;



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
}
