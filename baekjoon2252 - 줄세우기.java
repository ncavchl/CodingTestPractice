import java.util.*;
import java.io.*;

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
import java.util.Arrays;

//import java.util.*;

/*
���� 2252 �� ����� 
 */

public class Main {
	
	static HashSet<String> numbers = new HashSet<String> ();
	static int n, m, index; // n 4~10 , k 2~4

	
	public static void main(String args[]) throws Exception {
		//File file = new File("src/testcase.txt");
		
		 BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 StringTokenizer st = new StringTokenizer(br.readLine());

		//int n, k; // n 4~10, k 2~4

		//Scanner sc = new Scanner(file);
		n = Integer.parseInt(st.nextToken()); // �鸸
		m = Integer.parseInt(st.nextToken()); // �ʸ�
		
		List<List<Integer>> list = new ArrayList<List<Integer>> (); // ������ ����Ʈ
		int[] indegree = new int[n+1]; // ��� ���� 
		
		for(int i=0; i<n+1; i++)
			list.add(new ArrayList<Integer> ()); // ����Ʈ �ʱ�ȭ
		
		
		for(int i=0; i<m; i++) {
			//�Է� �ޱ�
			st = new StringTokenizer(br.readLine());
			
			//v1 -> v2 �Է¹���
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			list.get(v1).add(v2); // �� ������ �̾��� ������
			
			indegree[v2]++; // v2�� �����°� ���� ����.
		}
	
		
		//solve �Լ�
		sortprint(indegree, list);
		
	}
	
	public static void sortprint(int[] indegree, List<List<Integer>> list) {
		//����Ȱ� ���� 0�� ���� ���� ã�Ƽ� ť1�� �ֱ�
		//����� ���� ť�� �ֱ� 
		Queue<Integer> q = new LinkedList<Integer> ();
		Queue<Integer> result = new LinkedList<Integer> ();
		
		for(int i=1; i<=n; i++) {
			if(indegree[i] == 0) {
				//q.add(i);
				q.offer(i);
			}
		}
		//System.out.println("��");
		//q���� �ϳ��� ���鼭 �􋚱�Ӥ� �ְ��� �ְ� ���� �ϱ�
		while(!q.isEmpty()) {
			
			//System.out.println("?");
			int node = q.poll();
			result.offer(node);
			
			for(Integer link : list.get(node)) {
				indegree[link]--;// ����� �κ� ���� �ϳ� ����
				
				if(indegree[link] == 0)
					q.offer(link);
			}
			
		}
		
		while(!result.isEmpty()) {
			
			System.out.println(result.poll() + " ");
		}
		
	}
	
	public static class temp{
		private int x;
		
		public temp(int x) {
			this.x = x;
		}
	}
	
}

	