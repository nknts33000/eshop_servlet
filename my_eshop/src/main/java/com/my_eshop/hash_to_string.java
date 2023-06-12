package com.my_eshop;

import java.math.BigInteger;

public class hash_to_string {
public String toHexString(byte[] hash)
{
    BigInteger number = new BigInteger(1, hash); 
    StringBuilder hexString = new StringBuilder(number.toString(16)); 

    while (hexString.length() < 32) 
    { 
        hexString.insert(0, '0'); 
    } 

    return hexString.toString(); 
}

public byte[] backToByteArray(String salt) {
	
	int it = Integer.parseInt(salt, 16);
	BigInteger bigInt = BigInteger.valueOf(it);
	byte[] bytearray = (bigInt.toByteArray());
	return bytearray;
}
}
