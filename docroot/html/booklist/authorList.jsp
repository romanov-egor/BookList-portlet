<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:renderURL var="backURL">
	<portlet:param name="mvcPath" value="${backURL}"></portlet:param>
	<portlet:param name="itemId" value="${item.getId()}" />
</portlet:renderURL>

<table border="1">
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Birth Date</th>
		<th>Email</th>
		<th></th>
	</tr>
	<c:forEach items="${tableRows}" var="row">
		<c:if test="${row.contains(item)}">
			<c:forEach items="${row.getNamedElement(\"Authors\")}" var="author">
				<portlet:actionURL name="removeAuthorForBook" var="removeAuthorForBookURL">
						<portlet:param name="itemId" value="${item.getId()}"/>
						<portlet:param name="authorId" value="${author.getId()}"/>
				</portlet:actionURL>
				<tr>
					<td>${author.getParameter("firstName")}</td>
					<td>${author.getParameter("lastName")}</td>
					<td>${author.getParameter("birthDate")}</td>
					<td>${author.getParameter("email")}</td>
					<td>
						<a href="${removeAuthorForBookURL}">
							<button type="button">Remove</button>
						</a>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</c:forEach>
	<c:forEach items="${otherAuthors}" var="author">
		<portlet:actionURL name="addAuthorForBook" var="addAuthorForBookURL">
			<portlet:param name="itemId" value="${item.getId()}"/>
			<portlet:param name="authorId" value="${author.getAuthorId()}"/>
		</portlet:actionURL>
		<tr>
			<td>${author.getFirstName()}</td>
			<td>${author.getLastName()}</td>
			<td>${author.getBirthDate()}</td>
			<td>${author.getEmail()}</td>
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