package com.excilys.dto;

public class DTOComputer{
	public String id;
	public String name;
	public String introduced;
	public String discontinued;

	private DTOComputer() {
	}

	private DTOComputer(DTOComputerBuilder computerDTOBuilder) {
		this.id = computerDTOBuilder.id;
		this.name = computerDTOBuilder.name;
		this.introduced = computerDTOBuilder.introduced;
		this.discontinued = computerDTOBuilder.discontinued;
	}

	public String toString() {
		return this.id + " | " + this.name + " | " + this.introduced + " | " + this.discontinued ;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}


	public static class DTOComputerBuilder {

		private String id;
		private String name;
		private String introduced;
		private String discontinued;

		public DTOComputer build() {
			return new DTOComputer(this);
		}

		public DTOComputerBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public DTOComputerBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public DTOComputerBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public DTOComputerBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}


	}
}