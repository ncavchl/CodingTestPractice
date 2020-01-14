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

/*
백준 3176 도로네트워크

 */

public class Main {
	 static final int KMAX = 17; // 십만이라서 로그 2 해서 16단계씩 이동 하기 
	    static final int MAX = 1000001;
	    static ArrayList<Integer> adj[] = new ArrayList[MAX];
	    static Queue<Integer> queue = new LinkedList<Integer>();
	    static int parent[][] = new int[KMAX + 1][MAX];
	    static int depth[] = new int[MAX];
	    static int N, M = 0;
	    static int a, b = 0;

	    public static void main(String[] args) throws IOException {
	        // TODO Auto-generated method stub
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	        StringTokenizer st;

	        N = Integer.parseInt(br.readLine());
	        for (int i = 0; i < MAX; i++) {
	            depth[i] = -1;
	            adj[i] = new ArrayList<Integer>();
	        }
	        //트리 생성 
	        for (int i = 1; i < N; i++) {
	            st = new StringTokenizer(br.readLine());
	            a = Integer.parseInt(st.nextToken());
	            b = Integer.parseInt(st.nextToken());
	            adj[a].add(b);
	            adj[b].add(a);
	        }
	        //초기화
	        depth[1] = 0;
	        parent[0][1] = 0;
	        queue.offer(1);

	        //depth parent 돌리는 dfs // 큐 이용 
	        while (!queue.isEmpty()) {
	            int current = queue.poll();
	            for (int node : adj[current]) {
	                if (depth[node] == -1) { // 0 즉 1단꼐 부모도느랑 뎁스 저장하기 // 큐에 넣고 또 돌리기 Dfs 
	                    depth[node] = depth[current] + 1;
	                    parent[0][node] = current;
	                    queue.offer(node);
	                }
	            }
	        }

	        //나머지 2의n승  단계로 해서 채우기 ?? 2^n승 맞냐 아 뎁스차이니까 
	        for (int k = 1; k <= KMAX; k++) {//16단계
	            for (int n = 1; n <= N; n++) {
	                parent[k][n] = parent[k - 1][parent[k - 1][n]];
	            }
	        }

	        for (int i = 1; i < N+1; i++) {
	        //	System.out.print(" " + parent[2][i] + " ");
	        }
	        
	        M = Integer.parseInt(br.readLine());

	        for (int i = 0; i < M; i++) {
	            st = new StringTokenizer(br.readLine());
	            a = Integer.parseInt(st.nextToken());
	            b = Integer.parseInt(st.nextToken());
	            // System.out.println("#"+depth[a]+" "+depth[b]);

	            //높이 통b를 더 크게 만듬 
	            if (depth[a] > depth[b]) {
	                int tmp = a;
	                a = b;
	                b = tmp;
	            }

	            //뎁스 같거나 작아질때 까지 
	            for (int k = KMAX; k >= 0; k--) {
	                // System.out.println(depth[a]+" "+depth[parent[k][b]]);
	                if (depth[a] <= depth[parent[k][b]]) { // 더 밑의 노드에서 2^0 씩 뛰면서 
	                	//System.out.println(" b " + b + "  " + depth[a] + "depth[a]  " + depth[parent[k][b]] + " "  + parent[k][b]);
	                    b = parent[k][b];
	                  //  System.out.println(" - " + i + " "+ b+ " " + " k " + k);
	                }
	            }
	            System.out.println(" " + a + " "  + b + " " + i);
	            
	            //부모 노드 같을 떄까지 또는 꼭대기 올라올 때까지
	            for (int k = KMAX; k >= 0 && a != b; k--) {
	                if (parent[k][a] != parent[k][b]) {
	                     System.out.println(" " + i + "k " + k + "a" + a + " b " + b);
	                     a = parent[k][a];
	                    b = parent[k][b];
	                   
	                }
	            }
	            
	           // System.out.println( " a "  + a + " b" + b);
	            int lca = a; // 같은 라인이면 고대로 출력
	            if(a!=b) { // 같은 라인아니면 둘의 부모 출력 
	            	
	            	lca = parent[0][lca];//그냥 초기화인가 }
	            }
	            bw.write("?" + lca+"\n");         

	            // bw.write(lca(a, b)+"\n");
	        }
	        br.close(); 
	        bw.flush(); 
	        bw.close();
	    }

	    static int lca(int a, int b) {
	        // depth 가 a가 더 낮으면 더 깊은것으로 swap
	        if (depth[a] < depth[b]) {
	            int tmp = a;
	            a = b;
	            b = tmp;
	        } // 높이 차이 계산
	        int diff = depth[a] - depth[b];
	        // ex) diff = 11 // 11 = 2^3 + 2^1 + 2^0
	        int k = 0;
	        while (diff >= 1) {
	            if (diff % 2 == 1) {
	                a = parent[k][a];
	            }
	            diff /= 2;
	            k++;
	        }
	        // 위로 올라가 b와 동일한 값이 나오면 a는 LCA 임
	        if (a == b) {
	            return a;
	        }
	        // 남은 부분은 남은 값으로 점프
	        for (k = KMAX - 1; k > -1; k--) {
	            if (parent[k][a] != parent[k][b]) {
	                a = parent[k][a];
	                b = parent[k][b];
	            }
	        }
	        return parent[0][a];
	    }

	 // eof Class
}