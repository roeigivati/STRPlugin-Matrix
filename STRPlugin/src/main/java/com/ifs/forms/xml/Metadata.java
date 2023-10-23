/**
 * 
 */
package com.ifs.forms.xml;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author AWAK001
 *
 */
@XStreamAlias("metadata")
public class Metadata {

	/**
	 * Default Constructor
	 */
	public Metadata() {
	}
	
	@XStreamImplicit
	private List<Property> properties = new ArrayList<Property>();

	/**
	 * @return the properties
	 */
	public List<Property> getProperties() {
		return properties;
	}

	/**
	 * @param properties the properties to set
	 */
	public void setProperties(List<Property> properties) {
		this.properties = properties;
	}
	
	/**
	 * @param property the property to add
	 */
	public void addProperty(Property property) {
		this.properties.add(property);
	}

}
