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
백준 1256 사전
a자리고르는조합 = z자리 고르는 조합 

 */

public class Main {
	
	static HashSet<String> numbers = new HashSet<String> ();
	static String[] num;
	static boolean[] visit;
	static int n, k, index; // n 4~10 , k 2~4
	static String answer = "";
	static long[][] arr;
	static int left;
	
	public static void main(String args[]) throws Exception {
		 //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 //StringTokenizer st = new StringTokenizer(br.readLine());

		//int n, k; // n 4~10, k 2~4

		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		k = sc.nextInt();
		index = sc.nextInt();
		
		left = n+k;
		
		ArrayList<String> ans = new ArrayList<String> ();
		//System.out.println((long)Math.pow(10, 10));

		//십억이 나오는 경우 검색 안해도 됨.  십억 이상의 값은 십억외의 수(십억)을 처리해두기 
		arr = new long[left+1][left+1];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<=i; j++) {
				if(j>i) continue;
				else if(j==0 || i==j) {
					arr[i][j] = 1;
				}
				else {
					arr[i][j] = (int) arr[i-1][j] + arr[i-1][j-1];
									}
				//if(i==3 && j==3) System.out.println("   여기 " + arr[i][j]);
				if(arr[i][j] > (long) Math.pow(10, 9) || arr[i][j] < 0) arr[i][j] = (long) (Math.pow(10, 9) + 1);

			}
		}
		
	/*	for(int i=0; i<left+1; i++) {
			for(int j=0; j<left+1; j++) {
				if(i>=j)
					System.out.println(arr[i][j]+ " i " + i + "- j " + j );
			}
				
		}*/
		
		//위 배열에 저장해둠 
		if(index > arr[left][n]) System.out.println(-1);
		else {
			find(index, n, n+k, n);
			System.out.println(answer);
		}
		
	}
	//a 부터 시작 
	public static void find(long index, int a, int size, int acnt) {
		//System.out.println("find" +index + " " + a + " " + size + " " + acnt + " " + " " +answer);
		
		if(a == 0 || a==size) {//끝 인덱스 도착
			//System.out.println(acnt + " " + (n+k) + " " + (n-1) + " " + arr[n+k][n-1]);
			if(acnt == 0) {
				for(int i=0; i<size; i++)
					answer = answer + "z";
				return ;
			}
			else {
				for(int i=0; i<acnt; i++)
					answer = answer + "a";
				return ;
			}
			
		}
	//	System.out.println("?" + (size-1) + " " + (a-1));
		if(index <= (long)arr[size-1][a-1]) {
			//a 고른 경우
			
			answer = answer + "a";

			find(index, (a-1), (size-1), acnt-1);
			//System.out.println("--");
			
			
		}
		else {
			//z 고른 경우
			answer = answer + "z";
			find((long)((index - (long)arr[size-1][a-1])), a, (size-1), acnt);
		}
		
	}
	
	public static void dfs(int k, int i, String s) {
		System.out.println("dfs" + k + " " + i + " " + s);
		if(k == 0) {
			numbers.add(s);
			//System.out.println("!!");
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