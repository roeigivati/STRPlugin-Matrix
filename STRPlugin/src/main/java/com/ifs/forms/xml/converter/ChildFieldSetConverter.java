package com.ifs.forms.xml.converter;

import com.ifs.forms.xml.ChildFieldSet;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ChildFieldSetConverter extends FieldSetConverter {

	public ChildFieldSetConverter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canConvert(Class type) {
		return type.equals(ChildFieldSet.class);
	}

	@Override
	protected void beforeMarshalValue(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		ChildFieldSet field = (ChildFieldSet) source;
		writer.addAttribute("parentSetName", field.getParentSetType().toString());
		writer.addAttribute("parentIndex", field.getParentIndex().toString());
		super.beforeMarshalValue(source, writer, context);
	}

}
