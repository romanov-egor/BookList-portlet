<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="${backURL}"></portlet:param>
	<portlet:param name="authorId" value="${author.getId()}" />
</portlet:renderURL>

<table border="1">
	<tr>
		<th>Title</th>
		<th>ISBN</th>
		<th>Release Date</th>
		<th></th>
	</tr>
	<c:forEach items="${author.getRelatedBooks()}" var="currBook">
		<portlet:actionURL name="removeBookForAuthor" var="removeBookForAuthorURL">
			<portlet:param name="authorId" value="${author.getId()}"/>
			<portlet:param name="bookId" value="${currBook.getBookId()}"/>
		</portlet:actionURL>
		<tr>
			<td>${currBook.getTitle()}</td>
			<td>${currBook.getIsbn()}</td>
			<td>${currBook.getReleaseDate()}</td>
			<td>
				<a href="${removeBookForAuthorURL}">
					<button type="button">Remove</button>
				</a>
			</td>
		</tr>
	</c:forEach>			

	<c:forEach items="${otherBooks}" var="currBook">
		<portlet:actionURL name="addBookForAuthor" var="addBookForAuthorURL">
			<portlet:param name="authorId" value="${author.getId()}"/>
			<portlet:param name="bookId" value="${currBook.getBookId()}"/>
		</portlet:actionURL>
		<tr>
			<td>${currBook.getTitle()}</td>
			<td>${currBook.getIsbn()}</td>
			<td>${currBook.getReleaseDate()}</td>
			<td>
				<a href="${addBookForAuthorURL}">
					<button type="button">Add</button>
				</a>
			</td>
		</tr>
	</c:forEach>
</table>
<br>
<a href="${backURL}">
	<button type="button">Cancel</button>
</a>
