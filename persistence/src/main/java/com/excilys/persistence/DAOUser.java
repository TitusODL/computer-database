package com.excilys.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.UserCDB;

@Repository
public interface DAOUser extends JpaRepository<UserCDB, Long>{

UserCDB findByusername(String search);

}
