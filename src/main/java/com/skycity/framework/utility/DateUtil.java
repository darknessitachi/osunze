package com.skycity.framework.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期类
 * @author YingBo
 *
 */
public class DateUtil {
	public static final String Format_Date = "yyyy-MM-dd";
	public static final String Format_Time = "HH:mm:ss";
	public static final String Format_DateTime = "yyyy-MM-dd HH:mm:ss";

	/**
	 * currentDate: yyyy-MM-dd
	 * @return
	 */
	public static String getCurrentDate() {
		return new SimpleDateFormat(Format_Date).format(new Date());
	}

	/**
	 * currentTime: hh:mm:ss
	 * @return
	 */
	public static String getCurrentTime() {
		return new SimpleDateFormat(Format_Time).format(new Date());
	}

	/**
	 * currentDateTime:yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime(Format_DateTime);
	}

	/**
	 * dateformat :current format
	 * @param format
	 * @return
	 */
	public static String getCurrentDateTime(String format) {
		SimpleDateFormat t = new SimpleDateFormat(format);
		return t.format(new Date());
	}

	/**
	 * Calendar 
	 * @return
	 */
	public static int getDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		return cal.get(7);
	}

	/**
	 * 每个月的多少号
	 * @return
	 */
	public static int getDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(5);
	}
	
	/**
	 * day of year
	 * @return
	 */
	public static int getDayOfYear() {
		Calendar cal = Calendar.getInstance();
		return cal.get(6);
	}

	/**
	 * format date to yyyy-MM-dd String
	 * @param date
	 * @return
	 */
	public static String toString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_Date).format(date);
	}

	/**
	 * formate date to "yyyy-MM-dd HH:mm:ss"
	 * @param date
	 * @return
	 */
	public static String toDateTimeString(Date date) {
		if (date == null) {
			return "";
		}
		return new SimpleDateFormat(Format_DateTime).format(date);
	}

	/**
	 * is WeekEnd
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int t = cal.get(7);
		if ((t == 7) || (t == 1)) {
			return true;
		}
		return false;
	}

	/**
	 * is WeekEnd
	 * @param str
	 * @return
	 */
	public static boolean isWeekend(String str) {
		return isWeekend(parse(str));
	}

	/**
	 * parse str to Date(yyyy-MM-dd)
	 * @param str
	 * @return
	 */
	public static Date parse(String str) {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		try {
			return new SimpleDateFormat(Format_Date).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * parse string to datetime(yyyy-MM-dd HH:mm:ss)
	 * @param str
	 * @return
	 */
	public static Date parseDateTime(String str) {
		if (StringUtil.isEmpty(str)) {
			return null;
		}
		if (str.length() <= 10) {
			return parse(str);
		}
		try {
			return new SimpleDateFormat(Format_DateTime).parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}