package com.cmc.rest.commons;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String convertir(Date date1) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date1);

	}
}
