import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

//import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;

//import java.util.*;

/*
 백준  1837 골드바흐의 추측
 */
public class main {
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
		//index = sc.nextInt();
		
		left = n+k;
		
		ArrayList<String> ans = new ArrayList<String> ();
		

		//십억이 나오는 경우 검색 안해도 됨.  십억 이상의 값은 십억외의 수(십억)을 처리해두기 
		arr = new long[left+1][left+1];
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<=i; j++) {
				if(j>i) continue;
				else if(j==0 || i==j) {
					arr[i][j] = 1;
				}
				else {
					arr[i][j] = arr[i-1][j] + arr[i-1][j-1];
					if(arr[i][j] > Math.pow(10, 10)) arr[i][j] = (int) (Math.pow(10, 10) + 1);
				}
				//if(i==3 && j==3) System.out.println("   여기 " + arr[i][j]);
			}
		}
		
		for(int i=0; i<left+1; i++) {
			for(int j=0; j<left+1; j++) {
				if(i>=j)
					System.out.println(arr[i][j]+ " i " + i + "- j " + j );
			}
				
		}
    }

}