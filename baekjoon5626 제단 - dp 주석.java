import java.util.*;
import java.io.*;

/**
 * BOJ 5626 제단
 * 
 */
public class Main {
   static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int  n = Integer.parseInt(br.readLine());
    	int list[] = new int[n+1];
    	
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	for(int i=1; i<=n; i++) list[i] = Integer.parseInt(st.nextToken());
    	
    	 //dp 최대 열 개수 10000개에 대하여 최대 높이 N/2
    	long d[][] = new long[2][5003];
    	
    	// 처음 초기화
    	if(list[1] == -1 || list[1] == 0) {
    		d[0][0] = 1;
    	}
    	else d[0][0] = 0;
    	
    	//지도 옮기면서 채우기 / 1. 값 -1 도굴된거면 합하기 최대 N/2까지  2. 0이면 첫값만 고대로 더해서 받기 3. 다른 값 지정되있으면 그 부분만 더하기
    	for(int i=2; i<=n; i++) {
    		if(list[i] == -1) {
    			d[1][0] = (d[0][0] + d[0][1])%MOD;
    			for(int j=1; j<=n/2; j++)
    				d[1][j] = (d[0][j-1] + d[0][j] + d[0][j+1])%MOD;
    		}
    		else if(list[i] == 0) {
    			d[1][0] = (d[0][0] + d[0][1])%MOD;
    		}
    		else {
                d[1][list[i]] = (d[0][list[i]-1] + d[0][list[i]] + d[0][list[i]+1])%MOD;
            }
    		
    		//0열 1열 바꾸기
    		for(int j=0; j<=n/2; j++) {
    			d[0][j] = d[1][j];
    			d[1][j] = 0;
    		}
    	}
    	System.out.println(d[0][0]);
    
    }
}
