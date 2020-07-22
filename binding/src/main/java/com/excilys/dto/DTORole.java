package com.excilys.dto;

public class DTORole {

	private String idDTO;
	private String roleNameDTO;
	
	public DTORole() {
		
	}
	
	public DTORole(Builder builder) {
		this.idDTO = builder.idDTO;
		this.roleNameDTO = builder.roleNameDTO;
	}
	
	public String getId() {
		return idDTO;
	}

	public void setId(String idDTO) {
		this.idDTO = idDTO;
	}

	public String getRoleName() {
		return roleNameDTO;
	}

	public void setRoleName(String roleNameDTO) {
		this.roleNameDTO = roleNameDTO;
	}
	
	public static class Builder {
		private String idDTO;
		private String roleNameDTO;
		
		public Builder setId(String idDTO) {
			this.idDTO = idDTO;
			return this;
		}

		public Builder setRoleName(String nameDTO) {
			this.roleNameDTO = nameDTO;
			return this;
		}
		
		public DTORole build() {
			return new DTORole(this);
		}
	}
}