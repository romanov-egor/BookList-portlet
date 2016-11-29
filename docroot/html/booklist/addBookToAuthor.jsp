<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="${backURL}" />
	<portlet:param name="authorId" value="${author.getId()}" />
	<portlet:param name="mode" value="editAuthor" />
</portlet:renderURL>

<table>
	<tr>
		<th>Title</th>
		<th>ISBN</th>
		<th>Release Date</th>
		<th></th>
	</tr>
	<c:forEach items="${author.getRelatedBooks()}" var="currBook">
		<portlet:actionURL name="unbindBookAndAuthor" var="removeBookForAuthorURL">
			<portlet:param name="mvcPath" value="${backURL}" />
			<portlet:param name="authorId" value="${author.getId()}"/>
			<portlet:param name="bookId" value="${currBook.getBookId()}"/>
		</portlet:actionURL>
		<tr>
			<td class="main-table-td">${currBook.getTitle()}</td>
			<td class="main-table-td">${currBook.getIsbn()}</td>
			<td class="main-table-td">${currBook.getReleaseDate()}</td>
			<td>
				<a class="btn" href="${removeBookForAuthorURL}">Remove</a>
			</td>
		</tr>
	</c:forEach>			

	<c:forEach items="${otherBooks}" var="currBook">
		<portlet:actionURL name="bindBookAndAuthor" var="addBookForAuthorURL">
			<portlet:param name="mvcPath" value="${backURL}" />
			<portlet:param name="authorId" value="${author.getId()}"/>
			<portlet:param name="bookId" value="${currBook.getBookId()}"/>
		</portlet:actionURL>
		<tr>
			<td class="main-table-td">${currBook.getTitle()}</td>
			<td class="main-table-td">${currBook.getIsbn()}</td>
			<td class="main-table-td">${currBook.getReleaseDate()}</td>
			<td>
				<a class="btn" href="${addBookForAuthorURL}">Add</a>
			</td>
		</tr>
	</c:forEach>
</table>
<a class="btn" href="${backURL}">Cancel</a>
