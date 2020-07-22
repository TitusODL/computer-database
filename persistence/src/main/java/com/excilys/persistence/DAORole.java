package com.excilys.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.Role;

@Repository
public interface DAORole extends JpaRepository<Role, Long>{

}
