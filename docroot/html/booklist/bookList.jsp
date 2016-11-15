<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="${backURL}"></portlet:param>
	<portlet:param name="itemId" value="${item.getId()}" />
</portlet:renderURL>

<table border="1">
	<tr>
		<th>Title</th>
		<th>ISBN</th>
		<th>Release Date</th>
		<th></th>
	</tr>
	<c:forEach items="${tableRows}" var="row">
		<c:if test="${row.contains(item)}">
			<c:forEach items="${row.getNamedElement(\"Books\")}" var="book">
				<portlet:actionURL name="removeBookForAuthor" var="removeBookForAuthorURL">
						<portlet:param name="itemId" value="${item.getId()}"/>
						<portlet:param name="bookId" value="${book.getId()}"/>
				</portlet:actionURL>
				<tr>
					<td>${book.getParameter("title")}</td>
					<td>${book.getParameter("ISBN")}</td>
					<td>${book.getParameter("releaseDate")}</td>
					<td>
						<a href="${removeBookForAuthorURL}">
							<button type="button">Remove</button>
						</a>
					</td>
				</tr>
			</c:forEach>			
		</c:if>
	</c:forEach>
	<c:forEach items="${otherBooks}" var="book">
		<portlet:actionURL name="addBookForAuthor" var="addBookForAuthorURL">
			<portlet:param name="itemId" value="${item.getId()}"/>
			<portlet:param name="bookId" value="${book.getBookId()}"/>
		</portlet:actionURL>
		<tr>
			<td>${book.getTitle()}</td>
			<td>${book.getIsbn()}</td>
			<td>${book.getReleaseDate()}</td>
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
