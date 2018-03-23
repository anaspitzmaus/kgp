package com.rose.kgp.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.rose.kgp.MD5;


public class SQL_INSERT {
	
	static Statement stmt;
	static ResultSet rs;
	/**
	 * inserts an administrator if no administrator is stored in DB
	 * necessary for running the application because the login needs a registered user
	 * @return true if an administrator is already registered or the administrator could be registered now; false if there is no registered administrator and the standard administrator couldn't be registered 
	 */
	public static boolean Admin(){
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
					stmt.executeUpdate("INSERT INTO staff (surname, firstname, sex, alias, onset, username, password, admin) VALUES ('Rose', 'Ekkehard', '0', 'OA Rose', '2015-06-30', '" + hashUN +"' , '" + hashPW + "', '1')");
					stmt.executeUpdate("INSERT INTO physician(idstaff, title, graduation) VALUES ((SELECT last_insert_id()), 'Dr. med.', 'Oberarzt')");
					
					return true;
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(new JFrame(),
						    e.getMessage(), "SQL Exception warning",
						    JOptionPane.WARNING_MESSAGE);
					return false;
				}
			}else{
				return true;
			}				
			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(new JFrame(),
				    e.getMessage(), "SQL Exception warning",
				    JOptionPane.WARNING_MESSAGE);
			return false;
		}		
		
	}
}
