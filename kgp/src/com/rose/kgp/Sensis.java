package com.rose.kgp;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import com.rose.kgp.db.SQL_SELECT;
import com.rose.kgp.examination.LeftHeartCatheter;
import com.rose.kgp.personnel.Patient;

public class Sensis {
	Path folderPath;
	final static Charset ENCODING = StandardCharsets.ISO_8859_1;
	HashMap<String, HashMap<String, ArrayList<String>>>values = null;
	
	public Sensis(String path) {
		this.folderPath = Paths.get(path);	
		listFilesForFolder(this.folderPath.toFile(), ".HIS");
	}
	
	/**
	 * lists all files of a folder and returns it as an arrayList
	 * @param folder
	 * @return an arrayList with the files of the folder
	 */
	public ArrayList<File> listFilesForFolder(final File folder) {
		ArrayList<File> files = new ArrayList<File>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {	        	
	        	files.add(fileEntry);
	        	
	           // FilenameUtils.getExtension(fileEntry.getName()); //returns the extension of a file (Apache Commons IO)
	        }
	    }
	    return files;
	}
	
	public ArrayList<File> listFilesNotStoredAtDB(final File folder){
		ArrayList<File> files = new ArrayList<File>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	if(!SQL_SELECT.isExamFileStored(fileEntry)){//if file is not stored at database
	        		files.add(fileEntry);
	        	}
	        	
	           // FilenameUtils.getExtension(fileEntry.getName()); //returns the extension of a file (Apache Commons IO)
	        }
	    }
	    return files;
	}
	
	/**
	 * lists all files with a defined extension of a folder and returns it as an arrayList
	 * @param folder
	 * @return an arrayList with the files of the folder
	 */
	public ArrayList<File> listFilesForFolder(final File folder, String extension) {
		ArrayList<File> files = new ArrayList<File>();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	if(fileEntry.getName().endsWith(extension)){
	        		files.add(fileEntry);
	        	}
	           // FilenameUtils.getExtension(fileEntry.getName()); //returns the extension of a file (Apache Commons IO)
	        }
	    }
	    return files;
	}
	
	public HashMap<String, HashMap<String, ArrayList<String>>> readExamFile(String fileName) throws IOException {
		
		values = new HashMap<String, HashMap<String, ArrayList<String>>>();
		String[] fields = null;
		String [] group = null;
		Path filePath = folderPath.resolve(fileName);
	    
	    try (BufferedReader reader = Files.newBufferedReader(filePath, ENCODING)){
	      String line = null;
	      while ((line = reader.readLine()) != null) {
	    	 if(line.startsWith("Group")){	
	    		 group = line.split(":");
	    		 values.put(group[1], new HashMap<String, ArrayList<String>>());
	    		
	    	 }else if(line.startsWith("Fields")){
	    		 fields = line.split("³", -2);
	    		 for(int i = 1; i<fields.length; i++){
	    			 values.get(group[1]).put(fields[i], new ArrayList<String>()); 	    			 
	    		 }
	    	 }else if(!line.isEmpty()){
	    		 String[] fieldValues = line.split("³", -2);
	    		 for(int i = 1; i<fieldValues.length; i++){
	    			 values.get(group[1]).get(fields[i]).add(fieldValues[i]);
	    		 }
	    	 }
	      }      	
	    }
	    return this.values;
	}
	
	public HashMap<String, HashMap<String, ArrayList<String>>> getExamination(){
		return this.values;
	}
	
	public String getExamType(){
		String examType = values.get("STUDY").get("STUDESC").get(0);
		return examType;
	}
	
	class SensisException extends IOException{
		
		
		
	}
	
	
}
