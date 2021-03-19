package com.nagarro.EmployeeManager.utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
private static SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * This method accepts the date in string format and parse it into Date format
     * @param stringDate
     * @return parsed date
     */
    public static Date getFormattedDate(String stringDate) {
    	Date date=null;
    	try {
    		date=formatter.parse(stringDate);
    		
    	}
    	 catch(ParseException e) {
		      System.out.println(" date is incorrect!!");
	       }
    	return date;
    }
    
    
}
