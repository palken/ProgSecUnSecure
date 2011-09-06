<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="loggedInUser" type="no.ntnu.tdt4237.User" %>
<%
boolean loggedIn = loggedInUser != null;
%>

<div class="menu">
<%
if (loggedIn)
{
	%>
	<span><a href="/profile/${loggedInUser.userName}">Profile</a></span>
	<span><a href="/blog/${loggedInUser.userName}">Your blog</a></span>
	<span><a href="/newpost.jsp">New Blog Post</a></span>
	<span><a href="/users">Users</a></span>
	<span><a href="/logout.jsp">Log out</a></span>
	<%
}
else
{
	%>
	<span><a href="/login.jsp">Log in</a></span>
	<%
}
%>
<span></span>
</div>
