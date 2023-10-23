package com.ifs.forms.xml.converter;

import com.ifs.forms.xml.Field;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class FieldConvertor implements Converter {

	public FieldConvertor() {
	}

	public boolean canConvert(Class type) {
		return type.equals(Field.class);
	}

	protected void beforeMarshalValue(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Field field = (Field) source;
		writer.addAttribute("name", field.getName());
		writer.addAttribute("type", field.getType().toString());
	}

	private void setValue(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		if(source != null)
			writer.setValue(source.toString().toString());
	}
	
	protected void marshalValue(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Field field = (Field) source;
		switch (field.getType()) {
		case String:
			writer.startNode("value");
			setValue(field.getValue(), writer, context);
			writer.endNode();
			break;
		case Date:
			writer.startNode("date");
			writer.addAttribute("format", field.getFormat());
			writer.startNode("value");
			setValue(field.getValue(), writer, context);
			writer.endNode();
			writer.endNode();
		default:
			break;
		}
	}

	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		beforeMarshalValue(source, writer, context);
		marshalValue(source, writer, context);
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		// TODO Auto-generated method stub
		return null;
	}

}
