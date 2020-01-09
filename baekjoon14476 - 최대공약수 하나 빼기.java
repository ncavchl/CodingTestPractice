import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.util.StringTokenizer;
//import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.lang.*;
//import java.util.*;

/*
백준  14476 최대공약수 하나 빼기
 */

public class Main {

	public static void main(String args[]) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());

		 //int n, k;

		// n = st.nextToken();

		
		//Scanner sc = new Scanner(System.in);
		int N;
		
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		
		int[] num = new int[N];
		for(int i = 0; i<N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		
		int[] left = new int[N];
		int[] right = new int[N];
		//왼쪽용 
		left[0] = num[0];
		for(int i=0; i<N-1; i++) {
			left[i+1] = gcd(left[i], num[i+1]);
		}
		
		//오른쪽용 
		right[N-1] = num[N-1];
		for(int i=N-1; i>0; i--) {
			right[i-1] = gcd(right[i], num[i-1]);		
		}
		
		for(int i=0; i<N; i++) {
			System.out.println("l" + left[i] + " r" + right[i]);
		}
		
		int index=0;
		int ansmax = 1;
		int max=1;
		for(int i=0; i<N; i++){
			
			if(i==0) max = right[1];
			if(i==N-1) max = left[N-2];
			if(i!=0 && i!= N-1) max = gcd(left[i-1], right[i+1]);
			
			if(ansmax < max) {
				ansmax = max;
				index = num[i];
			}
			System.out.println(i + " " + ansmax + " " + num[i]);
			
			
		}
		
		if(ansmax == 1 || index % ansmax == 0) System.out.println(-1);
		else System.out.println(ansmax + " " + index);
		
	//	System.out.println(a1 + "-" + a2 + "-" + b1 + "-" + b2);
		

	}
	// gcd(a,b) = gc(b,a%b)
	static int gcd(int a, int b) {
		while(b != 0) {
			int temp = a%b;
			a = b;
			b = temp;
		}
		return Math.abs(a);
	}
}