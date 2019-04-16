package com.rabobank.reader.impl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.controller.StatementProcessorController;
import com.rabobank.dto.Record;
import com.rabobank.dto.Records;
import com.rabobank.reader.StatementReader;

@Component
@Qualifier("csvreader")
public class CSVStatementReaderImpl implements StatementReader {

	private static final Logger logger = LoggerFactory.getLogger(StatementProcessorController.class);

	@Override
	public Records readStatement(MultipartFile file) {
		List<Record> customerStatements = new ArrayList<>();
		Records records;

		CSVParser parser;
		try {
			parser = CSVFormat.EXCEL.withHeader().withDelimiter(',')
					.parse(new InputStreamReader(file.getInputStream()));

			parser.forEach(record -> {
				Record customerRecords = new Record(new BigInteger(record.get("Reference")),
						record.get("AccountNumber"), record.get("Description"),
						new BigDecimal(record.get("Start Balance")), new BigDecimal(record.get("Mutation")),
						new BigDecimal(record.get("End Balance")));

				customerStatements.add(customerRecords);
			});
		}

		catch (IOException e) {
			logger.error("Exception on Reading CSV file " , e);
		}

		records = new Records();
		records.setChildrecords(customerStatements);
		return records;

	}

}
