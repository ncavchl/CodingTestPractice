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
import java.util.Comparator;

//import java.util.*;

/*
���� 11438 LCA2
root �� 1
���� ��ȣ 1~N����  // 1~100,000
�� ��� �� 1~100,000 M ���� �־��� 


 */

public class Main {
	static int N,M;
    static int par[];
    static ArrayList<Integer>[] tree; // Ʈ��
    static int[] parent;
    static int[] depth;
    
	
	public static void main(String args[]) throws Exception {
		//File file = new File("src/testcase.txt");
		
		 //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 //StringTokenizer st = new StringTokenizer(br.readLine());

		//int n, k; // n 4~10, k 2~4

		//Scanner sc = new Scanner(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		
        N = Integer.parseInt(br.readLine());
       // M = Integer.parseInt(br.readLine());

       parent = new int[N+1];
       depth = new int[N+1];
       tree = new ArrayList[N+1];
       for(int i=1; i<N+1; i++) {
    	   parent[i] = 0;
    	   tree[i] = new ArrayList<Integer> ();
       }
       
       //Ʈ��, �θ�������迭, ���� ����迭 �ʱ�ȭ
       StringTokenizer st;
       for(int i=1; i<N; i++) {
    	   st = new StringTokenizer(br.readLine());
    	   int a = Integer.parseInt(st.nextToken());
    	   int b = Integer.parseInt(st.nextToken());
    	   tree[a].add(b);
    	   tree[b].add(a);
    	   
       }
       
       //dfs�� �θ���� ���� �����ϱ� 
       dfs(1, 1, 0); // ���۳��, ����, �θ���
       
       
       for(int i=1; i<N+1; i++) {
    	 //  System.out.println(depth[i] + " " + i);
    	   
       }
       
       
       st=new StringTokenizer(br.readLine());
       M = Integer.parseInt(st.nextToken());
       
       
       //�������� ã�� 
       for(int i=0; i<M; i++) {
    	   st=new StringTokenizer(br.readLine());
    	   int a = Integer.parseInt(st.nextToken());
    	   int b = Integer.parseInt(st.nextToken());
    	   
    	   int ad = depth[a];
    	   int bd = depth[b];
    	   
    	   while(ad>bd) {
    		   a = parent[a];
    		   ad--;
    	   }
    	   while(ad<bd) {
    		   b = parent[b];
    		   bd--;
    	   }
    	   
    	   while(a != b) {
    		   a = parent[a];
    		   b = parent[b]; 
    		   //���� �����Ƿ� ���� �ö�
    	   }
    	   
    	  System.out.println(a);
    	   
    	   
    	   
       }
       
        

    }
	
	static void dfs(int cur, int d, int p) {
		depth[cur] = d;
		parent[cur] = p;
		for(int i : tree[cur]) {//1������� ����� ���� ����Ʈ ��ŭ ��
			if(i != p) { // �θ� �ƴҶ�����?
				dfs(i, d+1, cur);
			}
			
		}
	}
	
    static class Edge {
        int from;
        int to;
        int cost;
        
        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static int find(int a) {
        if(par[a]==a) {
            return a;
        }
        return par[a]=find(par[a]);
    }

    static void union(int a, int b) {
        int g1=find(a);
        int g2=find(b);
        if(g1==g2) {
            return;
        } else {
            par[g2]=g1;
        }
    }
}