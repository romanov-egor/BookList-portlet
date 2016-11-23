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
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.util.bridges.mvc.MVCPortlet;
import com.softwerke.docs.booklist.businesslogic.AuthorItem;
import com.softwerke.docs.booklist.businesslogic.AuthorRenderPreferences;
import com.softwerke.docs.booklist.businesslogic.BookItem;
import com.softwerke.docs.booklist.businesslogic.BookRenderPreferences;
import com.softwerke.docs.booklist.businesslogic.exception.BookListException;
import com.softwerke.docs.booklist.model.Author;
import com.softwerke.docs.booklist.model.Book;
import com.softwerke.docs.booklist.service.AuthorLocalServiceUtil;
import com.softwerke.docs.booklist.service.BookLocalServiceUtil;

/**
 * Portlet implementation class BookList
 * @author Romanov Egor
 */
public class BookList extends MVCPortlet {
	
	private static Log log = LogFactoryUtil.getLog(BookList.class);
	
	/**
	 * Gather information from request and adds new {@link com.softwerke.docs.booklist.model.Book Book} instance 
	 * in database 
	 * @param request - Action Request
	 * @param response - Action Response
	 * @see javax.portlet.ActionRequest
	 * @see javax.portlet.ActionResponse
	 */
	public void addBook(ActionRequest request, ActionResponse response) {
	    try {
	    	ServiceContext serviceContext = ServiceContextFactory.getInstance(
	    			Book.class.getName(), request);
	    	String title = ParamUtil.getString(request, "title");
	    	String ISBN = ParamUtil.getString(request, "ISBN");
	    	Date releaseDate = ParamUtil.getDate(request, "releaseDate", 
	    			DateFormat.getDateInstance(DateFormat.SHORT));
	    	
	    	validate(title, ISBN, releaseDate);
	    	
	    	Book book = BookLocalServiceUtil.addBook(title, ISBN, releaseDate, null, 
	        		serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "book-added");
			response.setRenderParameter("bookId", String.valueOf(book.getBookId()));
	    } catch (BookListException e) {
	        SessionErrors.add(request, e.getLocalizationKey());
	        log.info(e);
	        log.info(e.getMessage());
		} catch (PortalException e) {
			SessionErrors.add(request, "portal-exception");
	        log.info(e);
	        log.info(e.getMessage());
		} catch (SystemException e) {
	        SessionErrors.add(request, "system-exception");
	        log.error(e);
	        log.error(e.getMessage());
		} finally {
			response.setRenderParameter("mvcPath", "/html/booklist/editBook.jsp");
			response.setRenderParameter("mode", "editBook");
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
	public void updateBook(ActionRequest request, ActionResponse response) {
		long bookId = ParamUtil.getLong(request, "bookId");
    	String title = ParamUtil.getString(request, "title");
	    String ISBN = ParamUtil.getString(request, "ISBN");
	    Date releaseDate = ParamUtil.getDate(request, "releaseDate", 
	    		DateFormat.getDateInstance(DateFormat.SHORT));
	    long authorId = ParamUtil.getLong(request, "authorId");
		
		try {
	    	ServiceContext serviceContext = ServiceContextFactory.getInstance(
	    			Book.class.getName(), request);	
		    ArrayList<Author> authors = new ArrayList<Author>(
		    		AuthorLocalServiceUtil.getAuthorsByBook(bookId));
		    
		    if (authorId > 0) {
		    	authors.add(AuthorLocalServiceUtil.getAuthor(authorId));
		    }
		    
		    validate(title, ISBN, releaseDate);
		    
		    BookLocalServiceUtil.updateBook(bookId, title, ISBN, releaseDate, 
		        	authors, serviceContext.getUserId(), serviceContext);
		    SessionMessages.add(request, "book-updated");
	    } catch (BookListException e) {
	        SessionErrors.add(request, e.getLocalizationKey());
	        log.info(e);
	        log.info(e.getMessage());
		} catch (PortalException e) {
			SessionErrors.add(request, "portal-exception");
	        log.info(e);
	        log.info(e.getMessage());
		} catch (SystemException e) {
	        SessionErrors.add(request, "system-exception");
	        log.error(e);
	        log.error(e.getMessage());
		} finally {
			response.setRenderParameter("mvcPath", "/html/booklist/editBook.jsp");
			response.setRenderParameter("bookId", String.valueOf(bookId));
			response.setRenderParameter("mode", "editBook");
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
	public void deleteBook(ActionRequest request, ActionResponse response) {
		long bookId = ParamUtil.getLong(request, "bookId");
		
		try {
	        BookLocalServiceUtil.deleteBook(bookId);
	        SessionMessages.add(request, "book-deleted");
	    } catch (PortalException e) {
			SessionErrors.add(request, "portal-exception");
	        log.info(e);
	        log.info(e.getMessage());
		} catch (SystemException e) {
	        SessionErrors.add(request, "system-exception");
	        log.error(e);
	        log.error(e.getMessage());
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
	public void addAuthor(ActionRequest request, ActionResponse response) {
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Author.class.getName(), request);
			String firstName = ParamUtil.getString(request, "firstName");
			String lastName = ParamUtil.getString(request, "lastName");
			Date birthDate = ParamUtil.getDate(request, "birthDate", 
					DateFormat.getDateInstance(DateFormat.SHORT));
			String email = ParamUtil.getString(request, "email");
			
			validate(firstName, birthDate, email);
			
	        Author author = AuthorLocalServiceUtil.addAuthor(firstName, lastName, birthDate, 
	        		email, null, serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "author-added");
	        response.setRenderParameter("authorId", String.valueOf(author.getAuthorId()));
	    } catch (BookListException e) {
	        SessionErrors.add(request, e.getLocalizationKey());
	        log.info(e);
	        log.info(e.getMessage());
		} catch (PortalException e) {
			SessionErrors.add(request, "portal-exception");
	        log.info(e);
	        log.info(e.getMessage());
		} catch (SystemException e) {
	        SessionErrors.add(request, "system-exception");
	        log.error(e);
	        log.error(e.getMessage());
		} finally {
			response.setRenderParameter("mvcPath", "/html/booklist/editAuthor.jsp");
			response.setRenderParameter("mode", "editAuthor");
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
	public void updateAuthor(ActionRequest request, ActionResponse response) {
		long authorId = ParamUtil.getLong(request, "authorId");
		String firstName = ParamUtil.getString(request, "firstName");
		String lastName = ParamUtil.getString(request, "lastName");
		Date birthDate = ParamUtil.getDate(request, "birthDate", 
				DateFormat.getDateInstance(DateFormat.SHORT));
		String email = ParamUtil.getString(request, "email");
		long bookId = ParamUtil.getLong(request, "bookId");
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Author.class.getName(), request);
		    ArrayList<Book> books = new ArrayList<Book>(BookLocalServiceUtil.getBooksByAuthor(authorId));
		    
		    if (bookId > 0) {
		    	books.add(BookLocalServiceUtil.getBook(bookId));
		    }
		    
		    validate(firstName, birthDate, email);
	    
	        AuthorLocalServiceUtil.updateAuthor(authorId, firstName, lastName, 
	        		birthDate, email, books, serviceContext.getUserId(), 
	        		serviceContext);
	        SessionMessages.add(request, "author-updated");
	    } catch (BookListException e) {
	        SessionErrors.add(request, e.getLocalizationKey());
	        log.info(e);
	        log.info(e.getMessage());
		} catch (PortalException e) {
			SessionErrors.add(request, "portal-exception");
	        log.info(e);
	        log.info(e.getMessage());
		} catch (SystemException e) {
	        SessionErrors.add(request, "system-exception");
	        log.error(e);
	        log.error(e.getMessage());
		} finally {
			response.setRenderParameter("mvcPath", "/html/booklist/editAuthor.jsp");
			response.setRenderParameter("authorId", String.valueOf(authorId));
			response.setRenderParameter("mode", "editAuthor");
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
	public void bindBookAndAuthor (ActionRequest request, ActionResponse response) {		
		long authorId = ParamUtil.getLong(request, "authorId");
		long bookId = ParamUtil.getLong(request, "bookId");
		try {
			ServiceContext serviceContext = ServiceContextFactory.getInstance(
					Author.class.getName(), request);
			Author author = AuthorLocalServiceUtil.getAuthor(authorId);
			ArrayList<Book> books = new ArrayList<Book>(
					BookLocalServiceUtil.getBooksByAuthor(authorId));
	    
			if (bookId > 0) {
				books.add(BookLocalServiceUtil.getBook(bookId));
			}
	    
	        AuthorLocalServiceUtil.updateAuthor(authorId, 
	        		author.getFirstName(), author.getLastName(), 
	        		author.getBirthDate(), author.getEmail(), books, 
	        		serviceContext.getUserId(), serviceContext);
	        SessionMessages.add(request, "author-updated");
	    } catch (PortalException e) {
			SessionErrors.add(request, "portal-exception");
	        log.info(e);
	        log.info(e.getMessage());
		} catch (SystemException e) {
	        SessionErrors.add(request, "system-exception");
	        log.error(e);
	        log.error(e.getMessage());
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
	public void unbindBookAndAuthor (ActionRequest request, ActionResponse response) {
		long authorId = ParamUtil.getLong(request, "authorId");
		long bookId = ParamUtil.getLong(request, "bookId");
	    try {
	    	ServiceContext serviceContext = ServiceContextFactory.getInstance(
	    			Author.class.getName(), request);
	    	Author author = AuthorLocalServiceUtil.getAuthor(authorId);
	    	ArrayList<Book> books = new ArrayList<Book>(BookLocalServiceUtil.getBooksByAuthor(authorId));
	    
	    	if (bookId > 0) {
	    		books.remove(BookLocalServiceUtil.getBook(bookId));
	    	}
	    
	    	AuthorLocalServiceUtil.updateAuthor(authorId, 
	    			author.getFirstName(), author.getLastName(), 
	    			author.getBirthDate(), author.getEmail(), books, 
	    			serviceContext.getUserId(), serviceContext);
	    	SessionMessages.add(request, "AuthorUpdated");
	    } catch (PortalException e) {
			SessionErrors.add(request, "portal-exception");
	        log.info(e);
	        log.info(e.getMessage());
		} catch (SystemException e) {
	        SessionErrors.add(request, "system-exception");
	        log.error(e);
	        log.error(e.getMessage());
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
	public void deleteAuthor(ActionRequest request, ActionResponse response) {
		long authorId = ParamUtil.getLong(request, "authorId");
		try {
	        AuthorLocalServiceUtil.deleteAuthor(authorId);
	        SessionMessages.add(request, "authorDeleted");
	    } catch (PortalException e) {
			SessionErrors.add(request, "portal-exception");
	        log.info(e);
	        log.info(e.getMessage());
		} catch (SystemException e) {
	        SessionErrors.add(request, "system-exception");
	        log.error(e);
	        log.error(e.getMessage());
		}
	}
	
	/**
	 * Validates {@link com.softwerke.docs.booklist.model.Author Author's}
	 * first name, birth date and email
	 * @param firstName
	 * @param birthDate
	 * @param email
	 * @throws PortalException
	 */
	protected void validate (String firstName, Date birthDate, String email) 
	        throws PortalException {
	    if (Validator.isNull(firstName)) {
	        throw new BookListException("first-name-is-null");
	    }
	    if (Validator.isNull(email)) {
	        throw new BookListException("email-is-null");
	    }
	    if (!Validator.isEmailAddress(email)) {
	        throw new BookListException("email-wrong-structure");
	    }
	    if (Validator.isNull(birthDate)) {
	        throw new BookListException("birth-date-is-null");
	    }
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
	        throws BookListException {
	    if (Validator.isNull(title)) {
	        throw new BookListException("title-is-null");
	    }

	    if (Validator.isNull(ISBN)) {
	        throw new BookListException("ISBN-is-null");
	    }

	    if (Validator.isNull(releaseDate)) {
	        throw new BookListException("release-date-is-null");
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
	public void getPreferences(ActionRequest request, ActionResponse response) {
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
	        RenderResponse renderResponse) {  	
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
	    	
	    	super.render(renderRequest, renderResponse);
	    } catch (PortalException e) {
	    	
	    } catch (SystemException e) {
			e.printStackTrace();
		} catch (PortletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void renderView(RenderRequest renderRequest,
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
	
	private void renderEditBook(RenderRequest renderRequest,
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
	
	private void renderEditAuthor(RenderRequest renderRequest,
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
	
	private void renderBookList(RenderRequest renderRequest,
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
	
	private void renderAuthorList(RenderRequest renderRequest,
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
