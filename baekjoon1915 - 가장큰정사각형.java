import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 1915 가장 큰 정사각형
 * 
 */
public class Main {
    static int[][] map;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        int N, M;
      
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    
        map = new int[N+1][M+1];
        d = new int[N+1][M+1];
        
        int ans = 0;
        //지도생성
        for(int i=1; i<N+1; i++) {
        	char[] line = br.readLine().toCharArray();
        	for(int j=1; j<M+1; j++) {
        		map[i][j] = line[j-1] - '0';
        		
        		if(map[i][j] == 1) {
        			d[i][j] = min(d[i-1][j-1], d[i-1][j], d[i][j-1]) + 1;
        			if(ans < d[i][j]) ans = d[i][j];
        		}
        		
        	}
        }

        
     
        
        System.out.println(ans * ans);

    }
    static int min(int a, int b, int c) {
        a=a<b?a:b;
        return a<c?a:c;
    }
    
}
