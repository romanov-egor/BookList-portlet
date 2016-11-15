<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects />

<portlet:actionURL name="getPreferences" var="getPreferencesURL"></portlet:actionURL>

<form name="<portlet:namespace />fm1" action="${getPreferencesURL}" method="post">
	Book<br>
	<input type="checkbox" name="<portlet:namespace />bookPrefs" value="title" checked>Title<br>
	<input type="checkbox" name="<portlet:namespace />bookPrefs" value="ISBN">ISBN<br>
	<input type="checkbox" name="<portlet:namespace />bookPrefs" value="releaseDate">Release Date<br>
	Author<br>
	<input type="checkbox" name="<portlet:namespace />authorPrefs" value="firstName" checked>First Name<br>
	<input type="checkbox" name="<portlet:namespace />authorPrefs" value="lastName">Last Name<br>
	<input type="checkbox" name="<portlet:namespace />authorPrefs" value="birthDate">Birth Date<br>
	<input type="checkbox" name="<portlet:namespace />authorPrefs" value="email">Email<br>
	<input type="submit" name="Submit" value="Submit"> 
 </form>
