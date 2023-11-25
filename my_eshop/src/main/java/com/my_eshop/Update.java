package com.my_eshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.beans.Statement;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Servlet implementation class Update_servlet
 */
public class Update extends HttpServlet 

{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
	
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String hash_password=""; //old pass
	String hash_password2=""; //new pass
	String attribute = req.getParameter("attribute"); //update choice
	System.out.println(attribute);
	String set = req.getParameter("set"); //value of new attribute
	HttpSession session = req.getSession(); 
	
	
	
	try {
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(url,"postgres","************");
		java.sql.Statement statement = conn.createStatement();
		String query0 = "SELECT * FROM users WHERE username = '"+session.getAttribute("username")+"';";
		String query1 = "UPDATE users SET username=? WHERE username='"+session.getAttribute("username")+"';";
		String query2 = "UPDATE users SET hashpassword =? WHERE username='"+session.getAttribute("username")+"';";
		String query3 = "UPDATE users SET email=? WHERE username='"+session.getAttribute("username")+"';";
		Connection connection = DriverManager.getConnection(url,"postgres","*********");
		
		
	
		ResultSet rs = statement.executeQuery(query0);
		rs.next();
		byte[] salt=rs.getBytes(5);
		
		
		try {
			String password =req.getParameter("password");
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] hash = md.digest(password.getBytes());
			hash_to_string h = new hash_to_string();
			hash_password = h.toHexString(hash);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] hash2 = md.digest(set.getBytes());
			hash_to_string h = new hash_to_string();
			hash_password2 = h.toHexString(hash2);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		

		
		
		try
		{
		if(rs.getString(2).equals(hash_password))
		{
			if(attribute.equals("USERNAME"))
			{
				PreparedStatement statement1 = connection.prepareStatement(query1);
				statement1.setString(1,set);
				statement1.executeUpdate();
				session.setAttribute("username", set);
				res.sendRedirect("ShopClient.jsp");

			}
			if(attribute.equals("PASSWORD"))
			{
				if(!Check_password.check(set))
				{
					PrintWriter pr = res.getWriter();
					pr.println("PASSWORD MUST HAVE:");
					pr.println("AT LEAST 8 CHARACTERS");
					pr.println("AT LEAST 1 NUMBER");
					pr.println("AND BOTH LOWERCASE ANT UPPERCASE LETTERS");
						
				}
				else
				{
				PreparedStatement statement2 = connection.prepareStatement(query2);
				statement2.setString(1,hash_password2);
				statement2.executeUpdate();
				res.sendRedirect("ShopClient.jsp");
				}

			}
			if(attribute.equals("EMAIL"))
			{
				PreparedStatement statement3 = connection.prepareStatement(query3);
				statement3.setString(1,set);
				statement3.executeUpdate();
				res.sendRedirect("ShopClient.jsp");
			}
		}
		else
		{
			PrintWriter pr = res.getWriter();
			pr.println("wrong password");
		}
		}
		catch(Exception e)
		{
			PrintWriter pr = res.getWriter();
			pr.println(e);
		}
		

		
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
	}
	}
