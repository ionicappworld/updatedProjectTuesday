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
	private List<Record> childrecords;

	public Records(List<Record> childrecords) {
		super();
		this.childrecords = childrecords;
	}

	public Records() {
	}

	public List<Record> getChildrecords() {
		return childrecords;
	}

	public void setChildrecords(List<Record> childrecords) {
		this.childrecords = childrecords;
	}

}
