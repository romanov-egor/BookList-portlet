<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.softwerke.docs.booklist.service.BookLocalServiceUtil" %>

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/booklist/view.jsp"></portlet:param>
</portlet:renderURL>

<portlet:renderURL var="listURL">
	<portlet:param name="mvcPath" value="/html/booklist/bookList.jsp"></portlet:param>
	<portlet:param name="backURL" value="/html/booklist/editAuthor.jsp"/>
	<portlet:param name="itemId" value="${item.getId()}"/>
</portlet:renderURL>

<portlet:actionURL name="deleteAuthor" var="deleteURL">
	<portlet:param name="itemId" value="${item.getId()}"/>
</portlet:actionURL>

<html>

<c:if test="${item == null}">
	<h1>Add Author</h1>
	<portlet:actionURL name="addAuthor" var="authorDetails"></portlet:actionURL>
	<c:set var="firstName" scope="session" value=""/>
	<c:set var="lastName" scope="session" value=""/>
	<c:set var="birthDate" scope="session" value=""/>
	<c:set var="email" scope="session" value=""/>	
</c:if>
<c:if test="${item != null}">
	<h1>Edit Author</h1>
	<portlet:actionURL name="updateAuthor" var="authorDetails">
		<portlet:param name="itemId" value="${item.getId()}"/>
	</portlet:actionURL>
	<c:set var="firstName" scope="session" value="${item.getParameter(\"firstName\")}"/>
	<c:set var="lastName" scope="session" value="${item.getParameter(\"lastName\")}"/>
	<c:set var="birthDate" scope="session" value="${item.getParameter(\"birthDate\")}"/>
	<c:set var="email" scope="session" value="${item.getParameter(\"email\")}"/>
</c:if>

	<form name="<portlet:namespace />fm1" action="${authorDetails}" method="POST">
		<br>
		First Name
		<br>
		<input name="<portlet:namespace />firstName" type="text" value="${firstName}"></input>
		<br>
		Last Name 
		<br>
		<input name="<portlet:namespace />lastName" type="text" value="${lastName}"></input>
		<br>
		Birth Date
		<br>
		<input name="<portlet:namespace />birthDate" type="date" value="${birthDate}"></input>
        <br>
        Email
        <br>
        <input name="<portlet:namespace />email" type="date" value="${email}"></input>
        <br>
        <c:if test="${item != null}">
        Books
        <a href="${listURL}">
        	<button type="button">Add Book for this Author</button>
        </a>
        <br>
        <table border="1">
        <c:forEach items="${tableRows}" var="row">
			<c:if test="${row.contains(item)}">
				<c:forEach items="${row.getNamedElement(\"Books\")}" var="book">
					<portlet:renderURL var="editURL">
						<portlet:param name="mvcPath" value="/html/booklist/editBook.jsp"></portlet:param>
						<portlet:param name="backURL" value="/html/booklist/editAuthor.jsp"/>
						<portlet:param name="itemId" value="${book.getId()}" />
					</portlet:renderURL>
					<tr>
						<td>
							<a href="${editURL}">
								<c:forEach items="${book.getParameters()}" var="parameter">
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
		<button type="submit">Submit</button>
		<c:if test="${item != null}">
			<a href="${deleteURL}">
				<button type="button">Delete this Author</button>
			</a>
		</c:if>
		<a href="${viewURL}">
			<button type="button">Cancel</button>
		</a>
	</form>
</html>

