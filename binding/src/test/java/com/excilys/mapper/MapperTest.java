package com.excilys.mapper;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import com.excilys.dto.DTOCompany;
import com.excilys.dto.DTOComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class MapperTest {

	MapperDTOComputer mapperComputer = new MapperDTOComputer();
	MapperDTOCompany mapperCompany = new MapperDTOCompany();

	DTOCompany companyDTO = new DTOCompany.DTOCompanyBuilder()
			.setId("1")
			.setName("Apple")
			.build();
	Company company = new Company.CompanyBuilder()
			.setId((long) 1)
			.setName("Apple")
			.build();

	DTOComputer computerDto = new DTOComputer.DTOComputerBuilder()
			.setId("1")
			.setName("ComputerTitus")
			.setIntroduced("2010-04-22")
			.setDiscontinued("2010-04-23")
			.setCompany_Id(String.valueOf(company.getId()))
			.setCompany_Name(companyDTO.getName())
			.build();

	Computer computer = new Computer.Builder()
			.setId((long) 1)
			.setName("ComputerTitus")
			.setIntroduced(LocalDate.of(2010, 04, 22))
			.setDiscontinued(LocalDate.of(2010, 04, 23))
			.setCompany(company)
			.build();
	List<DTOComputer> computerList3 = new ArrayList<DTOComputer>();
	List<Computer> computerList1 = new ArrayList<Computer>();



	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testDtoToComputer() {
		Computer computerRes = mapperComputer.dtoToComputer(computerDto);
		assertEquals(computer, computerRes);
	}

	@Test
	public void testComputerToDto() {
		DTOComputer computerRes2 = mapperComputer.computerToDto(computer);
		assertEquals(computerDto, computerRes2);
	}

	@Test
	public void testDtoToComputerlist() {
		computerList1.add(computer);
		computerList3.add(computerDto);
		List<Computer>computerList=mapperComputer.listDtoToComputer(computerList3);
		assertEquals(computerList1, computerList);
	}

	@Test
	public void testComputerlistToDto() {
		computerList1.add(computer);
		computerList3.add(computerDto);
		List<DTOComputer> computerListDto = mapperComputer.listComputerToDto(computerList1);
		assertEquals(computerList3, computerListDto);
	}

	@Test
	public void testDtoToCompany() {
		Company companyRes = mapperCompany.dtoToCompany(companyDTO);

		assertEquals(company.getId(), companyRes.getId());
		assertEquals(company.getName(), companyRes.getName());
	}

	@Test
	public void testCompanyToDto() {
		DTOCompany companyRes = mapperCompany.companyToDto(company);
		assertEquals(companyDTO.getId(), companyRes.getId());
		assertEquals(companyDTO.getName(), companyRes.getName());
	}
}

