package com.rose.kgp.data_exchange;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import com.rose.kgp.personnel.Patient;

public class FileContent {
	private File file;
	private Patient patient;
	final static Charset ENCODING_UTF_16 = StandardCharsets.UTF_16;
	final static Charset ENCODING_ISO_8859_1 = StandardCharsets.ISO_8859_1;
	
	protected Patient getPatient(){
		return this.patient;
	}
	
	public File getFile() {
		return this.file;
	}
	
	public FileContent(File file) {
		this.file = file;
		try {
			getPatientFromFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void getPatientFromFile()throws IOException{
		Boolean found = false;
		try (BufferedReader reader = Files.newBufferedReader(file.toPath(), ENCODING_UTF_16)){
			String line = null;
		      
		      while ((line = reader.readLine()) != null || found) {
		    	if(line.startsWith("Group:PATIENT")){		    		 
		    		readPatient(reader);
		    	}		    	 
		      }      	
		    
		 }catch(MalformedInputException e){
			   try(BufferedReader reader = Files.newBufferedReader(file.toPath(), ENCODING_ISO_8859_1)){
				   String line = null;
			   	    while ((line = reader.readLine()) != null || found) {
			   	    	if(line.startsWith("Group:PATIENT")){		    		 
				    		readPatient(reader);
				    	}
			   	    }
			   
			   }
		    }
	}
	
	private void readPatient(BufferedReader reader){
		String line = null;
		String[] fields = null;
		try {			
			reader.readLine();
			line = reader.readLine();
			fields = line.split("³", -2);
			this.patient = new Patient(fields[2], fields[3]);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
