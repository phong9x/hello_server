package org.trams.hello.rest.controller;

import java.util.Date;

public class MainTest {
	public static void main(String args[]) {
		/*String string_date = "2017-07-24";

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		try {
		    Date d = f.parse(string_date);
		    long milliseconds = d.getTime();
		    System.out.println("milliseconds:" + milliseconds);
		} catch (ParseException e) {
		    e.printStackTrace();
		}*/
		
//		Calendar now = Calendar.getInstance();
		/*now.add(Calendar.DATE, 1);
		SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println( s.format(now.getTime()));*/
		
		Date d = new Date();
		System.out.println("d: " + d.getHours() + " ~ " + d.getMinutes());
		
	}
}
