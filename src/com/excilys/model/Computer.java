package com.excilys.model;

import java.time.LocalDate;

public class Computer {
	public long id;
	public String name;
	public LocalDate introduced;
	public LocalDate discontinued;
	public Company company;

//	public Computer(long id, String name, LocalDate introduced, LocalDate discontinued, Company company) {
//		this.id = id;
//		this.name = name;
//		this.introduced = introduced;
//		this.discontinued = discontinued;
//		this.company = company;
//	}
	public Computer(Builder computerBuilder) {
		this.id = computerBuilder.id;
		this.name = computerBuilder.name;
		this.introduced = computerBuilder.introduced;
		this.discontinued = computerBuilder.discontinued;
		this.company = computerBuilder.company;
	}
	
	public Computer() {
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
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

	public LocalDate getIntroduced() {
		return introduced;
	}

	public void setIntroduced(LocalDate introduced) {
		this.introduced = introduced;
	}

	public LocalDate getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(LocalDate discontinued) {
		this.discontinued = discontinued;
	}
	
	public static class Builder {
		private long id;
		private String name;
		private LocalDate introduced;
		private LocalDate discontinued;
		private Company company;

		public Builder setName(String name) {
			this.name = name;
			return this;
		}

		public Builder setId(long id) {
			this.id = id;
			return this;
		}

		public Builder setIntroducedDate(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder setDiscontinuedDate(LocalDate discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public Builder setCompany(Company company) {
			this.company = company;
			return this;
		}

		public Computer build() {
			return new Computer(this);
		}
	}
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued=" + discontinued
				+ ", company=" + company;
	}
}
