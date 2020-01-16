import java.util.*;
import java.io.*;

/**
 * BOJ 11062 카드 게임 
 * 부분합 - 연속합이므로 구간 sum 구하는 방식으로 
 */
public class Main {
	static int[][] d;
	static int[] num;
	static int[] sum;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
       
        
        while(n>0) {
	        StringTokenizer st = new StringTokenizer(br.readLine()); 
	        int m = Integer.parseInt(st.nextToken());
	        num = new int[m+1];
	        st = new StringTokenizer(br.readLine()); 
	        sum  = new int[m+1];
	        for ( int i=1; i<=m; i++ ) {
	        	
	        	num[i] = Integer.parseInt(st.nextToken());
	        	sum[i] = num[i] + sum[i-1];
	        }
	        //System.out.println("sum "+ sum);
	        
	        //함수
	        int ans=0;
        
	        //근우가 얻게되는 최고점수dp
	        d = new int[m+1][m+1];
	        //1. 배열 
	        //2. 초기값 - i==j num[i]
	        //3. 점화식    --   왼쪽or 오른쪽 고르면 전체 점수에서 상대가 나머지에서고른 최고점수 
	        //D(1,4) -> max(12-D(2,4), 12-D(1,3))
	        
	        //D(i,j) -> max( sum - D(i+1, j) , sum - D(i, j-1))
	        
	        ans = find(1, m);
	        
	        
        
        	System.out.println(ans);
        	
        	n--;
        }
        
    }
    //구간 합 이용하기 
    static int find(int s, int e) {
    	//이미 한번 계산한 값
    	if(d[s][e] != 0) return d[s][e];
    	if(s==e) {
    		//d[s][e] = num[s];
    		return num[s];
    	}else {
    		int summ = sum[e] - sum[s-1];
    		d[s][e] = Math.max(summ - find(s+1, e), summ - find(s, e-1)); // 왼쪽 고른경우랑 오른쪽 고른경우 중 큰거
    	}
    	return d[s][e];
    }
}
