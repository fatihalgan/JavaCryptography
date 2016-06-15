package com.javasec1.messagedigest;

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

 //Generate a Message Digest
public class MessageDigestExample {

	public static void main(String[] args) throws Exception {
		
		//check args and get plaintext
		if(args.length != 1) {
			System.err.println("Usage: java MessageDigestExample text");
			System.exit(1);
		}
		
		byte[] plaintext = args[0].getBytes("UTF8");
		
		//get a message digest object using the MD5 algorithm
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		
		//print out the provider used
		System.out.println("\n" + messageDigest.getProvider().getInfo());
		
		//calculate the digest and print it out
		messageDigest.update(plaintext);
		System.out.println("\nDigest: ");
		System.out.println(new String(messageDigest.digest(), "UTF8"));
	}
}
