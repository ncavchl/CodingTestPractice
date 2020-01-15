import java.util.*;
import java.io.*;
import java.util.StringTokenizer;
//import java.util.HashSet;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

//import java.util.*;

/**
 * BOJ 3830 교수님은 기다리지 않는다 union find
 */

public class Main {
	static class Node{
		int next, w;
		Node(int a, int b){
			this.next = a;
			this.w = b;
		}
	}
	static int N, M; //
	static int[] parent;
	static boolean[] visit;
	static int[] dist;
	static ArrayList<Node>[] arrayList;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int[][] map;
		int MAX = 1000000;

		while (true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if (N == 0 && M == 0)
				break;
			map = new int[N + 1][N + 1];
			// Arrays.fill(map, 1000000); //MAX 로 채워두기
			for (int i = 1; i < N + 1; i++)
				for (int j = 1; j < N + 1; j++)
					map[i][j] = MAX;


		}

	}
	
	static int find(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = find(parent[x]);
	}
	
	static void union(int a, int b) {
		int A = find(a);
		int B = find(b);
		
		parent[B] = A;
	}
	
	static void dfs(int cur) {
		for(Node n : arrayList[cur]) {
			if(!visit[n.next]) {
				visit[n.next] = true;
				dist[n.next] = dist[cur] + n.w;
				dfs(n.next);
			}
		}
	}
}
