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
 ����  2504 ��ȣ�� ��
 */

public class Main {

	public static void main(String args[]) throws Exception {
		 //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 //StringTokenizer st = new StringTokenizer(br.readLine());

		 //int n, k;

		// n = st.nextToken();

		
		Scanner sc = new Scanner(System.in);
		String n = sc.next();
		
		Stack<String> stack = new Stack<String>();
		
		
		
		int result = 0;

		
		for(int i=0; i<n.length(); i++) {
			System.out.println("ss" + result + n.charAt(i));
			if(n.charAt(i) == '(') {
				stack.push("(");

				
			}else if(n.charAt(i) == '[') {
				stack.push("[");

			}
			
			else if(n.charAt(i) == ')'){
				if(stack.isEmpty() || stack.peek() != "(") {
					result = 0;
					// d���� ó�� ��¿��
				}
				else {

				}
				
			}
			else if(n.charAt(i) == ']'){
				if(stack.isEmpty() || stack.peek() != "[") {
					result = 0;
					// d���� ó�� ��¿��
				}
				else {

					
				}
			}
			
		}
		if(stack.isEmpty()) System.out.println(result);
		else System.out.println(0);
		
		String ee = "]";
		int d = Integer.parseInt(ee);
		System.out.println(d);
		
	}
}
// (()) �� ��� mul�� �̹� ���� �� �Ǿ������Ƿ� ������� ������ �ִ� ���� ����