package com.excilys.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.Company;

@Repository
public interface DAOCompany extends JpaRepository<Company, Long> {

}

