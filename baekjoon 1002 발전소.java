import java.util.*;
import java.io.*;

/**
 * BOJ 1102 ������ 
 * DP - Bit Mast
 */
public class Main {
    static int n, p;
    static int[] d; //d �迭 �ϳ�
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

        int cnt = 0, state = 0; // ���� ������ ����, ���ִ� ���� ���� 
        for ( int i=0; i<onoff.length(); i++ ) {
            if ( onoff.charAt(i) == 'Y' ) {
                state |= (1<<i); 
                cnt++;
            }
        }

        for ( int i=0; i<(1<<n); i++ ) d[i] = INF;

        if ( cnt == 0 ) {
            if ( p == 0 ) System.out.println(0);
            else System.out.println(-1);		 //����ִ� �����Ұ� 1���� ������  1�����Ѿ��Ҷ� �Ұ�
        } else if ( cnt >= p ) System.out.println(0); // ����ִ� �����Ұ� �ּұ����������� ũ�� ��� 0
        else System.out.println(solve(cnt, state));
    }

    //cnt���� �����ְ� �����ִ� ���´� ������. 
    static int solve ( int cnt, int state ) {
        if ( cnt == p ) return 0; // ��ǥġ ������ ���0
        if ( d[state] != INF ) return d[state]; // �̹� ��� �����������

        for ( int i=0; i<n; i++ ) {
            if ( (state&(1<<i)) > 0 ) { //i��° ������ ����������
                for ( int j=0; j<n; j++ ) {  // �׾��ִ� j��° �����Ҹ� �Ѿ��� 
                    if ( i == j ) continue;
                    if ( (state&(1<<j)) == 0 ) d[state] = Math.min(d[state], solve(cnt+1, state|(1<<j)) + list[i][j] );
                }
            }
        }
        return d[state];
    }
}