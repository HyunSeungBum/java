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
	public UserCredential(@Value("${db.user}") String encryptedUserName,
			@Value("${db.passwd}") String encryptedPassword) {
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
	
	private String encrypt(String planText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		Cipher cipher; 
		byte[] encrypted = null;

		cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		encrypted = cipher.doFinal(planText.getBytes("UTF-8"));

		return new String(Hex.encodeHex(encrypted));
	}
	
	private String decrypt(String encryptText) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, DecoderException, UnsupportedEncodingException {
		Cipher cipher;
		byte[] decrypted;
		String planText = "";

		cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
		decrypted = cipher.doFinal(Hex.decodeHex(encryptText.toCharArray()));
		planText = new String(decrypted, "UTF-8");


		return planText;
	}
	
	@Override
	public String getUsername() {
		String result = null;
		try {
			result = decrypt(super.getUsername());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public String getPassword() {
		String result = null;
		try {
			result = decrypt(super.getPassword());
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DecoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public String getEncrypt(String planText) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		return encrypt(planText);
	}
	
	public String getDecyprt(String encryptText) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, DecoderException {
		return decrypt(encryptText);
	}

}
