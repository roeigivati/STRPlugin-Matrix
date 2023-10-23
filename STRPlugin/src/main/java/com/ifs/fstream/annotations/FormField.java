/**
 * 
 */
package com.ifs.fstream.annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.ifs.forms.xml.FieldType;

@Retention(RUNTIME)
@Target(FIELD)
/**
 * @author AWAK001
 *
 */
public @interface FormField {
	String name();

	FieldType type() default FieldType.String;

	String format() default "";
}
