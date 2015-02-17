package pl.noname.stacjabenzynowa.util;

import java.util.Calendar;
import java.util.Date;

public class DateTimestampRemover {

	public static Date removeTimestamp(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		
		Date dateWithoutTimestamp = calendar.getTime();
		
		return dateWithoutTimestamp;		
	}
}
