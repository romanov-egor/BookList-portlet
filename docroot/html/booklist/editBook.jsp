<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-ui:error key="title-is-null" message="Title must not be empty!" />
<liferay-ui:error key="ISBN-is-null" message="ISBN must not be empty!" />
<liferay-ui:error key="release-date-is-null" message="Release date must not be empty!" />
<liferay-ui:error key="portal-exception" message="Input Error!" />
<liferay-ui:error key="system-exception" message="System Error!" />
<liferay-ui:success key="book-added" message="Book added" />
<liferay-ui:success key="book-updated" message="Book updated" />
<liferay-ui:success key="book-deleted" message="Book deleted" />

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/booklist/view.jsp"></portlet:param>
	<portlet:param name="mode" value="view" />
</portlet:renderURL>

<portlet:renderURL var="listURL">
	<portlet:param name="mvcPath" value="/html/booklist/addAuthorToBook.jsp"></portlet:param>
	<portlet:param name="backURL" value="/html/booklist/editBook.jsp"/>
	<portlet:param name="bookId" value="${book.getId()}"/>
	<portlet:param name="mode" value="addAuthorToBook" />
</portlet:renderURL>

<portlet:actionURL name="deleteBook" var="deleteURL">
	<portlet:param name="bookId" value="${book.getId()}"/>
</portlet:actionURL>


<c:if test="${book == null}">
	<h1>Add Book</h1>
	<portlet:actionURL name="addBook" var="bookDetails"></portlet:actionURL>
	<c:set var="title" scope="session" value=""/>
	<c:set var="ISBN" scope="session" value=""/>
	<c:set var="releaseDate" scope="session" value=""/>
</c:if>
<c:if test="${book != null}">
	<h1>Edit Book</h1>
	<portlet:actionURL name="updateBook" var="bookDetails">
		<portlet:param name="backURL" value="/html/booklist/view.jsp"/>
		<portlet:param name="bookId" value="${book.getId()}" />
	</portlet:actionURL>
	<c:set var="title" scope="session" value="${book.getTitle()}"/>
	<c:set var="ISBN" scope="session" value="${book.getISBN()}"/>
	<c:set var="releaseDate" scope="session" value="${book.getReleaseDate()}"/>
</c:if>

<form name="<portlet:namespace />fm1" action="${bookDetails}" method="POST">
	<label>
		Title
		<input name="<portlet:namespace />title" type="text" value="${title}"></input>
	</label>
	<label>
		ISBN 
		<input name="<portlet:namespace />ISBN" type="text" value="${ISBN}"></input>
	</label>
	<label>
		Release Date
		<input name="<portlet:namespace />releaseDate" type="date" value="${releaseDate}"></input>
    </label>
    <c:if test="${book != null}">
    	<a class="btn" href="${listURL}">Open full list of Authors</a>
    	<h2>Authors of this Book</h2>
    	<table>
			<c:forEach items="${book.getRelatedAuthors()}" var="currAuthor">
				<portlet:renderURL var="editAuthorURL">
					<portlet:param name="mvcPath" value="/html/booklist/editAuthor.jsp"></portlet:param>
					<portlet:param name="backURL" value="/html/booklist/editBook.jsp"/>
					<portlet:param name="authorId" value="${currAuthor.getAuthorId()}" />
					<portlet:param name="mode" value="editAuthor" />
				</portlet:renderURL>
				<tr>
					<td>
						<a class="table-link" href="${editAuthorURL}">
							<c:if test="${authorPrefs.isFirstNameVisible()}">
								${currAuthor.getFirstName()}
							</c:if>
							<c:if test="${authorPrefs.isLastNameVisible()}">
								${currAuthor.getLastName()}
							</c:if>
							<c:if test="${authorPrefs.isBirthDateVisible()}">
								${currAuthor.getBirthDate()}
							</c:if>
							<c:if test="${authorPrefs.isEmailVisible()}">
								${currAuthor.getEmail()}
							</c:if>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<input class="btn" type="submit" name="submit" value="Submit changes" />
	<c:if test="${book != null}">
		<a class="btn" href="${deleteURL}">Delete this Book</a>
	</c:if>
	<a class="btn" href="${viewURL}">Cancel</a>
</form>

