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
	<portlet:param name="mvcPath" value="/html/booklist/addBookToAuthor.jsp"></portlet:param>
	<portlet:param name="backURL" value="/html/booklist/editAuthor.jsp"/>
	<portlet:param name="authorId" value="${author.getId()}"/>
	<portlet:param name="mode" value="addBookToAuthor" />
</portlet:renderURL>

<portlet:actionURL name="deleteAuthor" var="deleteURL">
	<portlet:param name="authorId" value="${author.getId()}"/>
</portlet:actionURL>

<div class="edit-container container-left">
	<a class="btn" href="${viewURL}">Cancel</a>
	<c:if test="${author == null}">
		<h2>Add Author</h2>
		<portlet:actionURL name="addAuthor" var="authorDetails"></portlet:actionURL>
		<c:set var="firstName" scope="session" value=""/>
		<c:set var="lastName" scope="session" value=""/>
		<c:set var="birthDate" scope="session" value=""/>
		<c:set var="email" scope="session" value=""/>	
	</c:if>
	<c:if test="${author != null}">
		<h2>Edit Author</h2>
		<portlet:actionURL name="updateAuthor" var="authorDetails">
			<portlet:param name="authorId" value="${author.getId()}"/>
		</portlet:actionURL>
		<c:set var="firstName" scope="session" value="${author.getFirstName()}"/>
		<c:set var="lastName" scope="session" value="${author.getLastName()}"/>
		<c:set var="birthDate" scope="session" value="${author.getBirthDate()}"/>
		<c:set var="email" scope="session" value="${author.getEmail()}"/>
	</c:if>
	
	<form name="<portlet:namespace />fm1" action="${authorDetails}" method="POST">
		<label for="first-name-field">First Name<span class="hint">Type first name of the author</span></label>
		<input id="first-name-field" name="<portlet:namespace />firstName" type="text" value="${firstName}" />
		<label for="last-name-field">Last Name<span class="hint">Type last name of the author</span></label>
		<input id="last-name-field" name="<portlet:namespace />lastName" type="text" value="${lastName}" />
		<label for="birth-date-field">Birth Date<span class="hint">Type birth date of the author</span></label>
		<input id="birth-date-field" name="<portlet:namespace />birthDate" type="text" value="${birthDate}" />
		<label for="email-field">Email<span class="hint">Type email of the author</span></label>
		<input id="email-field" name="<portlet:namespace />email" type="text" value="${email}" />
		<div>
		    <button class="btn btn-primary" type="submit" name="submit">Submit changes</button>
			<c:if test="${author != null}">
				<a class="btn" href="${deleteURL}">Delete this Author</a>
			</c:if>
		</div>
	</form>
</div>
<div class="edit-container container-right">
    <c:if test="${author != null}">
	    <h2>Books of this Author</h2>
	    <ul>
			<c:forEach items="${author.getRelatedBooks()}" var="currBook">
				<portlet:renderURL var="editBookURL">
					<portlet:param name="mvcPath" value="/html/booklist/editBook.jsp"></portlet:param>
					<portlet:param name="backURL" value="/html/booklist/editAuthor.jsp"/>
					<portlet:param name="bookId" value="${currBook.getBookId()}" />
					<portlet:param name="mode" value="editBook" />
				</portlet:renderURL>
				<li>
					<i class="icon-book"></i>
					<a class="main-table-link" href="${editBookURL}">
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
				</li>
			</c:forEach>
		</ul>
		<a class="btn" href="${listURL}">Add or Remove Books</a>
	</c:if>
</div>


