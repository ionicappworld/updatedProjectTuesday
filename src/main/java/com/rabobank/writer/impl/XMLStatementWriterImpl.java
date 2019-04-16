package com.rabobank.writer.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import com.rabobank.controller.StatementProcessorController;
import com.rabobank.dbservice.CustomerStatementService;
import com.rabobank.dto.Record;
import com.rabobank.dto.Records;
import com.rabobank.writer.StatementWriter;

@Component(value = "xmlwriter")

public class XMLStatementWriterImpl implements StatementWriter {
	private static final Logger logger = LoggerFactory.getLogger(StatementProcessorController.class);

	@Autowired
	CustomerStatementService customerStatementService;

	@Override
	public void writeOutputReport(HttpServletResponse response) {
		String filename = "OutputReport.xml";
		response.setContentType("text/xml");
		response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);
		StringWriter sw = new StringWriter();

		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(Records.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			Records records = new Records();
			List<Record> recordList = new ArrayList<>();
			customerStatementService.retrieveAllStatements().stream().forEach(statements -> {

				Record record = new Record();
				BeanUtils.copyProperties(statements, record);
				recordList.add(record);

			});
			records.setChildrecords(recordList);
			marshaller.marshal(records, sw);
			FileCopyUtils.copy(sw.toString(), response.getWriter());
		} catch (IOException | JAXBException e) {
			logger.info("Exception on JAB unmarshalling", e);

		}
	}

}
