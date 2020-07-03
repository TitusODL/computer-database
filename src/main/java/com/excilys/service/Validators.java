package com.excilys.service;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperDate;
@Service
public class Validators {

	private final static Logger logger = LoggerFactory.getLogger(Validators.class);

	public static boolean verifyDateUserInput(String date) {

		if (date == null || date.isEmpty()) {
			return true;
		}
		else if (date.equals("null")) {
				return true;
		
		} 
		else if (date.contains("/")) {
			logger.info("Wrong Date format /// Use - instead of /");
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
				logger.error("Wrong format");
				return false;
			}

		}

	}

	public static boolean verifyDateOrder(String dateIntroduction, String dateTermination) {
		LocalDate intro = MapperDate.ConvertDateString(dateIntroduction);
		LocalDate termine = MapperDate.ConvertDateString(dateTermination);

		if (dateIntroduction.isEmpty() || dateTermination.isEmpty()) {
			return true;
		}

		return (termine.isAfter(intro));

	}
	public static boolean verifyDate(DTOComputer computerDto) {
		boolean orderDate = false;
		boolean dateIntroduced = verifyDateUserInput(computerDto.getIntroduced());
		boolean dateDiscontinued = verifyDateUserInput(computerDto.getDiscontinued());
		if (dateIntroduced && dateDiscontinued) {
			orderDate = verifyDateOrder(computerDto.getIntroduced(), computerDto.getDiscontinued());
			if (!orderDate) {
				logger.info("No valid Date!");
			}
		}
		if (dateIntroduced && dateDiscontinued && orderDate) {
			return true;
		} else return false;
	}
		
	public static boolean verifyName(DTOComputer computerDto) {
		boolean name = true;
		if (computerDto.getName().isEmpty()) {
			name = false;
			logger.info("Name required!");
		}
		return name;
	}
}
