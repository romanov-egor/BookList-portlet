<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="${backURL}"></portlet:param>
	<portlet:param name="bookId" value="${book.getId()}" />
	<portlet:param name="mode" value="editBook" />
</portlet:renderURL>

<table border="1">
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Birth Date</th>
		<th>Email</th>
		<th></th>
	</tr>
	<c:forEach items="${book.getRelatedAuthors()}" var="currAuthor">
		<portlet:actionURL name="unbindAuthorToBook" var="removeAuthorForBookURL">
			<portlet:param name="bookId" value="${book.getId()}"/>
			<portlet:param name="authorId" value="${currAuthor.getAuthorId()}"/>
		</portlet:actionURL>
		<tr>
			<td>${currAuthor.getFirstName()}</td>
			<td>${currAuthor.getLastName()}</td>
			<td>${currAuthor.getBirthDate()}</td>
			<td>${currAuthor.getEmail()}</td>
			<td>
				<a href="${removeAuthorForBookURL}">
					<button type="button">Remove</button>
				</a>
			</td>
		</tr>
	</c:forEach>
	<c:forEach items="${otherAuthors}" var="currAuthor">
		<portlet:actionURL name="bindAuthorToBook" var="addAuthorForBookURL">
			<portlet:param name="bookId" value="${book.getId()}"/>
			<portlet:param name="authorId" value="${currAuthor.getAuthorId()}"/>
		</portlet:actionURL>
		<tr>
			<td>${currAuthor.getFirstName()}</td>
			<td>${currAuthor.getLastName()}</td>
			<td>${currAuthor.getBirthDate()}</td>
			<td>${currAuthor.getEmail()}</td>
			<td>
				<a href="${addAuthorForBookURL}">
					<button type="button">Add</button>
				</a>
			</td>
		</tr>
	</c:forEach>
</table>
<a href="${backURL}">
	<button type="button">Cancel</button>
</a>