package com.ifs.forms.xml;

import com.ifs.forms.xml.converter.FieldSetConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("field")
@XStreamConverter(value = FieldSetConverter.class)
public class FieldSet extends Field {

	private SetType setType;
	private Integer index;

	public FieldSet() {
	}

	public Integer getIndex() {
		return index;
	}

	public SetType getSetType() {
		return setType;
	}

	private void setData(Integer index, SetType setType) {
		this.index = index;
		this.setType = setType;
	}

	public void setString (String name, String value, Integer index, SetType setType) {
		setData(index, setType);
		super.setString(name, value);
	}
	
	public void setDate (String name, Object value, String format, Integer index, SetType setType) {
		setData(index, setType);
		super.setDate(name, value, format);
	}
}
