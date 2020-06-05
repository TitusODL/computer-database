package com.excilys.dto;

public class DTOCompany {

	public String id;
	public String name;

	private DTOCompany() {
	}

	private DTOCompany(DTOCompanyBuilder companyDTOBuilder) {
		this.id = companyDTOBuilder.id;
		this.name = companyDTOBuilder.name;
	}

	public String toString() {
		return this.id + " | " + this.name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static class DTOCompanyBuilder {
		public String id;
		public String name;

		public DTOCompany build() {
			return new DTOCompany(this);
		}

		public DTOCompanyBuilder setId(String id) {
			this.id = id;
			return this;
		}

		public DTOCompanyBuilder setName(String name) {
			this.name = name;
			return this;
		}

	}

}