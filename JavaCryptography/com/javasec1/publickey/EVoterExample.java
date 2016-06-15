package com.javasec1.publickey;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author db2admin
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class EVoterExample {

	

	public static void main(String[] args) {
	
	try {	
		KeyPairGenerator RSAKeyGen = KeyPairGenerator.getInstance("RSA");
		RSAKeyGen.initialize(512);
		KeyPair RSAKeyPair = RSAKeyGen.generateKeyPair();
		Key publicKey = RSAKeyPair.getPublic();
		Key privateKey = RSAKeyPair.getPrivate();
		KeyGenerator DESKeyGen = KeyGenerator.getInstance("DES");
		DESKeyGen.init(56);
		Key DESKey = DESKeyGen.generateKey();
		System.out.println(new String(DESKey.getEncoded()));
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] plainText = DESKey.getEncoded();
		byte[] cipherDESKey = cipher.doFinal(plainText);
		cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] plainDESKey = cipher.doFinal(cipherDESKey);
		System.out.println(new String(plainDESKey));
	} catch(Exception e) {
		System.out.println(e.getMessage());	
	}	
	}
}
