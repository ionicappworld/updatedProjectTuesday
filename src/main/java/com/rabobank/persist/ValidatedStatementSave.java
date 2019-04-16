package com.rabobank.persist;

import com.rabobank.domain.Records;

public interface ValidatedStatementSave {
	
	public void validateAndSave(Records statements);

}
