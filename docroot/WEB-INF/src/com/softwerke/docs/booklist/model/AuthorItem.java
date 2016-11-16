package com.softwerke.docs.booklist.model;

import java.util.*;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.softwerke.docs.booklist.model.Author;
import com.softwerke.docs.booklist.service.AuthorLocalServiceUtil;

public class AuthorItem {
	
	long Id;
	String firstName;
	String lastName;
	Date birthDate;
	String email;
	ArrayList<Book> relatedBooks;

	public AuthorItem(Author author) throws PortalException, SystemException {
		this.Id = author.getAuthorId();
		this.firstName = author.getFirstName();
		this.lastName = author.getLastName();
		this.birthDate = author.getBirthDate();
		this.email = author.getEmail();
		this.relatedBooks = new ArrayList<Book>(AuthorLocalServiceUtil.getBooksByAuthor(this.Id));
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Book> getRelatedBooks() {
		return relatedBooks;
	}

	public void setRelatedBooks(List<Book> relatedBooks) {
		this.relatedBooks = new ArrayList<Book>(relatedBooks);
	}

}
