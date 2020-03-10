package com.excilys.model;


	import java.sql.Timestamp;
import java.util.Date;

	public class Computer {
	 public long id;
	 public String name;
	 public Date introduced;
	 public Date discontinued;
	 public long companyId;
	 public String companyName; 

	 
	public Computer(long id, String name, Date introduced, Date discontinued, long companyId, String companyName) {
		super();
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.companyId = companyId;
		this.companyName = companyName;
		
		
	}

	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", companyId=" + companyId + ", companyName=" + companyName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getIntroduced() {
		return introduced;
	}
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}
	public Date getDiscontinued() {
		return discontinued;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
	 
	 
	}


