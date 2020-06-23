package com.excilys.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.mapper.MapperDate;
@Service
public class Validators {

	private final static Logger LOGGER = LoggerFactory.getLogger(Validators.class);

	public static boolean verifyDateUserInput(String date) {
		System.out.println("Validator " + date);

		if (date == null || date.isEmpty()) {
			return true;
		}
		else if (date.equals("null")) {
				return true;
		
		} 
		else if (date.contains("/")) {
			LOGGER.info("Mauvais format de Date");
			return false;
		} else {
			try {
				String year = date.substring(0, 4);
				@SuppressWarnings("unused")
				int dateYear = Integer.parseInt(year);
				String month = date.substring(5, 7);
				@SuppressWarnings("unused")
				int dateMonth = Integer.parseInt(month);
				String day = date.substring(8, 10);
				@SuppressWarnings("unused")
				int dateDay = Integer.parseInt(day);

				return true;
				
			} catch (Exception e) {
				LOGGER.error("Wrong format");
				return false;
			}

		}

	}

	public static boolean verifierDateOrdre(String dateIntroduction, String dateTermination) {
		LocalDate intro = MapperDate.ConvertDateString(dateIntroduction);
		LocalDate termine = MapperDate.ConvertDateString(dateTermination);

		if (dateIntroduction.isEmpty() || dateTermination.isEmpty()) {
			return true;
		}

		return (termine.isAfter(intro));

	}

}
