import java.util.*;
import java.io.*;

/**
 * BOJ 7579 앱 
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
		//d[N][C] = i번쨰 앱까지 c 의 비용을 들여서 확보한 메모리  
		int dp[][] = new int[sumcost+1][N+1]; // 비교배열만듬
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
