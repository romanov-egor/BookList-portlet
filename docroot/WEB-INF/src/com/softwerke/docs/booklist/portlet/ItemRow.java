package com.softwerke.docs.booklist.portlet;

import java.util.*;

public class ItemRow {

	private HashMap<String, List<Item>> row;
	
	public ItemRow() {
	}
	
	public ItemRow(List<String> names) {
		this.row = new HashMap<String, List<Item>>();
		for (String name : names) {
			row.put(name, new ArrayList<Item>());
		}
	}
	
	public ItemRow(HashMap<String, List<Item>> row) {
		this.row = row;
	}
	
	public List<Item> getNamedElement(String name) {
		return row.get(name);
	}
	
	public void addNamedElement(String name, List<Item> namedElement) {
		this.row.put(name, namedElement);
	}
	
	public boolean contains(Item item) {
		for (List<Item> i : this.row.values()) {
			if (i.contains(item)) {
				return true;
			}
		}
		return false;
	}

	public List<String> getNames() {
		return new ArrayList<String>(this.row.keySet());
	}

	public void setNames(List<String> names) {
		for (String name : names) {
			row.put(name, new ArrayList<Item>());
		}
	}

	public HashMap<String, List<Item>> getRow() {
		return row;
	}

	public void setRow(HashMap<String, List<Item>> row) {
		this.row = row;
	}
}
