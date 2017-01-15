package com.sbhyun.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class ChiperUtil {

	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		// TODO Auto-generated method stub
		
		String userName = "spring";
		String password = "ald!rjflk";
		String database = "jdbc:mysql://192.168.96.30:3306/spring?characterEncoding=UTF-8";
		
		UserCredential userCredential = new UserCredential(userName, password);
		
		System.out.println("encrypted userName: " + userCredential.getEncrypt(userName));
		System.out.println("encrypted password: " + userCredential.getEncrypt(password));
		System.out.println("encrypted database: " + userCredential.getEncrypt(database));
		
	}

}
