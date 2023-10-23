package com.ifs.forms.xml.converter;

import com.ifs.forms.xml.FieldSet;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class FieldSetConverter extends FieldConvertor {

	public FieldSetConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(FieldSet.class);
	}

	@Override
	protected void beforeMarshalValue(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		FieldSet field = (FieldSet) source;
		writer.addAttribute("setName", field.getSetType().toString());
		writer.addAttribute("index", field.getIndex().toString());
		super.beforeMarshalValue(source, writer, context);
	}

}
