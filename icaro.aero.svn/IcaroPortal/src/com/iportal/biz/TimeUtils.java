package com.iportal.biz;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class TimeUtils {
	
	private static Log logger = LogFactory.getLog(TimeUtils.class);
	public TimeUtils() {
		super();
	}

	public static void printNow(String message) {
		if (message == null)
			message = "";
		Calendar calendar = new GregorianCalendar();
		logger.info(message +": "+ calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND) + " " + calendar.get(Calendar.MILLISECOND));
	}
}
