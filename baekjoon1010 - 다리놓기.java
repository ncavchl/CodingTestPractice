import java.util.Scanner; 
import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.util.StringTokenizer;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class main { 
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(in.readLine());
		int i, j, k, n, m, d[][];
		StringTokenizer st;
		while(t-->0){
			st = new StringTokenizer(in.readLine());
			n = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			d = new int[n+1][m+1]; //다리 수 맞추려고
			//d(i, j) = d(i-1, j-1) + d(i-1,j-2) + ... + d(i-1, i-1);
			//1, n 인 경우 는 다 1로 세팅
			for(i=0; i<=m; i++) d[1][i] = i;
			
			for(i=2; i<=n; i++) {
				for(j=i; j<=m; j++) {
					for(k=j; k>=i; k--)
						d[i][j] = d[i][j] + d[i-1][k-1];
				}
			}
			System.out.println(d[n][m]);
		}
		
		in.close();
	}
}