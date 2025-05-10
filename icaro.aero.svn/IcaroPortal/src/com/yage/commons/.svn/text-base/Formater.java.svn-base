/*
 * Created on 13/04/2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
package com.yage.commons;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author martha
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Formater {
	
	static String DATE_FORMAT = "yyyy/MM/dd";
	static String HOUR_FORMAT = "HH";
	static String MINUTE_FORMAT = "mm";
	

	public static String formatDate(Calendar calendar, String pattern){
		String formatedDate = null;
		String DATE_FORMAT = pattern;
		SimpleDateFormat sd = new SimpleDateFormat(DATE_FORMAT);
		if ( calendar != null)
			formatedDate = sd.format(calendar.getTime());
		
		return formatedDate;
	}
	
	public static String formatDate(Date date, String pattern){
		String formatedDate = null;
		String DATE_FORMAT = pattern;
		SimpleDateFormat sd = new SimpleDateFormat(DATE_FORMAT);
		if ( date != null)
			formatedDate = sd.format(date);
		
		return formatedDate;
	}
	
	public static Calendar parseString(String d, String pattern) {          
      DateFormat dateFormat = new SimpleDateFormat(pattern);
      Date date = null;
      GregorianCalendar calendar = null;
      try {       
      	date = dateFormat.parse(d);
		calendar = new GregorianCalendar();
      	calendar.setTime(date);
      } catch (ParseException pe) {
      	
      }
	  return calendar;  
	}  
	
	public static String formatNumber(float number) {
		String formatedNumber = new String("0");
		NumberFormat f = NumberFormat.getInstance();
		if (f instanceof DecimalFormat) {
			((DecimalFormat) f).applyPattern("#,##0.00");
			formatedNumber = f.format(number);
		 }
		return formatedNumber;
		
	}
	
	public static Calendar getFormatedCalendar (Calendar c, String pattern){
		Calendar cal = null;
		String s = Formater.formatDate(c, pattern);
		cal = Formater.parseString(s, pattern);		
		return cal;
	}
	
	public static Date convertToDate(String dateStr ){
		Date date = null;
		DateFormat df = DateFormat.getDateInstance();
		try {
			date = df.parse(dateStr);
		} catch (ParseException pe){
		}
		
		return date;
	}
	
	
	public static Calendar convertToCalendar(String dateStr, String hourStr, String minuteStr){
		Calendar cal = null;
		if (dateStr != null && hourStr!= null && minuteStr!= null){
			String toParse = dateStr + " " + hourStr + ":" + minuteStr;
			String pattern = DATE_FORMAT + " " + HOUR_FORMAT + ":" + MINUTE_FORMAT;  
			cal = parseString(toParse, pattern );
		}
		
		return cal;
	}
	
}
