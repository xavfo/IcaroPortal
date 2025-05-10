/*
 * Copyright © Yage. All Rights Reserved.
 *
 * YAGE evolucion digital
 * Av. Brasil 951 y Mariano Echeverría
 * Quito-Ecuador
 * www.yage.com.ec
 */
package com.yage.commons;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * DateFormatUtils.
 * Extends Jakarta Commons Lang DateFormatUtils v2.0
 * 
 * @author  YAGE (jtite)
 * @version 1.0
 *
 */
public class DateFormatUtils extends
        org.apache.commons.lang.time.DateFormatUtils {

    
    /**
     * Creates a new instance of DateFormatUtils
     */
    public DateFormatUtils() {
        super();
    }
    
    
    /**
     * <p>Parse a String into a date/time specific pattern in a locale.</p>
     * 
     * @param date  the String to parse
     * @param pattern  the pattern to use to format the date
     * @return the parsed date
     */
    public static Date parseToDate(String source, String pattern) {
        return parseToDate(source, pattern, null);
    }

    /**
     * <p>Parse a String into a date/time specific pattern in a locale.</p>
     * 
     * @param date  the String to parse
     * @param pattern  the pattern to use to format the date
     * @param locale  the locale to use, may be <code>null</code>
     * @return the parsed date
     */
    public static Date parseToDate(String source, String pattern, Locale locale) {
       final DateFormat formatter; 
       Date date = null;
       
       try {
           if (locale != null) {
               formatter = new SimpleDateFormat(pattern, locale);
           } else {
               formatter = new SimpleDateFormat(pattern);
           }
           date = formatter.parse(source);
       } catch (Exception e) {
       }
       
       return date;
    }
    /**
     * Parse a String into a Calendar with a date/time specific pattern 
     * @param source
     * @param pattern
     * @return
     */
    public static Calendar parseToCalendar(String source, String pattern) {          
          DateFormat dateFormat = new SimpleDateFormat(pattern);
          Date date = null;
          GregorianCalendar calendar = null;
          if (source != null){
              try {       
                date = dateFormat.parse(source);
                calendar = new GregorianCalendar();
                calendar.setTime(date);
              } catch (ParseException pe) {
              }
          }
          return calendar;  
     }  
}
