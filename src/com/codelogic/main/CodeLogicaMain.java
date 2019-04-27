package com.codelogic.main;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * Contains collection of useful code for in your programs.
 * Some came from other source, and some I wrote it by myself.
 * visit {@link http://codelogica.co.nf} for detailed explanation of 
 * the codes. 
 * 
 * @author rob bitancor
 *
 */
public class CodeLogicaMain {

	public static final Logger logger = Logger.getLogger(CodeLogicaMain.class.getCanonicalName());
	
	public static void main(String[] args) {
		
		logger.setLevel(Level.INFO);
		keyGenEncryptDecrypt();
		
	}
	
	/**
	 * sample code for encryption and decryption of a string
	 * 
	 */
	public static void keyGenEncryptDecrypt() {
		
		CodeLogicaMain main = new CodeLogicaMain();
		try {
			
			SecretKey key = main.generateKeys();
			String password = "password123";
			logger.info("plain password: " + password);
			String encryptedPassword = main.encryptString(password, key);
			logger.info("encrypted password: " + encryptedPassword);
			char decryptedPasswordChars[] = main.decryptString(encryptedPassword, key);
			logger.info("decrypted password: " + new String(decryptedPasswordChars));
			
		}catch(GeneralSecurityException  | IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * generating keys
	 * credits to: learning@linkedIn: Java Learn By Example - by Julian Robichaux
	 * 
	 * @return
	 * @throws GeneralSecurityException
	 */
	public SecretKey generateKeys() throws GeneralSecurityException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128); // options are 128, 192, 256
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey;
	}
	
	/**
	 * 
	 * 
	 * Encryption
	 * credits to: learning@linkedIn: Java Learn By Example - by Julian Robichaux
	 * 
	 * @param plainText
	 * @param secretKey
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public String encryptString(String plainText, SecretKey secretKey) throws GeneralSecurityException, IOException {
		Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		
		byte[] textBytes = plainText.getBytes("UTF-8");
		byte[] encryptedBytes = cipher.doFinal(textBytes);
		
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedBytes);
		return encryptedText;
		
 	}
	
	/**
	 * decryption
	 * credits to: learning@linkedIn: Java Learn By Example - by Julian Robichaux
	 * 
	 * @param encryptedText
	 * @param secretKey
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public char[] decryptString(String encryptedText, SecretKey secretKey) throws GeneralSecurityException, IOException{
		Base64.Decoder decoder = Base64.getDecoder();
		
		
		byte[] encryptedBytes = decoder.decode(encryptedText);
		Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		
		byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
		Charset utf8 = StandardCharsets.UTF_8;
		CharBuffer decryptedChars = utf8.decode(ByteBuffer.wrap(decryptedBytes));
		return decryptedChars.array();
		
	}

	
	public static int[] solution(int[] A, int K) {
		int[] newArr = new int[A.length];
		int counter = 1;
		while(K>=1) {
			int lastValue = A[A.length-1];
			newArr[0] = lastValue;
			for(int i=0;i<A.length;i++) {
				if(counter == A.length) break;
				newArr[counter++] = A[i];
			}
			counter = 1;
			A = Arrays.copyOf(newArr, newArr.length);
			K--;
		}
		
		return A;
	}
	
	
	public static int solution(int X, int Y, int D) {
		int counter = 0;
		int progress= X;
		
		int totalDistance = Y-X;
		
		int v = (Y-X) / D; // 4
		int r = (Y-X) % D;
		if(r > 0) {
			v+=1;
		}
		
		System.out.println("Total distance:" + totalDistance);
		System.out.println("goal:" + Y);
		System.out.println("v:" +v);
			
		return counter;
	}
	public static int binary(int num, StringBuilder sb) {
		if(num < 1) {
			
			return Integer.parseInt(sb.reverse().toString());
		}
		
		int a = num / 2;
		int b = num % 2;
		sb.append(b);
		return binary(a,sb);
	}
	
	public static int sequentialZeros(String binary) {
		char nums[] = binary.toCharArray();
		boolean isSequentialStart = false;
		int counter = 0;
		int countedValue = 0;
		for(int i = 0; i< nums.length;i++) {
			
			if(nums[i] == '1') {
				if(countedValue <= counter)
					countedValue = ++counter;
				
				if(!isSequentialStart && counter > 1) {
					isSequentialStart = true;
				}
				
			}else {
				
				isSequentialStart = false;
				counter = 0;
			}
		}
		
		return countedValue;
	}
}
