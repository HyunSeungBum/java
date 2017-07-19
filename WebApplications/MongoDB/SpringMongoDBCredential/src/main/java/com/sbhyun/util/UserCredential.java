package com.sbhyun.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.stereotype.Component;

@Component
public class UserCredential extends UserCredentials {
	
	private final String key = ">ny;GG9`m+?%W}~:"; // 128 bit key
	private final String initVector = "-?C^x3ph.,cGXChK"; // 16 bytes IV
	private IvParameterSpec iv;
	private SecretKeySpec skeySpec;

	@Autowired
	public UserCredential(@Value("${mongo.userName}") String encryptedUserName,
			@Value("${mongo.password}") String encryptedPassword) {
		super(encryptedUserName, encryptedPassword);
		// TODO Auto-generated constructor stub
		try {
			iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
			skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private String encrypt(String planText) {
		Cipher cipher; 
		byte[] encrypted = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			encrypted = cipher.doFinal(planText.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return new String(Hex.encodeHex(encrypted));
	}
	
	private String decrypt(String encryptText) {
		Cipher cipher;
		byte[] decrypted;
		String planText = "";
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			decrypted = cipher.doFinal(Hex.decodeHex(encryptText.toCharArray()));
			planText = new String(decrypted, "UTF-8");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | DecoderException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return planText;
	}
	
	@Override
	public String getUsername() {
		return decrypt(super.getUsername());
	}
	
	@Override
	public String getPassword() {
		return decrypt(super.getPassword());
	}
	
	public String getEncrypt(String planText) {
		return encrypt(planText);
	}
	
	public String getDecyprt(String encryptText) {
		return decrypt(encryptText);
	}

}
