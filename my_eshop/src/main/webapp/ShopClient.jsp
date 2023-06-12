<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.my_eshop.product" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<form action="logout" >
<p>Logout here<p/><input type="submit" value="logout">
<br>
<a href="Update.jsp">update profile here !</a>
<br>
<br>

</form>


		<table style ="border: 5px solid red;">
		<thead>
		<trstyle>
		
		
		<th>Name</th>
		<th>price</th>
		<th>description</th>
		<th>Image</th>
		
		
		</tr>
		</thead>
		<tbody>
		
		<%
		if (session.getAttribute("username") == null) response.sendRedirect("index.html");
		else if (session.getAttribute("role").equals("Owner")) response.sendRedirect("ShopAdmin.jsp");
		else
			{
			ArrayList<product> product_list = (ArrayList<product>)session.getAttribute("product_list"); 
			
		
		%>
		<%for (int i = 0; i < product_list.size(); i++){ 
		product p = product_list.get(i);
		System.out.println(p.picture_file);%>
		<tr align="center">
		<td><%=p.name%></td>
		<td><%=p.price %></td>
		<td><%=p.description %></td>
		<td>
		<img src="<%=p.picture_file %>" style="width:150px;height:100px;"></img>
		</td>
		<td><a href="BuyProduct.jsp">Buy</a></td>
		</tr>
		<%}} %>
		
		</tbody>
		</table>
		
</body>
</html>