package com.excilys.dto;

public class DTOComputer{
	private String id;
	private String name;
	private String introduced;
	private String discontinued;
	private String company_id;
	private String company_name;
 
	private DTOComputer() {

	}

	private DTOComputer(DTOComputerBuilder computerDTOBuilder) {
		this.id = computerDTOBuilder.id;
		this.name = computerDTOBuilder.name;
		this.introduced = computerDTOBuilder.introduced;
		this.discontinued = computerDTOBuilder.discontinued;
		this.company_id = computerDTOBuilder.company_id;
		this.company_name = computerDTOBuilder.company_name;
	}

	@Override
	public String toString() {
		
		return "id=" + id + ", name=" + name + ", introduced=" + this.introduced + ", discontinued=" + this.discontinued + ", company_id=" + this.company_id + ",company_name=" + this.company_name ;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company_id == null) ? 0 : company_id.hashCode());
		result = prime * result + ((company_name == null) ? 0 : company_name.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
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
		DTOComputerBuilder other = (DTOComputerBuilder) obj;
		if (company_id == null) {
			if (other.company_id != null)
				return false;
		} else if (!company_id.equals(other.company_id))
			return false;
		if (company_name == null) {
			if (other.company_name != null)
				return false;
		} else if (!company_name.equals(other.company_name))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
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
	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}




	public static class DTOComputerBuilder {

		private String id;
		private String name;
		private String introduced;
		private String discontinued;
		private String company_id;
		private String company_name;
		

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
		
		public DTOComputerBuilder setCompany_Id(String company_id) {
			this.company_id = company_id;
			return this;
		}
		public DTOComputerBuilder setCompany_Name(String company_name) {
			this.company_name = company_name;
			return this;
		}
		
		
	}
}