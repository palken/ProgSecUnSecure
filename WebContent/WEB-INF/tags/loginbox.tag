<%@ tag language="java" pageEncoding="UTF-8"%>
<div class="LoginBox">Login
	<form action="login.jsp" method="post">
		<span>UserName</span> <input type="text" name="UserName"></input><br/>
		<span>Password</span> <input type="password" name="Password"></input><br/>
		<input type="submit" value="Login"></input><br/>
	</form>
	<span><a href="register.jsp">Register new user</a></span>
	<span><a href="resetpassword.jsp">Reset password</a></span>
</div>