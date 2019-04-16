package com.rabobank.validator;

import com.rabobank.domain.Record;

public interface CustomerStatementValidator {

	public Boolean validateDuplicate(Record record);

	public Boolean validateEndBalance(Record record);

}
