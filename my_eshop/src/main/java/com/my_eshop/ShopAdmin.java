package com.my_eshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShopAdmin extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
	ArrayList<product> product_list = product_list();
	req.setAttribute("product_list",product_list);
	RequestDispatcher dispatcher = req.getRequestDispatcher("ShopAdmin.jsp");
	dispatcher.forward(req,res);
	}
	
	public ArrayList<product> product_list()
	{	
		ArrayList<product> product_list = new ArrayList<product>();
		String query="SELECT * FROM products WHERE";
		String url = "jdbc:postgresql://localhost:5432/postgres";
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url,"postgres","*********");
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet rs = statement.executeQuery();
			
			while (rs.next())
			{
				product p = new product(rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4),rs.getString(5));
				product_list.add(p);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
			
		return product_list;
		
	}

}
