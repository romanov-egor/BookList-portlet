package com.softwerke.docs.booklist.model;

import java.util.*;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.softwerke.docs.booklist.model.Book;
import com.softwerke.docs.booklist.service.BookLocalServiceUtil;

public class BookItem {
	
	long Id;
	String title;
	String ISBN;
	Date releaseDate;
	ArrayList<Author> relatedAuthors;
	
	public BookItem(Book book) throws PortalException, SystemException {
		this.Id = book.getBookId();
		this.title = book.getTitle();
		this.ISBN = book.getIsbn();
		this.releaseDate = book.getReleaseDate();
		this.relatedAuthors = new ArrayList<Author>(BookLocalServiceUtil.getAuthorsByBook(this.Id));
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public ArrayList<Author> getRelatedAuthors() {
		return relatedAuthors;
	}

	public void setRelatedAuthors(List<Author> relatedAuthors) {
		this.relatedAuthors = new ArrayList<Author>(relatedAuthors);
	}
}
