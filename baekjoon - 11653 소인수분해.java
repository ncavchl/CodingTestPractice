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

//import java.util.*;

/*
 백준  11653 소인수분해 
 */
public class main {
   
   static int N; //1~1000000 수의 개수 

   
   
   public static void main(String args[]) throws Exception {
      //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      //StringTokenizer st = new StringTokenizer(br.readLine()); 
      
	  Scanner sc = new Scanner(System.in);
     // N = Integer.parseInt(st.nextToken());
   
      N = sc.nextInt();
     
     // System.out.println(prime(15) + " " + prime(17));
      int[] divide = new int[N];

      /*
      for(int i=2; i<N; i++) {
    	  if(prime(i) == true) {
    		  System.out.println("소수" + i);
    	  }
      }*/
      
      int i=2;
      int mod = N;
     while(mod != 1) {
    	  
    	  //나누고 출력하고 저장 for
    	  while((mod) % i == 0) {
    		  mod = mod/i;
    		  System.out.println(i);
    	  }
    	 
    	  //i 증가
    	  
    	  while(true) {
    		  i++;
    		  if(mod % i != 0) break;
    		  if(prime(i)==true) break;
    	  }
    	  
      }
      
      
     
   }
   
   public static boolean prime(int a) {
	   //int i=2;
	   int n = a;
	   if(n==2) return true;
	   for(int i=2; i<=n/2; i++) {
		   
		   if(n%i == 0) {
			   return false;
		   }
	   }
	   return true;
	   
   }
   
   public static int gcd(int a, int b) {
	   if((a%b) == 0) return b;
	   else return  gcd(b, a%b);
   }
}   