package com.rabobank.dbservice.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabobank.db.repository.CustomerStatementsRepository;
import com.rabobank.dbservice.CustomerStatementService;
import com.rabobank.entity.CustomerStatements;

@Service
public class CustomerStatementServiceImpl implements CustomerStatementService {

	@Autowired
	CustomerStatementsRepository customerStatementsRepository;

	@Override
	public List<CustomerStatements> retrieveAllStatements() {

		return customerStatementsRepository.findAll();

	}

	@Override
	@Transactional
	public void saveRecord(CustomerStatements customerStatement) {

		customerStatementsRepository.save(customerStatement);
	}

	@Override
	public Optional<CustomerStatements> findByReference(BigInteger reference) {

		return customerStatementsRepository.findById(reference);
	}

}
