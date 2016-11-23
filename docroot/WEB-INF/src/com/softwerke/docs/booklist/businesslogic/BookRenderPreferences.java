package com.softwerke.docs.booklist.businesslogic;

import java.util.List;

public class BookRenderPreferences {
	
	private boolean titleVisible;
	private boolean isbnVisible;
	private boolean releaseDateVisible;
	
	public BookRenderPreferences(List<String> preferences) {
		this.titleVisible = preferences.contains("title");
		this.isbnVisible = preferences.contains("ISBN");
		this.releaseDateVisible = preferences.contains("releaseDate");
	}
	
	public boolean isTitleVisible() {
		return titleVisible;
	}
	public boolean isIsbnVisible() {
		return isbnVisible;
	}
	public boolean isReleaseDateVisible() {
		return releaseDateVisible;
	}
}
