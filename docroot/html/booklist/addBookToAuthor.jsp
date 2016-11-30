<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="${backURL}" />
	<portlet:param name="authorId" value="${author.getId()}" />
	<portlet:param name="mode" value="editAuthor" />
</portlet:renderURL>

<a class="btn" href="${backURL}">Cancel</a>
<table>
	<tr>
		<th class="col-title">Title</th>
		<th class="col-isbn">ISBN</th>
		<th class="col-release-date">Release Date</th>
		<th></th>
	</tr>
	<c:forEach items="${author.getRelatedBooks()}" var="currBook">
		<portlet:actionURL name="unbindBookAndAuthor" var="removeBookForAuthorURL">
			<portlet:param name="mvcPath" value="${backURL}" />
			<portlet:param name="authorId" value="${author.getId()}"/>
			<portlet:param name="bookId" value="${currBook.getBookId()}"/>
		</portlet:actionURL>
		<tr>
			<td class="col-title">${currBook.getTitle()}</td>
			<td class="col-isbn">${currBook.getIsbn()}</td>
			<td class="col-release-date">${currBook.getReleaseDate()}</td>
			<td>
				<a href="${removeBookForAuthorURL}"><i class="icon-minus"></i></a>
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
			<td class="col-title">${currBook.getTitle()}</td>
			<td class="col-isbn">${currBook.getIsbn()}</td>
			<td class="col-release-date">${currBook.getReleaseDate()}</td>
			<td>
				<a href="${addBookForAuthorURL}"><i class="icon-plus"></i></a>
			</td>
		</tr>
	</c:forEach>
</table>
