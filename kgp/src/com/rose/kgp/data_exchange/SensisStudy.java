package com.rose.kgp.data_exchange;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.MalformedInputException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;


public class SensisStudy extends Study{
	Sensis sensis;
	File file;
	
	final static Charset ENCODING_ISO_8859_1 = StandardCharsets.ISO_8859_1;
	final static Charset ENCODING_UTF_16 = StandardCharsets.UTF_16;
	
	public Sensis getSensis(){
		return this.sensis;
	}
	
	public SensisStudy(Path sensisFilesPath){
		sensis = new Sensis(sensisFilesPath);	
		dataValues = new HashMap<String, HashMap<String, ArrayList<String>>>();
	}
	
	public void readSensisFile(File file) throws IOException{
		String[] fields = null;
		String [] group = null;
		this.file = file;
		
		try (BufferedReader reader = Files.newBufferedReader(this.file.toPath(), ENCODING_UTF_16)){
		      String line = null;
		      while ((line = reader.readLine()) != null) {
		    	 if(line.startsWith("Group")){	
		    		 group = line.split(":");
		    		 dataValues.put(group[1], new HashMap<String, ArrayList<String>>());
		    		
		    	 }else if(line.startsWith("Fields")){
		    		 fields = line.split("³", -2);
		    		 for(int i = 1; i<fields.length; i++){
		    			 dataValues.get(group[1]).put(fields[i], new ArrayList<String>()); 	    			 
		    		 }
		    	 }else if(!line.isEmpty()){
		    		 String[] fieldValues = line.split("³", -2);
		    		 for(int i = 1; i<fieldValues.length; i++){
		    			 dataValues.get(group[1]).get(fields[i]).add(fieldValues[i]);
		    		 }
		    	 }
		      }      	
		    }catch(MalformedInputException e){
		    	try(BufferedReader reader = Files.newBufferedReader(this.file.toPath(), ENCODING_ISO_8859_1)){
		    		 String line = null;
		   	      while ((line = reader.readLine()) != null) {
		   	    	 if(line.startsWith("Group")){	
		   	    		 group = line.split(":");
		   	    		 dataValues.put(group[1], new HashMap<String, ArrayList<String>>());
		   	    		
		   	    	 }else if(line.startsWith("Fields")){
		   	    		 fields = line.split("³", -2);
		   	    		 for(int i = 1; i<fields.length; i++){
		   	    			 dataValues.get(group[1]).put(fields[i], new ArrayList<String>()); 	    			 
		   	    		 }
		   	    	 }else if(!line.isEmpty()){
		   	    		 String[] fieldValues = line.split("³", -2);
		   	    		 for(int i = 1; i<fieldValues.length; i++){
		   	    			 dataValues.get(group[1]).get(fields[i]).add(fieldValues[i]);
		   	    		 }
		   	    	 }
		   	      }      	
		    	}
		    }
	}

	

	
	
	
}
