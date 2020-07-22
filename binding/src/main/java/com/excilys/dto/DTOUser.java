package com.excilys.dto;

public class DTOUser {

	private String userNameDTO;
	private String passwordDTO;
	private DTORole roleDTO;
	
	public DTOUser() {
		
	}
	
	private DTOUser(Builder builder) {
		this.userNameDTO = builder.userNameDTO;
		this.passwordDTO = builder.passwordDTO;
		this.roleDTO = builder.roleDTO;
	}
	
	public String getName() {
		return userNameDTO;
	}

	public void setName(String nameDTO) {
		this.userNameDTO = nameDTO;
	}
	
	public String getPassword() {
		return passwordDTO;
	}

	public void setPassword(String passwordDTO) {
		this.passwordDTO = passwordDTO;
	}

	public DTORole getRole() {
		return roleDTO;
	}

	public void setRole(DTORole roleDTO) {
		this.roleDTO = roleDTO;
	}
	
	public static class Builder {
		private String userNameDTO;
		private String passwordDTO;
		private DTORole roleDTO;

		public Builder setUserName(String userNameDTO) {
			this.userNameDTO = userNameDTO;
			return this;
		}
	
		public Builder setPassword(String passwordDTO) {
			this.passwordDTO = passwordDTO;
			return this;
		}

		public Builder setRole(DTORole roleDTO) {
			this.roleDTO = roleDTO;
			return this;
		}
		
		public DTOUser build() {
			return new DTOUser(this);
		}
	}

	@Override
	public String toString() {
		return "DTOUser [userNameDTO=" + userNameDTO + ", passwordDTO=" + passwordDTO + ", roleDTO=" + roleDTO + "]";
	}
	
}