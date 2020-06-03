package com.excilys.service;

import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.mapper.MapperComputer;


public class Validators {

	private final static Logger LOGGER = LoggerFactory.getLogger(Validators.class.getName());
	
	public static boolean verifyDateUserInput(String date) {
		
		try
		{	
			
		
			if (date.isEmpty()) {
	            return true;
	        }
	        if (date.substring(4, 5).equals("/")) {
	            LOGGER.info("Mauvais format de Date");
	            return false;
	         }   
			String year = date.substring(0,4);
			int dateYear = Integer.parseInt(year);
			String month = date.substring(5,7);
			int dateMonth = Integer.parseInt(month);
			String day = date.substring(8,10);
			int dateDay = Integer.parseInt(day);

			return true;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			LOGGER.error("Wrong format");
		}
		return false;
		
			

	}

	public static boolean verifierDateOrdre(String dateIntroduction, String dateTermination) {
		LocalDate intro = MapperComputer.transString(dateIntroduction);
		LocalDate termine = MapperComputer.transString(dateTermination);
		if(dateIntroduction.isEmpty() || dateTermination.isEmpty()) {
			return true;
		}
		if(termine.isBefore(intro)) {
			return false;
		}
		return true;
		
	}
	
		public boolean discontinuedAfterIntroduced(LocalDate discontinued, LocalDate introduced) {
			
			return discontinued.isAfter(introduced);
		
}
}
	