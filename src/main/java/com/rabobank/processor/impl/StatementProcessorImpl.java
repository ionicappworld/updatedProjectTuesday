package com.rabobank.processor.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.controller.StatementProcessorController;
import com.rabobank.domain.Records;
import com.rabobank.factory.StatementFactory;
import com.rabobank.persist.ValidatedStatementSave;
import com.rabobank.processor.StatementProcessor;

@Service
public class StatementProcessorImpl implements StatementProcessor {

	private static final Logger logger = LoggerFactory.getLogger(StatementProcessorController.class);

	@Autowired
	ValidatedStatementSave validatedStatementSave;

	@Autowired
	StatementFactory statementFactory;

	@Override
	public void processStatement(MultipartFile file) {
		try {
			Records statements = statementFactory.getFileReader(file).readStatement(file);
			validatedStatementSave.validateAndSave(statements);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}
