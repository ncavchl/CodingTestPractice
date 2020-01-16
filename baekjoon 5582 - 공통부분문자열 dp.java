import java.util.*;
import java.io.*;

/**
 * BOJ 5582 공통 부분 문자열
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		// int N = Integer.parseInt(br.readLine());
		// st = new StringTokenizer(br.readLine());
		String a = br.readLine();
		String b = br.readLine();
		// .length() .charAt(i)
		int ans = 0;
		int alen = a.length();
		int blen = b.length();
		
		int dp[][] = new int[4005][4005]; // 비교배열만듬
		
		//배열로 비교 
		for(int i=0; i<alen; ++i) {
			for(int j=0; j<blen; ++j) {
				if(a.charAt(i) == b.charAt(j)) {
					dp[i+1][j+1] = dp[i][j] + 1;
					ans = Math.max(ans,  dp[i+1][j+1]);
				}
			}
		}


		System.out.println(ans);
	}

}
