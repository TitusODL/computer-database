package com.excilys.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.excilys.service.Validators;

public class MapperDate {

	public static LocalDate ConvertDateString(String entry) {
		boolean format = Validators.verifyDateUserInput(entry);
		if (entry == null || entry.isEmpty()) {
			return null;
		} else if (entry.equals("null")) {
			return null;
		} else if (!format) {
			return null;
		} else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(entry, formatter);
			return localDate;

		}
	}
}
