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
		
		Stack<Character> stack = new Stack<Character>();
		
		
		int result = 0;
		int mul = 1;
		
		for(int i=0; i<n.length(); i++) {
			System.out.println(mul + "ss" + result);
			if(n.charAt(i) == '(') {
				stack.push('(');
				mul = mul * 2;
				
			}else if(n.charAt(i) == '[') {
				stack.push('[');
			
				mul = mul * 3;
			}
			
			else if(n.charAt(i) == ')'){
				if(stack.isEmpty() || stack.peek() != '(') {
					result = 0;
					// d���� ó�� ��¿��
				}
				else {
					if(n.charAt(i-1) == '(') result = result + mul;
					stack.pop();
					mul = mul / 2;
					
				}
				
			}
			else if(n.charAt(i) == ']'){
				if(stack.isEmpty() || stack.peek() != '[') {
					result = 0;
					// d���� ó�� ��¿��
				}
				else {
					System.out.println(mul + "m "+ result + "size" + stack.peek());
					if(n.charAt(i-1) == '[') result = result + mul;
					stack.pop();
					mul = mul / 3;
					
				}
			}
			
		}
		if(stack.isEmpty()) System.out.println(result);
		else System.out.println(0);
	}
}
// (()) �� ��� mul�� �̹� ���� �� �Ǿ������Ƿ� ������� ������ �ִ� ���� ����