
package com.excilys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.excilys.mapper.MapperComputer;
import com.excilys.model.Computer;
import com.excilys.model.Computer.Builder;
import com.excilys.service.Validators;


public class DAOComputerTest extends Mockito {
	String dateInt = "1998-08-10";
	String dateTerm = "1998-08-11";


	@Test
	public void testCoversionDateTrue() {
		String date = "2010-04-22";
		LocalDate local = MapperComputer.transString(date);
		LocalDate localDate = LocalDate.of(2010, 04, 22);
		assertEquals(localDate, local);
	}

	@Test
	public void testdateOrdreTrue() {
		boolean vtt = Validators.verifierDateOrdre(dateInt,dateTerm );
		assertEquals(true,vtt);
	}
	@Test
	public void testDateEmpty() {
		String date = "";
		LocalDate local = MapperComputer.transString(date);
		assertEquals(null, local);
	}
	@Test
	public void testDateFormat() {
		String date = "sdqs";
		LocalDate local = MapperComputer.transString(date);
		assertEquals(null, local);
	}
	@Test
	public void testDateSl() {
		String date = "2010/04/22";
		LocalDate local = MapperComputer.transString(date);
		assertEquals(null, local);
	}

	// @Mock Computer computer ==> computer = mock(Computer.class);
	public Computer computer;
	public DAOComputer compi;
	LocalDate datedebut = LocalDate.of(2000,01,05);
	LocalDate datefin = LocalDate.of(2000,06,05);

	@Before
	public void init() {
		MysqlConnect.getDbCon();
		//H2ConnectTest.getDbCon();
		computer = mock(Computer.class);
		compi = mock(DAOComputer.class);
	}

	@Test
	public void discontinuedAfterIntroducedExpectedTrue(){
		Validators validatorComputer = new Validators();
		when(computer.getDiscontinued()).thenReturn(datefin);
		when(computer.getIntroduced()).thenReturn(datedebut);
		//assertEquals(true, actual);
		//assertTrue(actual);
		assertTrue(validatorComputer.discontinuedAfterIntroduced(computer.getDiscontinued(), computer.getIntroduced()));
	}
	
	Computer computer2 = new Computer.Builder().setId(2).setName("CM-2a").build();
	@Test
	public void testsetComputerDetail() throws SQLException  {

		Computer computer1 = DAOComputer.getInstance().getComputerDetail(2).get();
		
		
		assertEquals(computer1.name, computer2.name);
	}
}