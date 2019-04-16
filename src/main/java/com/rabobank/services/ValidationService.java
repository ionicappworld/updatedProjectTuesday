package com.rabobank.services;

import com.rabobank.domain.Record;

public interface ValidationService {

	public Boolean validateDuplicate(Record record);

	public Boolean validateEndBalance(Record record);

}
