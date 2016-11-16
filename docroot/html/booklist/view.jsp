<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:renderURL var="addBookURL">
	<portlet:param name="mvcPath" value="/html/booklist/editBook.jsp"></portlet:param>
	<portlet:param name="itemId" value="-1" />
</portlet:renderURL>
<portlet:renderURL var="addAuthorURL">
	<portlet:param name="mvcPath" value="/html/booklist/editAuthor.jsp"></portlet:param>
	<portlet:param name="itemId" value="-1" />
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
						<portlet:param name="authorId" value="${currBook.getId()}" />
					</portlet:renderURL>
					<a href="${editAuthorURL}">
						<c:if test="${bookPrefs.contains(\"title\"}">
							${currBook.getTitle()} 
						</c:if>
						<c:if test="${bookPrefs.contains(\"ISBN\"}">
							${currBook.getISBN()} 
						</c:if>
						<c:if test="${bookPrefs.contains(\"releaseDate\"}">
							${currBook.getReleaseDate()} 
						</c:if>
					</a>
				</td>
				<td>
					<table border="1">
						<tr>
							<td>
								<c:if test="${currBook.getRelatedAuthors() != null}">
									<c:forEach items="${currBook.getRelatedAuthors()}" var="currAuthor">
										<portlet:renderURL var="editAuthorURL">
											<portlet:param name="mvcPath" value="/html/booklist/editAuthor.jsp"></portlet:param>
											<portlet:param name="backURL" value="/html/booklist/view.jsp"/>
											<portlet:param name="authorId" value="${currAuthor.getAuthorId()}" />
										</portlet:renderURL>
										<a href="${editAuthorURL}">
											<c:if test="${authorPrefs.contains(\"firstName\"}">
												${currAuthor.getFirstName()}
											</c:if>
											<c:if test="${authorPrefs.contains(\"lastName\"}">
												${currAuthor.getLastName()}
											</c:if>
											<c:if test="${authorPrefs.contains(\"birthDate\"}">
												${currAuthor.getBirthDate()}
											</c:if>
											<c:if test="${authorPrefs.contains(\"email\"}">
												${currAuthor.getEmail()}
											</c:if>
										</a>
									</c:forEach>
								</c:if>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</c:forEach>
	</table>
</html>