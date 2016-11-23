<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:renderURL var="addBookURL">
	<portlet:param name="mvcPath" value="/html/booklist/editBook.jsp"></portlet:param>
	<portlet:param name="bookId" value="-1" />
	<portlet:param name="mode" value="editBook" />
</portlet:renderURL>
<portlet:renderURL var="addAuthorURL">
	<portlet:param name="mvcPath" value="/html/booklist/editAuthor.jsp"></portlet:param>
	<portlet:param name="authorId" value="-1" />
	<portlet:param name="mode" value="editAuthor" />
</portlet:renderURL>

<html>
	<a href="${addBookURL}">
		<button type="button">Add Book</button>
	</a>
	<a href="${addAuthorURL}">
		<button type="button">Add Author</button>
	</a>
	<br>
	<table border="1">
		<tr>
			<c:forEach items="${names}" var="name">
				<th>${name}</th>
			</c:forEach>
		</tr>
		<c:forEach items="${books}" var="currBook">
			<tr>
				<td>
					<portlet:renderURL var="editBookURL">
						<portlet:param name="mvcPath" value="/html/booklist/editBook.jsp"></portlet:param>
						<portlet:param name="backURL" value="/html/booklist/view.jsp"/>
						<portlet:param name="bookId" value="${currBook.getId()}" />
						<portlet:param name="mode" value="editBook" />
					</portlet:renderURL>
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
				<td>
					<table border="1">
						<c:if test="${currBook.getRelatedAuthors() != null}">
							<c:forEach items="${currBook.getRelatedAuthors()}" var="currAuthor">
								<tr>
								<td>
								<portlet:renderURL var="editAuthorURL">
									<portlet:param name="mvcPath" value="/html/booklist/editAuthor.jsp"></portlet:param>
									<portlet:param name="backURL" value="/html/booklist/view.jsp"/>
									<portlet:param name="authorId" value="${currAuthor.getAuthorId()}" />
									<portlet:param name="mode" value="editAuthor" />
								</portlet:renderURL>
								<a href="${editAuthorURL}">
									<c:if test="${authorPrefs.isFirstNameVisible()}">
										${currAuthor.getFirstName()}
									</c:if>
									<c:if test="${authorPrefs.isLastNameVisible()}">
										${currAuthor.getLastName()}
									</c:if>
									<c:if test="${authorPrefs.isBirthDateVisible()}">
										${currAuthor.getBirthDate()}
									</c:if>
									<c:if test="${authorPrefs.isEmailVisible()}">
										${currAuthor.getEmail()}
									</c:if>
								</a>
								</td>
								</tr>
							</c:forEach>
						</c:if>			
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
</html>