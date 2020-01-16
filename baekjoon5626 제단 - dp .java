import java.util.*;
import java.io.*;

/**
 * BOJ 5626 Á¦´Ü
 * 
 */
public class Main {
    static final int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());
        int list[] = new int[n+1];

        StringTokenizer st = new StringTokenizer(br.readLine().trim()); 
        for ( int i=1; i<=n; i++ ) list[i] = Integer.parseInt(st.nextToken());

        long d[][] = new long[2][5003];
        d[0][0] = list[1]==-1?1:list[1]==0?1:0;
        for ( int i=2; i<=n; i++ ) {
            if ( list[i] == -1 ) {
                d[1][0] = (d[0][0] + d[0][1])%MOD;
                for ( int j=1; j<=n/2; j++ ) {
                    d[1][j] = (d[0][j-1] + d[0][j] + d[0][j+1])%MOD;
                }
            } else if ( list[i] == 0 ) {
                d[1][0] = (d[0][0] + d[0][1])%MOD;
            } else {
                d[1][list[i]] = (d[0][list[i]-1] + d[0][list[i]] + d[0][list[i]+1])%MOD;
            }

            for ( int j=0; j<=n/2; j++ ) {
                d[0][j] = d[1][j];
                d[1][j] = 0;
            }
        }

        System.out.println(d[0][0]);
    }
}
