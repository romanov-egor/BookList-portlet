package com.softwerke.docs.booklist.portlet;

import com.softwerke.docs.booklist.model.Book;

public class BookItem extends Item {

	public BookItem(String column, Book book) {
		super();
		this.Id = book.getBookId();
		this.column = column;
	}
}
