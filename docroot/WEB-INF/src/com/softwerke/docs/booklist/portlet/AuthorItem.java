package com.softwerke.docs.booklist.portlet;

import com.softwerke.docs.booklist.model.Author;

public class AuthorItem extends Item {

	public AuthorItem(String column, Author author) {
		super();
		this.Id = author.getAuthorId();
		this.column = column;
	}

}
