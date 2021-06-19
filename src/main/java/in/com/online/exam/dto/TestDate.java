package in.com.online.exam.dto;

import java.util.Calendar;
import java.util.Date;

import in.com.online.exam.util.DataUtility;

public class TestDate {
	
	public static void main(String[] args) {
		Date referenceDate = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(referenceDate); 
		c.add(Calendar.MONTH,6);
		System.out.println(DataUtility.getDateString(c.getTime()));
	}

}
