package com.my_eshop;

public class product {
	
	public int id;
	public String name;
	public float price;
	public String description;
	public String picture_file;
	
	public product(int Id,String Name,float Price,String Description,String Picture_file)
	{
		id = Id;
		name = Name;
		price = Price;
		description = Description;
		picture_file = "images/"+Picture_file;
	}

}
