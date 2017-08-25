package com.braindigit.addagent;

import java.util.Random;

public class LociplaceImplementaion {	

	
	public String GetRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";       
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 2) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;

    }
	
	String randomString=GetRandomString();
	
	public String SetRandomString() {
		System.out.println(randomString);
	return randomString;
	}
	
	
	
}
