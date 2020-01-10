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
import java.util.HashSet;
//import java.util.*;

/*
백준 5568 카드놓기

 */

public class Main {
	
	static HashSet<String> numbers = new HashSet<String> ();
	static String[] num;
	static boolean[] visit;
	static int n, k; // n 4~10 , k 2~4
	
	public static void main(String args[]) throws Exception {
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());

		//int n, k; // n 4~10, k 2~4

		n = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());

	    num = new String[n+1];
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			num[i] = st.nextToken();
			
			//System.out.println("-" + num[i]+ " -");
		}
		int cnt = 0;
	
		visit = new boolean[n+1];
		//똑같은걸 또 고르지만 않으면 됨.
		dfs(k, 1, "");

		System.out.println(numbers.size());
	}
	
	public static void dfs(int k, int i, String s) {
		if(k == 0) {
			numbers.add(s);
		//	System.out.println("!!");
			return ;
			//다 고름 
		}
		if(i > n){
			return ;
		}
		for(int j=1; j<=n; j++) {
			if(!visit[j]) {
				visit[j] = true;
				dfs(k-1, j, s + num[j]);
				visit[j] = false;
			}
		}
	}

	
	
	//배열에 저장해두기 
	public static int combi(int n, int k) {
		long[][] arr = new long[n+1][k+1];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<=i; j++) {
				if(j>k) continue;
				else if(j==0 || i==j) {
					arr[i][j] = 1;
				}
				else {
					arr[i][j] = arr[i-1][j] + arr[i-1][j-1];
				}
			}
		}
		return (int)arr[n][k];
		
	}
	
	public static int fac(int n){
        int i=1;
        for(int j=1; j<=n; j++){
            i = i * j;
        }
        return i;
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