package no.ntnu.tdt4237.helperactions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateHelpers {
	
	private static final String SHORT_DATE = "yyyy-MM-dd";
	private static final String SHORT_DATE_DAY_FIRST = "yyyy-MM-dd";
	public static String toShortFormat(Date d)
	{
		SimpleDateFormat sd = new SimpleDateFormat(SHORT_DATE);
		return sd.format(d);
	}
	
	public static Date parseString(String date)
	{
		SimpleDateFormat sd = new SimpleDateFormat(SHORT_DATE);
		try {
			return sd.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static java.sql.Date utilDateToSqlDate(java.util.Date uDate) throws ParseException
	{
		return java.sql.Date.valueOf(new SimpleDateFormat(SHORT_DATE).format(uDate));
	}

	public static java.util.Date sqlDateToutilDate(java.sql.Date sDate) throws ParseException
	{
		SimpleDateFormat sd = new SimpleDateFormat(SHORT_DATE_DAY_FIRST);
		return (java.util.Date) sd.parse(sd.format(sDate));
	}

	public static boolean compareDates(Date date1, Date date2) {
		
		return date1.equals(date2);
	}
}
