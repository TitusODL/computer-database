
package com.excilys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excilys.mapper.MapperDate;
import com.excilys.model.Computer;
import com.excilys.service.Validators;

@RunWith(SpringJUnit4ClassRunner.class)
public class DAOComputerTest extends Mockito {
String dateInt = "1998-08-10";
String dateTerm = "1998-08-11";
Computer computer2 = new Computer.Builder().setId(2).setName("CM-2a").build();
DAOComputer daoComputer;

public DAOComputerTest(DAOComputer daoComputer) {
	this.daoComputer = daoComputer;
}


//@Mock Computer computer ==> computer = mock(Computer.class);
public Computer computer;
LocalDate datedebut = LocalDate.of(2000,01,05);
LocalDate datefin = LocalDate.of(2000,06,05);



@Before
public void init() {
//	Connecticut.getDbCon();
	//H2ConnectTest.getDbCon();
	computer = mock(Computer.class);
	//compi = mock(DAOComputer.class);
	
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
	boolean vtt = Validators.verifierDateOrdre(dateInt,dateTerm );
	
	assertEquals(true,vtt);
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
public void discontinuedAfterIntroducedExpectedTrue(){
when(computer.getDiscontinued()).thenReturn(datefin);
when(computer.getIntroduced()).thenReturn(datedebut);
//assertEquals(true, actual);
//assertTrue(actual);
assertTrue(Validators.verifierDateOrdre((datedebut.toString()),datefin.toString()));
}


@Test
public void testgetComputerDetail() throws SQLException  {

	Computer computer1 = daoComputer.getComputerDetail(2).get();

	assertEquals(computer1.getName(), computer2.getName());
}
}
