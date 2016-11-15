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
import com.softwerke.docs.booklist.AuthorBirthDateException;
import com.softwerke.docs.booklist.AuthorEmailException;
import com.softwerke.docs.booklist.AuthorFirstNameException;
import com.softwerke.docs.booklist.model.Author;
import com.softwerke.docs.booklist.model.Book;
import com.softwerke.docs.booklist.service.base.AuthorLocalServiceBaseImpl;

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
 * @see com.softwerke.docs.booklist.service.base.AuthorLocalServiceBaseImpl
 * @see com.softwerke.docs.booklist.service.AuthorLocalServiceUtil
 */
public class AuthorLocalServiceImpl extends AuthorLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.softwerke.docs.booklist.service.AuthorLocalServiceUtil} to access the author local service.
	 */
	
	public List<Author> getAuthors (long groupId) throws SystemException {
	    return authorPersistence.findByGroupId(groupId);
	}

	public List<Author> getAuthors (long groupId, int start, int end)
	   throws SystemException {
	    return authorPersistence.findByGroupId(groupId, start, end);
	}
	
	public List<Book> getBooksByAuthor(long authorId)
		    throws PortalException, SystemException {

		    return authorPersistence.getBooks(authorId);
		}
	
	protected void validate (String firstName, Date birthDate, String email) 
	        throws PortalException {
	    if (Validator.isNull(firstName)) {
	        throw new AuthorFirstNameException();
	    }

	    if (Validator.isNull(email)) {
	        throw new AuthorEmailException();
	    }
	    
	    if (!Validator.isEmailAddress(email)) {
	        throw new AuthorEmailException();
	    }

	    if (Validator.isNull(birthDate)) {
	        throw new AuthorBirthDateException();
	    }
	}
	
	public Author addAuthor(String firstName, String lastName, Date birthDate, String email,
			List<Book> books, long userId, ServiceContext serviceContext)
	        throws PortalException, SystemException {
		
	    long groupId = serviceContext.getScopeGroupId();
	    User user = userPersistence.findByPrimaryKey(userId);
	    Date now = new Date();

	    validate(firstName, birthDate, email);

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
	
	public Author updateAuthor(long authorId, String firstName, String lastName, Date birthDate, String email,
			List<Book> books, long userId, ServiceContext serviceContext)
	        throws PortalException, SystemException {
		
		Date now = new Date();
		User user = userPersistence.findByPrimaryKey(userId);

	    validate(firstName, birthDate, email);

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
	
	public Author deleteAuthor(long authorId) 
			throws PortalException, SystemException {
		Author author = authorPersistence.findByPrimaryKey(authorId);
		authorPersistence.clearBooks(authorId);
		authorPersistence.remove(author);
		return author;
	}
}