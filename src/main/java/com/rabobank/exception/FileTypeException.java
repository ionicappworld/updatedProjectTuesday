package com.rabobank.exception;

import com.rabobank.enums.StatementFileType;

public class FileTypeException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileTypeException(StatementFileType fileType) {
        super("UnSupported File Format !!! : " + fileType);
    } 

}
