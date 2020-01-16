import java.util.*;
import java.io.*;

/**
 * BOJ 7579 �� 
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] mem = new int[N];
		int[] cost = new int[N];
		int sumcost=0;
		
		for(int i=0; i<N; i++) {
			mem[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			cost[i] = Integer.parseInt(st.nextToken());
			sumcost += cost[i];
		}
		
		int ans = 0;
		//d[N][C] = i���� �۱��� c �� ����� �鿩�� Ȯ���� �޸�  
		int dp[][] = new int[sumcost+1][N+1]; // �񱳹迭����
		int[] d;
		d = new int[sumcost+1];
		for(int i=0; i<N; i++) {
			for(int j=sumcost; j>=cost[i]; j--) {
				d[j] = Math.max(d[j], d[j-cost[i]] + mem[i]);
			}
		}
		
		int m;
		for(m=0; m<sumcost && d[m]<M; m++);
		System.out.println(m);
		
		
		
		System.out.println(ans);
	}

}
