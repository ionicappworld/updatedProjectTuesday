package com.rabobank.validator.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabobank.domain.CustomerStatements;
import com.rabobank.domain.Record;
import com.rabobank.service.CustomerStatementService;
import com.rabobank.validator.CustomerStatementValidator;

@Component
public class CustomerStatementValidatorImpl implements CustomerStatementValidator {

	@Autowired
	CustomerStatementService customerStatementService;

	@Override
	public Boolean validateDuplicate(Record record) {
		if (record != null) {
			Optional<CustomerStatements> statement = customerStatementService.findByReference(record.getReference());
			record.setIsUniqueStatement(!statement.isPresent());
			return statement.isPresent();
		} else {
			return false;
		}
	}

	@Override
	public Boolean validateEndBalance(Record record) {
		if (record != null) {

			if ((record.getStartBalance().add(record.getMutation())).compareTo(record.getEndBalance()) == 0) {
				record.setIsValidEndBalance(true);
				return record.getIsValidEndBalance();
			} else {
				record.setIsValidEndBalance(false);
				return record.getIsValidEndBalance();
			}

		} else {
			return false;
		}
	}

}
