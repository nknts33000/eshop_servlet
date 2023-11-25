package com.my_eshop;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


public class SignUp extends HttpServlet {
	
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException
	{
		String username = req.getParameter("username");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		String email = req.getParameter("email");
		Random RANDOM = new SecureRandom();
		byte[] salt = new byte[16];
	    RANDOM.nextBytes(salt);
		
		String query="INSERT INTO users (username, hashpassword,_role, email,salt) VALUES (?,?,'Client',?,?);";
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String hash_password="";
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt);
			byte[] hash = md.digest(password1.getBytes());
			hash_to_string h = new com.my_eshop.hash_to_string();
			hash_password = h.toHexString(hash);
			
			System.out.println(hash_password+"\n"+salt);
		} catch (NoSuchAlgorithmException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		try {
			
			
			if(!Check_password.check(password1))
			{
				PrintWriter pr = res.getWriter();
				pr.println("PASSWORD MUST HAVE:");
				pr.println("AT LEAST 8 CHARACTERS");
				pr.println("AT LEAST 1 NUMBER");
				pr.println("AND BOTH LOWERCASE ANT UPPERCASE LETTERS");

			}
			else if(!password1.equals(password2))
			{
				PrintWriter pr = res.getWriter();
				pr.println("please type your password correctly");
			}
			else
			{
			Class.forName("org.postgresql.Driver");
			Connection connection = DriverManager.getConnection(url,"postgres","**********");
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1,username);
			statement.setString(2,hash_password);
			statement.setString(3,email);
			statement.setBytes(4,salt);
			statement.executeUpdate();
			HttpSession session = req.getSession();
			session.setAttribute("username", username);
			session.setAttribute("role", "Client");
			ArrayList<product> product_list = ProductList.product_list();
			session.setAttribute("product_list",product_list);
			res.sendRedirect("ShopClient.jsp");
			System.out.println(session.getAttribute("username"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			PrintWriter pr2 = res.getWriter();
			pr2.println(e);
		}
		catch (Exception e)
		{
			
		}
		
	}



}
