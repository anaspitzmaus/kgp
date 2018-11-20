package com.rose.kgp.useful;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateMethods {

	public static LocalDate ConvertDateToLocalDate(Date date){
		Date input = date;
		Instant instant = input.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate ldate= zdt.toLocalDate();
		return ldate;
	}
	
	public static Date ConvertLocalDateToDate(LocalDate localDate){
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}
	
	public static LocalTime ConvertDateToLocalTime(Date date){
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Instant instant = Instant.ofEpochMilli(cal.getTimeInMillis());
		LocalTime convert = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
		return convert;
	}
	
	public static LocalDate getDateFromTimestamp(long timestamp) {
		LocalDate date =
			    Instant.ofEpochMilli(timestamp).atZone(ZoneId.systemDefault()).toLocalDate();
	    return date;
	}
	
	public static LocalDateTime getDateTimeFromTimestamp(long timestamp) {
	    if (timestamp == 0)
	      return null;
	    return LocalDateTime.ofInstant(Instant.ofEpochSecond(timestamp), TimeZone
	        .getDefault().toZoneId());
	}
}
