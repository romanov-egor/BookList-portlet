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

<div class="edit-container container-left">
	<a class="btn" href="${viewURL}">Cancel</a>
	<c:if test="${book == null}">
		<h2>Add Book</h2>
		<portlet:actionURL name="addBook" var="bookDetails"></portlet:actionURL>
		<c:set var="title" scope="session" value=""/>
		<c:set var="ISBN" scope="session" value=""/>
		<c:set var="releaseDate" scope="session" value=""/>
	</c:if>
	<c:if test="${book != null}">
		<h2>Edit Book</h2>
		<portlet:actionURL name="updateBook" var="bookDetails">
			<portlet:param name="backURL" value="/html/booklist/view.jsp"/>
			<portlet:param name="bookId" value="${book.getId()}" />
		</portlet:actionURL>
		<c:set var="title" scope="session" value="${book.getTitle()}"/>
		<c:set var="ISBN" scope="session" value="${book.getISBN()}"/>
		<c:set var="releaseDate" scope="session" value="${book.getReleaseDate()}"/>
	</c:if>

	<form name="<portlet:namespace />fm1" action="${bookDetails}" method="POST">
		<label for="title-field">Title<span class="hint">Type full title of the book</span></label>
		<input class="text-input" id="title-field" name="<portlet:namespace />title" type="text" value="${title}" />
		<label for="isbn-field">ISBN<span class="hint">Type ISBN of the book</span></label>
		<input class="text-input" id="isbn-field" name="<portlet:namespace />ISBN" type="text" value="${ISBN}" />
		<label for="release-date-field">Release Date<span class="hint">Type release date of the book</span></label>
		<input class="text-input" id="release-date-field" name="<portlet:namespace />releaseDate" type="text" value="${releaseDate}" />
		<div>
			<button class="btn btn-primary" type="submit" name="submit">Submit changes</button>
			<c:if test="${book != null}">
				<a class="btn" href="${deleteURL}">Delete this Book</a>
			</c:if>
		</div>
	</form>
</div>
<div class="edit-container container-right">
    <c:if test="${book != null}">
    	<h2>Authors of this Book</h2>
    	<ul>
			<c:forEach items="${book.getRelatedAuthors()}" var="currAuthor">
				<portlet:renderURL var="editAuthorURL">
					<portlet:param name="mvcPath" value="/html/booklist/editAuthor.jsp"></portlet:param>
					<portlet:param name="backURL" value="/html/booklist/editBook.jsp"/>
					<portlet:param name="authorId" value="${currAuthor.getAuthorId()}" />
					<portlet:param name="mode" value="editAuthor" />
				</portlet:renderURL>
				<li>
					<i class="icon-user"></i>
					<a class="main-table-link" href="${editAuthorURL}">
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
				</li>
			</c:forEach>
		</ul>
		<a class="btn" href="${listURL}">Add or Remove Authors</a>
	</c:if>
</div>