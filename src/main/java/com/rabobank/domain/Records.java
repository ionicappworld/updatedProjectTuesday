package com.rabobank.domain;

/**
 * @author vinesh
 *
 */
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)

public class Records {

	@XmlElement(name = "record")
	private List<Record> records;

	public Records(List<Record> records) {
		super();
		this.records = records;
	}

	public Records() {
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setChildrecords(List<Record> records) {
		this.records = records;
	}

}
