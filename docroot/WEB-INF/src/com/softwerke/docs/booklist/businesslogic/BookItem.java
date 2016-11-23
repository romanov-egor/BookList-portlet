package com.softwerke.docs.booklist.businesslogic;

import java.util.*;
import java.text.DateFormat;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.softwerke.docs.booklist.model.Author;
import com.softwerke.docs.booklist.model.Book;
import com.softwerke.docs.booklist.service.AuthorLocalServiceUtil;

/**
 * Model class for {@link com.softwerke.docs.booklist.model.Book Book}
 * @author Romanov Egor
 */
public class BookItem {
	private long Id;
	private String title;
	private String ISBN;
	private Date releaseDate;
	private ArrayList<Author> relatedAuthors;
	
	public BookItem(Book book) throws PortalException, SystemException {
		this.Id = book.getBookId();
		this.title = book.getTitle();
		this.ISBN = book.getIsbn();
		this.releaseDate = book.getReleaseDate();
		this.relatedAuthors = new ArrayList<Author>(
				AuthorLocalServiceUtil.getAuthorsByBook(this.Id));
	}

	public long getId() {
		return Id;
	}

	public String getTitle() {
		return title;
	}

	public String getISBN() {
		return ISBN;
	}

	public String getReleaseDate() {
		String date = releaseDate.toString();
		String[] tmp = date.split(" ");
		return String.format("%s %s %s", tmp[2], tmp[1], tmp[5]); 
	}

	public ArrayList<Author> getRelatedAuthors() {
		return relatedAuthors;
	}
}
