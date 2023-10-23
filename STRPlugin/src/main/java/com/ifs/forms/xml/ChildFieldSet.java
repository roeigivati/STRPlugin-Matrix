package com.ifs.forms.xml;

import com.ifs.forms.xml.converter.ChildFieldSetConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("field")
@XStreamConverter(value = ChildFieldSetConverter.class)
public class ChildFieldSet extends FieldSet {

	private SetType parentSetType;
	private Integer parentIndex;

	public ChildFieldSet() {
	}

	/**
	 * @return the parentSetType
	 */
	public SetType getParentSetType() {
		return parentSetType;
	}


	/**
	 * @return the parentIndex
	 */
	public Integer getParentIndex() {
		return parentIndex;
	}


	private void setData(Integer parentIndex, SetType parentSetType) {
		this.parentIndex = parentIndex;
		this.parentSetType = parentSetType;
	}
	
	public void setString (String name, String value, Integer index, SetType setType, Integer parentIndex, SetType parentSetType) {
		setData(parentIndex, parentSetType);
		super.setString(name, value, index, setType);
	}
	
	public void setDate (String name, Object value, String format, Integer index, SetType setType, Integer parentIndex, SetType parentSetType) {
		setData(parentIndex, parentSetType);
		super.setDate(name, value, format, index, setType);
	}
}
