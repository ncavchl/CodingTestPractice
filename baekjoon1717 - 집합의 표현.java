import java.util.*;
import java.io.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;

//import java.util.*;

/*
백준 1717 집합의 표현
합집합 union
찾는 함수 find 두개 만듬 
 */

public class Main {
	
	static HashSet<String> numbers = new HashSet<String> ();
	static int n, m, index; // n 4~10 , k 2~4
	static int[] num;
	
	public static void main(String args[]) throws Exception {
		//File file = new File("src/testcase.txt");
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());

		//int n, k; // n 4~10, k 2~4

		//Scanner sc = new Scanner(file);
		n = Integer.parseInt(st.nextToken()); // 백만
		m = Integer.parseInt(st.nextToken()); // 십만
		
		num = new int[n+1];
		
		for(int i=1; i<=n; i++) {
			num[i] = i;
		}
		
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int menu = Integer.parseInt(st.nextToken());
			int first = Integer.parseInt(st.nextToken());
			int second = Integer.parseInt(st.nextToken());
			
			if(menu == 0) {
				union(first, second);
			}
			else {
				if(find(first) == find(second)) {
					System.out.println("YES");
				}
				else {
					System.out.println("NO");
				}
			}
		
		}
	
		
	}
	
	public static void union(int a, int b) {
		int aa = find(a);
		int bb = find(b);
		num[bb] = aa;
		
	}
	
	public static int find(int a) {
		if(num[a] == a ) return a;
		else return num[a] = find(num[a]);
	}
	
	public static void cntsum(int result, int last, int index) {
	
		
	}
	
	
	public static class temp{
		private int x;
		
		public temp(int x) {
			this.x = x;
		}
	}
	
}

	