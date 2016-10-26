package br.ufsc.bridge.res.util;

import java.util.Calendar;
import java.util.Date;

public class RDateUtil {

	public static String fromDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int ano = c.get(Calendar.YEAR);
		int mes = c.get(Calendar.MONTH) + 1;
		int dia = c.get(Calendar.DAY_OF_MONTH);
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int min = c.get(Calendar.MINUTE);
		int sec = c.get(Calendar.SECOND);

		return String.valueOf(ano * 10000000000L + mes * 100000000 + dia * 1000000 + hour * 10000 + min * 100 + sec);
	}

	public static Date fromISO(String date) {
		Calendar c = Calendar.getInstance();

		String year = date.substring(0, 4);
		c.set(Calendar.YEAR, Integer.valueOf(year));

		String month = date.substring(4, 6);
		c.set(Calendar.MONTH, Integer.valueOf(month) - 1);

		String day = date.substring(6, 8);
		c.set(Calendar.DAY_OF_MONTH, Integer.valueOf(day));

		if (date.length() >= 10) {
			String hour = date.substring(8, 10);
			c.set(Calendar.HOUR_OF_DAY, Integer.valueOf(hour));
		} else {
			c.set(Calendar.HOUR_OF_DAY, 0);
		}

		if (date.length() >= 12) {
			String min = date.substring(10, 12);
			c.set(Calendar.MINUTE, Integer.valueOf(min));
		} else {
			c.set(Calendar.MINUTE, 0);
		}

		if (date.length() >= 14) {
			String sec = date.substring(12, 14);
			c.set(Calendar.SECOND, Integer.valueOf(sec));
		} else {
			c.set(Calendar.SECOND, 0);
		}

		return c.getTime();
	}
}
