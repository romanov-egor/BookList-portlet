/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.softwerke.docs.booklist.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.softwerke.docs.booklist.BookISBNException;
import com.softwerke.docs.booklist.BookReleaseDateException;
import com.softwerke.docs.booklist.BookTitleException;
import com.softwerke.docs.booklist.model.Author;
import com.softwerke.docs.booklist.model.Book;
import com.softwerke.docs.booklist.service.base.BookLocalServiceBaseImpl;

/**
 * The implementation of the book local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.softwerke.docs.booklist.service.BookLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Romanov Egor
 * @see com.softwerke.docs.booklist.service.base.BookLocalServiceBaseImpl
 * @see com.softwerke.docs.booklist.service.BookLocalServiceUtil
 */
public class BookLocalServiceImpl extends BookLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.softwerke.docs.booklist.service.BookLocalServiceUtil} to access the book local service.
	 */
	
	/**
	 * Returns {@link java.util.List List} of 
	 * {@link com.softwerke.docs.booklist.model.Book Books}
	 */
	public List<Book> getBooks (long groupId) throws SystemException {
	    return bookPersistence.findByGroupId(groupId);
	}
	
//	/**
//	 * Returns {@link java.util.List List} of 
//	 * {@link com.softwerke.docs.booklist.model.Book Books}
//	 */
//	public List<Book> getBooks (long groupId, int start, int end)
//	   throws SystemException {
//	    return bookPersistence.findByGroupId(groupId, start, end);
//	}
	
	/**
	 * Returns {@link java.util.List List} of 
	 * {@link com.softwerke.docs.booklist.model.Book Book} related to a
	 * {@link com.softwerke.docs.booklist.model.Author Author}
	 */
	public List<Book> getBooksByAuthor(long authorId)
		    throws SystemException {
		return authorPersistence.getBooks(authorId);
	}
	
	/**
	 * Validates {@link com.softwerke.docs.booklist.model.Book Book's}
	 * title, ISBN and release date
	 * @param title
	 * @param ISBN
	 * @param releaseDate
	 * @throws PortalException
	 */
	protected void validate(String title, String ISBN, Date releaseDate) 
	        throws PortalException {
	    if (Validator.isNull(title)) {
	        throw new BookTitleException();
	    }

	    if (Validator.isNull(ISBN)) {
	        throw new BookISBNException();
	    }

	    if (Validator.isNull(releaseDate)) {
	        throw new BookReleaseDateException();
	    }
	}
	
	/**
	 * Adds new {@link com.softwerke.docs.booklist.model.Book Book} instance in
	 * database
	 */
	public Book addBook(String title, String ISBN, Date releaseDate, 
			List<Author> authors, long userId, ServiceContext serviceContext)
	        throws PortalException, SystemException {
	    long groupId = serviceContext.getScopeGroupId();
	    User user = userPersistence.findByPrimaryKey(userId);
	    Date now = new Date();
	    long bookId = counterLocalService.increment();
	    Book book = bookPersistence.create(bookId);
	    
	    validate(title, ISBN, releaseDate);

	    book.setUuid(serviceContext.getUuid());
	    book.setUserId(userId);
	    book.setGroupId(groupId);
	    book.setCompanyId(user.getCompanyId());
	    book.setUserName(user.getFullName());
	    book.setCreateDate(serviceContext.getCreateDate(now));
	    book.setModifiedDate(serviceContext.getModifiedDate(now));
	    book.setExpandoBridgeAttributes(serviceContext);
	    book.setTitle(title);
	    book.setIsbn(ISBN);
	    book.setReleaseDate(releaseDate);
	    if (authors != null && !authors.isEmpty()) {
	    	bookPersistence.addAuthors(book.getPrimaryKey(), authors);
	    }
	    bookPersistence.update(book);
	    return book;
	}
	
	/**
	 * Updates existing {@link com.softwerke.docs.booklist.model.Book Book} 
	 * instance in database
	 */
	public Book updateBook(long bookId, String newTitle, String newISBN, 
			Date newReleaseDate, List<Author> newAuthors, long userId, 
			ServiceContext serviceContext) 
			throws PortalException, SystemException {
		
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
		Book book = bookPersistence.findByPrimaryKey(bookId);
		
		validate(newTitle, newISBN, newReleaseDate);
		
		book.setUuid(serviceContext.getUuid());
		book.setUserId(userId);
		book.setCompanyId(user.getCompanyId());
	    book.setUserName(user.getFullName());
	    book.setModifiedDate(serviceContext.getModifiedDate(now));
	    book.setExpandoBridgeAttributes(serviceContext);
	    book.setTitle(newTitle);
	    book.setIsbn(newISBN);
	    book.setReleaseDate(newReleaseDate);
	    bookPersistence.clearAuthors(bookId);
	    if (newAuthors != null && !newAuthors.isEmpty()) {
	    	bookPersistence.addAuthors(bookId, newAuthors);
	    }
	    bookPersistence.update(book);
		return book;
	}
	
	/**
	 * Removes {@link com.softwerke.docs.booklist.model.Book Book} instance 
	 * from database
	 */
	public Book deleteBook(long bookId) 
			throws PortalException, SystemException {
		Book book = bookPersistence.findByPrimaryKey(bookId);
		
	    bookPersistence.clearAuthors(bookId);
		bookPersistence.remove(book);
		return book;
	}
}