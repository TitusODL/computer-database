package com.excilys.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;

import com.excilys.configuration.ConfigurationSpring;
import com.excilys.configuration.ConfigurationWebController;
import com.excilys.dto.DTOComputer;
import com.excilys.mapper.MapperDate;
import com.excilys.model.Computer;
import com.excilys.persistence.DAOComputer;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {ConfigurationSpring.class,ConfigurationWebController.class})

public class ServiceTest extends Mockito {
	String dateInt = "1998-08-10";
	String dateTerm = "1998-08-11";
	LocalDate datedebut = LocalDate.of(2000, 01, 05);
	LocalDate datefin = LocalDate.of(2000, 06, 05);
	
	DTOComputer computer2 = new DTOComputer.DTOComputerBuilder().setId("2").setName("CM").build();

	@Mock 
	DAOComputer daoComputer;
	//@Mock
	Computer computer;
	@InjectMocks
	ComputerService computerService;
	@Before
	public void init() {
		}

	@Test
	public void testCoversionDateTrue() {
		String date = "2010-04-22";
		LocalDate local = MapperDate.ConvertDateString(date);
		LocalDate localDate = LocalDate.of(2010, 04, 22);

		assertEquals(localDate, local);
	}

	@Test
	public void testdateOrdreTrue() {
		boolean vtt = Validators.verifyDateOrder(dateInt, dateTerm);

		assertEquals(true, vtt);
	}

	@Test
	public void testDateEmpty() {
		String date = "";
		LocalDate localdate = MapperDate.ConvertDateString(date);
		assertEquals(null, localdate);
	}

	@Test
	public void testDateFormat() {
		String date = "sdqs";
		LocalDate localdate = MapperDate.ConvertDateString(date);

		assertEquals(null, localdate);
	}

	@Test
	public void testDateSl() {
		String date = "2010/04/22";
		LocalDate localdate = MapperDate.ConvertDateString(date);

		assertEquals(null, localdate);
	}

	@Test
	public void discontinuedAfterIntroducedExpectedTrue() {
		when(computer.getDiscontinued()).thenReturn(datefin);
		when(computer.getIntroduced()).thenReturn(datedebut);
		assertTrue(Validators.verifyDateOrder((datedebut.toString()), datefin.toString()));
	}

	@Test
	public void testgetComputerById(){
		when(computerService.getComputerById("2")).thenReturn(Optional.of (computer2));
		DTOComputer computer1 = computerService.getComputerById("2").get();
		assertEquals(computer1.getName(), computer2.getName());
	}
}
