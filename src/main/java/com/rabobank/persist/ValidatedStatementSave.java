package com.rabobank.persist;

import com.rabobank.dto.Records;

public interface ValidatedStatementSave {
	
	public void validateAndSave(Records statements);

}
