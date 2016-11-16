package com.softwerke.docs.booklist.portlet;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletMode;
import javax.portlet.PortletModeException;
import javax.portlet.PortletPreferences;
import javax.portlet.ReadOnlyException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ValidatorException;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.softwerke.docs.booklist.model.Author;
import com.softwerke.docs.booklist.model.AuthorItem;
import com.softwerke.docs.booklist.model.Book;
import com.softwerke.docs.booklist.model.BookItem;
import com.softwerke.docs.booklist.service.AuthorLocalServiceUtil;
import com.softwerke.docs.booklist.service.BookLocalServiceUtil;

/**
 * Portlet implementation class BookList
 */
public class BookList extends MVCPortlet {
	
	public void addBook(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {

	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	        Book.class.getName(), request);

	    String title = ParamUtil.getString(request, "title");
	    String ISBN = ParamUtil.getString(request, "ISBN");
	    Date releaseDate = ParamUtil.getDate(request, "releaseDate", DateFormat.getDateInstance());
	    
	    try {
	        BookLocalServiceUtil.addBook(title, ISBN, releaseDate, null, serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "bookAdded");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editBook.jsp");
	    }
	}
	
	public void updateBook(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Book.class.getName(), request);
		
			long bookId = ParamUtil.getLong(request, "bookId");
		    String title = ParamUtil.getString(request, "title");
		    String ISBN = ParamUtil.getString(request, "ISBN");
		    Date releaseDate = ParamUtil.getDate(request, "releaseDate", DateFormat.getDateInstance());
		    long authorId = ParamUtil.getLong(request, "authorId");
		    
		    ArrayList<Author> authors = new ArrayList<Author>(BookLocalServiceUtil.getAuthorsByBook(bookId));
		    
		    if (authorId > 0) {
		    authors.add(AuthorLocalServiceUtil.getAuthor(authorId));
		    }

