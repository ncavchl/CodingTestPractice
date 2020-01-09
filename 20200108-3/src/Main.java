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
백준 3955 캔디분배
테스트 케이스 T 1~100
K명 한봉지 C개 // 10^9
사탕총개수 10^9 못넘음

 */

public class Main {

	public static void main(String args[]) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());

		 //int n, k;

		// n = st.nextToken();

		
		//Scanner sc = new Scanner(System.in);
		int T; // testcase개수
		int K;
		int C;
		
		T = Integer.parseInt(st.nextToken());
		
		double max = Math.pow(10, 9) ;
		//System.out.println(max);
		int sum = 0;
		int result = -1;
		
		for(int i = 0; i<T; i++) {
			result = -1;
			st = new StringTokenizer(br.readLine());
			K = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			
			// 봉지 함수  들어가기 
			if(C == 1) { // 봉지가 한개면 사람인원+1 개 주문
				if(K+1>max) System.out.println("IMPOSSIBLE");
				else System.out.println(K+1);
				continue;
			}

			else if(K == 1) { // 사람이 한명이면 
					System.out.println(1); // 
					continue;
				}
			else if(gcd(K, C) != 1) { // 배수면 1개 남게 살 수 없음
					result = -1;
					System.out.println("IMPOSSIBLE");
					continue;
			}

			result = (int) egcd(K, C);

			if(result > max) System.out.println("IMPOSSIBLE");
			else System.out.println(result);
			
		}
		

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
	
	static long egcd(long a, long b) {
		long r, q, tA = a, t, t1=0, t2=1;
		while(b!=0) {
			
			q = a/b;
			r = a%b;
			t = t1- q*t2;
			a = b;
			b = r; 
			t1 = t2; 
			t2 = t;
			System.out.println(a +" " + b +" " +r+" "+q+" "+t);
		}
		while(t1<0) {
			System.out.println(t1 + "vv");
			t1 += tA;
			System.out.println(" + " + tA + " t1 " + t1);
		}
		return t1;
	}
	
}