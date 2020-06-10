package com.excilys.model;

import java.time.LocalDate;

public class Computer {
	public long id;
	public String name;
	public LocalDate introduced;
	public LocalDate discontinued;
	public Company company;

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

		public Builder setIntroduced(LocalDate introduced) {
			this.introduced = introduced;
			return this;
		}

		public Builder setDiscontinued(LocalDate discontinued) {
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
