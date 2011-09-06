<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="title"%>
<%@ attribute name="errorMsg"%>
<div class="error">
	<span class=title">${title}</span>
	<div class="text">
		${errorMsg}
	</div>
</div>