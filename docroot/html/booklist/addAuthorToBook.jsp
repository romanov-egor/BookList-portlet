<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="${backURL}"></portlet:param>
	<portlet:param name="bookId" value="${book.getId()}" />
	<portlet:param name="mode" value="editBook" />
</portlet:renderURL>

<a class="btn" href="${backURL}">Cancel</a>
<table>
	<tr>
		<th class="col-first-name">First Name</th>
		<th class="col-last-name">Last Name</th>
		<th class="col-birth-date">Birth Date</th>
		<th class="col-email">Email</th>
		<th></th>
	</tr>
	<c:forEach items="${book.getRelatedAuthors()}" var="currAuthor">
		<portlet:actionURL name="unbindBookAndAuthor" var="removeAuthorForBookURL">
			<portlet:param name="mvcPath" value="${backURL}" />
			<portlet:param name="bookId" value="${book.getId()}"/>
			<portlet:param name="authorId" value="${currAuthor.getAuthorId()}"/>
		</portlet:actionURL>
		<tr>
			<td class="col-first-name">${currAuthor.getFirstName()}</td>
			<td class="col-last-name">${currAuthor.getLastName()}</td>
			<td class="col-birth-date">${currAuthor.getBirthDate()}</td>
			<td class="col-email">${currAuthor.getEmail()}</td>
			<td>
				<a href="${removeAuthorForBookURL}"><i class="icon-minus"></i></a>
			</td>
		</tr>
	</c:forEach>
	<c:forEach items="${otherAuthors}" var="currAuthor">
		<portlet:actionURL name="bindBookAndAuthor" var="addAuthorForBookURL">
			<portlet:param name="mvcPath" value="${backURL}" />
			<portlet:param name="bookId" value="${book.getId()}"/>
			<portlet:param name="authorId" value="${currAuthor.getAuthorId()}"/>
		</portlet:actionURL>
		<tr>
			<td class="col-first-name">${currAuthor.getFirstName()}</td>
			<td class="col-last-name">${currAuthor.getLastName()}</td>
			<td class="col-birth-date">${currAuthor.getBirthDate()}</td>
			<td class="col-email">${currAuthor.getEmail()}</td>
			<td>
				<a href="${addAuthorForBookURL}"><i class="icon-plus"></i></a>
			</td>
		</tr>
	</c:forEach>
</table>
