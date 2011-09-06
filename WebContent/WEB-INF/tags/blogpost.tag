<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="post" type="no.ntnu.tdt4237.BlogPost" %>
<%@ attribute name="loggedInUser" type="no.ntnu.tdt4237.User"%>
<%@ attribute name="full" %>
<div class="blogPost">
	<div class="postHeader">
		<span class="title">${post.title}</span>
		
	</div>
	<div class="text">${post.formatedText}</div>
	<%
	if (post.getPictureName() != null)
	{
	%>
	<img alt="${post.pictureName}" src="/pictures/${post.pictureName}">
	<%
	}
	%>
	<div class="postFooter">
		<span class="date">${post.date}</span>
		<span class="owner"><a href="/profile/${post.owner.userName}">${post.owner.userName}</a></span>
		<%if (full==null || full=="")
		{
			%>
				<span class="more"><a href="/post/${post.owner.userName}/${post.date}/${post.title}">more   comments</a></span>
			<%
		}
		if (loggedInUser != null && loggedInUser.equals(post.getOwner()))
		{
			%>
			<form action="/post/${post.owner.userName}/${post.date}/${post.title}" method="post">
			<input type="submit" name="Delete" value="Delete post"/>
			</form>
			<form action="/editpost/${post.owner.userName}/${post.date}/${post.title}" method="post">
			<input type="submit" name="Edit" value="Edit post"/>
			</form>
			<%	
		}
		%>
				
	</div>
</div>