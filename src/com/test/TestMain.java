package com.test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.KeyGenerator;

public class TestMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String value = String.valueOf(binary(1009, new StringBuilder()));
//		System.out.println(value);
//		System.out.println("the max number of zeros are: " + sequentialZeros(value));
//		System.out.println(solution(0,20,5));
//		int[] b = {1,2,};
//		System.out.println(b.length);
//		int b[] = new int[]{3, 8, 9, 7, 6};
//		for(int a : solution(b, 3)) {
//			System.out.print(a + ",");
//		}
//		System.out.println();
		SecureRandom random = new SecureRandom();
		try {
			KeyGenerator keygen = KeyGenerator.getInstance("AES");
			keygen.init(256, random);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
