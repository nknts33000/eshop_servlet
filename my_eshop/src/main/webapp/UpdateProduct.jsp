    <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>


<%	

	response.setHeader("Cache-Control","no-cache, no-store, must-revalidate");
	if(session.getAttribute("username") == null)
	{
	response.sendRedirect("index.html");
	}

%>
		<form action="update_product">
		
		<input type="hidden" name="id" value=<%=request.getParameter("id") %>>
		<h3>UPDATE</h3>
		<select name="attribute">
		<option>product_name</option>
		<option>price</option>
		<option>description</option>
		</select>
		<h3>SET</h3>
		<input type="text" name="new_value">
		<input type="submit">
		
		</form>
</body>
</html>