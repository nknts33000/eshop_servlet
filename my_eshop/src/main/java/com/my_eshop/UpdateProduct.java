package com.my_eshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateProduct extends HttpServlet{
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{

		HttpSession session = req.getSession();
		System.out.println(req.getParameter("id"));
		int id = Integer.parseInt(req.getParameter("id"));
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String query1 = "UPDATE products SET product_name = ? WHERE product_id = ?";
		String query2 = "UPDATE products SET price = ? WHERE product_id = ?";
		String query3 = "UPDATE products SET description = ? WHERE product_id = ?";
		String attribute = req.getParameter("attribute");
		System.out.println(attribute);
		String new_value = req.getParameter("new_value");
		try {
			Connection connection = DriverManager.getConnection(url,"postgres","6972419550n");
			
			if (attribute.equals("product_name"))
			{	
				PreparedStatement statement = connection.prepareStatement(query1);
				statement.setString(1,new_value);
				statement.setInt(2,id);
				statement.executeUpdate();
			}
			else if (attribute.equals("price"))
			{
				PreparedStatement statement = connection.prepareStatement(query2);
				statement.setFloat(1,Float.parseFloat(new_value));
				statement.setInt(2,id);
				statement.executeUpdate();
			}
			else
				{
				PreparedStatement statement = connection.prepareStatement(query3);
				statement.setString(1,new_value);
				statement.setInt(2,id);
				statement.executeUpdate();
				}
			
			ArrayList<product> product_list = ProductList.product_list();
			session.setAttribute("product_list",product_list);
			res.sendRedirect("ShopAdmin.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}

}
}
