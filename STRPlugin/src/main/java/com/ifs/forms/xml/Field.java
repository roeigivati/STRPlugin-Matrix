package com.ifs.forms.xml;

import com.ifs.forms.xml.converter.FieldConvertor;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("field")
@XStreamConverter(value=FieldConvertor.class)
public class Field {

	public Field() {
	}

	private FieldType type;
	private String name;
	private Object value;
	private String format;
	
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @return the type
	 */
	public FieldType getType() {
		return type;
	}

	private void setData(String name, String value, FieldType type) {
		this.type = type;
		this.name = name;
		this.value = value;
	}

	private void setData(String name, Object value, FieldType type, String format) {
		this.type = type;
		this.name = name;
		this.value = value;
		this.format = format;
	}

	public void setString(String name, String value) {
		setData(name, value, FieldType.String);
	}

	public void setDate(String name, Object value, String format) {
		setData(name, value, FieldType.Date, format);
	}
	
	
}
