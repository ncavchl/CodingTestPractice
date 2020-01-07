import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

/*
백준 2096 내려가기
 */

public class Main {
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N; // N개 줄 500000
	

		N = Integer.parseInt(st.nextToken());

		map = new int[N+1][3];
		int[][] plus = new int[N+1][3];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
			// System.out.println(arr[i]);
		}
/*
		for (int i = 1; i <= N; i++) {
			for(int j=0; j<3; j++)
				System.out.print(map[i][j]);
			System.out.println();
		}*/
		
		int min=0, max = 0;
		plus[0][0] = 0;
		plus[0][1] = 0;
		plus[0][2] = 0;
		
		//max
		for(int i=1; i<=N; i++) {
			plus[i][0] = max(plus[i-1][0], plus[i-1][1]) + map[i][0];
			plus[i][1] = max(plus[i][0] - map[i][0], plus[i-1][2]) + map[i][1];
			plus[i][2] = max(plus[i-1][1], plus[i-1][2]) + map[i][2];
		}
		max = max(plus[N][0], max(plus[N][1], plus[N][2]));
		

		//min
		for(int i=1; i<=N; i++) {
			plus[i][0] = min(plus[i-1][0], plus[i-1][1]) + map[i][0];
			plus[i][1] = min(plus[i][0] - map[i][0], plus[i-1][2]) + map[i][1];
			plus[i][2] = min(plus[i-1][1], plus[i-1][2]) + map[i][2];
	
/*			for (int k = 1; k <= N; k++) {
				for(int j=0; j<3; j++)
					System.out.print(plus[k][j]+ " ");
				System.out.println();
			}*/
			
		}
		min = min(plus[N][0], min(plus[N][1], plus[N][2]));

		
		System.out.println(max + " " + min);
		br.close();
	}
	
	public static int min(int a, int b) {
		if(a>b) return b;
		else return a;
	}
	
	public static int max(int a, int b) {
		if(a>b) return a;
		else return b;
	}
}