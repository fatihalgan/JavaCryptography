package com.javasec1.publickey;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

import javax.crypto.Cipher;


/**
 * @author db2admin
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */

//Public Key cryptography using the RSA algorithm
public class PublicExample {

	public static void main(String[] args) throws Exception {
		try {
		//Check args and get plaintext
		if(args.length != 1) {
			System.err.println("Usage: java PublicExample text");
			System.exit(1);
		}
		byte[] plainText = args[0].getBytes("UTF8");
		
		//Generate an RSA key
		System.out.println("\nStart generating RSA key");
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		KeyPair key = keyGen.generateKeyPair();
		System.out.println("Finish generating RSA key");
		
		//get an RSA cipher object and print the provider
		Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
		System.out.println("\n" + cipher.getProvider().getInfo());
		
		//encrypt the plaintext using the public key
		System.out.println("\nStart Encryption");
		cipher.init(Cipher.ENCRYPT_MODE, key.getPublic());
		byte[] cipherText = cipher.doFinal(plainText);
		System.out.println("Finish Encryption: ");
		System.out.println(new String(cipherText, "UTF8"));
		
		//decrypt the ciphertext using the private key
		System.out.println("\nStart Decryption");
		cipher.init(Cipher.DECRYPT_MODE, key.getPrivate());
		byte[] newPlainText = cipher.doFinal(new String(cipherText).getBytes());
		System.out.println("Finish Decryption: ");
		System.out.println(new String(plainText, "UTF8"));
		} catch(Exception e) {
			System.out.println(e.getMessage());	
		}
	}
}
