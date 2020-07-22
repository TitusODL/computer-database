package com.excilys.mapper;

import com.excilys.dto.DTORole;
import com.excilys.dto.DTOUser;
import com.excilys.model.Role;
import com.excilys.model.Role.RoleBuilder;
import com.excilys.model.UserCDB;

public class MapperUser{
	public static DTOUser userToUserDTO(UserCDB user) {

		if(user.getRole() != null) {

			DTORole roleDTO = new DTORole.Builder().setId(String.valueOf(user.getRole().getId()))
					.setRoleName(user.getRole().getRoleName())
					.build();

			DTOUser userDTO = new DTOUser.Builder().setUserName(user.getUsername())
					.setPassword(user.getPassword())
					.setRole(roleDTO)
					.build();
			return userDTO;

		} else {

			DTOUser userDTO = new DTOUser.Builder().setUserName	(user.getUsername())
					.setPassword(user.getPassword())
					.build();

			return userDTO;
		}
	}
	public static UserCDB userDTOtouser(DTOUser userdto) {

		Role role = new Role.RoleBuilder().setId(Long.parseLong(userdto.getRole().getId())).setName(userdto.getRole().getRoleName()).build();


		UserCDB user = new UserCDB.Builder().setPassword(userdto.getPassword()).setUsername(userdto.getName()).setRole(role).build();

		return user;

	}
}