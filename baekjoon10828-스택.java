//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.util.StringTokenizer;
//import java.util.HashSet;
import java.util.Scanner;
//import java.util.*;

/*
백준 스택 
 */

public class Main {

	public static void main(String args[]) throws Exception {
		// BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		// int n, k;

		// n = Integer.parseInt(st.nextToken());

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();

		int[] stack = new int[n]; // �닽�옄 �떞湲� �뒪�깮
		String input; // 紐낅졊�뼱
		int top = -1;

		for (int i = 0; i < n; i++) {
			input = sc.next();

			if (input.equals("push")) {
				++top;

				stack[top] = sc.nextInt();
				
			} else if (input.equals("pop")) {
				if(top == -1) {
					System.out.println(-1);
				}
				else {
					System.out.println(stack[top]);
					top--;
				}
				
			
			} else if (input.equals("size")) {
				System.out.println((top+1));

			} else if (input.equals("empty")) {
				if(top == -1) System.out.println(1);
				else System.out.println(0);
				
			} else if(input.equals("top")) {
				if(top == -1) {
					System.out.println(-1);
				}
				else {
					System.out.println(stack[top]);
				}
			}

			// System.out.println(" " +input);
		}
		sc.nextLine();
		
		//System.out.println("");
	}
}
