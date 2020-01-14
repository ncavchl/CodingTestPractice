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
백준 2252 줄 세우기 
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
		n = Integer.parseInt(st.nextToken()); // 백만
		m = Integer.parseInt(st.nextToken()); // 십만
		
		List<List<Integer>> list = new ArrayList<List<Integer>> (); // 이차원 리스트
		int[] indegree = new int[n+1]; // 노드 개수 
		
		for(int i=0; i<n+1; i++)
			list.add(new ArrayList<Integer> ()); // 리스트 초기화
		
		
		for(int i=0; i<m; i++) {
			//입력 받기
			st = new StringTokenizer(br.readLine());
			
			//v1 -> v2 입력받음
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			
			list.get(v1).add(v2); // 한 개씩만 이어져 있으니
			
			indegree[v2]++; // v2로 들어오는걸 개수 세줌.
		}
	
		
		//solve 함수
		sortprint(indegree, list);
		
	}
	
	public static void sortprint(int[] indegree, List<List<Integer>> list) {
		//연결된게 없는 0인 노드들 부터 찾아서 큐1에 넣기
		//출력할 노드들 큐에 넣기 
		Queue<Integer> q = new LinkedList<Integer> ();
		Queue<Integer> result = new LinkedList<Integer> ();
		
		for(int i=1; i<=n; i++) {
			if(indegree[i] == 0) {
				//q.add(i);
				q.offer(i);
			}
		}
		//System.out.println("들");
		//q에서 하나씩 빼면서 빌떄까ㅣㅈ 넣고빼고 넣고 뺴고 하기
		while(!q.isEmpty()) {
			
			//System.out.println("?");
			int node = q.poll();
			result.offer(node);
			
			for(Integer link : list.get(node)) {
				indegree[link]--;// 연결된 부분 간선 하나 삭제
				
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

	