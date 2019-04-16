package com.rabobank.reader;

import org.springframework.web.multipart.MultipartFile;

import com.rabobank.dto.Records;

public interface StatementReader {

	public Records readStatement(MultipartFile file);

}
