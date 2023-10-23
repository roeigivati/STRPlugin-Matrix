package com.ifs.fstream;

import java.lang.reflect.Field; 
import java.util.List;

import com.ifs.forms.xml.ChildFieldSet;
import com.ifs.forms.xml.FieldSet;
import com.ifs.forms.xml.FieldType;
import com.ifs.forms.xml.FormDataRaw;
import com.ifs.fstream.annotations.FormData;
import com.ifs.fstream.annotations.FormField;
import com.ifs.fstream.annotations.FormFieldImplicit;
import com.ifs.str.parts.PartInterface;

public class FStream {
	FormDataRaw raw;
	
	Integer dispositionCounter = 0;
	
	public FStream() {
	}

	public FormDataRaw getDataRaw(Object obj) {
		dispositionCounter = 0;
		raw = new FormDataRaw();
		if (!(obj instanceof PartInterface)) {
			throw new RuntimeException("The root object for the Stream processer needs to implement PartInterface");
		}
		
		try {
			getData(obj,0,0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return raw;
	}

	private void getData(Object obj, int index, int parentIndex) throws Exception {
		//Skip processing null objects
		if(obj == null) return;
		//process if object instance is not null
		Class clazz = obj.getClass();
		if (obj instanceof PartInterface && clazz.isAnnotationPresent(FormData.class)) {
			FormData classAnnotation = (FormData) clazz.getAnnotation(FormData.class);
			switch (classAnnotation.type()) {
			case Field:
				processFieldAnnotations(classAnnotation, obj);
				break;
			case FieldSet:
				processFieldSetAnnotations(classAnnotation, obj, index);
				break;
			case ChildFieldSet:
				processChildFieldSetAnnotations(classAnnotation, obj, index, parentIndex);
				break;
			default:
				break;
			}
		} else if (obj instanceof List) {
			int i = 0;
			for (Object item : (List) obj) {
				if (index > 0)
					getData(item, ++i, index);
				else
					getData(item, ++i, 0);
			}
		}
	}

	private void processFieldAnnotations(FormData classAnnotation, Object obj) throws Exception {
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(FormFieldImplicit.class)) {
				getData(field.get(obj), 0, 0);
			} else if (field.isAnnotationPresent(FormField.class)) {
				FormField fieldAnnotation = field.getAnnotation(FormField.class);
				com.ifs.forms.xml.Field f = new com.ifs.forms.xml.Field();
				if (fieldAnnotation.type() == FieldType.String)
					f.setString(fieldAnnotation.name(), getStringValue(field.get(obj)));
				else if (fieldAnnotation.type() == FieldType.Date)
					f.setDate(fieldAnnotation.name(), (String) field.get(obj), fieldAnnotation.format());
				raw.addData(f);
			}
		}
	}

	private void processFieldSetAnnotations(FormData classAnnotation, Object obj, int index) throws Exception {
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(FormFieldImplicit.class)) {
				getData(field.get(obj), index, 0);
			} else if (field.isAnnotationPresent(FormField.class)) {
				FormField fieldAnnotation = field.getAnnotation(FormField.class);
				FieldSet f = new FieldSet();
				if (fieldAnnotation.type() == FieldType.String) {
					f.setString(fieldAnnotation.name()+index, getStringValue(field.get(obj)), index, classAnnotation.setType());
				} else if (fieldAnnotation.type() == FieldType.Date) {
					f.setDate(fieldAnnotation.name()+index, getStringValue(field.get(obj)), fieldAnnotation.format(), index,
							classAnnotation.setType());
				}
				raw.addData(f);
			}
		}
	}

	private String getStringValue(Object obj) {
		if (obj!=null)
			return obj.toString();
		return null;
	}
	private void processChildFieldSetAnnotations(FormData classAnnotation, Object obj, int index, int parentIndex)
			throws Exception {

		String className = obj.getClass().getSimpleName();
		if (className.equalsIgnoreCase("PartB2DispositionCompletion")) {
			dispositionCounter = dispositionCounter + 1;
		}
		
		
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(FormFieldImplicit.class)) {
				getData(field.get(obj), index, parentIndex);
			} else if (field.isAnnotationPresent(FormField.class)) {
				FormField fieldAnnotation = field.getAnnotation(FormField.class);
				ChildFieldSet f = new ChildFieldSet();
				if (className.equalsIgnoreCase("PartB2DispositionCompletion") || className.equalsIgnoreCase("PartCDispositionAccount") 
						|| className.equalsIgnoreCase("PartEOnBehalfOfEntity") || className.equalsIgnoreCase("PartFOnBehalfOfIndividual") ) {
					//System.out.println("className-----> "+ className);
					//System.out.println("classAnnotation.setType -----> "+  classAnnotation.setType());
					
					if (fieldAnnotation.type() == FieldType.String)
						f.setString(fieldAnnotation.name()+dispositionCounter, getStringValue(field.get(obj)), dispositionCounter, classAnnotation.setType(), parentIndex, classAnnotation.parentSetType());
					else if (fieldAnnotation.type() == FieldType.Date)
						f.setDate(fieldAnnotation.name()+dispositionCounter, getStringValue(field.get(obj)), fieldAnnotation.format(), dispositionCounter, classAnnotation.setType(), parentIndex, classAnnotation.parentSetType());
					
				} else {
					if (fieldAnnotation.type() == FieldType.String)
						f.setString(fieldAnnotation.name()+index, getStringValue(field.get(obj)), index, classAnnotation.setType(), parentIndex, classAnnotation.parentSetType());
					else if (fieldAnnotation.type() == FieldType.Date)
						f.setDate(fieldAnnotation.name()+index, getStringValue(field.get(obj)), fieldAnnotation.format(), index, classAnnotation.setType(), parentIndex, classAnnotation.parentSetType());
				}
				
				raw.addData(f);
			}
		}
	}

}
