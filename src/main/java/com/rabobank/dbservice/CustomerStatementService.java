package com.rabobank.dbservice;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.rabobank.entity.CustomerStatements;

@Component
public interface CustomerStatementService {

	public List<CustomerStatements> retrieveAllStatements();

	public void saveRecord(CustomerStatements customerStatement);
	
	public Optional<CustomerStatements> findByReference(BigInteger reference);

}
