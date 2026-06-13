package generic_utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JavaUtility {
	
	
	public static int generateRandomNumber() {
		double db=Math.random()*10000;
		int in=(int)db;
		return in;
	}
	
	public static String getCurrentTime() {
		LocalDateTime now =LocalDateTime.now();
//		2026-06-11T11:15:21.351064300
//		111345_11062026
//		System.out.println(now);
	String time=DateTimeFormatter.ofPattern("hhmmss_ddmmyyyy").format(now);
		return time;
	}
}
