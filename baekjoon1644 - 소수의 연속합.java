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
 백준  1644 소수의 연속합 
 */
public class main {
   
   static int N; //1~1000000 수의 개수 

   
   
   public static void main(String args[]) throws Exception {
      //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      //StringTokenizer st = new StringTokenizer(br.readLine()); 
      
	  Scanner sc = new Scanner(System.in);
     // N = Integer.parseInt(st.nextToken());
   
      N = sc.nextInt();
     
      ArrayList<Integer> di = new ArrayList<Integer> (); // 소수 담긴 리스트 

      int index = 0;

      if(N == 1) {
    	  System.out.println(0); // 1은 소수가 아니므로 0 출력
    	  return;
      }
      
      di = getprime(N);
   
     // System.out.println(di.size() + "-");

     int len = di.size();
     int cnt = 0;
     
     int left = 0;
     int right = 0;
     int sum = di.get(0); // 처음값으로세팅
     int size = len;
     int last = size - 1;
     while(left <= right && right < size) {
    	 if(sum >= N ) {
    		 if(sum == N) {
    			 cnt++;
    			
    			 System.out.println(left + " " + di.get(left) +" -" + right + " " + di.get(right));
    		}
    		 if(left+1<size) {
    			 
    			 sum -= di.get(left);
    		 }
    		 left++;
    		 continue;
    	 }
    	 if(sum < N) {
    		 if(right+1<size)
    			 sum += di.get(right+1);
    		 right++;
    		 continue;
    	 }
     }
     
     //  if(prime(N) == true ) cnt++;
     
     System.out.println(cnt + " ");
      
      
     
   }
   

   
   public static ArrayList getprime(int N) {
	   ArrayList<Integer> arr = new ArrayList<Integer> ();
	   int[] visit = new int[N+2];
	   
	   int index=2;
		int end = 0;
		int result = 0;
		int check = 0;
		while(index <= N) {
			//System.out.println("1while " + index );
	
			if(visit[index] == 0) {
				
				arr.add(index);
				//System.out.println("-" + index);
				
				int i=1;
				int delete =index;
				
				while(delete<= N) {
					
					if(visit[delete] == 0) {
						visit[delete] = 1;					
						delete = index * i;						
						check++;
					}
					i++;
					delete = index * i;

				}
			}
			
			index++;
		}	   
	   
	   return arr;
   }
   

}   