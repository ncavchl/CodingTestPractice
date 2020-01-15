import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 11049 행렬 곱셈 순서 
 * 
 */
public class Main {
    static int[] num;
    static int[] d;
    static int[][] dp;
    static final int MAX_VALUE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        int N, M;
        long ans = 1;
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 500개
       
	    num = new int[N+1];
	    dp = new int[N+1][N+1];
	    
	    for(int i=0; i<N; i++) {
	    	st = new StringTokenizer(br.readLine());
	    	num[i] = Integer.parseInt(st.nextToken());
	    	num[i+1] = Integer.parseInt(st.nextToken());
	    	Arrays.fill(dp[i], MAX_VALUE); // 최대값으로 채워두기 
	    }

	    
	    
        System.out.println(solve(0, N-1));

    }
    static int solve(int s, int e) {
    	if(s == e) return 0; // 행렬 1개일땐 0 출력?
    	if(dp[s][e] != MAX_VALUE) return dp[s][e];
    	
    	for(int i=s; i<e; i++) {
    		dp[s][e] = Math.min(dp[s][e], 
    				solve(s, i) + solve(i+1, e) + num[s]*num[i+1]*num[e+1]);
    	}
    	
    	return dp[s][e];
    }
}
