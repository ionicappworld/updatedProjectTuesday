package com.rabobank.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rabobank.enums.StatementFileType;
import com.rabobank.exception.FileTypeException;
import com.rabobank.reader.StatementReader;
import com.rabobank.writer.StatementWriter;

@Component
public class StatementFactory {

	@Autowired
	@Qualifier("csvreader")
	StatementReader csvReader;

	@Autowired
	@Qualifier("xmlreader")
	StatementReader xmlReader;

	@Autowired
	@Qualifier("csvwriter")
	StatementWriter csvwriter;

	@Autowired
	@Qualifier("xmlwriter")
	StatementWriter xmlwriter;

	public StatementReader getFileReader(MultipartFile inputFile) {
		StatementFileType fileType = StatementFileType.getFileType(inputFile.getOriginalFilename());
		switch (fileType) {
		case CSV:
			return csvReader;
		case XML:
			return xmlReader;
		default:
			throw new FileTypeException(fileType);
		}
	}

	public StatementWriter getFileWriter(MultipartFile inputFile) {
		StatementFileType fileType = StatementFileType.getFileType(inputFile.getOriginalFilename());
		switch (fileType) {
		case CSV:
			return csvwriter;
		case XML:
			return xmlwriter;
		default:
			throw new FileTypeException(fileType);
		}
	}

}
