package com.my_eshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductList {
	
	public static ArrayList<product> product_list()
	{	
		ArrayList<product> product_list = new ArrayList<product>();
		String url = "jdbc:postgresql://localhost:5432/postgres";
		String query="SELECT * FROM products";
		try {
			Class.forName("org.postgresql.Driver");
			Connection conn = DriverManager.getConnection(url,"postgres","***********");
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
