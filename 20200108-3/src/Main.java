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
���� 3955 ĵ��й�
�׽�Ʈ ���̽� T 1~100
K�� �Ѻ��� C�� // 10^9
�����Ѱ��� 10^9 ������

 */

public class Main {

	public static void main(String args[]) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());

		 //int n, k;

		// n = st.nextToken();

		
		//Scanner sc = new Scanner(System.in);
		int T; // testcase����
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
			
			// ���� �Լ�  ���� 
			if(C == 1) { // ������ �Ѱ��� ����ο�+1 �� �ֹ�
				if(K+1>max) System.out.println("IMPOSSIBLE");
				else System.out.println(K+1);
				continue;
			}

			else if(K == 1) { // ����� �Ѹ��̸� 
					System.out.println(1); // 
					continue;
				}
			else if(gcd(K, C) != 1) { // ����� 1�� ���� �� �� ����
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