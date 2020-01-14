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
백준2458 키순서 
플로이드 와샬
 */

public class Main {
	static int N, M;
	static boolean[][] dist;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M =Integer.parseInt(st.nextToken());
        
        dist = new boolean[N+1][N+1];
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a = Integer.parseInt(st.nextToken()); // a<b
        	int b = Integer.parseInt(st.nextToken());
        	dist[a][b] = true; // 키 비교 여부
        }
        //연결된거 있는지 찾는 
        //mid start end
        for(int k = 1; k<=N; k++) {
        	for(int i=1; i<=N; i++) {
        		for(int j=1; j<=N; j++) {
        			if(dist[i][k] && dist[k][j]) dist[i][j] = true;
        		}
        	}
        }
        
        
        //
        int ans=0;
        for(int i=1; i<=N; i++) {
        	int cnt = 0;
        	for(int j=1; j<=N; j++) {
        		if(i != j) {
        			if(dist[i][j] || dist[j][i]) cnt++;
        		}	
        	}
        	if(cnt == N-1) ans++;
        }
        System.out.println(ans);
        
        
    }
}