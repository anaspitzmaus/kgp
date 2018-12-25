package com.rose.kgp.data_exchange;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import com.rose.kgp.db.SQL_SELECT;

public class Sensis implements DataOutput{
	Path folderPath;
	final static Charset ENCODING_ISO_8859_1 = StandardCharsets.ISO_8859_1;
	final static Charset ENCODING_UTF_16 = StandardCharsets.UTF_16;
	HashMap<String, HashMap<String, ArrayList<String>>>values = null;
	
	/**
	 * constructor
	 * @param path needs to be a directory, not a full file name;
	 */
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
	
	/**
	 * lists all files with a defined extension of a folder and returns it as an arrayList
	 * @param folder
	 * @return an arrayList with the files of the folder
	 */
	public ArrayList<File> getFilesForFolder(String extension, LocalDate startDate, LocalDate endDate) {
		ArrayList<File> files = new ArrayList<File>();
		File folder = this.folderPath.toFile();
	    for (final File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	        	Path filePath = fileEntry.toPath();
	        	LocalDate fileCreationDate = null;
	        	try {
					BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
					FileTime fileCreationTime = attr.creationTime();
					DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
					String created = df.format(fileCreationTime.toMillis());
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");					
					//convert String to LocalDate
					fileCreationDate = LocalDate.parse(created, formatter);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	LocalDate fileDate =
	        		    Instant.ofEpochMilli(fileEntry.lastModified()).atZone(ZoneId.systemDefault()).toLocalDate();
	        	if(fileEntry.getName().endsWith(extension) && !fileCreationDate.isAfter(endDate) && !fileCreationDate.isBefore(startDate)){
	        		files.add(fileEntry);
	        	}
	           // FilenameUtils.getExtension(fileEntry.getName()); //returns the extension of a file (Apache Commons IO)
	        }
	    }
	    return files;
	}
	
	/**
	 * read the file and stores its content into an hashMap
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public HashMap<String, HashMap<String, ArrayList<String>>> readExamFile(String fileName) throws IOException {
		
		values = new HashMap<String, HashMap<String, ArrayList<String>>>();
		String[] fields = null;
		String [] group = null;
		Path filePath = folderPath.resolve(fileName);
	    
	    try (BufferedReader reader = Files.newBufferedReader(filePath, ENCODING_UTF_16)){
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
	    }catch(MalformedInputException e){
	    	try(BufferedReader reader = Files.newBufferedReader(filePath, ENCODING_ISO_8859_1)){
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
	    }
	    return this.values;
	}
	
	public HashMap<String, HashMap<String, ArrayList<String>>> getExamData(){
		return this.values;
	}
	
	public String getExamType(){
		System.out.println(values);
		String examType = values.get("STUDY").get("STUDESC").get(0);
		return examType;
	}
	
	class SensisException extends IOException{
		
		
		
	}
	
	
}
