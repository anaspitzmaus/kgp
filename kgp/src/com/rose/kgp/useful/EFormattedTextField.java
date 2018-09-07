package com.rose.kgp.useful;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFormattedTextField;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

public class EFormattedTextField extends JFormattedTextField {
	
	public EFormattedTextField() {
		super();
		String dateFormat = "dd.MM.yyyy";
		DateFormat displayFormat = new SimpleDateFormat(dateFormat);
		DateFormatter displayFormatter = new DateFormatter(displayFormat);
		DateFormat editFormat = new SimpleDateFormat(dateFormat.replace('.', '/'));
		DateFormatter editFormatter = new DateFormatter(editFormat);
		DefaultFormatterFactory factory = new DefaultFormatterFactory(displayFormatter, displayFormatter, editFormatter);
		setFormatterFactory(factory);
			
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7738996506639342312L;
	
	

}
