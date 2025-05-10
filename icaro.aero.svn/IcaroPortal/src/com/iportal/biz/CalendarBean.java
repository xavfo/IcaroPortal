package com.iportal.biz;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * La clase CalendarBean permite para crear mes calendario por mes. 
 * @author YAGE
 * @version 1.0 
 * */
public class CalendarBean {
	
	private final static int DAYS_IN_MONTH[] = {
			31, 28, 31, 30,	/* jan feb mar apr */
			31, 30, 31, 31, /* may jun jul aug */
			30, 31, 30, 31	/* sep oct nov dec */
	};

	private GregorianCalendar calendar;
	
	/**
     *  Crea una nueva instancia de CalendarBean
	 */
	public CalendarBean() {
		calendar = new GregorianCalendar();
	}
	
	/**
	 * @return	el año actual
	 */
	public Integer getYear() {
		return new Integer(calendar.get(Calendar.YEAR));
	}
	
	/**
	 * @return	el mes actual
	 */
	public Integer getMonth() {
		return new Integer(calendar.get(Calendar.MONTH));
	}
	
	/**
	 * @param year
	 */
	public void setYear(Integer year) {
		calendar.set(Calendar.YEAR, year.intValue());
	}

	/**
	 * @param year
	 */
	public void setMonth(Integer month) {
		calendar.set(Calendar.MONTH, month.intValue());
	}

	/**
	 * @return los dias del mes
	 */
	public String[][] getDays() {
		calendar.set(Calendar.DATE, 1);
		
		int leadGap = calendar.get(Calendar.DAY_OF_WEEK)-1;
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int daysInMonth = DAYS_IN_MONTH[month];
		
		if (calendar.isLeapYear(year) && month == 1)
			++ daysInMonth;
		
		String[][] days = new String[6][7];
		int day = 1;
		
		for (int d = leadGap; d < 7; d++) {
			days[0][d] = String.valueOf(day);
			day ++;
		}
		for (int w = 1; w < 6; w++) {
			for (int d = 0; d < 7; d++) {
				if (day <= daysInMonth) {
					days[w][d] = String.valueOf(day);
					day ++;
				}
			}
		}
		
		return days;
	}
}