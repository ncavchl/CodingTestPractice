import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 2579 계단 오르기
 * 
 */
public class Main {
    static int[] num;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        int N;
        
        long ans;
      
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
    
        num = new int[N+1];
        dp = new int[N+1];
        for(int i=1; i<N+1; i++) {
        	num[i] = Integer.parseInt(br.readLine());
        	//System.out.println(num[i]);
        	if(i==1) dp[1] = num[1];
        	if(i==2) dp[2] = num[1] + num[2];
        	if(i>=3) {//3개 안겹치게 + 마지막 무조건 밟게
        		dp[i] = Math.max(dp[i-2] + num[i],dp[i-3] + num[i-1]  + num[i]);
        	}
        }
       
        System.out.println(dp[N]);

    }
    
}
