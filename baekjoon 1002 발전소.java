import java.util.*;
import java.io.*;

/**
 * BOJ 1102 발전소 
 * DP - Bit Mast
 */
public class Main {
    static int n, p;
    static int[] d; //d 배열 하나
    static int[][] list;
    static String onoff;
    static final int INF = (int) (1e9 + 1);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        list = new int[n][n];
        d = new int[1 << n];

        for ( int i=0; i<n; i++ ) {
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for ( int j=0; j<n; j++ ) list[i][j] = Integer.parseInt(st.nextToken());
        }

        onoff = br.readLine().trim();
        p = Integer.parseInt(br.readLine().trim());

        int cnt = 0, state = 0; // 현재 발전소 상태, 켜있는 개수 저장 
        for ( int i=0; i<onoff.length(); i++ ) {
            if ( onoff.charAt(i) == 'Y' ) {
                state |= (1<<i); 
                cnt++;
            }
        }

        for ( int i=0; i<(1<<n); i++ ) d[i] = INF;

        if ( cnt == 0 ) {
            if ( p == 0 ) System.out.println(0);
            else System.out.println(-1);		 //살아있는 발전소가 1개도 없으면  1개라도켜야할때 불가
        } else if ( cnt >= p ) System.out.println(0); // 살아있는 발전소가 최소구동개수보다 크면 비용 0
        else System.out.println(solve(cnt, state));
    }

    //cnt개는 켜져있고 켜져있는 상태는 저렇다. 
    static int solve ( int cnt, int state ) {
        if ( cnt == p ) return 0; // 목표치 맞으면 비용0
        if ( d[state] != INF ) return d[state]; // 이미 비용 저장되있으면

        for ( int i=0; i<n; i++ ) {
            if ( (state&(1<<i)) > 0 ) { //i번째 발전소 켜져있으면
                for ( int j=0; j<n; j++ ) {  // 죽어있는 j번째 발전소를 켜야함 
                    if ( i == j ) continue;
                    if ( (state&(1<<j)) == 0 ) d[state] = Math.min(d[state], solve(cnt+1, state|(1<<j)) + list[i][j] );
                }
            }
        }
        return d[state];
    }
}