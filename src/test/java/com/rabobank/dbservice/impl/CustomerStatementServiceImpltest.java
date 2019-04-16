package com.rabobank.dbservice.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.rabobank.domain.CustomerStatements;
import com.rabobank.repository.CustomerStatementsRepository;
import com.rabobank.service.CustomerStatementService;
import com.rabobank.service.impl.CustomerStatementServiceImpl;

@RunWith(SpringRunner.class)
public class CustomerStatementServiceImpltest {

	@TestConfiguration
	static class CustomerStatementServiceContextConfiguration {

		@Bean
		public CustomerStatementService sustomerStatementService() {
			return new CustomerStatementServiceImpl();
		}
	}

	@Autowired
	private CustomerStatementService customerStatementService;

	@MockBean
	private CustomerStatementsRepository customerStatementsRepository;

	@Before
	public void setUp() {
		List<CustomerStatements> statementslist = new ArrayList<>();
		CustomerStatements statements = new CustomerStatements();
		statements.setAccountNumber("NL91RABO0315273637");
		statements.setDescription("Clothes from Jan Bakker");
		statements.setEndBalance(BigDecimal.valueOf(21.6));
		statements.setMutation(BigDecimal.valueOf(-41.83));
		statements.setReference(BigInteger.valueOf(194));
		statements.setStartBalance(BigDecimal.valueOf(-20.23));
		statementslist.add(statements);

		Mockito.when(customerStatementsRepository.findAll()).thenReturn(statementslist);
	}

	@Test
	public void retrieveAllStatementsTest() {
		List<CustomerStatements> statementslist = customerStatementService.retrieveAllStatements();
		assertTrue(!statementslist.isEmpty() && statementslist.size() > 1);
	}

	public void findByReferenceTest() {
		Optional<CustomerStatements> statements = customerStatementService.findByReference(BigInteger.valueOf(194));
		assertTrue(statements.isPresent());
		assertEquals(statements.get().getAccountNumber(), "NL91RABO0315273637");
	}

}
