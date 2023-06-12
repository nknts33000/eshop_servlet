package com.my_eshop;

public class Check_password {
	
	public static boolean contain_number(String str){
		
		for(int i =0; i<=9; i++)
		{
			if(str.contains(Integer.toString(i)))
					{
						return true;
					}
		}
		return false;
	}
	public static boolean check(String password)
	{
		if(password.toUpperCase() == password || password.toLowerCase() == password || password.length()<8 || !contain_number(password)) return false;
		return true;
	}

}

