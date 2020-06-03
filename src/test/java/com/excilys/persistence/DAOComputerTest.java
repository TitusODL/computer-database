
package com.excilys.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import com.excilys.mapper.MapperComputer;
import com.excilys.model.Computer;
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
LocalDate datedebut = LocalDate.of(2000,01,05);
LocalDate datefin = LocalDate.of(2000,06,05);

@Before
public void init() {
	
	computer = mock(Computer.class);
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

}
//@RunWith(MockitoJUnitRunner.class)
//
//public class PremsMockito {
//	private DAOComputer request;
//	private DAOComputer response;
//	private DAOComputer requestDispatcher;
//	private DAOComputer daoComp;
//	
//	
//	@Before
//	public void init() {
//		daoComp = new DAOComputerTest();
//		request = mock(HttpServletRequest.class);
//		response = mock(HttpServletResponse.class);
//		requestDispatcher = mock(RequestDispatcher.class);
//	}
//
//	@Test
//	public void testServletDashBard() throws ServletException, IOException {
//		when(request.getRequestDispatcher(eq("views/ListComputer.jsp"))).thenReturn(requestDispatcher);
//		servletDashBard.doGet(request, response);
//	}
//}


//@RunWith(MockitoJunitRunner.class)
//public class MyTestClass {
//
//}
//@Before
//public void setUp() {
//	MockitoAnnotations.initMocks(this);
//}
//User user = Mockito.mock(User.class);
//public class DAOComputerTest {
//
//	public static DAOComputer daoTest;
//	public static DAOComputer computerDAO;
//	private static Logger logger = LoggerFactory.getLogger(ComputerService.class);
//	@Before
//	public void setUp() throws Exception {
//	
//	}
//	
//	@After
//	public void tearDown() throws Exception 
//	{
//		
//	}
//
//	@Test
//	public void testAjouter() throws Exception {
//		
//		Computer computer= new Computer.Builder()
//				   .setIntroducedDate(null)
//				   .setDiscontinuedDate(null)
//				   .setCompany(new Company.CompanyBuilder()
//						   			  	  .setId((long) 1)
//						   			  	  .setName("IPad 2").build())
//				   .build();
//		
//		try {
//			int i =  DAOComputer.getInstance().addComputer(computer);
//			assertEquals(i, 1);
//			
//		} catch (SQLException e) {
//			logger.info("Exception SQL testAjouter()");
//		}
//		
//
//	import org.junit.Before;
//	import org.junit.Test;
//	import org.mockito.Mockito;
//
//	public class DAOComputerTest extends Mockito {
//		private DAOComputerTest request;
//		private DAOComputerTest response;
//
//		@Before
//		public void init() {
//			 DAOComputerTest dao = new DAOComputerTest();
//			request = mock(DAOComputerTest.class);
//			response = mock(DAOComputerTest.class);
//			requestDispatcher = mock(RequestDispatcher.class);
//		}
//
//		@Test
//		public void testServletDashBard() throws  IOException {
//			when(request.getRequestDispatcher(eq("views/ListComputer.jsp"))).thenReturn(requestDispatcher);
//			dao.doGet(request, response);
//		}
//
//	}
//}