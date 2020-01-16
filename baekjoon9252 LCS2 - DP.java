import java.util.*;
import java.io.*;

/**
 * BOJ 9252 LCS2 DP
 * 
 */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer st = new StringTokenizer(br.readLine());

		String s1 = br.readLine();
		String s2 = br.readLine();

		int len1 = s1.length();
		int len2 = s2.length();

		char a[], b[], ans[];
		a = s1.toCharArray();
		b = s2.toCharArray();
		int[][] dp = new int[len1+1][len2+1];
		ans = new char[Math.max(len1, len2)];
		int cnt = 0;


		for (int i = 1; i <= len1; i++) {
			for (int j = 1; j <= len2; j++) {
				if(a[i-1]==b[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                }else{
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
			}
		}

	/*	for (int i = 0; i <= len1; i++) {
			for (int j = 0; j <= len2; j++) {
				System.out.print(dp[i][j] + " ");
			}
			System.out.println();
		}
*/
		System.out.println(dp[len1][len2]);
		int c = len1;
		int d = len2;

		while (dp[c][d] != 0) {
			
			if (dp[c][d] == dp[c][d - 1]) {
				d--;
			} else if (dp[c][d] == dp[c - 1][d]) {
				c--;
			} else if (dp[c][d] - 1 == dp[c - 1][d - 1]) {
				ans[cnt] = a[c-1];
				
				cnt++;
				c--;
				d--;
			}
			
		}

		for (int i = cnt - 1; i >= 0; i--) {
			System.out.print(ans[i]);
		}

	}

}
