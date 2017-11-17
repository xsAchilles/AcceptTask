package com.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

	public static boolean isBefore(int targetHour){
		try {
			Calendar c = Calendar.getInstance();
			int hourOfDay = c.get(Calendar.HOUR_OF_DAY);
			return hourOfDay < targetHour;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static String getNowDate(){
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        String date = format.format(c.getTime());
        return date;
	}
}
