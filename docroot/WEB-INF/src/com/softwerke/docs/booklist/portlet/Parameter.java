package com.softwerke.docs.booklist.portlet;

public class Parameter {
	private String name;
	private String value;
	private boolean visible;
	
	public Parameter(String name, String value, boolean visible) {
		this.name = name;
		this.value = value;
		this.visible = visible;
	}
	
	public Parameter(String name) {
		this(name, "", true);
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public boolean isVisible() {
		return visible;
	}
	
	@Override
	public boolean equals(Object o) {
		Parameter parameter = null;
		try {
			parameter = (Parameter) o;
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		if (parameter.name == this.name && parameter.value == this.value) {
			return true;
		}
		return false;
	}
}
