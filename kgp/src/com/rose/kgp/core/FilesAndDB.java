package com.rose.kgp.core;

import java.io.File;

import com.rose.kgp.db.SQL_SELECT;

public class FilesAndDB {
	
	public FilesAndDB() {
		// TODO Auto-generated constructor stub
	}
	
	protected Boolean IsFileStoredInDB(File file) {
		return SQL_SELECT.IsFileStored(file);
	}
}
