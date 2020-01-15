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
 * BOJ 14003 가장 긴 증가하는 부분 수 
 * 
 */
public class Main {
    static int[] num;
    static int[] d;
    static int[][] dp; // 1~n 이동 // 중간도 적어야 하므로 
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
	    	//앞의 뒤와 뒤의 앞 번호 같으므로 
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
    		System.out.println(s + "s " + e + "e " + dp[s][e]);
    	}
    	
    	return dp[s][e];
    }
    
    
    /*
            Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        a = new int[N+1][2];
        d = new int[N+1][N+1];
        for (int i=1; i<=N; i++) {
            a[i][0] = sc.nextInt();
            a[i][1] = sc.nextInt();
        }

        int m, v;
        for (int i=1; i<N; i++) {
            for (int j=1; j<N; j++) {
                int f=j;
                int t=j+i;
                if (t>N) break;

                m = 1<<30;
                for (int k=f+1; k<=t; k++) {
                    v = d[f][k-1]+d[k][t]+a[f][0]*a[k][0]*a[t][1];
                    if (v < m) m = v;
                }
                d[f][t] = m;
            }
        }

        System.out.println( d[1][N] );
     */
}
