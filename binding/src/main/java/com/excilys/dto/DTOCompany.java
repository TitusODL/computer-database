package com.excilys.dto;

public class DTOCompany {

	private String id;
	private String name;

	private DTOCompany() {
	}

	private DTOCompany(DTOCompanyBuilder companyDTOBuilder) {
		this.id = companyDTOBuilder.id;
		this.name = companyDTOBuilder.name;
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

	public static class DTOCompanyBuilder {
		private String id;
		private String name;

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
		@Override
		public String toString() {
			return "id=" + this.id + ", name=" + this.name;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
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
			DTOCompanyBuilder other = (DTOCompanyBuilder) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
				return false;
			return true;
		}

	

	}
	

}