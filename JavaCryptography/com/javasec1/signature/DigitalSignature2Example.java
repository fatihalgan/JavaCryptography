package com.javasec1.signature;

import java.security.*;
import javax.crypto.*;
/**
 * @author db2admin
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DigitalSignature2Example {

	/**
	 * This example uses the Digital Signature features to generate and verify
	 * a signature.
	 */
	
	

	public static void main(String[] args) throws Exception {
		//check args and get plain text
		if(args.length != 1) {
			System.err.println("Usage: java DigitalSignatureExample text");
			System.exit(1);
		}
		byte[] plainText = args[0].getBytes("UTF8");
		
		//generate an RSA key pair
		System.out.println("\nStart generating RSA key");
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(512);
		KeyPair key = keyGen.generateKeyPair();
		System.out.println("Finish generating RSA key");
		
		//Get a Signature object using the MD5 and RSA combo
		//and sign the plain text with the private key, listing
		//the provider along the way
		Signature sig = Signature.getInstance("MD5WithRSA");
		sig.initSign(key.getPrivate());
		sig.update(plainText);
		byte[] signature = sig.sign();
		System.out.println(sig.getProvider().getInfo());
		System.out.println("\nSignature");
		System.out.println(new String(signature, "UTF8"));
		
		//verify the signature with the public key
		System.out.println("\nStart signature verification");
		sig.initVerify(key.getPublic());
		sig.update(plainText);
		try {
			if(sig.verify(signature)) System.out.println("Signature verified");
			else System.out.println("Signature failed");
		} catch(SignatureException se) {
			System.out.println("Signature Failed");
		}
	}
}
