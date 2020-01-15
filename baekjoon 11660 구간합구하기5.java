import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 11660 구간합 구하기
 * 
 */
public class Main {
    static int[][] num;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        int N, M;
        

      
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        
        num = new int[N+1][N+1];
        map = new int[N+1][N+1];
        
        
        for(int i=1; i<N+1; i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=1; j<N+1; j++) {
        		num[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        //누적합 지도 만들기
        map[0][0] = 0;
        for(int i=1; i<N+1; i++) {
        	for(int j=1; j<N+1; j++) {
        		map[i][j] = num[i][j] + map[i][j-1] + map[i-1][j] - map[i-1][j-1];
        	}
        }
  /*      System.out.println(" - " );
       
        for(int i=0; i<N+1; i++) {
        	
        	for(int j=0; j<N+1; j++) {
        		System.out.print(map[i][j] + " ");
        	}
        	System.out.println();
        }
        
       //
        System.out.println(" - ");*/
        for(int i=1; i<M+1; i++) {
        	st = new StringTokenizer(br.readLine());
        	int x1 = Integer.parseInt(st.nextToken());
        	int y1 = Integer.parseInt(st.nextToken());
        	int x2 = Integer.parseInt(st.nextToken());
        	int y2 = Integer.parseInt(st.nextToken());
		
        	
        	
        	System.out.println(map[x2][y2] - map[x1-1][y2] - map[x2][y1-1] + map[x1-1][y1-1]);
        }
        

    }

}
