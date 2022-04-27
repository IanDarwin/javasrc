package jce;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class JceSimpledemo {

	public static void main(String[] args) {
		try {
			String clearText = "This is a secret"; // multiple of 128 bits!
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			byte[] keyBytes = { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15 };
			SecretKey key = new SecretKeySpec(keyBytes,  "AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] encryptedMessage = cipher.doFinal(clearText.getBytes());
			for (byte b : encryptedMessage) {
				System.out.printf("%02x ", b);
			}
			System.out.println();
		} catch (InvalidKeyException 
				| NoSuchAlgorithmException 
				| NoSuchPaddingException 
				| IllegalBlockSizeException
				| BadPaddingException e) {
			System.out.println("Encryption didn't work! " + e);;
		}
	}

}
