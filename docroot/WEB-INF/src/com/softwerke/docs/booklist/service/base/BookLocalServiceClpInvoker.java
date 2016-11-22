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

package com.softwerke.docs.booklist.service.base;

import com.softwerke.docs.booklist.service.BookLocalServiceUtil;

import java.util.Arrays;

/**
 * @author rei
 * @generated
 */
public class BookLocalServiceClpInvoker {
	public BookLocalServiceClpInvoker() {
		_methodName0 = "addBook";

		_methodParameterTypes0 = new String[] {
				"com.softwerke.docs.booklist.model.Book"
			};

		_methodName1 = "createBook";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteBook";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteBook";

		_methodParameterTypes3 = new String[] {
				"com.softwerke.docs.booklist.model.Book"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "dynamicQueryCount";

		_methodParameterTypes9 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery",
				"com.liferay.portal.kernel.dao.orm.Projection"
			};

		_methodName10 = "fetchBook";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "fetchBookByUuidAndCompanyId";

		_methodParameterTypes11 = new String[] { "java.lang.String", "long" };

		_methodName12 = "fetchBookByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getBook";

		_methodParameterTypes13 = new String[] { "long" };

		_methodName14 = "getPersistedModel";

		_methodParameterTypes14 = new String[] { "java.io.Serializable" };

		_methodName15 = "getBookByUuidAndCompanyId";

		_methodParameterTypes15 = new String[] { "java.lang.String", "long" };

		_methodName16 = "getBookByUuidAndGroupId";

		_methodParameterTypes16 = new String[] { "java.lang.String", "long" };

		_methodName17 = "getBooks";

		_methodParameterTypes17 = new String[] { "int", "int" };

		_methodName18 = "getBooksCount";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "updateBook";

		_methodParameterTypes19 = new String[] {
				"com.softwerke.docs.booklist.model.Book"
			};

		_methodName20 = "addAuthorBook";

		_methodParameterTypes20 = new String[] { "long", "long" };

		_methodName21 = "addAuthorBook";

		_methodParameterTypes21 = new String[] {
				"long", "com.softwerke.docs.booklist.model.Book"
			};

		_methodName22 = "addAuthorBooks";

		_methodParameterTypes22 = new String[] { "long", "long[][]" };

		_methodName23 = "addAuthorBooks";

		_methodParameterTypes23 = new String[] { "long", "java.util.List" };

		_methodName24 = "clearAuthorBooks";

		_methodParameterTypes24 = new String[] { "long" };

		_methodName25 = "deleteAuthorBook";

		_methodParameterTypes25 = new String[] { "long", "long" };

		_methodName26 = "deleteAuthorBook";

		_methodParameterTypes26 = new String[] {
				"long", "com.softwerke.docs.booklist.model.Book"
			};

		_methodName27 = "deleteAuthorBooks";

		_methodParameterTypes27 = new String[] { "long", "long[][]" };

		_methodName28 = "deleteAuthorBooks";

		_methodParameterTypes28 = new String[] { "long", "java.util.List" };

		_methodName29 = "getAuthorBooks";

		_methodParameterTypes29 = new String[] { "long" };

		_methodName30 = "getAuthorBooks";

		_methodParameterTypes30 = new String[] { "long", "int", "int" };

		_methodName31 = "getAuthorBooks";

		_methodParameterTypes31 = new String[] {
				"long", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName32 = "getAuthorBooksCount";

		_methodParameterTypes32 = new String[] { "long" };

		_methodName33 = "hasAuthorBook";

		_methodParameterTypes33 = new String[] { "long", "long" };

		_methodName34 = "hasAuthorBooks";

		_methodParameterTypes34 = new String[] { "long" };

		_methodName35 = "setAuthorBooks";

		_methodParameterTypes35 = new String[] { "long", "long[][]" };

		_methodName58 = "getBeanIdentifier";

		_methodParameterTypes58 = new String[] {  };

		_methodName59 = "setBeanIdentifier";

		_methodParameterTypes59 = new String[] { "java.lang.String" };

		_methodName64 = "getBooks";

		_methodParameterTypes64 = new String[] { "long" };

		_methodName65 = "getBooksByAuthor";

		_methodParameterTypes65 = new String[] { "long" };

		_methodName67 = "addBook";

		_methodParameterTypes67 = new String[] {
				"java.lang.String", "java.lang.String", "java.util.Date",
				"java.util.List", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName68 = "updateBook";

		_methodParameterTypes68 = new String[] {
				"long", "java.lang.String", "java.lang.String", "java.util.Date",
				"java.util.List", "long",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName69 = "deleteBook";

		_methodParameterTypes69 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return BookLocalServiceUtil.addBook((com.softwerke.docs.booklist.model.Book)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return BookLocalServiceUtil.createBook(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return BookLocalServiceUtil.deleteBook(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return BookLocalServiceUtil.deleteBook((com.softwerke.docs.booklist.model.Book)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return BookLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return BookLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return BookLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return BookLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return BookLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return BookLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				(com.liferay.portal.kernel.dao.orm.Projection)arguments[1]);
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return BookLocalServiceUtil.fetchBook(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return BookLocalServiceUtil.fetchBookByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return BookLocalServiceUtil.fetchBookByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return BookLocalServiceUtil.getBook(((Long)arguments[0]).longValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return BookLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return BookLocalServiceUtil.getBookByUuidAndCompanyId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return BookLocalServiceUtil.getBookByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName17.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes17, parameterTypes)) {
			return BookLocalServiceUtil.getBooks(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return BookLocalServiceUtil.getBooksCount();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			return BookLocalServiceUtil.updateBook((com.softwerke.docs.booklist.model.Book)arguments[0]);
		}

		if (_methodName20.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes20, parameterTypes)) {
			BookLocalServiceUtil.addAuthorBook(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName21.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes21, parameterTypes)) {
			BookLocalServiceUtil.addAuthorBook(((Long)arguments[0]).longValue(),
				(com.softwerke.docs.booklist.model.Book)arguments[1]);

			return null;
		}

		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			BookLocalServiceUtil.addAuthorBooks(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			BookLocalServiceUtil.addAuthorBooks(((Long)arguments[0]).longValue(),
				(java.util.List<com.softwerke.docs.booklist.model.Book>)arguments[1]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			BookLocalServiceUtil.clearAuthorBooks(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			BookLocalServiceUtil.deleteAuthorBook(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());

			return null;
		}

		if (_methodName26.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes26, parameterTypes)) {
			BookLocalServiceUtil.deleteAuthorBook(((Long)arguments[0]).longValue(),
				(com.softwerke.docs.booklist.model.Book)arguments[1]);

			return null;
		}

		if (_methodName27.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes27, parameterTypes)) {
			BookLocalServiceUtil.deleteAuthorBooks(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			BookLocalServiceUtil.deleteAuthorBooks(((Long)arguments[0]).longValue(),
				(java.util.List<com.softwerke.docs.booklist.model.Book>)arguments[1]);

			return null;
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return BookLocalServiceUtil.getAuthorBooks(((Long)arguments[0]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return BookLocalServiceUtil.getAuthorBooks(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			return BookLocalServiceUtil.getAuthorBooks(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return BookLocalServiceUtil.getAuthorBooksCount(((Long)arguments[0]).longValue());
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			return BookLocalServiceUtil.hasAuthorBook(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return BookLocalServiceUtil.hasAuthorBooks(((Long)arguments[0]).longValue());
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			BookLocalServiceUtil.setAuthorBooks(((Long)arguments[0]).longValue(),
				(long[])arguments[1]);

			return null;
		}

		if (_methodName58.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes58, parameterTypes)) {
			return BookLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName59.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes59, parameterTypes)) {
			BookLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return BookLocalServiceUtil.getBooks(((Long)arguments[0]).longValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return BookLocalServiceUtil.getBooksByAuthor(((Long)arguments[0]).longValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return BookLocalServiceUtil.addBook((java.lang.String)arguments[0],
				(java.lang.String)arguments[1], (java.util.Date)arguments[2],
				(java.util.List<com.softwerke.docs.booklist.model.Author>)arguments[3],
				((Long)arguments[4]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[5]);
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return BookLocalServiceUtil.updateBook(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.lang.String)arguments[2],
				(java.util.Date)arguments[3],
				(java.util.List<com.softwerke.docs.booklist.model.Author>)arguments[4],
				((Long)arguments[5]).longValue(),
				(com.liferay.portal.service.ServiceContext)arguments[6]);
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return BookLocalServiceUtil.deleteBook(((Long)arguments[0]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName17;
	private String[] _methodParameterTypes17;
	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName20;
	private String[] _methodParameterTypes20;
	private String _methodName21;
	private String[] _methodParameterTypes21;
	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
	private String _methodName26;
	private String[] _methodParameterTypes26;
	private String _methodName27;
	private String[] _methodParameterTypes27;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
	private String _methodName58;
	private String[] _methodParameterTypes58;
	private String _methodName59;
	private String[] _methodParameterTypes59;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
}