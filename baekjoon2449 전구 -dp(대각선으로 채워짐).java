import java.util.*;
import java.io.*;

/**
 * BOJ 2449 전구
 * 전구색을 같게 하는데 드는 횟수 
 * D[i][N]
 Dij = Di~k + Dk+1~j + Ai랑 Ak+1이 같으면 ? 0 : 1
 D11 = 0 // Dii 0
 
 */
public class Main {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n, k;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        int[] num = new int[n+1];
        int[][] d = new int[n+1][n+1];
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<n+1; i++) {
        	num[i] = Integer.parseInt(st.nextToken());
        }
        //애초에 0으로 세팅됨
        int max = Integer.MAX_VALUE;
        //전체 훑어야함.
        //개수별로
        for(int l=1; l<=n; l++) {
        	for(int i=1; i<=n-l; i++) {
        		int j = i+l; // 끝번
        		d[i][j] = max;
        		for(int p=i; p<j; p++) {
        			int c = d[i][p] + d[p+1][j] + (num[i] == num[p+1]? 0:1); // 나눈거에서 각 첫 값이 같나 틀리나에 따라 변경 비용 발생
        			d[i][j] = Math.min(c,  d[i][j]);
        		}
        	}
        }
        
        
        
        System.out.println(d[1][n]);
        
    }
}
