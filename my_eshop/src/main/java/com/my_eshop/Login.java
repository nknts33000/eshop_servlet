package com.my_eshop;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.beans.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class Login extends HttpServlet {
	
	String username;
	String password;
	


		public void doGet(HttpServletRequest req, HttpServletResponse res)
		{
			username = req.getParameter("username");
			password = req.getParameter("password");
			String query="SELECT * FROM users WHERE username = ?";
			String url = "jdbc:postgresql://localhost:5432/postgres";
			String hash_password="";
			
			try {
				
				
				Class.forName("org.postgresql.Driver");
				Connection conn = DriverManager.getConnection(url,"postgres","6972419550n");
				PreparedStatement statement = conn.prepareStatement(query);
				statement.setString(1,username);
				ResultSet rs = statement.executeQuery();//connection and query execution to obtain the row
	
				if(rs.next() )//check if username exists
				{
					byte[] salt=rs.getBytes(5);//if it exists,get the salt from the db row
					try {
						hash_to_string h = new com.my_eshop.hash_to_string();
						MessageDigest md = MessageDigest.getInstance("SHA-256");
						
						md.update(salt);
						byte[] hash = md.digest(password.getBytes());
						
						hash_password = h.toHexString(hash);//hash and salt the password given from the user
						if(rs.getString(2).equals(hash_password)) {
							/*check if the hashed and salted password matches
						the one in the db and give access if it does*/
							PrintWriter pr = res.getWriter();
							pr.println("LOGGED IN!");
							if(rs.getString(3).equals("Owner"))
							{
							HttpSession session = req.getSession();
							session.setAttribute("username", username);
							session.setAttribute("role",rs.getString(3));
							ArrayList<product> product_list = ProductList.product_list();
							session.setAttribute("product_list",product_list);
							res.sendRedirect("ShopAdmin.jsp");
							}
							
							else if(rs.getString(3).equals("Client"))
							{
							HttpSession session = req.getSession();
							session.setAttribute("username", username);
							session.setAttribute("role",rs.getString(3));
							ArrayList<product> product_list = ProductList.product_list();
							session.setAttribute("product_list",product_list);
							res.sendRedirect("ShopAdmin.jsp");

							}
							else
							{
								
								res.sendRedirect("index.html");
							}
							
						}
						else {
							PrintWriter pr = res.getWriter();
							pr.println("NOT LOGGED IN!");
						}
						}
						
						
						
					 catch (NoSuchAlgorithmException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
				{
					res.sendRedirect("index.html");
				}

				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

		}

	}




