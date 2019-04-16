package com.rabobank.persist.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabobank.domain.CustomerStatements;
import com.rabobank.domain.Records;
import com.rabobank.persist.ValidatedStatementSave;
import com.rabobank.service.CustomerStatementService;
import com.rabobank.validator.CustomerStatementValidator;

@Service
public class ValidatedStatementSaveImpl implements ValidatedStatementSave {

	@Autowired
	CustomerStatementValidator customerStatementValidator;

	@Autowired
	CustomerStatementService customerStatementService;

	@Override
	public void validateAndSave(Records records) {

		records.getChildrecords().stream().forEach(record -> {

			customerStatementValidator.validateEndBalance(record);

			customerStatementValidator.validateDuplicate(record);

			CustomerStatements statments = new CustomerStatements();

			BeanUtils.copyProperties(record, statments);

			customerStatementService.saveRecord(statments);

		});

	}

}
