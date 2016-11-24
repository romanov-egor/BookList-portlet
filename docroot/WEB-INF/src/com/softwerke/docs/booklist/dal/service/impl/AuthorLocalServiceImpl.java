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

package com.softwerke.docs.booklist.dal.service.impl;

import java.util.Date;
import java.util.List;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.ServiceContext;
import com.softwerke.docs.booklist.dal.service.base.AuthorLocalServiceBaseImpl;
import com.softwerke.docs.booklist.model.Author;
import com.softwerke.docs.booklist.model.Book;

/**
 * The implementation of the author local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.softwerke.docs.booklist.service.AuthorLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author rei
 * @see com.softwerke.docs.booklist.dal.service.base.AuthorLocalServiceBaseImpl
 * @see com.softwerke.docs.booklist.service.AuthorLocalServiceUtil
 */
public class AuthorLocalServiceImpl extends AuthorLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.softwerke.docs.booklist.service.AuthorLocalServiceUtil} to access the author local service.
	 */
	
	/**
	 * Returns {@link java.util.List List} of 
	 * {@link com.softwerke.docs.booklist.model.Author Authors}
	 */
	public List<Author> getAuthors (long groupId) throws SystemException {
	    return authorPersistence.findByGroupId(groupId);
	}
	
	/**
	 * Returns {@link java.util.List List} of 
	 * {@link com.softwerke.docs.booklist.model.Book Books} related to an
	 * {@link com.softwerke.docs.booklist.model.Author Author}
	 */
	public List<Author> getAuthorsByBook(long bookId)
		    throws SystemException {
	    return bookPersistence.getAuthors(bookId);
	}
	
	/**
	 * Adds new {@link com.softwerke.docs.booklist.model.Author Author} instance 
	 * in database
	 */
	public Author addAuthor(String firstName, String lastName, Date birthDate, 
			String email, List<Book> books, long userId, 
			ServiceContext serviceContext)
	        throws PortalException, SystemException {
	    long groupId = serviceContext.getScopeGroupId();
	    User user = userPersistence.findByPrimaryKey(userId);
	    Date now = new Date();
	    long authorId = counterLocalService.increment();
	    Author author = authorPersistence.create(authorId);
	    
	    author.setUuid(serviceContext.getUuid());
	    author.setUserId(userId);
	    author.setGroupId(groupId);
	    author.setCompanyId(user.getCompanyId());
	    author.setUserName(user.getFullName());
	    author.setCreateDate(serviceContext.getCreateDate(now));
	    author.setModifiedDate(serviceContext.getModifiedDate(now));
	    author.setExpandoBridgeAttributes(serviceContext);
	    author.setFirstName(firstName);
	    author.setLastName(lastName);
	    author.setBirthDate(birthDate);
	    author.setEmail(email);
	    if (books != null && !books.isEmpty()) {
	    	authorPersistence.addBooks(author.getPrimaryKey(), books);
	    }
	    authorPersistence.update(author);
	    return author;
	}
	
	/**
	 * Updates existing {@link com.softwerke.docs.booklist.model.Author Author} 
	 * instance in database
	 */
	public Author updateAuthor(long authorId, String firstName, 
			String lastName, Date birthDate, String email, List<Book> books, 
			long userId, ServiceContext serviceContext)
	        throws PortalException, SystemException {
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);
	    Author author = authorPersistence.findByPrimaryKey(authorId);
	    
	    author.setUuid(serviceContext.getUuid());
	    author.setUserId(userId);
	    author.setCompanyId(user.getCompanyId());
	    author.setUserName(user.getFullName());
	    author.setModifiedDate(serviceContext.getModifiedDate(now));
	    author.setExpandoBridgeAttributes(serviceContext);
	    author.setFirstName(firstName);
	    author.setLastName(lastName);
	    author.setBirthDate(birthDate);
	    author.setEmail(email);
	    authorPersistence.clearBooks(authorId);
	    if (books != null && !books.isEmpty()) {
	    	authorPersistence.addBooks(authorId, books);
	    }
	    authorPersistence.update(author);
	    return author;
	}
	
	/**
	 * Removes {@link com.softwerke.docs.booklist.model.Author Author} instance 
	 * from database
	 */
	public Author deleteAuthor(long authorId) 
			throws PortalException, SystemException {
		Author author = authorPersistence.findByPrimaryKey(authorId);
		
		authorPersistence.clearBooks(authorId);
		authorPersistence.remove(author);
		return author;
	}
}