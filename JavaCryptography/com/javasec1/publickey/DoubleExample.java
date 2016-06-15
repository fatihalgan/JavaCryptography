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
public class DoubleExample {

	/**
	 * Constructor for DoubleExample.
	 */
	public DoubleExample() {
		super();
	}

	public static void main(String[] args) {
		
		if(args.length != 1) {
			System.err.println("Usage: java PrivateExample text");
			System.exit(1);
		}
		try {
		byte[] plainText = args[0].getBytes("UTF8");
		
		//Generate an RSA key
		System.out.println("\nStart generating RSA key");
		KeyPairGenerator RSAKeyGen = KeyPairGenerator.getInstance("RSA");
		RSAKeyGen.initialize(512);
		KeyPair RSAKeyPair = RSAKeyGen.generateKeyPair();
		System.out.println("Finish generating RSA key");
		
		//get a DES private key
		System.out.println("\nStart generating DES key");
		KeyGenerator DESKeyGen = KeyGenerator.getInstance("DES");
		DESKeyGen.init(56);
		Key DESKey = DESKeyGen.generateKey();
		System.out.println("Finish generating DES key");
		
		//encrypt message with DES
		System.out.println("Plain Text: " + new String(plainText));
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		
		//encrypt using the key and the plaintext
		cipher.init(Cipher.ENCRYPT_MODE, DESKey);
		byte[] cipherText = cipher.doFinal(plainText);
		System.out.println("Cipher Text: " + new String(cipherText, "UTF8"));
		
		byte[] plainDESKey = DESKey.getEncoded();
		System.out.println("Plain DES Key: " + new String(plainDESKey, "UTF8"));
		
		
		cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
				
		//encrypt the plaintext using the public key
		cipher.init(Cipher.ENCRYPT_MODE, RSAKeyPair.getPublic());
		
		byte[] cipherDESKey = cipher.doFinal(plainDESKey);
		System.out.println("Cipher DES Key: " + new String(cipherDESKey, "UTF8"));
		
		cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		cipher.init(Cipher.DECRYPT_MODE, RSAKeyPair.getPrivate());
		plainDESKey = cipher.doFinal(cipherDESKey);
		System.out.println("Plain DES Key: " + new String(plainDESKey, "UTF8"));
		
		DESKey = new SecretKeySpec(plainDESKey, "DES");
		
		cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, DESKey);
		plainText = cipher.doFinal(cipherText);
		System.out.println("Plain Text: " + new String(plainText, "UTF8")); 
		
		} catch(Exception e) {
			System.out.println(e.getMessage());	
		}		
		
	}
}
