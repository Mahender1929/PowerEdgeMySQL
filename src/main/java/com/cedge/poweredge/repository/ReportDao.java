package com.cedge.poweredge.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cedge.poweredge.dto.Bank;

@Repository
public interface ReportDao extends JpaRepository<Bank, Integer> {

	@Query(value="SELECT bank_name FROM cesys004 ORDER BY bank_name ASC", nativeQuery=true)
	public Optional<List<String>> findAllBankNameByOrderByBankNameAsc();
	
}
