/**
 * 
 */
package com.ifs.fstream.annotations;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.ifs.forms.xml.SetType;

@Retention(RUNTIME)
@Target(TYPE)
/**
 * @author AWAK001
 *
 */
public @interface FormData {
	public enum Type {
		Field, FieldSet, ChildFieldSet
	}

	Type type() default Type.Field;

	SetType setType() default SetType.BLANK;

	SetType parentSetType() default SetType.BLANK;
}
