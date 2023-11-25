package com.my_eshop;

import java.io.IOException;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


@MultipartConfig(
		location = "C:\\Users\\nknts\\eclipse-workspace\\my_eshop\\src\\main\\webapp\\images",
		fileSizeThreshold = 1024*1024,
		maxFileSize = 1024*1024*20,
		maxRequestSize = 1024*1024*11
		)


public class UpdateImage extends HttpServlet{
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		
		HttpSession session = req.getSession();
		try {
		
			int id = Integer.parseInt(req.getParameter("id"));
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String query = "UPDATE products SET picture_file= ? WHERE product_id= ?";
		Connection connection = DriverManager.getConnection(url,"postgres","**********");
		PreparedStatement statement = connection.prepareStatement(query);
		Part part = req.getPart("file");
		String cd = part.getHeader("content-disposition");
		String file_name = cd.substring(cd.indexOf("filename=")+10,cd.length()-1);
		part.write(file_name);
		System.out.println(part.getHeader("content-disposition"));
		statement.setString(1,file_name);
		statement.setInt(2,id);
		statement.executeUpdate();
			
		
		ArrayList<product> product_list = new ArrayList<product>();
		try {
			product_list = ProductList.product_list();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		session.removeAttribute("product_list");
		session.setAttribute("product_list",product_list);
		res.sendRedirect("ShopAdmin.jsp");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}
