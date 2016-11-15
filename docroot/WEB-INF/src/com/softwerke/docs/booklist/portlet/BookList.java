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
import com.softwerke.docs.booklist.model.Book;
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
		
			long bookId = ParamUtil.getLong(request, "itemId");
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
		
			long bookId = ParamUtil.getLong(request, "itemId");
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
		
			long bookId = ParamUtil.getLong(request, "itemId");
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
		
			long bookId = ParamUtil.getLong(request, "itemId");
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
	    
	    long authorId = ParamUtil.getLong(request, "itemId");
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
		
			long authorId = ParamUtil.getLong(request, "itemId");
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
		
			long authorId = ParamUtil.getLong(request, "itemId");
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
		
		long authorId = ParamUtil.getLong(request, "itemId");
		
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
	
	private BookItem getBookItem(Book book, PortletPreferences prefs) {
		BookItem item = new BookItem("Book", book);
		ArrayList<String> bookPrefs = new ArrayList<String>(Arrays.asList(prefs.getValues("bookPrefs", new String[]{})));
		item.addParameter("title", book.getTitle(), bookPrefs.contains("title"));
		item.addParameter("ISBN", book.getIsbn(), bookPrefs.contains("ISBN"));
		item.addParameter("releaseDate", book.getReleaseDate().toString(), bookPrefs.contains("releaseDate"));
		return item;
	}
	
	private AuthorItem getAuthorItem(Author author, PortletPreferences prefs) {
		AuthorItem item = new AuthorItem("Author", author);
		ArrayList<String> authorPrefs = new ArrayList<String>(Arrays.asList(prefs.getValues("authorPrefs", new String[]{})));
		item.addParameter("firstName", author.getFirstName(), authorPrefs.contains("firstName"));
		item.addParameter("lastName", author.getLastName(), authorPrefs.contains("lastName"));
		item.addParameter("birthDate", author.getBirthDate().toString(), authorPrefs.contains("birthDate"));
		item.addParameter("email", author.getEmail(), authorPrefs.contains("email"));
		return item;
	}
	
	public Item getItem(List<ItemRow> tableRows, long itemId) {
		for (ItemRow ir : tableRows) {
			for (List<Item> li : ir.getRow().values()) {
				for (Item i : li) {
					if (i.getId() == itemId) {
						return i;
					}
				}
			}
		}
		return null;
	}
	
	public List<ItemRow> getAllAuthors(List<ItemRow> tableRows, long itemId) {
		ArrayList<ItemRow> rows = new ArrayList<ItemRow>(); 
		for (ItemRow ir : tableRows) {
			for (List<Item> li : ir.getRow().values()) {
				for (Item i : li) {
					if (i.getId() == itemId) {
						rows.add(ir);
					}
				}
			}
		}
		return rows;
	}
	
	@Override
	public void render(RenderRequest renderRequest,
	        RenderResponse renderResponse) throws PortletException, IOException {

	    try {
	        ServiceContext serviceContext = ServiceContextFactory.getInstance(renderRequest);
	        PortletPreferences prefs = renderRequest.getPreferences();
	        long groupId = serviceContext.getScopeGroupId();
	        
	        ArrayList<ItemRow> tableRows = new ArrayList<ItemRow>();
	        ArrayList<String> names = new ArrayList<String>();
	        names.add("Books");
	        names.add("Authors");
	        
	        List<Book> books = BookLocalServiceUtil.getBooks(groupId);
	        
	        for (int i = 0; i < books.size(); i++) {
	        	ItemRow row = new ItemRow(names);
	        	ArrayList<Item> bookList = new ArrayList<Item>();
	        	bookList.add(getBookItem(books.get(i), prefs));
	        	row.addNamedElement("Books", bookList);
	        	
	        	List<Author> authors = BookLocalServiceUtil.getAuthorsByBook(books.get(i).getBookId());
	        	
	        	ArrayList<Item> authorList = new ArrayList<Item>();
	        	for(int j = 0; j < authors.size(); j++) {
	        		authorList.add(getAuthorItem(authors.get(j), prefs));
	        	}
	        	
	        	row.addNamedElement("Authors", authorList);
	        	tableRows.add(row);
	        }
	        
	        Item currItem = getItem(tableRows, ParamUtil.getLong(renderRequest, "itemId"));
	        ArrayList<Author> otherAuthors = new ArrayList<Author>(AuthorLocalServiceUtil.getAuthors(groupId));
	        List<Book> otherBooks = new ArrayList<Book>(BookLocalServiceUtil.getBooks(groupId));
	        
	        if (currItem != null)
	        {
		        if (currItem.getColumn() == "Book")
		        {
		        	List<Author> currBookAuthors = BookLocalServiceUtil.getAuthorsByBook(currItem.getId());
		        	otherAuthors.removeAll(currBookAuthors);
		        }
		        
		        if (currItem.getColumn() == "Author")
		        {
		        	List<Book> currAuthorBooks = AuthorLocalServiceUtil.getBooksByAuthor(currItem.getId());
		        	otherBooks.removeAll(currAuthorBooks);
		        }
	        }
	        
	        renderRequest.setAttribute("prefs", prefs);
	        renderRequest.setAttribute("otherBooks", otherBooks);
	        renderRequest.setAttribute("otherAuthors", otherAuthors);
	        renderRequest.setAttribute("names", names);
	        renderRequest.setAttribute("tableRows", tableRows);
	        renderRequest.setAttribute("item", currItem);
	        
	    } catch (Exception e) {

	        throw new PortletException(e);
	    }

	    super.render(renderRequest, renderResponse);

	}
}
