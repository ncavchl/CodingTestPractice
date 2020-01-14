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
���� 1922 ��Ʈ��ũ ����  

MST �ּ� ���д� Ʈ�� // ��尡 ��� ������ֵ� ����ŬX
1.������ �ּұ������� �������� ����
2.start node�� root node�� ���� ������ �����ϱ�  / cycle�Ȼ������ Ȯ���ϱ� 


-find / union
 



 */

public class Main {
	static int N,M;
    static int par[];
	
	public static void main(String args[]) throws Exception {
		//File file = new File("src/testcase.txt");
		
		 //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		 //StringTokenizer st = new StringTokenizer(br.readLine());

		//int n, k; // n 4~10, k 2~4

		//Scanner sc = new Scanner(file);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        int from,to,cost;
        Edge edge[] = new Edge[M];
        for(int i=0;i<M;i++) {
            StringTokenizer stk = new StringTokenizer(br.readLine());
            from = Integer.parseInt(stk.nextToken());
            to = Integer.parseInt(stk.nextToken());
            cost = Integer.parseInt(stk.nextToken());
            edge[i]= new Edge(from,to,cost); // ���� ����Ʈ�� ������ 
        }

        long Answer = 0;

        par = new int[N+1]; // ����Ȱ� üũ�� 
        for(int i=0;i<=N;i++) {
            par[i]=i;
        }

        //�ּ� �������� ���� ���� 
        Arrays.sort(edge, new Comparator<Edge>() {
            public int compare(Edge a, Edge b) {
                return a.cost-b.cost; // ������������ ���� ���� 
            }
        });
        
        
        //
        for(int i=0;i<M;i++) {
            if(find(edge[i].from)!=find(edge[i].to)) {// ����Ŭ ����� ���� Ȯ�� 
                union(edge[i].from, edge[i].to);
                Answer+=edge[i].cost;   
            }
        }

        System.out.println(Answer);

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