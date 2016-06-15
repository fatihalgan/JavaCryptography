package com.javasec1.messagedigest;

import java.security.*;
import javax.crypto.*;
import javax.sound.midi.SysexMessage;

//Generate a message authentication code
/**
 * @author db2admin
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class MessageAuthenticationCodeExample {

	public static void main(String[] args) throws Exception {
		
		//Check args and get plaintext
		if(args.length != 1) {
			System.err.println("Usage: java MessageAuthenticationCodeExample text ");
			System.exit(1);
		}
		byte[] plaintext = args[0].getBytes("UTF8");
		
		//get a key for the HmacMD5 algorithm
		System.out.println("\nStart generating key");
		KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
		SecretKey MD5Key = keyGen.generateKey();
		System.out.println("Finish generating key");
		
		//get a MAC object and update it with the plaintext
		Mac mac = Mac.getInstance("HmacMD5");
		mac.init(MD5Key);
		mac.update(plaintext);
		
		//print out the provider used and the MAC
		System.out.println("\n" + mac.getProvider().getInfo());
		System.out.println("\n MAC: ");
		System.out.println(new String(mac.doFinal(), "UTF8"));
		
	}
}
