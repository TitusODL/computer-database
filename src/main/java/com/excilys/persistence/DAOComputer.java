package com.excilys.persistence;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.excilys.model.Computer;

@Repository
public interface DAOComputer extends JpaRepository<Computer, Long> {
	
	List<Computer> findByNameContaining(String search, Pageable pageable);
	
	Long countByNameContaining(String search);
}


