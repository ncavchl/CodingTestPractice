import java.util.*;
import java.io.*;

/**
 * BOJ 5626 Á¦´Ü
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N,K;
		N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] num = new int[N+1];
		for(int i=1; i<=N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		


		int[][] dp = new int[N+1][N+1];




	}

}
