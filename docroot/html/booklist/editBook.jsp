<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.softwerke.docs.booklist.service.BookLocalServiceUtil" %>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/booklist/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="listURL">
	<portlet:param name="mvcPath" value="/html/booklist/authorList.jsp"></portlet:param>
	<portlet:param name="backURL" value="/html/booklist/editBook.jsp"/>
	<portlet:param name="itemId" value="${item.getId()}"/>
</portlet:renderURL>

<portlet:actionURL name="deleteBook" var="deleteURL">
	<portlet:param name="itemId" value="${item.getId()}"/>
</portlet:actionURL>

<html>
<c:if test="${item == null}">
	<h1>Add Book</h1>
	<portlet:actionURL name="addBook" var="bookDetails"></portlet:actionURL>
	<c:set var="title" scope="session" value=""/>
	<c:set var="ISBN" scope="session" value=""/>
	<c:set var="releaseDate" scope="session" value=""/>
</c:if>
<c:if test="${item != null}">
	<h1>Edit Book</h1>
	<portlet:actionURL name="updateBook" var="bookDetails">
		<portlet:param name="itemId" value="${item.getId()}"/>
	</portlet:actionURL>
	<c:set var="title" scope="session" value="${item.getParameter(\"title\")}"/>
	<c:set var="ISBN" scope="session" value="${item.getParameter(\"ISBN\")}"/>
	<c:set var="releaseDate" scope="session" value="${item.getParameter(\"releaseDate\")}"/>
</c:if>

	<form name="<portlet:namespace />fm1" action="${bookDetails}" method="POST">
		<br>
		Title
		<br>
		<input name="<portlet:namespace />title" type="text" value="${title}"></input>
		<br>
		ISBN 
		<br>
		<input name="<portlet:namespace />ISBN" type="text" value="${ISBN}"></input>
		<br>
		Release Date
		<br>
		<input name="<portlet:namespace />releaseDate" type="date" value="${releaseDate}"></input>
        <br>
        <c:if test="${item != null}">
        Authors
        <a href="${listURL}">
        	<button type="button">Add Author for this Book</button>
        </a>
        <br>
        <table border="1">
        <c:forEach items="${tableRows}" var="row">
			<c:if test="${row.contains(item)}">
				<c:forEach items="${row.getNamedElement(\"Authors\")}" var="author">
					<portlet:renderURL var="editURL">
						<portlet:param name="mvcPath" value="/html/booklist/editAuthor.jsp"></portlet:param>
						<portlet:param name="backURL" value="/html/booklist/editBook.jsp"/>
						<portlet:param name="itemId" value="${author.getId()}" />
					</portlet:renderURL>
					<tr>
						<td>
							<a href="${editURL}">
								<c:forEach items="${author.getParameters()}" var="parameter">
									<c:if test="${parameter.isVisible()}">
								 		${parameter.getValue()}
								 	</c:if>
								</c:forEach>
							</a>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</c:forEach>
		</table>
		<br>
		</c:if>
		<button type="submit">Submit changes</button>
		<c:if test="${item != null}">
			<a href="${deleteURL}">
				<button type="button">Delete this Book</button>
			</a>
		</c:if>
		<a href="${viewURL}">
			<button type="button">Cancel</button>
		</a>
	</form>
</html>

