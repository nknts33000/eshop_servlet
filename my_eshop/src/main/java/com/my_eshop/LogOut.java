package com.my_eshop;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOoutServlet
 */
public class LogOut extends HttpServlet 
{
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
    	HttpSession session = request.getSession();
    	session.removeAttribute("username");
    	session.removeAttribute("role");
    	session.removeAttribute("product_list");
    	session.invalidate();
    	response.sendRedirect("index.html");
    }

}
