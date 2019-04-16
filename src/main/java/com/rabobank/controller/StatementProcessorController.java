package com.rabobank.controller;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.factory.StatementFactoryInterface;
import com.rabobank.processor.StatementProcessor;

@RestController
@RequestMapping("/rabo")
public class StatementProcessorController {
	private static final Logger logger = LoggerFactory.getLogger(StatementProcessorController.class);

	@Autowired
	StatementProcessor statementProcessor;

	@Autowired
	StatementFactoryInterface statementFactory;

	@PostMapping("/getValidatedStatement")
	public void getValidatedStatement(@RequestParam("files") MultipartFile file, HttpServletResponse response) {
		if (file != null) {
			logger.info("Statement Process Starting");
			statementProcessor.processStatement(file);
			statementFactory.getFileWriter(file).writeOutputReport(response);

		}

	}
}
