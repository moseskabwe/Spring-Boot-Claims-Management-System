package com.simplehomeinsurance.claims_management_system.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

// Source: Chad Darby. Spring & Hibernate for Beginners (includes Spring Boot). Udemy. 
// https://www.udemy.com/course/spring-hibernate-tutorial/
public class DateUtils {
    
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 

    public static Date parseDate(String dateString) throws ParseException {      
    	return formatter.parse(dateString);               
    }
    
    public static String formatDate(Date date) {   	
    	if (date != null) {
    		return formatter.format(date);
    	} 	
    	return null;
    }
}
