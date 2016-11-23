<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<liferay-ui:error key="first-name-is-null" message="First name must not be empty!" />
<liferay-ui:error key="birth-date-is-null" message="Birth date must not be empty!" />
<liferay-ui:error key="email-is-null" message="Email must not be empty!" />
<liferay-ui:error key="email-wrong-structure" message="Email is incorrect!" />
<liferay-ui:error key="portal-exception" message="Input Error!" />
<liferay-ui:error key="system-exception" message="System Error!" />
<liferay-ui:success key="author-added" message="Author added" />
<liferay-ui:success key="author-updated" message="Author updated" />
<liferay-ui:success key="author-deleted" message="Author deleted" />

<portlet:defineObjects />

<portlet:renderURL var="viewURL">
	<portlet:param name="mvcPath" value="/html/booklist/view.jsp"></portlet:param>
	<portlet:param name="mode" value="view" />
</portlet:renderURL>

<portlet:renderURL var="listURL">
	<portlet:param name="mvcPath" value="/html/booklist/bookList.jsp"></portlet:param>
	<portlet:param name="backURL" value="/html/booklist/editAuthor.jsp"/>
	<portlet:param name="authorId" value="${author.getId()}"/>
	<portlet:param name="mode" value="bookList" />
</portlet:renderURL>

<portlet:actionURL name="deleteAuthor" var="deleteURL">
	<portlet:param name="authorId" value="${author.getId()}"/>
</portlet:actionURL>

<html>
<c:if test="${author == null}">
	<h1>Add Author</h1>
	<portlet:actionURL name="addAuthor" var="authorDetails"></portlet:actionURL>
	<c:set var="firstName" scope="session" value=""/>
	<c:set var="lastName" scope="session" value=""/>
	<c:set var="birthDate" scope="session" value=""/>
	<c:set var="email" scope="session" value=""/>	
</c:if>
<c:if test="${author != null}">
	<h1>Edit Author</h1>
	<portlet:actionURL name="updateAuthor" var="authorDetails">
		<portlet:param name="authorId" value="${author.getId()}"/>
	</portlet:actionURL>
	<c:set var="firstName" scope="session" value="${author.getFirstName()}"/>
	<c:set var="lastName" scope="session" value="${author.getLastName()}"/>
	<c:set var="birthDate" scope="session" value="${author.getBirthDate()}"/>
	<c:set var="email" scope="session" value="${author.getEmail()}"/>
</c:if>
	<form name="<portlet:namespace />fm1" action="${authorDetails}" method="POST">
		<p>First Name</p>
		<input name="<portlet:namespace />firstName" type="text" value="${firstName}"></input>
		<p>Last Name</p> 
		<input name="<portlet:namespace />lastName" type="text" value="${lastName}"></input>
		<p>Birth Date</p>
		<input name="<portlet:namespace />birthDate" type="date" value="${birthDate}"></input>
        <p>Email</p>
        <input name="<portlet:namespace />email" type="date" value="${email}"></input>
        <c:if test="${author != null}">
        <p>
	        Books
	        <a href="${listURL}">
	        	<button type="button">Open Book list</button>
	        </a>
	    </p>
        <table border="1">
			<c:forEach items="${author.getRelatedBooks()}" var="currBook">
				<portlet:renderURL var="editBookURL">
					<portlet:param name="mvcPath" value="/html/booklist/editBook.jsp"></portlet:param>
					<portlet:param name="backURL" value="/html/booklist/editAuthor.jsp"/>
					<portlet:param name="bookId" value="${currBook.getBookId()}" />
					<portlet:param name="mode" value="editBook" />
				</portlet:renderURL>
				<tr>
					<td>
						<a href="${editBookURL}">
							<c:if test="${bookPrefs.isTitleVisible()}">
								${currBook.getTitle()} 
							</c:if>
							<c:if test="${bookPrefs.isIsbnVisible()}">
								${currBook.getISBN()} 
							</c:if>
							<c:if test="${bookPrefs.isReleaseDateVisible()}">
								${currBook.getReleaseDate()} 
							</c:if>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<button type="submit">Submit</button>
	<c:if test="${author != null}">
		<a href="${deleteURL}">
			<button type="button">Delete this Author</button>
		</a>
	</c:if>
	<a href="${viewURL}">
		<button type="button">Cancel</button>
	</a>
	</form>
</html>

