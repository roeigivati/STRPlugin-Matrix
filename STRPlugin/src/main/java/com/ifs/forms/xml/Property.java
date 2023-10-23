/**
 * 
 */
package com.ifs.forms.xml;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * @author AWAK001
 *
 */
@XStreamAlias("prop")
public class Property {

	/**
	 * 
	 */
	public Property(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	@XStreamAsAttribute
	private String name;
	@XStreamAlias("value")
	private String value;

}
