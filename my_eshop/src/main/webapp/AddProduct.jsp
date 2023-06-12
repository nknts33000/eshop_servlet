<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

</head>
<body>
	<%
	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
	if(session.getAttribute("username") == null)
	{
	response.sendRedirect("index.html");
	}
	else if(session.getAttribute("role").equals("Client"))response.sendRedirect("ShopClient.jsp");
	%>
	<form action="add_product" method="post" enctype="multipart/form-data">
		<p>Product name:</p><input type ="text" name="name">
		<p>price:</p><input type ="text" name="price">
		<br>
		<br>
		<textarea name="description" rows="4" cols="50">
		product description
		</textarea>
		<br>
		<br>
		<p>Choose file:</p><input type="file" name="file" required/>
		<br>
		<br>
		<button type="submit">Add product</button>
	</form>
	
</body>
</html>