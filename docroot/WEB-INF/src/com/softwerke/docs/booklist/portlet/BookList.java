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
import com.softwerke.docs.booklist.businesslogic.AuthorItem;
import com.softwerke.docs.booklist.businesslogic.AuthorRenderPreferences;
import com.softwerke.docs.booklist.businesslogic.BookItem;
import com.softwerke.docs.booklist.businesslogic.BookRenderPreferences;
import com.softwerke.docs.booklist.model.Author;
import com.softwerke.docs.booklist.model.Book;
import com.softwerke.docs.booklist.service.AuthorLocalServiceUtil;
import com.softwerke.docs.booklist.service.BookLocalServiceUtil;

/**
 * Portlet implementation class BookList
 * @author Romanov Egor
 */
public class BookList extends MVCPortlet {
	
	/**
	 * Gather information from request and adds new {@link com.softwerke.docs.booklist.model.Book Book} instance 
	 * in database 
	 * @param request - Action Request
	 * @param response - Action Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
	public void addBook(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {
	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	        Book.class.getName(), request);
	    String title = ParamUtil.getString(request, "title");
	    String ISBN = ParamUtil.getString(request, "ISBN");
	    Date releaseDate = ParamUtil.getDate(request, "releaseDate", 
	    		DateFormat.getDateInstance());
	    
	    try {
	        BookLocalServiceUtil.addBook(title, ISBN, releaseDate, null, 
	        		serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "bookAdded");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editBook.jsp");
	    }
	}
	
	/**
	 * Gathers information from request and updates existing {@link com.softwerke.docs.booklist.model.Book Book} instance 
	 * in database
	 * @param request - Action Request
	 * @param response - Action Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
	public void updateBook(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Book.class.getName(), request);
		long bookId = ParamUtil.getLong(request, "bookId");
	    String title = ParamUtil.getString(request, "title");
	    String ISBN = ParamUtil.getString(request, "ISBN");
	    Date releaseDate = ParamUtil.getDate(request, "releaseDate", 
	    		DateFormat.getDateInstance());
	    long authorId = ParamUtil.getLong(request, "authorId");
	    ArrayList<Author> authors = new ArrayList<Author>(
	    		AuthorLocalServiceUtil.getAuthorsByBook(bookId));
	    
	    if (authorId > 0) {
	    authors.add(AuthorLocalServiceUtil.getAuthor(authorId));
	    }
	    try {
	        BookLocalServiceUtil.updateBook(bookId, title, ISBN, releaseDate, 
	        		authors, serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "bookUpdated");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editBook.jsp");
	        }
	}

	/**
	 * Gathers information from request and removes {@link com.softwerke.docs.booklist.model.Book Book} instance 
	 * from database
	 * @param request - Action Request
	 * @param response - Action Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
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
	
	/**
	 * Gathers information from request and adds new {@link com.softwerke.docs.booklist.model.Author Author} instance 
	 * in database 
	 * @param request - Action Request
	 * @param response - Action Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
	public void addAuthor(ActionRequest request, ActionResponse response)
	        throws PortalException, SystemException {
	    ServiceContext serviceContext = ServiceContextFactory.getInstance(
	        Author.class.getName(), request);
	    String firstName = ParamUtil.getString(request, "firstName");
	    String lastName = ParamUtil.getString(request, "lastName");
	    Date birthDate = ParamUtil.getDate(request, "birthDate", 
	    		DateFormat.getDateInstance());
	    String email = ParamUtil.getString(request, "email");
	    
	    try {
	        AuthorLocalServiceUtil.addAuthor(firstName, lastName, birthDate, 
	        		email, null, serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "authorAdded");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editAuthor.jsp");
	    }
	}
	
	/**
	 * Gathers information from request and updates existing {@link com.softwerke.docs.booklist.model.Author Author} 
	 * instance in database
	 * @param request - Action Request
	 * @param response - Action Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
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
	    ArrayList<Book> books = new ArrayList<Book>(BookLocalServiceUtil.getBooksByAuthor(authorId));
	    
	    if (bookId > 0) {
	    	books.add(BookLocalServiceUtil.getBook(bookId));
	    }
	    try {
	        AuthorLocalServiceUtil.updateAuthor(authorId, firstName, lastName, 
	        		birthDate, email, books, serviceContext.getUserId(), 
	        		serviceContext);
	        SessionMessages.add(request, "authorUpdated");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editAuthor.jsp");
	    }
	}
	
	/**
	 * Links {@link com.softwerke.docs.booklist.model.Book Book} to 
	 * {@link com.softwerke.docs.booklist.model.Author Author}<br>
	 * Uses {@link com.softwerke.docs.booklist.service.AuthorLocalServiceUtil}
	 * @param request - Action Request
	 * @param response - Action Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
	public void bindBookAndAuthor (ActionRequest request, ActionResponse response) 
			throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Author.class.getName(), request);		
		long authorId = ParamUtil.getLong(request, "authorId");
	    long bookId = ParamUtil.getLong(request, "bookId");
	    Author author = AuthorLocalServiceUtil.getAuthor(authorId);
	    ArrayList<Book> books = new ArrayList<Book>(BookLocalServiceUtil.getBooksByAuthor(authorId));
	    
	    if (bookId > 0) {
	    	books.add(BookLocalServiceUtil.getBook(bookId));
	    }
	    try {
	        AuthorLocalServiceUtil.updateAuthor(authorId, 
	        		author.getFirstName(), author.getLastName(), 
	        		author.getBirthDate(), author.getEmail(), books, 
	        		serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "AuthorUpdated");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editAuthor.jsp");
	        }
	}
	
	/**
	 * Removes link between {@link com.softwerke.docs.booklist.model.Book Book} 
	 * and {@link com.softwerke.docs.booklist.model.Book Book}<br>
	 * Uses {@link com.softwerke.docs.booklist.service.BookLocalServiceUtil}
	 * @param request - Action Request
	 * @param response - Action Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
	public void unbindBookAndAuthor (ActionRequest request, ActionResponse response) 
			throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
		        Author.class.getName(), request);		
		long authorId = ParamUtil.getLong(request, "authorId");
	    long bookId = ParamUtil.getLong(request, "bookId");
	    Author author = AuthorLocalServiceUtil.getAuthor(authorId);
	    ArrayList<Book> books = new ArrayList<Book>(BookLocalServiceUtil.getBooksByAuthor(authorId));
	    
	    if (bookId > 0) {
	    	books.remove(BookLocalServiceUtil.getBook(bookId));
	    }
	    try {
	        AuthorLocalServiceUtil.updateAuthor(authorId, 
	        		author.getFirstName(), author.getLastName(), 
	        		author.getBirthDate(), author.getEmail(), books, 
	        		serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "AuthorUpdated");
	    } catch (Exception e) {
	        SessionErrors.add(request, e.getClass().getName());
	        response.setRenderParameter("mvcPath",
	            "/html/booklist/editAuthor.jsp");
	        }
	}
	
	/**
	 * Gathers information from request and removes {@link com.softwerke.docs.booklist.model.Author Author} instance 
	 * from database
	 * @param request - Action Request
	 * @param response - Action Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
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
	
	/**
	 * Retrieves {@link javax.portlet.PortletPreferences PortletPreferences} 
	 * from {@link javax.portlet.ActionRequest ActionRequest} and store them
	 * @param request - Action Request
	 * @param response - Action Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
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
	
	/**
	 * Render method for 
	 * {@link com.softwerke.docs.booklist.portlet.BookList BookList Portlet} 
	 * @param renderRequest - Render Request
	 * @param renderResponse - Render Response
	 * @throws PortalException
	 * @throws SystemException
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
	@Override
	public void render(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortletException, IOException {
	    try {	    	
	    	String mode = ParamUtil.getString(renderRequest, "mode");
	        
	    	if (mode.equals("view")) {
	    		renderView(renderRequest, renderResponse);
	    	} else if (mode.equals("editBook")) {
	    		renderEditBook(renderRequest, renderResponse);
	    	} else if (mode.equals("editAuthor")) {
	    		renderEditAuthor(renderRequest, renderResponse);
	    	} else if (mode.equals("bookList")) {
	    		renderBookList(renderRequest, renderResponse);
	    	} else if (mode.equals("authorList")) {
	    		renderAuthorList(renderRequest, renderResponse);
	    	} else {
	    		renderView(renderRequest, renderResponse);
	    	}
	    	
	    } catch (Exception e) {

	        throw new PortletException(e);
	    }
	    super.render(renderRequest, renderResponse);
	}
	
	public void renderView(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
        		renderRequest);
		long groupId = serviceContext.getScopeGroupId();
		PortletPreferences prefs = renderRequest.getPreferences();
        List<String> bookPrefs = Arrays.asList(
        		prefs.getValues("bookPrefs", new String[]{}));
        List<String> authorPrefs = Arrays.asList(
        		prefs.getValues("authorPrefs", new String[]{}));
        ArrayList<String> names = new ArrayList<String>();
        List<Book> books = BookLocalServiceUtil.getBooks(groupId);
        ArrayList<BookItem> bookItems = new ArrayList<BookItem>();
        List<Author> authors = AuthorLocalServiceUtil.getAuthors(groupId);
        ArrayList<AuthorItem> authorItems = new ArrayList<AuthorItem>();
        
        names.add("Books");
        names.add("Authors");
        
        for (Book book : books) {
        	bookItems.add(new BookItem(book));
        }
        for (Author author : authors) {
        	authorItems.add(new AuthorItem(author));
        }
        
        renderRequest.setAttribute("bookPrefs", 
        		new BookRenderPreferences(bookPrefs));
        renderRequest.setAttribute("authorPrefs", 
        		new AuthorRenderPreferences(authorPrefs));
        renderRequest.setAttribute("names", names);
        renderRequest.setAttribute("books", bookItems);
        renderRequest.setAttribute("authors", authorItems);
	}
	
	public void renderEditBook(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortalException, SystemException {
        PortletPreferences prefs = renderRequest.getPreferences();
        List<String> authorPrefs = Arrays.asList(
        		prefs.getValues("authorPrefs", new String[]{}));
        long currBookId = ParamUtil.getLong(renderRequest, "bookId");
        BookItem currBook = null;
        
        if (currBookId > 0) {
        	currBook = new BookItem(BookLocalServiceUtil.getBook(currBookId));
        }
        
        renderRequest.setAttribute("book", currBook);
        renderRequest.setAttribute("authorPrefs", 
        		new AuthorRenderPreferences(authorPrefs));
	}
	
	public void renderEditAuthor(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortalException, SystemException {
        PortletPreferences prefs = renderRequest.getPreferences();
        List<String> bookPrefs = Arrays.asList(
        		prefs.getValues("bookPrefs", new String[]{}));
        long currAuthorId = ParamUtil.getLong(renderRequest, "authorId");
        AuthorItem currAuthor = null;
        
        if (currAuthorId > 0) {
        	currAuthor = new AuthorItem(AuthorLocalServiceUtil.getAuthor(
        			currAuthorId));
        }
        
        renderRequest.setAttribute("author", currAuthor);
        renderRequest.setAttribute("bookPrefs", 
        		new BookRenderPreferences(bookPrefs));
	}
	
	public void renderBookList(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
        		renderRequest);
        long groupId = serviceContext.getScopeGroupId();
		long currAuthorId = ParamUtil.getLong(renderRequest, "authorId");
        AuthorItem currAuthor = null;
        ArrayList<Book> otherBooks = new ArrayList<Book>(
        		BookLocalServiceUtil.getBooks(groupId));
        
        if (currAuthorId > 0) {
        	currAuthor = new AuthorItem(AuthorLocalServiceUtil.getAuthor(
        			currAuthorId));
        	otherBooks.removeAll(currAuthor.getRelatedBooks());
        }
        
        renderRequest.setAttribute("backURL", ParamUtil.getString(
        		renderRequest, "backURL"));
        renderRequest.setAttribute("otherBooks", otherBooks);
        renderRequest.setAttribute("author", currAuthor);
	}
	
	public void renderAuthorList(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortalException, SystemException {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(
        		renderRequest);
        long groupId = serviceContext.getScopeGroupId();
        long currBookId = ParamUtil.getLong(renderRequest, "bookId");
        BookItem currBook = null;
        ArrayList<Author> otherAuthors = new ArrayList<Author>(
        		AuthorLocalServiceUtil.getAuthors(groupId));
        
        if (currBookId > 0) {
        	currBook = new BookItem(BookLocalServiceUtil.getBook(currBookId));
	        otherAuthors.removeAll(currBook.getRelatedAuthors());
        }
        
        renderRequest.setAttribute("backURL", ParamUtil.getString(
        		renderRequest, "backURL"));
        renderRequest.setAttribute("otherAuthors", otherAuthors);
        renderRequest.setAttribute("book", currBook);
	}
}
