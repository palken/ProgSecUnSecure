<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="no.ntnu.tdt4237.*" %>
    <%@ page import="no.ntnu.tdt4237.helperactions.*" %>
    <%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@page import="java.util.Date"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.tomcat.util.http.fileupload.FileUpload"%>
<%@page import="org.apache.tomcat.util.http.fileupload.MultipartStream"%>
<%@page import="java.util.Map"%><html xmlns="http://www.w3.org/1999/xhtml">

<tags:header title=" - Post"></tags:header>


<body>

<%
User loggedInUser = (User) session.getAttribute(SessionKeys.USER_OBJECT);
String blogOwnerName = StringHelpers.getPostOwnerName(request.getRequestURL().toString(), "editpost");
User postOwner = Database.getUser(blogOwnerName);
%>
<tags:menu loggedInUser="<%=loggedInUser%>"></tags:menu>

<%
if (loggedInUser != null && postOwner !=null)
{
	String postDate = StringHelpers.getPostDate(request.getRequestURL().toString(), postOwner.getUserName(), "editpost");
	String postTitle = StringHelpers.getPostTitle(request.getRequestURL().toString(), postOwner.getUserName(), "editpost");
	BlogPost postToEdit = Database.getPost(postOwner, postTitle, DateHelpers.parseString(postDate));
	
	if (postToEdit != null)
	{
		boolean postOk = false;
		if (request.getParameter("Title") !=null)
		{
			
			BlogPost newPost = new BlogPost(request.getParameter("Title"),
								 request.getParameter("Post"),
								 new Date(),
								 loggedInUser);
			 
			
			newPost.setPictureName(postToEdit.getPictureName());
			
			postOk = Database.editPost(postToEdit, newPost);
		} 
		if (postOk)
		{
			%>
			<span>Your post is stored!</span>
			<%
		}
		else if (request.getParameter("Edit") !=null )
		{
			%>
			<div class="blogPost">
				<form action="" method="post">
					<span>Title:</span><input id="title" type="text" name="Title" value="<%=postToEdit.getTitle()%>"/><br/>
					<span>Date:</span><input type="text" name="Date" disabled="disabled" value="<%=DateHelpers.toShortFormat(new Date())%>"/><span> (will be updated)</span><br/>
					<span>Post:</span><br/>
					<textarea class="text validate" rows="5" cols="30" name="Post"><%=postToEdit.getText()%></textarea>
					<input type="submit" value="Post"/>
				</form>
			</div>
			<%	
		}
		else
		{
			%>
			<tags:error errorMsg="You have to select edit in a post"></tags:error>
			<%
			
		}
	}
	else
	{
		%>
		<tags:error errorMsg="Didn't find the post"></tags:error>
		<%	
	}
} 
else
{
	%>
	<span>You have to log in to be able to edit a post!</span> 
	<%
}
%>
</body>
</html>
