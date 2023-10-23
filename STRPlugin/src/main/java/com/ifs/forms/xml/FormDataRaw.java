package com.ifs.forms.xml;

import java.util.ArrayList;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("form")
public class FormDataRaw {

	public FormDataRaw() {
	}
	
	@XStreamImplicit
	private ArrayList<Object> data = new ArrayList<Object>();

	public ArrayList<Object> getData() {
		return data;
	}

	public void setData(ArrayList<Object> data) {
		this.data = data;
	}
	
	public void addData(Object data) {
		this.data.add(data);
	}

}
