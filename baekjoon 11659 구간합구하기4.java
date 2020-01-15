import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * BOJ 11659 구간합 구하기4
 * 
 */
public class Main {
    static int[] num;

    public static void main(String[] args) throws IOException {
        int N, M;
        int[] map;

      
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        
        num = new int[N+1];
        map = new int[N+1];
        
        for(int i=1; i<N+1; i++) {
        	num[i] = Integer.parseInt(st.nextToken());
        }
        map[0] = 0;
       for(int i=1; i<N+1; i++) {
    	   map[i] = map[i-1] + num[i];
    	   //System.out.print(map[i] + " " );
       }
     /*  
       for(int i=1; i<N+1; i++) {
    	   for(int j=i; j<N+1; j++) {
    		   //0은 안들어옴
    		   System.out.print(map[i][j]);
    	   }
    	   System.out.println();
       }*/
        
        for(int i=0; i<M; i++) {
        	st = new StringTokenizer(br.readLine());
        	int a= Integer.parseInt(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	
        	System.out.println(map[b] - map[a-1]);
        }
        

    }

}
