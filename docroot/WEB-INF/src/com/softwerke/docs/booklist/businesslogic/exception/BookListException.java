package com.softwerke.docs.booklist.businesslogic.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class BookListException extends PortalException{
	
	protected String localizationKey;
	
	public BookListException(String key) {
		this.localizationKey = key;
	}
	
	public String getLocalizationKey() {
		return this.localizationKey;
	}
}
