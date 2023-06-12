<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
		if(session.getAttribute("username") == null)
		{
		response.sendRedirect("index.html");
		}
		else if(session.getAttribute("role").equals("Client"))response.sendRedirect("ShopClient.jsp");
		else{
		%>
<form action="update_image" method="post" enctype="multipart/form-data">
<input type="hidden" name="id" value=<%=request.getParameter("id") %>>
<p>Choose file:</p><input type="file" name="file" required/>
<input type="submit">
</form>
		<%} %>
</body>
</html>