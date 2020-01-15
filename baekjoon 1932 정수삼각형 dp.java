import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 1932 정수 삼각형
 * 
 */
public class Main {
    

    public static void main(String[] args) throws IOException {
        int N;
        int[][] map;

        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        
        st = new StringTokenizer(br.readLine());
        
        map[0][0] = Integer.parseInt(st.nextToken());
        
        for(int i=1; i<N; i++) {
        	 st = new StringTokenizer(br.readLine());
        	for(int k=0; k<=i; k++) {
        		int num = Integer.parseInt(st.nextToken());
        		
        		if(k==0) {
        			map[i][k] = num + map[i-1][k];
        		}else if(k==i) {
        			map[i][k] = num + map[i-1][i-1];
        		}else {
        			//중간에서 골라야 하는 경우 
        			map[i][k] = Math.max(num + map[i-1][k-1], num+map[i-1][k]);
        			
        		}
        		
        	}
        }
        for(int i=0; i<N; i++) {
        	//for(int j=0; j<N; j++)
        	//	System.out.print(map[i][j] + " ");
        	//System.out.println();
        }
        
        int max = 0;
        for(int i=0; i<N; i++) {
        	if(map[N-1][i] > max) max = map[N-1][i];
        }
        System.out.println(max);
        

    
    }
}