package com.xplorethis.util;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;

/**
 * The Class ApplicationUtil.
 */
public class ApplicationUtil {
	
	/** The Constant YES. */
	public static final String YES = "YES";

	/**
	 * Instantiates a new application util.
	 */
	private ApplicationUtil() {
		
	}

	/**
	 * Gets the exception stack trace.
	 * @param exception the exception
	 * @return the error stack trace
	 */
	public static String getExceptionStackTrace(Exception exception) {
		StringWriter sw = new StringWriter();
		exception.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	/**
	 * Gets the error stack trace.
	 * Gets the error stack trace.
	 * @param error the error
	 * @return the error stack trace
	 */
	public static String getErrorStackTrace(Error error) {
		StringWriter sw = new StringWriter();
		error.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}
	
	
	/**
	 * Gets the system user name.
	 *
	 * @return the system user name
	 */
	public static String getSystemUserName() {
		return System.getProperty("user.name");
	}

	/**
	 * Gets the exception stack trace first line.
	 *
	 * @param e the e
	 * @return the exception stack trace first line
	 */
	public static String getExceptionStackTraceFirstLine(Exception e) {
		String[] errorMsg = e.getMessage().split("\\r?\\n");
		return errorMsg[0];
	}
	
	/**
	 * Gets the current time stamp.
	 *
	 * @return the current time stamp
	 */
	public static Timestamp getCurrentTimeStamp() {
		Date today = new Date();
		return new Timestamp(today.getTime());
	}


}
