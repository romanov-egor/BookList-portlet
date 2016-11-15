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
		<c:forEach items="${tableRows}" var="row">
			<tr>
				<c:forEach items="${row.getRow()}" var="element">
					<td>
						<table border="1">
							<c:forEach items="${element.value}" var="item">
								<tr>
								<c:if test="${element.value != null}">
									<portlet:renderURL var="editURL">
										<c:if test="${item.getColumn() == \"Book\"}">
											<portlet:param name="mvcPath" value="/html/booklist/editBook.jsp"></portlet:param>
										</c:if>
										<c:if test="${item.getColumn() == \"Author\"}">
											<portlet:param name="mvcPath" value="/html/booklist/editAuthor.jsp"></portlet:param>
										</c:if>
										<portlet:param name="backURL" value="/html/booklist/view.jsp"/>
										<portlet:param name="itemId" value="${item.getId()}" />
									</portlet:renderURL>
									<td>
									<a href="${editURL}">
										<c:forEach items="${item.getParameters()}" var="parameter">
											<c:if test="${parameter.isVisible()}">
												${parameter.getValue()}
											</c:if>
										</c:forEach>
									</a>
									</td>
								</c:if>
								</tr>
							</c:forEach>
						</table>
					</td>
				</c:forEach>
			</tr>
		</c:forEach>
	</table>
</html>
