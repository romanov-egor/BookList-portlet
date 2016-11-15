package com.softwerke.docs.booklist.portlet;

import java.util.*;

public abstract class Item {
	protected String column;
	protected long Id;
	protected List<Parameter> parameters;

	public Item() {
		this.parameters = new ArrayList<Parameter>();
	}
	
	public void addParameter(String paramName, String paramValue, boolean isVisible) {
		this.parameters.add(new Parameter(paramName, paramValue, isVisible));
	}
	
	public String getParameter(String name) {
		for (Parameter parameter : parameters) {
			if (parameter.getName().equals(name)) {
				return parameter.getValue();
			}
		}
		return null;
	}
	
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	
	public String getColumn() {
		return this.column;
	}

	public void setColumn(String column) {
		this.column = column;
	}
	
	public List<Parameter> getParameters() {
		return parameters;
	}

	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}

	@Override
	public boolean equals(Object o) {
		Item item = null;
		try {
			item = (Item) o;
		} catch (ClassCastException e) {
			e.printStackTrace();
		}
		if (item.column == this.column && item.Id == this.Id) {
			return true;
		}
		return false;
	}
}
