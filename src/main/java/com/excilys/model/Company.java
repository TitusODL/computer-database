package com.excilys.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

@Entity
@Table(name = "company")

public class Company {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name")
	private String name;
	
	public Company() {
		
	}

	public Company(CompanyBuilder builder) {
		this.id = builder.id;
		this.name = builder.name;
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
			return new Company(this);
		}
	}
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.DEFAULT_STYLE);
		
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
		Company other = (Company) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
