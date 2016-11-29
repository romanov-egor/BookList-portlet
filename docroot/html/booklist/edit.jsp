<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:actionURL name="getPreferences" var="getPreferencesURL"></portlet:actionURL>

<form name="<portlet:namespace />fm1" action="${getPreferencesURL}" method="post">
	<div>Book</div>
	<div>
		<input type="checkbox" name="<portlet:namespace />bookPrefs" value="title" ${bookPrefs.isTitleVisible() ? "checked" : ""}>Title
	</div>
	<div>
		<input type="checkbox" name="<portlet:namespace />bookPrefs" value="ISBN" ${bookPrefs.isIsbnVisible() ? "checked" : ""}>ISBN
	</div>
	<div>
		<input type="checkbox" name="<portlet:namespace />bookPrefs" value="releaseDate" ${bookPrefs.isReleaseDateVisible() ? "checked" : ""}>Release Date
	</div>
	<div>Author</div>
	<div>
		<input type="checkbox" name="<portlet:namespace />authorPrefs" value="firstName" ${authorPrefs.isFirstNameVisible() ? "checked" : ""}>First Name
	</div>
	<div>
		<input type="checkbox" name="<portlet:namespace />authorPrefs" value="lastName" ${authorPrefs.isLastNameVisible() ? "checked" : ""}>Last Name
	</div>
	<div>
		<input type="checkbox" name="<portlet:namespace />authorPrefs" value="birthDate" ${authorPrefs.isBirthDateVisible() ? "checked" : ""}>Birth Date
	</div>
	<div>
		<input type="checkbox" name="<portlet:namespace />authorPrefs" value="email" ${authorPrefs.isEmailVisible() ? "checked" : ""}>Email
	</div>
	<div>
		<button class="btn btn-primary" type="submit" name="Submit">Submit preferences</button>
	</div>
</form>
 