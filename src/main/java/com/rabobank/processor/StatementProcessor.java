package com.rabobank.processor;

import org.springframework.web.multipart.MultipartFile;


public interface StatementProcessor {

	void processStatement(MultipartFile file);

}
