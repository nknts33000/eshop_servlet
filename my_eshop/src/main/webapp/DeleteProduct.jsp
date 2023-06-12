<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.PreparedStatement" %>
    <%@ page import="java.sql.Connection" %>
    <%@ page import="java.sql.DriverManager" %>
    <%@ page import="com.my_eshop.ProductList" %>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.my_eshop.product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	if(session.getAttribute("role").equals("Owner"))
	{
	try{
		
		int id = Integer.parseInt(request.getParameter("id"));
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String query = "DELETE FROM products WHERE product_id=?";
		Connection connection = DriverManager.getConnection(url,"postgres","123_Unipi");
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1,id);
		statement.executeUpdate();
		ArrayList<product> product_list = ProductList.product_list();
		session.setAttribute("product_list",product_list);
		
	}catch(Exception e)
	{
		System.out.println(e);
	}
	}
	response.sendRedirect("ShopAdmin.jsp");
	
	%>
</body>
</html>