package com.sbhyun.util;

public class ChiperUtil {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String userName = "test";
		String password = "154321";
		String database = "testDB";
		
		UserCredential userCredential = new UserCredential(userName, password);
		
		System.out.println("encrypted userName: " + userCredential.getEncrypt(userName));
		System.out.println("encrypted password: " + userCredential.getEncrypt(password));
		System.out.println("encrypted database: " + userCredential.getEncrypt(database));
		
	}

}