		    try {
		        BookLocalServiceUtil.updateBook(bookId, title, ISBN, releaseDate, authors, serviceContext.getUserId(), serviceContext);
		        SessionMessages.add(request, "bookUpdated");
		    } catch (Exception e) {
		        SessionErrors.add(request, e.getClass().getName());
		        response.setRenderParameter("mvcPath",
		            "/html/booklist/editBook.jsp");
		        }
	}
	
	public void addAuthorForBook(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Book.class.getName(), request);
		
			long bookId = ParamUtil.getLong(request, "bookId");
		    long authorId = ParamUtil.getLong(request, "authorId");
		    
		    Book book = BookLocalServiceUtil.getBook(bookId);
		    
		    ArrayList<Author> authors = new ArrayList<Author>(BookLocalServiceUtil.getAuthorsByBook(bookId));
		    
		    if (authorId > 0) {
		    authors.add(AuthorLocalServiceUtil.getAuthor(authorId));
		    }

		    try {
		        BookLocalServiceUtil.updateBook(bookId, book.getTitle(), book.getIsbn(),
		        		book.getReleaseDate(), authors, serviceContext.getUserId(), serviceContext);
		        SessionMessages.add(request, "bookUpdated");
		    } catch (Exception e) {
		        SessionErrors.add(request, e.getClass().getName());
		        response.setRenderParameter("mvcPath",
		            "/html/booklist/editBook.jsp");
		        }
	}
	
	public void removeAuthorForBook(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Book.class.getName(), request);
		
			long bookId = ParamUtil.getLong(request, "bookId");
		    long authorId = ParamUtil.getLong(request, "authorId");
		    
		    Book book = BookLocalServiceUtil.getBook(bookId);
		    
		    ArrayList<Author> authors = new ArrayList<Author>(BookLocalServiceUtil.getAuthorsByBook(bookId));
		    
		    if (authorId > 0) {
		    	authors.remove(AuthorLocalServiceUtil.getAuthor(authorId));
		    }

		    try {
		        BookLocalServiceUtil.updateBook(bookId, book.getTitle(), book.getIsbn(),
		        		book.getReleaseDate(), authors, serviceContext.getUserId(), serviceContext);
		        SessionMessages.add(request, "bookUpdated");
		    } catch (Exception e) {
		        SessionErrors.add(request, e.getClass().getName());
		        response.setRenderParameter("mvcPath",
		            "/html/booklist/editBook.jsp");
		        }
	}
	
	public void deleteBook(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {
		
			long bookId = ParamUtil.getLong(request, "bookId");
			try {
		        BookLocalServiceUtil.deleteBook(bookId);
		        SessionMessages.add(request, "bookDeleted");
		    } catch (Exception e) {
		        SessionErrors.add(request, e.getClass().getName());
		        response.setRenderParameter("mvcPath",
		            "/html/booklist/editBook.jsp");
		        }
	}
	
	public void addAuthor(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {

	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	        Author.class.getName(), request);

	    String firstName = ParamUtil.getString(request, "firstName");
	    String lastName = ParamUtil.getString(request, "lastName");
	    Date birthDate = ParamUtil.getDate(request, "birthDate", DateFormat.getDateInstance());
	    String email = ParamUtil.getString(request, "email");
	    
	    try {
	        AuthorLocalServiceUtil.addAuthor(firstName, lastName, birthDate, email, null, serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "authorAdded");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editAuthor.jsp");
	    }
	}
	
	public void updateAuthor(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {

	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	        Author.class.getName(), request);
	    
	    long authorId = ParamUtil.getLong(request, "authorId");
	    String firstName = ParamUtil.getString(request, "firstName");
	    String lastName = ParamUtil.getString(request, "lastName");
	    Date birthDate = ParamUtil.getDate(request, "birthDate", DateFormat.getDateInstance());
	    String email = ParamUtil.getString(request, "email");
	    long bookId = ParamUtil.getLong(request, "bookId");
	    
	    ArrayList<Book> books = new ArrayList<Book>(AuthorLocalServiceUtil.getBooksByAuthor(authorId));
	    if (bookId > 0) {
	    books.add(BookLocalServiceUtil.getBook(bookId));
	    }
	    
	    try {
	        AuthorLocalServiceUtil.updateAuthor(authorId, firstName, lastName, birthDate, email, books, serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "authorUpdated");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editAuthor.jsp");
	    }
	}
	
	public void addBookForAuthor (ActionRequest request, ActionResponse response) 
			throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Author.class.getName(), request);
		
			long authorId = ParamUtil.getLong(request, "authorId");
		    long bookId = ParamUtil.getLong(request, "bookId");
		    
		    Author author = AuthorLocalServiceUtil.getAuthor(authorId);
		    
		    ArrayList<Book> books = new ArrayList<Book>(AuthorLocalServiceUtil.getBooksByAuthor(authorId));
		    
		    if (bookId > 0) {
		    	books.add(BookLocalServiceUtil.getBook(bookId));
		    }

		    try {
		        AuthorLocalServiceUtil.updateAuthor(authorId, author.getFirstName(), author.getLastName(),
		        		author.getBirthDate(), author.getEmail(), books, serviceContext.getUserId(), serviceContext);
		        SessionMessages.add(request, "AuthorUpdated");
		    } catch (Exception e) {
		        SessionErrors.add(request, e.getClass().getName());
		        response.setRenderParameter("mvcPath",
		            "/html/booklist/editAuthor.jsp");
		        }
	}
	
	public void removeBookForAuthor (ActionRequest request, ActionResponse response) 
			throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Author.class.getName(), request);
		
			long authorId = ParamUtil.getLong(request, "authorId");
		    long bookId = ParamUtil.getLong(request, "bookId");
		    
		    Author author = AuthorLocalServiceUtil.getAuthor(authorId);
		    
		    ArrayList<Book> books = new ArrayList<Book>(AuthorLocalServiceUtil.getBooksByAuthor(authorId));
		    
		    if (bookId > 0) {
		    	books.remove(BookLocalServiceUtil.getBook(bookId));
		    }

		    try {
		        AuthorLocalServiceUtil.updateAuthor(authorId, author.getFirstName(), author.getLastName(),
		        		author.getBirthDate(), author.getEmail(), books, serviceContext.getUserId(), serviceContext);
		        SessionMessages.add(request, "AuthorUpdated");
		    } catch (Exception e) {
		        SessionErrors.add(request, e.getClass().getName());
		        response.setRenderParameter("mvcPath",
		            "/html/booklist/editAuthor.jsp");
		        }
	}
	
	public void deleteAuthor(ActionRequest request, ActionResponse response)
			throws PortalException, SystemException {
		
		long authorId = ParamUtil.getLong(request, "authorId");
		
		try {
	        AuthorLocalServiceUtil.deleteAuthor(authorId);
	        SessionMessages.add(request, "authorDeleted");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editAuthor.jsp");
	    }
	}
	
	public void getPreferences(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {
		String[] bookPrefs = ParamUtil.getParameterValues(request, "bookPrefs");
		String[] authorPrefs = ParamUtil.getParameterValues(request, "authorPrefs");
		PortletPreferences prefs = request.getPreferences();
		try {
			prefs.setValues("bookPrefs", bookPrefs);
			prefs.setValues("authorPrefs", authorPrefs);
			prefs.store();
			response.setPortletMode(PortletMode.VIEW);
		} catch (ReadOnlyException e) {
			e.printStackTrace();
		} catch (ValidatorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PortletModeException e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void render(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortletException, IOException {

	    try {
	        ServiceContext serviceContext = ServiceContextFactory.getInstance(renderRequest);
	        PortletPreferences prefs = renderRequest.getPreferences();
	        long groupId = serviceContext.getScopeGroupId();
	        
	        ArrayList<String> names = new ArrayList<String>();
	        names.add("Books");
	        names.add("Authors");
	        
	        List<Book> books = BookLocalServiceUtil.getBooks(groupId);
	        ArrayList<BookItem> bookItems = new ArrayList<BookItem>();
	        for (Book book : books) {
	        	bookItems.add(new BookItem(book));
	        }
	        
	        List<Author> authors = AuthorLocalServiceUtil.getAuthors(groupId);
	        ArrayList<AuthorItem> authorItems = new ArrayList<AuthorItem>();
	        for (Author author : authors) {
	        	authorItems.add(new AuthorItem(author));
	        }
	        
	        long currBookId = ParamUtil.getLong(renderRequest, "bookId");
	        long currAuthorId = ParamUtil.getLong(renderRequest, "authorId");
	        BookItem currBook = null;
	        AuthorItem currAuthor = null;
	        ArrayList<Author> otherAuthors = new ArrayList<Author>(AuthorLocalServiceUtil.getAuthors(groupId));
	        ArrayList<Book> otherBooks = new ArrayList<Book>(BookLocalServiceUtil.getBooks(groupId));
	        
	        if (currBookId > 0) {
	        	currBook = new BookItem(BookLocalServiceUtil.getBook(currBookId));
		        otherAuthors.removeAll(currBook.getRelatedAuthors());
	        }
	        
	        if (currAuthorId > 0)
	        {
	        	currAuthor = new AuthorItem(AuthorLocalServiceUtil.getAuthor(currAuthorId));
	        	otherBooks.removeAll(currAuthor.getRelatedBooks());
	        }
	        
	        List<String> bookPrefs = Arrays.asList(prefs.getValues("bookPrefs", new String[]{}));
	        List<String> authorPrefs = Arrays.asList(prefs.getValues("authorPrefs", new String[]{}));
			
	        renderRequest.setAttribute("backURL", ParamUtil.getString(renderRequest, "backURL"));
	        renderRequest.setAttribute("bookPrefs", bookPrefs);
	        renderRequest.setAttribute("authorPrefs", authorPrefs);
	        renderRequest.setAttribute("names", names);
	        renderRequest.setAttribute("otherBooks", otherBooks);
	        renderRequest.setAttribute("otherAuthors", otherAuthors);
	        renderRequest.setAttribute("books", bookItems);
	        renderRequest.setAttribute("authors", authorItems);
	        renderRequest.setAttribute("book", currBook);
	        renderRequest.setAttribute("author", currAuthor);
	        
	    } catch (Exception e) {

	        throw new PortletException(e);
	    }

	    super.render(renderRequest, renderResponse);

	}
}
