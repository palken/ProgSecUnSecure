<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="comment" type="no.ntnu.tdt4237.Comment"%>
<%@ attribute name="showDelete" type="java.lang.Boolean" %>

<div class="comment">
	<span class="owner"><a href="/profile/${comment.owner.userName}">${comment.owner.userName}</a></span>
	<span class="date">${comment.date}</span>
	<div class="text">${comment.text}</div>
	<%
	if (showDelete !=null && showDelete)
	{
		%>
		<form action="" method="post">
		<input type="hidden" name="dOwner" value="${comment.owner.userName}"/>
		<input type="hidden" name="dText" value="${comment.text}"/>
		<input type="hidden" name="dDate" value="${comment.date}"/>
		<input type="submit" name="DeleteComment" value="Delete Comment"/>
		</form>
		<%
	}
	%>
</div>
