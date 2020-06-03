package com.excilys.model;


public class Company {
	public long id;
	public String name;

	public Company(long id, String name) {
		this.id = id;
		this.name = name;
	}

	public Company() {
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

	public static class CompanyBuilder {
		private Long id;
		private String name;

		public CompanyBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public CompanyBuilder setId(Long id) {
			this.id = id;
			return this;
		}

		public Company build() {
			return new Company(id,name);
		}
	}
	@Override
	public String toString() {
		return "id=" + id + ", name=" + name;
	}


}
