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

<a class="btn" href="${addBookURL}">Add Book</a>
<a class="btn" href="${addAuthorURL}">Add Author</a>
<table>
	<tr>
		<c:forEach items="${tableHeader}" var="name">
			<th scope="col">${name}</th>
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
				<a class="table-link" href="${editBookURL}">
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
				<c:if test="${currBook.getRelatedAuthors() != null}">
					<c:forEach items="${currBook.getRelatedAuthors()}" var="currAuthor">
						<div class="cell-item">
							<portlet:renderURL var="editAuthorURL">
								<portlet:param name="mvcPath" value="/html/booklist/editAuthor.jsp"></portlet:param>
								<portlet:param name="backURL" value="/html/booklist/view.jsp"/>
								<portlet:param name="authorId" value="${currAuthor.getAuthorId()}" />
								<portlet:param name="mode" value="editAuthor" />
							</portlet:renderURL>
							<a class="table-link" href="${editAuthorURL}">
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
						</div>
					</c:forEach>
				</c:if>			
			</td>
		</tr>
	</c:forEach>
</table>