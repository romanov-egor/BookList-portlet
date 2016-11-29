package com.softwerke.docs.booklist.businesslogic.model;

import java.util.List;

public class AuthorRenderPreferences {
	
	private boolean firstNameVisible;
	private boolean lastNameVisible;
	private boolean birthDateVisible;
	private boolean emailVisible;
	
	public AuthorRenderPreferences(List<String> preferences) {
		this.firstNameVisible = preferences.contains("firstName");
		this.lastNameVisible = preferences.contains("lastName");
		this.birthDateVisible = preferences.contains("birthDate");
		this.emailVisible = preferences.contains("email");
	}
	
	public boolean isFirstNameVisible() {
		return firstNameVisible;
	}
	public boolean isLastNameVisible() {
		return lastNameVisible;
	}
	public boolean isBirthDateVisible() {
		return birthDateVisible;
	}
	public boolean isEmailVisible() {
		return emailVisible;
	}
}
