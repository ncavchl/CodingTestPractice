import java.util.*;
import java.io.*;

/**
 * BOJ 14003 ���� �� �����ϴ� �κ� �� 
 * 
 */
public class Main { 
	/* * ���� �� �����ϴ� �κ� ���� 5 * https://www.acmicpc.net/problem/14003 */ 
	public static void main(String[] args) throws IOException { 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
		StringTokenizer st; 
		/* 
		* ù° �ٿ� ���� A�� ũ�� N (1 �� N �� 1,000)�� �־�����. 
		* * ��° �ٿ��� ���� A�� �̷�� �ִ� Ai�� �־�����. (1 �� Ai �� 1,000) * 
		* * ex) input * 6 * 10 20 10 30 20 50 */ 
		int N = Integer.parseInt(br.readLine()); 
		st = new StringTokenizer(br.readLine()); 
		int[] A = new int[N+1]; //���� 
		int[] L = new int[N+1]; //LIS ���� 
		int[] P = new int[N+1]; //LIS ���� ���� 
		for (int i = 1; i <= N; i++) { 
			A[i] = Integer.parseInt(st.nextToken()); 
		} 
		int size = 0; 
		for (int i = 1; i <= N; i++) { 
			System.out.println("i " + i + "  l[size] "+ L[size] + " " + A[i]);
			if (L[size] < A[i]) { 
				size++; 
				L[size] = A[i]; 
				P[i] = size; 
			} else if (L[size] == A[i]) { 
				continue; 
			} else { 
				int idx = lowerBound(L, 1, size, A[i]); 
				L[idx] = A[i]; 
				P[i] = idx;
			} 
		} 
		bw.write(size+"\n"); 
		Stack stk = new Stack(); 
		
		for (int i = N; i >= 1; i--) { 
			if(P[i] != size) continue; 
			
			if (stk.isEmpty()) { 
				stk.add(A[i]); 
				size--; 
			} else { 
				if ((int)stk.peek() > A[i]) { 
					stk.add(A[i]); 
					size--; 
				} 
			} 
		}
		while(!stk.isEmpty()) { 
			bw.write(stk.pop()+" "); 
		} 
		bw.write("\n"); 
		br.close(); 
		bw.flush(); 
		bw.close(); 
	} 
	
	static int lowerBound (int[] L, int l, int r, int val) { 
		int mid = 0; 
		int ans = 0; 
		
		while(l <= r) { 
			mid = (l+r)/2; 
			if (L[mid] < val) { 
				l = mid + 1; 
			} else { 
				ans = mid; 
				r = mid - 1; 
			}
		} 
		return ans; 
	} 

}
