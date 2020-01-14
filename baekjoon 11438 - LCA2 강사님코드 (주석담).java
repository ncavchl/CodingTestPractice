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
���� 3176 ���γ�Ʈ��ũ

 */

public class Main {
	 static final int KMAX = 17; // �ʸ��̶� �α� 2 �ؼ� 16�ܰ辿 �̵� �ϱ� 
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
	        //Ʈ�� ���� 
	        for (int i = 1; i < N; i++) {
	            st = new StringTokenizer(br.readLine());
	            a = Integer.parseInt(st.nextToken());
	            b = Integer.parseInt(st.nextToken());
	            adj[a].add(b);
	            adj[b].add(a);
	        }
	        //�ʱ�ȭ
	        depth[1] = 0;
	        parent[0][1] = 0;
	        queue.offer(1);

	        //depth parent ������ dfs // ť �̿� 
	        while (!queue.isEmpty()) {
	            int current = queue.poll();
	            for (int node : adj[current]) {
	                if (depth[node] == -1) { // 0 �� 1�ܲ� �θ𵵴��� ���� �����ϱ� // ť�� �ְ� �� ������ Dfs 
	                    depth[node] = depth[current] + 1;
	                    parent[0][node] = current;
	                    queue.offer(node);
	                }
	            }
	        }

	        //������ 2��n��  �ܰ�� �ؼ� ä��� ?? 2^n�� �³� �� �������̴ϱ� 
	        for (int k = 1; k <= KMAX; k++) {//16�ܰ�
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

	            //���� ��b�� �� ũ�� ���� 
	            if (depth[a] > depth[b]) {
	                int tmp = a;
	                a = b;
	                b = tmp;
	            }

	            //���� ���ų� �۾����� ���� 
	            for (int k = KMAX; k >= 0; k--) {
	                // System.out.println(depth[a]+" "+depth[parent[k][b]]);
	                if (depth[a] <= depth[parent[k][b]]) { // �� ���� ��忡�� 2^0 �� �ٸ鼭 
	                	//System.out.println(" b " + b + "  " + depth[a] + "depth[a]  " + depth[parent[k][b]] + " "  + parent[k][b]);
	                    b = parent[k][b];
	                  //  System.out.println(" - " + i + " "+ b+ " " + " k " + k);
	                }
	            }
	            System.out.println(" " + a + " "  + b + " " + i);
	            
	            //�θ� ��� ���� ������ �Ǵ� ����� �ö�� ������
	            for (int k = KMAX; k >= 0 && a != b; k--) {
	                if (parent[k][a] != parent[k][b]) {
	                     System.out.println(" " + i + "k " + k + "a" + a + " b " + b);
	                     a = parent[k][a];
	                    b = parent[k][b];
	                   
	                }
	            }
	            
	           // System.out.println( " a "  + a + " b" + b);
	            int lca = a; // ���� �����̸� ���� ���
	            if(a!=b) { // ���� ���ξƴϸ� ���� �θ� ��� 
	            	
	            	lca = parent[0][lca];//�׳� �ʱ�ȭ�ΰ� }
	            }
	            bw.write("?" + lca+"\n");         

	            // bw.write(lca(a, b)+"\n");
	        }
	        br.close(); 
	        bw.flush(); 
	        bw.close();
	    }

	    static int lca(int a, int b) {
	        // depth �� a�� �� ������ �� ���������� swap
	        if (depth[a] < depth[b]) {
	            int tmp = a;
	            a = b;
	            b = tmp;
	        } // ���� ���� ���
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
	        // ���� �ö� b�� ������ ���� ������ a�� LCA ��
	        if (a == b) {
	            return a;
	        }
	        // ���� �κ��� ���� ������ ����
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