package com.rabobank.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.controller.StatementController;
import com.rabobank.domain.CustomerStatements;
import com.rabobank.domain.Records;
import com.rabobank.factory.StatementFactory;
import com.rabobank.services.CustomerStatementService;
import com.rabobank.services.ValidationService;
import com.rabobank.services.StatementProcessorService;

@Service
public class StatementProcessorServiceImpl implements StatementProcessorService {

	private static final Logger logger = LoggerFactory.getLogger(StatementController.class);

	@Autowired
	StatementFactory statementFactory;

	@Autowired
	ValidationService customerStatementValidator;

	@Autowired
	CustomerStatementService customerStatementService;

	@Override
	public void process(MultipartFile file) {
		
		try {
			Records statements = (Records) statementFactory.getFileReader(file).readStatement(file);
			statements.getRecords().parallelStream().forEach(record -> {

				customerStatementValidator.validateEndBalance(record);

				customerStatementValidator.validateDuplicate(record);

				CustomerStatements statments = new CustomerStatements();

				BeanUtils.copyProperties(record, statments);

				customerStatementService.saveRecord(statments);

			});
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}
