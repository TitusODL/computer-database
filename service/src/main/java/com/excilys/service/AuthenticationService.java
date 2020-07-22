package com.excilys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.DTOUser;
import com.excilys.mapper.MapperUser;
import com.excilys.model.UserCDB;
import com.excilys.persistence.DAOUser;

@Service
public class AuthenticationService {

	@Autowired
	private DAOUser userRepository;
	
//	@Autowired
//	private DAORole roleRepository;
	
	public void addUser(DTOUser userdto) {
		UserCDB user = MapperUser.userDTOtouser(userdto);
		userRepository.save(user);
	}
	
//	public void addRole(Role role) {
//		
//		roleRepository.save(role);
//	}
//	
//	public Page<UserCDB> listUser(int PageNum, int pageSize) {
//		
//		Page<UserCDB> usersPag = userRepository.findAll(PageRequest.of(PageNum, pageSize));
//		
//		return usersPag;
//	}

	public UserCDB findFirstByUsername(String username) {
		
		return userRepository.findByusername(username);
	}
}