import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
//import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.lang.*;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.math.BigInteger;
//import java.util.*;

/*
백준 10610 30

 
 */

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		String str = sc.nextLine();
		int[] numcnt;
		long total = 0;
		
		numcnt = new int[10]; // 각 숫자 자리수 개수 누적
		int len = str.length();
		for(int i=0; i<len; i++) {
			int num = Integer.parseInt(str.substring(i, i+1)); // 한개씩 자르기 
			numcnt[num] += 1;
			total += num;
			//System.out.println(num + " = " + numcnt[num] + " - ");
		}
			
		
		//숫자에 0 있어야되고
		//각 자리수 합이 3의 배수
		if(total % 3 != 0 || !str.contains("0")) {
			System.out.println(-1);
			return ;
		}
		//각 1~9 개수 별로  스트링에 9부터 붙이기 
		
		//StringBuilder s = new StringBuilder();
		String ans = "";
		for(int i=9; i>=0; i--) {
			while(numcnt[i] > 0) {
				//s.append(i);
				ans = ans + Integer.toString(i);
				numcnt[i]-- ;
				//System.out.println(numcnt[i]);
			}
		}
		
		System.out.println(ans);
		
	}

}