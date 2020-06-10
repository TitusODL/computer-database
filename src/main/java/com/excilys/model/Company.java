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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Company other = (Company) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


}
